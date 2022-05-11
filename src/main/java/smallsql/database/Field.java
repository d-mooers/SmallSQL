package smallsql.database;

public class Field {
    private String tableName;
    private String fieldName;
    private int selections;
    private int deletions;
    private int insertions;
    private int joins;

    public Field(String tableName, String fieldName) {
        this.tableName = tableName;
        this.fieldName = fieldName;
    }

    public Field(String tableName, String fieldName, int selections, int deletions, int insertions, int joins) {
        this.tableName = tableName;
        this.fieldName = fieldName;
        this.selections = selections;
        this.deletions = deletions;
        this.insertions = insertions;
        this.joins = joins;
    }

    public static String formatKey(String tableName, String fieldName) {
        StringBuilder builder = new StringBuilder();
        builder.append(tableName.toLowerCase());
        builder.append('.');
        builder.append(fieldName.toLowerCase());
        return builder.toString();
    }

    @Override
    public String toString() {
        return Field.formatKey(tableName, fieldName);
    }

    // operationID will be changed to an enum
    public void incrementCounter(int operationType) throws Error {
        switch (operationType) {
            case 0:
                this.selections++;
                break;
            case 1: 
                this.deletions++;
                break;
            case 2:
                this.insertions++;
                break;
            case 3:
                this.joins++;
                break;
            default:
                throw new Error("Unrecognized operationType: " + operationType);
        }
    }

    public int getSelections() {
        return this.selections;
    }

    public int getDeletions() {
        return this.deletions;
    }

    public int getInsertions() {
        return this.insertions;
    }

    public int getJoins() {
        return this.joins;
    }

    public String getTableName() {
        return this.tableName;
    }

    public String getFieldName() {
        return this.fieldName;
    }
}
