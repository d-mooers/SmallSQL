package smallsql.junit;

import smallsql.basicTestFrame;
import smallsql.database.Field;
import smallsql.database.IndexRecommender;
import smallsql.database.IndexRecommenderBasic;
import smallsql.database.SSConnection;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.*;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestIndexRecommender extends BasicTestCase {

    private static final String TABLE_NAME = "table_index_recommender";
    private static SSConnection con;
    private static Statement stat;

    @AfterAll
    public void tearDown() throws SQLException {
    }

    @BeforeAll
    public void setUp() throws SQLException {
        con = (SSConnection)basicTestFrame.getConnection();
        stat = con.createStatement();
        try {
            stat.execute("DROP TABLE " + TABLE_NAME);
        } catch (SQLException e) {}
        createTable();
    }

    private void createTable() throws SQLException {
        stat.execute("CREATE TABLE " + TABLE_NAME +" (colA INT, colB INT)");

        stat.execute("INSERT INTO " + TABLE_NAME + " VALUES (1, 2)");
        stat.execute("INSERT INTO " + TABLE_NAME + " VALUES (1, 3)");

        stat.execute("SELECT colA FROM " + TABLE_NAME + " WHERE colA = 1");
        stat.execute("SELECT colA FROM " + TABLE_NAME + " WHERE colA = 1");
        stat.execute("SELECT colA FROM " + TABLE_NAME + " WHERE colA = 1");
        stat.execute("SELECT colB FROM " + TABLE_NAME + " WHERE colB = 1");
    }

    @Test
    public void testBasic() throws Exception {        
        ArrayList<Field> fields = new ArrayList<Field>(con.getFieldTracker().getFields());
        IndexRecommender ir = new IndexRecommenderBasic(con, fields);
        
        ArrayList<String> recommendedIndexes = ir.recommendIndex();
        assertTrue(recommendedIndexes.size() == 1);
        assertTrue(recommendedIndexes.get(0).equals("colA"));
    }
}

