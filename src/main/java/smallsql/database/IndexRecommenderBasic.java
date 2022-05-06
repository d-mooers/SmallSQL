package smallsql.database;

import java.util.ArrayList;

public class IndexRecommenderBasic extends IndexRecommender {
    
    IndexRecommenderBasic(Table table, ArrayList<Field> fields) {
        super(table, fields);
    }

    IndexRecommenderBasic(Table table) {
        super(table);
    }

    public ArrayList<String> recommendIndex() {
        for (Field field : this.fields) {
            if ((field.getJoins() + field.getSelections()) - (field.getDeletions() + field.getInsertions()) > 0
                    && !this.recommendedIndexes.contains(field.getFieldName())
                    && !this.containsIndex(field.getFieldName())) {
                this.recommendedIndexes.add(field.getFieldName());
            }
        }
        return this.recommendedIndexes;
    }
}
