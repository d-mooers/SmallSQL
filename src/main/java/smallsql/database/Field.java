package smallsql.database;

public class Field {
    private String tableName;
    private String fieldName;
    private int selections;
    private int deletions;
    private int insertions;
    private int joins;
    private int updates;

    public Field(String tableName, String fieldName) {
        this.tableName = tableName;
        this.fieldName = fieldName;
    }

    public Field(String tableName, String fieldName, int selections, int deletions, int insertions, int joins, int updates) {
        this.tableName = tableName;
        this.fieldName = fieldName;
        this.selections = selections;
        this.deletions = deletions;
        this.insertions = insertions;
        this.joins = joins;
        this.updates = updates;
    }

    public static String formatKey(String tableName, String fieldName) {
        StringBuilder builder = new StringBuilder();
        if (tableName != null) {
            builder.append(tableName.toLowerCase());
        } else {
            builder.append("null");
        }
        builder.append('.');
        if (fieldName != null) {
            builder.append(fieldName.toLowerCase());
        } else {
            // DELETE and INSERT commands have null as their fieldName.
            builder.append("null");
        }
        return builder.toString();
    }

    public String formatKey() {
        return Field.formatKey(this.tableName, this.fieldName);
    }

    @Override
    public String toString() {
        return Field.formatKey(tableName, fieldName);
    }

    public String outputResult() {
        return this.toString() + " Selections: " + this.selections + " Deletions: " + this.deletions + " Insertions: " + this.insertions + " Joins: " + this.joins + " Updates: " + this.updates;
    }


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
                this.updates++;
                break;
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

    public int getUpdates() {
        return this.updates;
    }

    public String getTableName() {
        return this.tableName;
    }

    public String getFieldName() {
        return this.fieldName;
    }
}
