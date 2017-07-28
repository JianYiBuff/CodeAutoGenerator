package tangxiaotang.code.generater.application.db;

import java.util.List;

public class Table {
    private DataBase database;
    private String tableName;
    private List<Column> columns;

    public Table(DataBase database) {
        this.database = database;
    }

    public DataBase getDatabase() {
        return database;
    }

    public void setDatabase(DataBase database) {
        this.database = database;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
