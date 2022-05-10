package smallsql.database;

public class TrackerRecord {
    private int operationType;
    private String tableName;
    private String fieldName;

    public TrackerRecord(int operationType, String tableName, String fieldName){
        this.operationType = operationType;
        this.tableName = tableName;
        this.fieldName = fieldName;
    }

    int getOperationType(){
        return operationType;
    }

    String getTableName(){
        return tableName;
    }

    String getFieldName(){
        return fieldName;
    }
}
