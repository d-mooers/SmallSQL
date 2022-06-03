package smallsql.database;

import java.sql.SQLException;
import java.util.ArrayList;

public abstract class IndexRecommender {
    
    protected ArrayList<Field> fields;
    // list of list of strings, first val is table name and second val is field name
    protected ArrayList<String[]> recommendedIndexes;
    private SSConnection con;

    IndexRecommender(SSConnection con) {
        this.con = con;
        this.recommendedIndexes = new ArrayList<>();
    }

    IndexRecommender(SSConnection con, ArrayList<Field> fields) {
        this.con = con;
        this.fields = fields;
        this.recommendedIndexes = new ArrayList<>();
    }

    public abstract ArrayList<String[]> recommendIndex();

    protected Boolean containsIndex(Field field) {
        // temp fix
        if (field.getTableName().equals("UNION")) {
            return false;
        }
        Table table;
        try {
            table = (Table) con.getDatabase(true).getTableView(con, field.getTableName());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error("Database not found.");
        }
        IndexDescriptions indexes = table.indexes;
        for (int i = 0; i < indexes.size(); i++) {
            IndexDescription index = indexes.get(i);
            if (index.getColumns().get(0).equals(field.getFieldName())) {
                return true;
            }
        }
        return false;
    }

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }

    public ArrayList<Field> getFields() {
        return this.fields;
    }

    public ArrayList<String[]> getRecommendedIndexes() {
        return this.recommendedIndexes;
    }
}
