package smallsql.database;

import java.util.ArrayList;
import java.util.HashMap;

public class IndexRecommenderBasic extends IndexRecommender {
    
    public IndexRecommenderBasic(SSConnection con, ArrayList<Field> fields) {
        super(con, fields);
    }

    public IndexRecommenderBasic(SSConnection con) {
        super(con);
    }

    public ArrayList<String[]> recommendIndex() {
        // tracks number of deletions and insertions per table
        HashMap<String, Integer> tableUpdates = new HashMap<>();
        for (Field field : this.fields) {
            // System.out.println(field.outputResult());
            if (field.getFieldName() == null) {
                String table = field.getTableName();
                int val = tableUpdates.getOrDefault(table, 0);
                val = val + field.getDeletions() + field.getInsertions();
                tableUpdates.put(table, val);
            }
        }
        for (Field field : this.fields) {
            String[] tuple = new String[2];
            String table = field.getTableName();
            tuple[0] = table;
            tuple[1] = field.getFieldName();
            if ((field.getJoins() + field.getSelections())
                    - (tableUpdates.getOrDefault(table, 0) + field.getUpdates()) > 0
                    && !this.recommendedIndexes.contains(tuple)
                    && !this.containsIndex(field)) {
                this.recommendedIndexes.add(tuple);
            }
        }
        return this.recommendedIndexes;
    }
}
