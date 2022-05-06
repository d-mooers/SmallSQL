package smallsql.database;

import java.util.ArrayList;

public class IndexRecommenderBasic extends IndexRecommender {
    
    IndexRecommenderBasic(ArrayList<Field> fields) {
        super(fields);
    }

    IndexRecommenderBasic() {}

    public ArrayList<String> recommendIndex() {
        ArrayList<String> newIndexes = new ArrayList<>();
        for (Field field : this.fields) {
            if ((field.getJoins() + field.getSelections()) - (field.getDeletions() + field.getInsertions()) > 0
                    && !this.recommendedIndexes.contains(field.toString())) {
                this.recommendedIndexes.add(field.toString());
                newIndexes.add(field.toString());
            }
        }
        return newIndexes;
    }
}
