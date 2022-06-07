package smallsql.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class IndexRecommenderRelativeFrequency extends IndexRecommender {
    
    int totalSelections;
    int totalJoins;
    double threshold;

    PriorityQueue<Field> pq;

    public IndexRecommenderRelativeFrequency(SSConnection con, ArrayList<Field> fields) {
        super(con, fields);
        pq = new PriorityQueue<Field>((f1, f2) -> {
            double relativeSelections_1 = calculateRelativeFrequency(f1);
            double relativeSelections_2 = calculateRelativeFrequency(f2);
    
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

    private double calculateRelativeFrequency(Field f) {
        return (f.getSelections() / (double) this.totalSelections) + (f.getJoins() / (double) this.totalJoins);
    }

    public ArrayList<String[]> recommendIndex() {
        // tracks number of deletions and insertions per table
        for (Field field : this.fields) {
            totalSelections += field.getSelections();
            totalJoins += field.getJoins();
        }
        for (Field field : this.fields) {
            String[] tuple = new String[3];
            tuple[0] = field.getTableName();
            tuple[1] = field.getFieldName();
            double score = calculateRelativeFrequency(field);
            tuple[2] = String.valueOf(score);
            if (calculateRelativeFrequency(field) > 0 && !this.recommendedIndexes.contains(tuple) && !this.containsIndex(field)) {
                this.recommendedIndexes.add(tuple);
            }
        }
        return super.getRecommendedIndexes();
    }
}
