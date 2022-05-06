package smallsql.database;

import java.util.ArrayList;

public class IndexRecommenderBasic extends IndexRecommender {
    
    IndexRecommenderBasic(SSConnection con, ArrayList<Field> fields) {
        super(con, fields);
    }

    IndexRecommenderBasic(SSConnection con) {
        super(con);
    }

    public ArrayList<String> recommendIndex() {
        for (Field field : this.fields) {
            if ((field.getJoins() + field.getSelections()) - (field.getDeletions() + field.getInsertions()) > 0
                    && !this.recommendedIndexes.contains(field.getFieldName())
                    && !this.containsIndex(field)) {
                this.recommendedIndexes.add(field.getFieldName());
            }
        }
        return this.recommendedIndexes;
    }
}
