package tangxiaotang.code.generater.application.db;

import org.apache.commons.lang.StringUtils;
import tangxiaotang.code.generater.application.config.Config;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class DataBase {
    private boolean isInit = false;
    private List<Table> tables = null;
    private Config cfg = null;

    public DataBase(Config cfg) {
        this.cfg = cfg;
    }

    public Config getCfg() {
        return cfg;
    }

    public List<Table> getTables() {
        if (!isInit) {// 这个状态不会改呀...
            init();
        }
        return tables;
    }

    private void init() {
        Statement stm = null;
        ResultSet rs = null;
        Connection conn = null;
        boolean isFilter = false;
        String tbNames = cfg.getTbNames();
        Set<String> filterSet = new HashSet<String>();

        if (StringUtils.isNotBlank(tbNames)) {
            String[] tables = StringUtils.split(StringUtils.trim(tbNames), ",");
            if (tables != null && tables.length > 0) {
                for (String s : tables) {
                    filterSet.add(s);
                }
                isFilter = true;
            }
        }
        try {
            conn = connect();
            if (conn != null) {
                stm = conn.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                String sql = "select table_name from information_schema.tables where table_schema='"
                        + cfg.getDbName() + "' and table_type='base table'";
                rs = stm.executeQuery(sql);
                tables = new ArrayList<Table>();
                while (rs.next()) {
                    String tn = rs.getString(1);
                    if (isFilter) {

                        for (String reg : filterSet) {
                            boolean matches = tn.matches(reg);
                            if (matches) {
                                Table table = new Table(this);
                                table.setTableName(tn);
                                table.setColumns(getTableCols(tn).get(0));
                                tables.add(table);
                                break;
                            }
                        }
                        /*
                         * if (filterSet.contains(tn)) {
                         * 
                         * }
                         */
                    } else {
                        Table table = new Table(this);
                        table.setTableName(tn);
                        table.setColumns(getTableCols(tn).get(0));
                        tables.add(table);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public Connection connect() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(cfg.getUrl(),
                    cfg.getUserName(), cfg.getPassword());

        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return conn;
    }

    private List<List<Column>> getTableCols(String tableName) {
        List<List<Column>> result = new ArrayList<List<Column>>(2);


        Statement stm = null;
        ResultSet rs = null;
        Connection conn = null;
        List<Column> list = null;
        try {
            conn = connect();
            if (conn != null) {
                list = new ArrayList<Column>();
                stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                String sql = "Select COLUMN_NAME c, DATA_TYPE v, COLUMN_COMMENT co " +
                        "from INFORMATION_SCHEMA.COLUMNS" +
                        " Where table_name = '" + tableName + "'" +
                        " AND table_schema = '" + cfg.getDbName() + "'";
                rs = stm.executeQuery(sql);
                while (rs.next()) {
                    Column col = new Column();
                    //表名
                    String columnName = rs.getString("c");
                    //数据类型
                    String columnType = rs.getString("v");
                    //字段备注
                    String columnComment = rs.getString("co");

                    col.setColName(columnName);
                    col.setColType(columnType);
                    col.setComment(columnComment);
                    list.add(col);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        result.add(list);
        return result;
    }
}
