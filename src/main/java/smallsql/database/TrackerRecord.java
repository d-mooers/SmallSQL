package smallsql.database;

public class TrackerRecord {
    private AccessType operationType;
    private String tableName;
    private String fieldName;

    public TrackerRecord(AccessType operationType, String tableName, String fieldName){
        this.operationType = operationType;
        this.tableName = tableName;
        this.fieldName = fieldName;
    }

    AccessType getOperationType(){
        return operationType;
    }

    String getTableName(){
        return tableName;
    }

    String getFieldName(){
        return fieldName;
    }
}
