package smallsql.database;

public class TrackerRecord {
    private AccessType accessType;
    private String tableName;
    private String fieldName;

    public TrackerRecord(AccessType accessType, String tableName, String fieldName){
        this.accessType = accessType;
        this.tableName = tableName;
        this.fieldName = fieldName;
    }

    AccessType getAccessType(){
        return accessType;
    }

    String getTableName(){
        return tableName;
    }

    String getFieldName(){
        return fieldName;
    }
}
