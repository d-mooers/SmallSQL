package smallsql.junit;

import smallsql.basicTestFrame;
import smallsql.database.Field;
import smallsql.database.FieldTracker;
import smallsql.database.IndexRecommender;
import smallsql.database.IndexRecommenderBasic;
import smallsql.database.IndexRecommenderRelativeFrequency;
import smallsql.database.SSConnection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.*;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestIndexRecommender extends BasicTestCase {

    private static final String TABLE_NAME = "table_index_recommender";
    private static SSConnection con;
    private static Statement stat;

    @BeforeAll
    public void initialSetUp() throws SQLException {
        con = (SSConnection)basicTestFrame.getConnection();
        stat = con.createStatement();
        FieldTracker tracker = con.getFieldTracker();
        tracker.reset();
        stat.execute("START MONITORING");
    }

    @AfterAll
    public void finalTearDown() throws SQLException {
        stat.close();
        con.close();
    }

    @BeforeEach
    private void createTable() throws SQLException {
        stat.execute("CREATE TABLE " + TABLE_NAME +" (colA INT, colB INT)");

        stat.execute("INSERT INTO " + TABLE_NAME + " VALUES (1, 2)");
        stat.execute("INSERT INTO " + TABLE_NAME + " VALUES (1, 3)");

        stat.execute("SELECT colA FROM " + TABLE_NAME + " WHERE colA = 1");
        stat.execute("SELECT colA FROM " + TABLE_NAME + " WHERE colA = 1");
        stat.execute("SELECT colA FROM " + TABLE_NAME + " WHERE colA = 1");
        stat.execute("SELECT colB FROM " + TABLE_NAME + " WHERE colB = 1");
    }

    @AfterEach
    private void dropTable() throws SQLException {
        stat.execute("DROP TABLE " + TABLE_NAME);
    }

    @Test
    public void testBasic() throws Exception {        
        ArrayList<Field> fields = new ArrayList<Field>(con.getFieldTracker().getFields());
        IndexRecommender ir = new IndexRecommenderBasic(con, fields);
        ArrayList<String[]> recommendedIndexes = ir.recommendIndex();
        
        assertEquals(recommendedIndexes.get(0)[1], "colA");
        assertEquals(recommendedIndexes.get(0)[2], "1");

    }

    @Test
    public void testRelativeFrequency() throws Exception {
        ArrayList<Field> fields = new ArrayList<Field>(con.getFieldTracker().getFields());
        IndexRecommender ir = new IndexRecommenderRelativeFrequency(con, fields);
        ArrayList<String[]> recommendedIndexes = ir.recommendIndex();
        
        assertEquals(recommendedIndexes.get(0)[1], "colA");
        assertEquals(recommendedIndexes.get(0)[2], "0.75");
        assertEquals(recommendedIndexes.get(1)[1], "colB");
        assertEquals(recommendedIndexes.get(1)[2], "0.25");
    }

    @Test
    public void testDuplicateIndex() throws SQLException {
        // Create index on colA.
        stat.execute("CREATE INDEX test_index ON " + TABLE_NAME+" (colA)");

        ArrayList<Field> fields = new ArrayList<Field>(con.getFieldTracker().getFields());
        IndexRecommender ir = new IndexRecommenderBasic(con, fields);
        ArrayList<String[]> recommendedIndexes = ir.recommendIndex();

        assertTrue(recommendedIndexes.size() == 0);

        stat.execute("DROP INDEX "+TABLE_NAME+".test_index");

        recommendedIndexes = ir.recommendIndex();

        assertTrue(recommendedIndexes.size() == 1);
    }
}

