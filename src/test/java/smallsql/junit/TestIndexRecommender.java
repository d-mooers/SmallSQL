package smallsql.junit;

import smallsql.basicTestFrame;
import smallsql.database.Field;
import smallsql.database.IndexRecommender;
import smallsql.database.IndexRecommenderBasic;
import smallsql.database.IndexRecommenderRelativeFrequency;
import smallsql.database.SSConnection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.sql.*;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestIndexRecommender extends BasicTestCase {

    private static final String TABLE_NAME = "table_index_recommender";
    private static SSConnection con;
    private static Statement stat;

    @AfterEach
    public void tearDown() throws SQLException {
        dropTable();
        stat.close();
        con.close();
    }

    @BeforeEach
    public void setUp() throws SQLException {
        con = (SSConnection)basicTestFrame.getConnection();
        stat = con.createStatement();
        createTable();
    }

    private void createTable() throws SQLException {
        stat.execute("CREATE TABLE " + TABLE_NAME +" (colA INT, colB INT)");

        stat.execute("INSERT INTO " + TABLE_NAME + " VALUES (1, 2)");
        stat.execute("INSERT INTO " + TABLE_NAME + " VALUES (1, 3)");

        stat.execute("SELECT * FROM " + TABLE_NAME + " WHERE colA = 1");
        stat.execute("SELECT * FROM " + TABLE_NAME + " WHERE colA = 1");
    }

    private void dropTable() throws SQLException {
        stat.execute("DROP TABLE " + TABLE_NAME);
    }

    @Test
    public void testBasic() throws Exception {        
        ArrayList<Field> fields = new ArrayList<Field>(con.getFieldTracker().getFields());
        IndexRecommender ir = new IndexRecommenderBasic(con, fields);
        ArrayList<String[]> recommendedIndexes = ir.recommendIndex();
        
        // Check that the only recommended index from this table is colA.
        for (String[] index : recommendedIndexes) {
            if (index[0].equals(TABLE_NAME)) {
                assertEquals(index[1], "colA");;
            }
        }
    }

    @Test
    public void testRelativeFrequency() throws Exception {
        ArrayList<Field> fields = new ArrayList<Field>(con.getFieldTracker().getFields());
        IndexRecommender ir = new IndexRecommenderRelativeFrequency(con, fields);
        ArrayList<String[]> recommendedIndexes = ir.recommendIndex();
        
        // Check that the only recommended index from this table is colA.
        for (String[] index : recommendedIndexes) {
            System.out.println(index[0] + " " + index[1]);
            if (index[0].equals(TABLE_NAME)) {
                assertEquals(index[1], "colA");;
            }
        }
    }

    @Test
    public void testDuplicateIndex() throws SQLException {
        // Create index on colA.
        stat.execute("CREATE INDEX test_index ON " + TABLE_NAME+" (colA)");

        ArrayList<Field> fields = new ArrayList<Field>(con.getFieldTracker().getFields());
        IndexRecommender ir = new IndexRecommenderBasic(con, fields);
        ArrayList<String[]> recommendedIndexes = ir.recommendIndex();

        // Check that there are no recommended indexes for this table.
        for (String[] index : recommendedIndexes) {
            assertFalse(index[0].equals(TABLE_NAME));
        }

        stat.execute("DROP INDEX "+TABLE_NAME+".test_index");

        recommendedIndexes = ir.recommendIndex();

        // Check that the only recommended index from this table is colA.
        for (String[] index : recommendedIndexes) {
            if (index[0].equals(TABLE_NAME)) {
                assertEquals(index[1], "colA");;
            }
        }
    }
}

