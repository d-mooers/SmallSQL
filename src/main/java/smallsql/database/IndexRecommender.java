package smallsql.database;

import java.util.ArrayList;

public abstract class IndexRecommender {
    
    protected ArrayList<Field> fields;
    protected ArrayList<String> recommendedIndexes;
    private Table table;

    IndexRecommender(Table table) {
        this.table = table;
    }

    IndexRecommender(Table table, ArrayList<Field> fields) {
        this.table = table;
        this.fields = fields;
    }

    abstract ArrayList<String> recommendIndex();

    protected Boolean containsIndex(String fieldName) {
        IndexDescriptions indexes = table.indexes;
        for (int i = 0; i < indexes.size(); i++) {
            IndexDescription index = indexes.get(i);
            if (index.getName().equals(fieldName)) {
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
