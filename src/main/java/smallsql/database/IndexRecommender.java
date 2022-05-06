package smallsql.database;

import java.sql.SQLException;
import java.util.ArrayList;

public abstract class IndexRecommender {
    
    protected ArrayList<Field> fields;
    protected ArrayList<String> recommendedIndexes;
    private SSConnection con;

    IndexRecommender(SSConnection con) {
        this.con = con;
    }

    IndexRecommender(SSConnection con, ArrayList<Field> fields) {
        this.con = con;
        this.fields = fields;
    }

    abstract ArrayList<String> recommendIndex();

    protected Boolean containsIndex(Field field) {
        Table table;
        try {
            table = (Table) con.getDatabase(true).getTableView(con, field.getTableName());
        } catch (SQLException e) {
            throw new Error("Database not found.");
        }
        IndexDescriptions indexes = table.indexes;
        for (int i = 0; i < indexes.size(); i++) {
            IndexDescription index = indexes.get(i);
            if (index.getName().equals(field.getFieldName())) {
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

    public ArrayList<String> getRecommendedIndexes() {
        return this.recommendedIndexes;
    }
}
