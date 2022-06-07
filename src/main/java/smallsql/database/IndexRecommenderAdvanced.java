package smallsql.database;

import java.util.ArrayList;
import java.util.HashMap;

public class IndexRecommenderAdvanced extends IndexRecommender {

    // weights of selects, joins, deletes, inserts in determining
    // whether or not we should create an index on that field
    static HashMap<AccessType, Integer> weights = new HashMap<>();
    static {
        // TODO: experiment with these values
        weights.put(AccessType.SELECT, 1);
        weights.put(AccessType.JOIN, 5);
        weights.put(AccessType.DELETION, -2);
        weights.put(AccessType.INSERTION, -2);
        weights.put(AccessType.UPDATE, -3);
    }

    public IndexRecommenderAdvanced(SSConnection con, ArrayList<Field> fields) {
        super(con, fields);
    }

    public ArrayList<String[]> recommendIndex() {
        // tracks number of deletions and insertions per table
        HashMap<String, Integer> tableUpdates = new HashMap<>();

        for (Field field : this.fields) {
            if (field.getFieldName() == null) {
                String table = field.getTableName();
                int val = tableUpdates.getOrDefault(table, 0);
                val = val + field.getDeletions() * weights.get(AccessType.DELETION);
                val = val + field.getInsertions() * weights.get(AccessType.INSERTION);
                tableUpdates.put(table, val);
            }
        }
        for (Field field : this.fields) {
            // System.out.println(field.outputResult());
            String[] tuple = new String[3];
            String table = field.getTableName();
            tuple[0] = table;
            tuple[1] = field.getFieldName();
            int score = field.getJoins() * weights.get(AccessType.JOIN)
                + field.getSelections() * weights.get(AccessType.SELECT)
                + tableUpdates.getOrDefault(table, 0)
                + field.getUpdates() * weights.get(AccessType.UPDATE);
            if (score > 0 && !this.recommendedIndexes.contains(tuple) && !this.containsIndex(field)) {
                tuple[2] = Integer.toString(score);
                this.recommendedIndexes.add(tuple);
            }
        }
        return super.getRecommendedIndexes();
    }
}

