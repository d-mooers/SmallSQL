package smallsql.database;

import java.util.ArrayList;

public class IndexRecommenderBasic extends IndexRecommender {
    
    public IndexRecommenderBasic(SSConnection con, ArrayList<Field> fields) {
        super(con, fields);
    }

    public IndexRecommenderBasic(SSConnection con) {
        super(con);
    }

    public ArrayList<String> recommendIndex() {
        int insertions = 0;
        int deletions = 0;
        for (Field field : this.fields) {
            if (field.getFieldName() == null) {
                insertions = field.getInsertions();
                deletions = field.getDeletions();
            }
        }
        for (Field field : this.fields) {
            if ((field.getJoins() + field.getSelections()) - (deletions + insertions) > 0
                    && !this.recommendedIndexes.contains(field.getFieldName())
                    && !this.containsIndex(field)) {
                this.recommendedIndexes.add(field.getFieldName());
            }
        }
        return this.recommendedIndexes;
    }
}
