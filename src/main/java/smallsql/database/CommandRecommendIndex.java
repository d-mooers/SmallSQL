package smallsql.database;

import java.sql.*;

/* Called via REC_INDEX; */
public class CommandRecommendIndex extends Command {

    private IndexRecommender rec;

    CommandRecommendIndex(Logger log, IndexRecommender rec) {
        super(log);
        this.rec = rec;
    }

    @Override
    void executeImpl(SSConnection con, SSStatement st) throws Exception {
        // check if session?
        String format = "%-15.15s | %15.15s | %15.15s%n";
        System.out.println("Recommended Indexes:\n");
        System.out.printf(format, "Table Name", "Field Name", "Index Rec Score");
        System.out.println("---------------------------------------------------");
        for (String[] index : rec.recommendIndex()) {
            System.out.printf(format, index[0], index[1], index[2]);
        }
    }
}