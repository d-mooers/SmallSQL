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
        if (fieldName != null) {
            builder.append(fieldName.toLowerCase());
        } else {
            // DELETE and INSERT commands have null as their fieldName.
            // TODO: is there a better way to represent this?
            builder.append("null");
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        return Field.formatKey(tableName, fieldName);
    }

    public void incrementCounter(AccessType accessType) {
        switch (accessType) {
            case SELECT:
                this.selections++;
                break;
            case DELETION: 
                this.deletions++;
                break;
            case INSERTION:
                this.insertions++;
                break;
            case JOIN:
                this.joins++;
                break;
            case WHERE:
            case UPDATE:
            case GROUPBY:
            case HAVING:
            case ORDERBY:
            case NULL:
                // TODO: handle these
                break;
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
