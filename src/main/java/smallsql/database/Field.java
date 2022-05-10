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

    /**
    SELECT,
    WHERE,
    INSERTION,
    DELETION,
    JOIN,
    UPDATE,
    GROUPBY,
    HAVING,
    ORDERBY,
    NULL

     */

    // operationID will be changed to an enum
    public void incrementCounter(AccessType operationType) throws Error {
        switch (operationType) {
            case SELECT:
            case WHERE:
            case ORDERBY:
            case GROUPBY:
                this.selections++;
                break;
            case DELETION:
                this.deletions++;
                break;
            case INSERTION:
                this.insertions++;
                break;
            case JOIN:
            case HAVING:
                this.joins++;
                break;
            case UPDATE:
            case NULL:
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
