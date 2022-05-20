package smallsql.database;

import java.util.ArrayList;

public class IndexRecommenderBasic extends IndexRecommender {
    
    public IndexRecommenderBasic(SSConnection con, ArrayList<Field> fields) {
        super(con, fields);
    }

    public IndexRecommenderBasic(SSConnection con) {
        super(con);
    }

    public ArrayList<String[]> recommendIndex() {
        int insertions = 0;
        int deletions = 0;
        for (Field field : this.fields) {
            if (field.getFieldName() == null) {
                insertions = field.getInsertions();
                deletions = field.getDeletions();
            }
        }
        for (Field field : this.fields) {
            String[] tuple = new String[2];
            tuple[0] = field.getTableName();
            tuple[1] = field.getFieldName();
            if ((field.getJoins() + field.getSelections()) - (deletions + insertions) > 0
                    && !this.recommendedIndexes.contains(tuple)
                    && !this.containsIndex(field)) {
                this.recommendedIndexes.add(tuple);
            }
        }
        return this.recommendedIndexes;
    }
}
