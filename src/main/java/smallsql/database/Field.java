package smallsql.database;

public class Field {
    private String tableName;
    private String fieldName;
    private int selectionsFromThisSession;
    private int deletionsFromThisSession;
    private int insertionsFromThisSession;
    private int joinsFromThisSession;

    public Field(String tableName, String fieldName) {
        this.tableName = tableName;
        this.fieldName = fieldName;
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
                this.selectionsFromThisSession++;
                break;
            case 1: 
                this.deletionsFromThisSession++;
                break;
            case 2:
                this.insertionsFromThisSession++;
                break;
            case 3:
                this.joinsFromThisSession++;
                break;
            default:
                throw new Error("Unrecognized operationType: " + operationType);
        }
    }

    public int getSelectionsFromThisSession() {
        return this.selectionsFromThisSession;
    }

    public int getDeletionsFromThisSession() {
        return this.deletionsFromThisSession;
    }

    public int getInsertionsFromThisSession() {
        return this.insertionsFromThisSession;
    }

    public int getJoinsFromThisSession() {
        return this.joinsFromThisSession;
    }
}
