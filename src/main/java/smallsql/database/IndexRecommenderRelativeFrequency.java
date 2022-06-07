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
        double selectionScore = 0;
        double joinScore = 0;
        if (this.totalSelections != 0) {
            selectionScore = f.getSelections() / (double) this.totalSelections;
        }
        if (this.totalJoins != 0) {
            joinScore = f.getJoins() / (double) this.totalJoins;
        }
        return selectionScore + joinScore;
    }

    public ArrayList<String[]> recommendIndex() {
        // tracks number of deletions and insertions per table
        for (Field field : this.fields) {
            totalSelections += field.getSelections();
            totalJoins += field.getJoins();
        }
        for (Field field : this.fields) {
            if (calculateRelativeFrequency(field) > 0) this.recommendedIndexes.add(new String[]{field.getTableName(), field.getFieldName(), String.valueOf(calculateRelativeFrequency(field))});
        }
        return super.getRecommendedIndexes();
    }
}
