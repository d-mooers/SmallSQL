package smallsql.database;

import java.sql.*;

/* Called via CREATE_INDEX; */
public class CommandCreateIndex extends Command {

    private IndexRecommender rec;
    private int max;

    CommandCreateIndex(Logger log, IndexRecommender rec, int max) {
        super(log);
        this.rec = rec;
        this.max = max;
    }

    @Override
    void executeImpl(SSConnection con, SSStatement st) throws Exception {
        for (String[] index : rec.createRecommendedIndexes(this.max)) {
            System.out.println("Created index on " + index[0] + "." + index[1]);
        }
    }
}