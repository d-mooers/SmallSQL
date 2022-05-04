package smallsql.database;

import java.util.ArrayList;

public abstract class IndexRecommender {
    
    protected ArrayList<Field> fields;
    protected ArrayList<String> recommendedIndexes;

    IndexRecommender() {}

    IndexRecommender(ArrayList<Field> fields) {
        this.fields = fields;
    }

    abstract ArrayList<String> recommendIndex();

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
