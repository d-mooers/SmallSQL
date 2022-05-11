package smallsql.database;

import java.sql.*;

/* Called via RECOMMEND INDEX; */
public class CommandRecommendIndex extends Command {

    private IndexRecommender rec;

    CommandRecommendIndex(Logger log) {
        super(log);
        this.rec = null;
    }

    CommandRecommendIndex(Logger log, IndexRecommender rec) {
        super(log);
        this.rec = rec;
    }

    @Override
    void executeImpl(SSConnection con, SSStatement st) throws Exception {
        // check if session?
        // TODO: do this in CLI, not this class
        System.out.println("TODO: recommend an index");
        //System.out.println(rec.getRecommendedIndexes());
    }
}