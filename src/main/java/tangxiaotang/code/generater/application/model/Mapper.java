package tangxiaotang.code.generater.application.model;

import java.util.List;


public class Mapper {
    private String filePath;
    private String tableName;
    private List<MapperField> fields;
    private List<MapperField> updateInsertFields;
    private String resultMapId;

    public List<MapperField> getUpdateInsertFields() {
        return updateInsertFields;
    }

    public void setUpdateInsertFields(List<MapperField> updateInsertFields) {
        this.updateInsertFields = updateInsertFields;
    }

    public List<MapperField> getFields() {
        return fields;
    }

    public void setFields(List<MapperField> fields) {
        this.fields = fields;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getResultMapId() {
        return resultMapId;
    }

    public void setResultMapId(String resultMapId) {
        this.resultMapId = resultMapId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
