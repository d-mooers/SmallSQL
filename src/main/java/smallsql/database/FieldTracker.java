package smallsql.database;
import java.util.*;
import java.sql.*;


public class FieldTracker {

    // Maps fields to their counter, where key is <table-name>.<field-name>
    private Map<String, Field> fieldTracker;
    private final String TABLE_NAME = "field_____counts";
    private Table table;
    private SSConnection con;
    private Database db;
    private final int    TABLE_NAME_IDX = 0;
    private final int    FIELD_NAME_IDX = 1; 
    private final int    SELECTIONS_IDX = 2;
    private final int    DELETIONS_IDX = 3; 
    private final int    INSERTIONS_IDX = 4;
    private final int    JOINS_IDX = 5;
    private final int    UPDATES_IDX = 6;

    public FieldTracker(SSConnection con, Database db) {
        this.con = con;
        this.db = db;
        this.fieldTracker = new HashMap<>();
        this.con = con;
        this.db = db;
        if (this.loadOrCreateTable()) {
            this.loadFieldsFromTable();
        }
    }

    public void incrementCounter(AccessType accessType, Table table, String fieldName) {
        this.incrementCounter(accessType, table.name, fieldName);
    }

    private void loadFieldsFromTable() {
        TableResult from = new TableResult(this.table);
        try {
            from.init(con);
            from.execute();
            from.beforeFirst();
             while (from.next()) {
                String tableName = from.getString(TABLE_NAME_IDX);
                String fieldName = from.getString(FIELD_NAME_IDX);
                int selections = from.getInt(SELECTIONS_IDX);
                int deletions = from.getInt(DELETIONS_IDX);
                int insertions = from.getInt(INSERTIONS_IDX);
                int joins = from.getInt(JOINS_IDX);
                int updates = from.getInt(UPDATES_IDX);
                Field field = new Field(tableName, fieldName, selections, deletions, insertions, joins, updates);
                this.fieldTracker.put(field.toString(), field);
                from.deleteRow();
            }
        } catch (Exception e) {
            System.out.println("Failed in loadFieldsFromTable: " + e);
        }
    }

    private void saveToTable() {
        TableResult to = new TableResult(table);
        try {
            to.init(this.con);
            to.execute();
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
        Set<String> keys = this.fieldTracker.keySet();
        for (String key : keys) {
            Expression[] updateValues = new ExpressionValue[UPDATES_IDX + 1];
            Field field = this.fieldTracker.get(key);
            updateValues[TABLE_NAME_IDX] = new ExpressionValue(field.getTableName(), SQLTokenizer.LONGNVARCHAR);
            updateValues[FIELD_NAME_IDX] = new ExpressionValue(field.getFieldName(), SQLTokenizer.LONGNVARCHAR);
            updateValues[INSERTIONS_IDX] = new ExpressionValue(field.getInsertions(), SQLTokenizer.INT);
            updateValues[DELETIONS_IDX] = new ExpressionValue(field.getDeletions(), SQLTokenizer.INT);
            updateValues[JOINS_IDX] = new ExpressionValue(field.getJoins(), SQLTokenizer.INT);
            updateValues[UPDATES_IDX] = new ExpressionValue(field.getUpdates(), SQLTokenizer.INT);
            updateValues[SELECTIONS_IDX] = new ExpressionValue(field.getSelections(), SQLTokenizer.INT);
            try {
                to.insertRow(updateValues);
            } catch (Exception e) {
                System.out.println("Failed writing field " + field.toString());
                System.out.println(e);
            }
        }
        try {
            to.execute();
            if(con.getAutoCommit()) con.commit();
        } catch (Exception e) {
            System.out.println("Failed inserting rows into table: " + e);
        } finally {
        }
    }

    public void incrementCounter(AccessType accessType, String tableName, String fieldName) {
        // TODO: where is this coming from?
        if (fieldName != null && fieldName.equals("DESC")) return; 
        Field field;
        if (!this.fieldTracker.containsKey(Field.formatKey(tableName, fieldName))) {
            field = new Field(tableName, fieldName);
            this.fieldTracker.put(field.toString(), field);
        } else {
            field = this.fieldTracker.get(Field.formatKey(tableName, fieldName));
        }
        field.incrementCounter(accessType);
    }

    public boolean loadOrCreateTable() {
        try {
            // TableView tv =  TableView.load(con, db, this.TABLE_NAME);
            TableView tv = db.getTableView(con, this.TABLE_NAME);
            if (tv instanceof Table) this.table = (Table) tv;
            else {
                throw new Error("Expected table, got view for field tracker");
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Fieldtracker table does not exist yet");
            Columns columns = new Columns();

            Column tableName = new Column();
            tableName.setName("table_name");
            tableName.setDataType(SQLTokenizer.LONGNVARCHAR);
            columns.add(tableName);

            Column fieldName = new Column();
            fieldName.setName("field_name");
            fieldName.setDataType(SQLTokenizer.LONGNVARCHAR);
            columns.add(fieldName);

            Column selections = new Column();
            selections.setName("selections");
            selections.setDataType(SQLTokenizer.INT);
            columns.add(selections);

            Column deletions = new Column();
            deletions.setName("deletions");
            deletions.setDataType(SQLTokenizer.INT);
            columns.add(deletions);

            Column insertions = new Column();
            insertions.setName("insertions");
            insertions.setDataType(SQLTokenizer.INT);
            columns.add(insertions);

            Column joins = new Column();
            joins.setName("joins");
            joins.setDataType(SQLTokenizer.INT);
            columns.add(joins);

            Column updates = new Column();
            updates.setName("updates");
            updates.setDataType(SQLTokenizer.INT);
            columns.add(updates);

            try {
                this.table = db.createTable(this.con, TABLE_NAME, columns, new IndexDescriptions(), new ForeignKeys());
            } catch (Exception error) {
                System.out.println("Uhoh, failed");
                error.printStackTrace();
            }
            return false;
        }
    }

    public boolean save() {
        this.saveToTable();
        return true;
    }

    public ArrayList<Field> getFields() {
        return new ArrayList<Field>(this.fieldTracker.values());
    }

    public int getSelections(String tableName, String fieldName) {
        Field field = this.fieldTracker.get(Field.formatKey(tableName, fieldName));
        return field.getSelections();
    }

    public void reset() {
        this.fieldTracker = new HashMap<>();
    }
    // drop all fields from a certain table
    public void drop(String tableName) {
        // avoid modifying list while iterating over it
        for (Field field : new ArrayList<Field>(this.fieldTracker.values())) {
            if (field.getTableName().equals(tableName)) {
                this.fieldTracker.remove(field.formatKey());
            }
        }
    }
}
