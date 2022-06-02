package smallsql.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class IndexRecommenderRelativeFrequency extends IndexRecommender {
    
    int totalSelections;
    int totalJoins;

    PriorityQueue<Field> pq;

    public IndexRecommenderRelativeFrequency(SSConnection con, ArrayList<Field> fields) {
        super(con, fields);
        pq = new PriorityQueue<Field>((f1, f2) -> {
            double relativeSelections_1 = (f1.getSelections() / (double) this.totalSelections) + (f1.getJoins() / (double) this.totalJoins);
            double relativeSelections_2 = (f1.getSelections() / (double) this.totalSelections) + (f1.getJoins() / (double) this.totalJoins);
    
            if (relativeSelections_1 > relativeSelections_2) return -1;
            return 1;
        });
    }

    public IndexRecommenderRelativeFrequency(SSConnection con) {
        super(con);
        pq = new PriorityQueue<Field>((f1, f2) -> {
            double relativeSelections_1 = (f1.getSelections() / (double) this.totalSelections) + (f1.getJoins() / (double) this.totalJoins);
            double relativeSelections_2 = (f1.getSelections() / (double) this.totalSelections) + (f1.getJoins() / (double) this.totalJoins);
    
            if (relativeSelections_1 > relativeSelections_2) return -1;
            return 1;
        });
    }



    public ArrayList<String[]> recommendIndex() {
        // tracks number of deletions and insertions per table
        for (Field field : this.fields) {
            totalSelections += field.getSelections();
            totalJoins += field.getJoins();
        }
        for (Field field : this.fields) {
            pq.offer(field);
        }
        while(!pq.isEmpty()) {
            Field field = pq.poll();
            this.recommendedIndexes.add(new String[]{field.getTableName(), field.getFieldName()});
        }
        return this.recommendedIndexes;
    }
}
