package smallsql.database;
import java.util.*;

import javax.swing.text.TabExpander;

import java.sql.*;


public class FieldTracker {

    // Maps fields to their counter, where key is <table-name>.<field-name>
    private Map<String, Field> fieldTracker;
    private final String TABLE_NAME = "field_____counts";
    private Table table;
    private SSConnection con;
    private Database db;

    public FieldTracker(SSConnection con, Database db) {
        this.con = con;
        this.db = db;
        this.fieldTracker = new HashMap<>();
        this.con = con;
        this.db = db;
    }

    public void incrementCounter(int operationType, Table table, String fieldName) {
        this.incrementCounter(operationType, table.name, fieldName);
    }

    public void incrementCounter(int operationType, String tableName, String fieldName) {
        Field field;
        if (!this.fieldTracker.containsKey(Field.formatKey(tableName, fieldName))) {
            field = new Field(tableName, fieldName);
            this.fieldTracker.put(field.toString(), field);
        } else {
            field = this.fieldTracker.get(Field.formatKey(tableName, fieldName));
        }
        field.incrementCounter(operationType);
    }

    public void loadOrCreateTable() {
        try {
            TableView tv =  TableView.load(con, db, this.TABLE_NAME);
            if (tv instanceof Table) this.table = (Table) tv;
            else {
                throw new Error("Expected table, got view for field tracker");
            }
        } catch (SQLException e) {
            System.out.println("Fieldtracker table does not exist yet");
            Columns columns = new Columns();

            Column tableName = new Column();
            tableName.setName("table_name");
            tableName.setDataType(SQLTokenizer.SQL_LONGVARCHAR);
            columns.add(tableName);

            Column fieldName = new Column();
            fieldName.setName("field_name");
            fieldName.setDataType(SQLTokenizer.SQL_LONGVARCHAR);
            columns.add(fieldName);

            Column selections = new Column();
            selections.setName("selections");
            selections.setDataType(SQLTokenizer.SQL_INTEGER);
            columns.add(selections);

            Column deletions = new Column();
            deletions.setName("deletions");
            deletions.setDataType(SQLTokenizer.SQL_INTEGER);
            columns.add(deletions);

            Column insertions = new Column();
            insertions.setName("insertions");
            insertions.setDataType(SQLTokenizer.SQL_INTEGER);
            columns.add(insertions);

            Column joins = new Column();
            joins.setName("joins");
            joins.setDataType(SQLTokenizer.SQL_INTEGER);
            columns.add(joins);
            try {
                this.table = db.createTable(this.con, TABLE_NAME, columns, new IndexDescriptions(), new ForeignKeys());
            } catch (Exception error) {
                System.out.println("Uhoh, failed");
            }
        }
    }

    public boolean save() {
        this.loadOrCreateTable();
        // TO DO: Save table
        return true;
    }
}
