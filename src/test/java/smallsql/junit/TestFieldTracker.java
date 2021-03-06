package smallsql.junit;

import smallsql.basicTestFrame;
import smallsql.database.FieldTracker;
import smallsql.database.SSConnection;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestFieldTracker extends BasicTestCase {

    private static final String TABLE_NAME = "table_test";
    private static SSConnection con;
    private static Statement stat;

    @AfterEach
    public void tearDown() throws SQLException {
        FieldTracker tracker = con.getFieldTracker();
        tracker.reset();
        stat.execute("STOP MONITORING");
        con.close();
    }

    @BeforeEach
    public void setUp() throws SQLException {
        con = (SSConnection)basicTestFrame.getConnection();
        stat = con.createStatement();
        try {
            stat.execute("DROP TABLE " + TABLE_NAME);
        } catch (SQLException e) {}
        stat.execute("START MONITORING");
        createTable();
    }
    

    private void createTable() throws SQLException {
        stat.execute("CREATE TABLE " + TABLE_NAME +" (a INT, b INT, c INT, d INT)");

        stat.execute("INSERT INTO " + TABLE_NAME + " VALUES (1, 2, 3, 4)");
        stat.execute("INSERT INTO " + TABLE_NAME + " VALUES (1, 3, 10, 12)");
    }


    @Test
    public void test_0() throws Exception {

        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 296");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 401");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 526");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 10");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 47");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 3");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 26");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 187");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 502");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 113");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
    }



    @Test
    public void test_1() throws Exception {

        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 237");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 45");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 254");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 994");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 526");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 381");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 195");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 93");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 254");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 449");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 3);
    }



    @Test
    public void test_2() throws Exception {

        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 786");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 196");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 856");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 975");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 10");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 521");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 320");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 612");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 649");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 439");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 1);
    }



    @Test
    public void test_3() throws Exception {

        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 119");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 737");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 640");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 851");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 463");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 926");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 764");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 84");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 223");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 79");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 6);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 1);
    }



    @Test
    public void test_4() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 55");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 500");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 877");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 509");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 659");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 879");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 635");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 279");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 979");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 539");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 2);
    }



    @Test
    public void test_5() throws Exception {

        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 348");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 518");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 286");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 727");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 168");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 998");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 39");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 860");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 916");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 736");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 6);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 1);
    }



    @Test
    public void test_6() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 82");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 149");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 601");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 59");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 598");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 680");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 406");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 763");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 243");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 864");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
    }



    @Test
    public void test_7() throws Exception {

        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 640");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 165");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 345");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 458");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 376");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 674");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 361");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 328");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 98");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 11");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 5);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 3);
    }



    @Test
    public void test_8() throws Exception {

        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 623");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 268");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 783");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 515");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 451");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 135");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 770");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 800");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 371");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 869");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 5);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 1);
    }



    @Test
    public void test_9() throws Exception {

        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 433");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 901");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 466");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 4");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 951");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 250");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 906");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 797");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 73");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 971");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 5);
    }



    @Test
    public void test_10() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 587");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 383");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 303");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 470");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 928");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 327");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 801");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 260");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 231");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 371");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 4);
    }



    @Test
    public void test_11() throws Exception {

        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 506");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 958");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 190");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 47");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 200");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 537");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 706");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 761");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 646");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 929");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 4);
    }



    @Test
    public void test_12() throws Exception {

        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 548");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 989");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 901");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 939");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 640");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 641");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 296");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 827");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 679");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 49");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 1);
    }



    @Test
    public void test_13() throws Exception {

        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 12");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 612");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 181");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 681");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 280");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 400");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 345");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 794");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 921");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 850");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 1);
    }



    @Test
    public void test_14() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 688");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 514");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 69");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 413");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 211");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 351");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 829");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 83");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 87");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 791");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 1);
    }



    @Test
    public void test_15() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 548");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 329");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 897");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 627");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 561");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 889");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 458");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 983");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 368");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 319");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 1);
    }



    @Test
    public void test_16() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 676");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 669");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 479");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 192");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 382");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 944");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 883");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 504");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 672");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 629");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 5);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 1);
    }



    @Test
    public void test_17() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 262");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 434");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 36");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 279");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 129");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 701");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 726");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 366");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 19");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 994");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 1);
    }



    @Test
    public void test_18() throws Exception {

        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 574");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 664");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 56");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 340");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 437");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 576");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 270");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 886");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 798");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 164");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 5);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 1);
    }



    @Test
    public void test_19() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 107");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 178");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 621");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 629");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 158");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 790");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 476");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 585");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 248");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 414");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 5);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 1);
    }



    @Test
    public void test_20() throws Exception {

        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 195");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 49");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 548");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 856");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 734");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 11");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 441");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 979");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 143");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 548");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 5);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
    }



    @Test
    public void test_21() throws Exception {

        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 561");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 190");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 610");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 839");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 495");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 134");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 220");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 75");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 547");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 277");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
    }



    @Test
    public void test_22() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 282");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 190");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 368");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 143");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 109");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 635");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 405");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 387");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 332");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 647");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 1);
    }



    @Test
    public void test_23() throws Exception {

        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 943");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 629");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 911");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 261");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 616");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 716");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 66");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 374");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 595");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 427");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 1);
    }



    @Test
    public void test_24() throws Exception {

        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 425");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 599");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 757");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 791");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 458");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 482");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 634");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 807");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 557");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 614");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 2);
    }



    @Test
    public void test_25() throws Exception {

        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 652");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 747");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 846");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 812");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 832");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 85");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 942");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 178");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 817");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 871");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 4);
    }



    @Test
    public void test_26() throws Exception {

        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 738");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 69");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 417");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 927");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 650");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 256");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 985");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 950");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 443");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 477");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
    }



    @Test
    public void test_27() throws Exception {

        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 460");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 722");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 105");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 72");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 936");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 856");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 891");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 822");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 678");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 147");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 5);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 1);
    }



    @Test
    public void test_28() throws Exception {

        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 582");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 552");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 86");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 876");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 737");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 344");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 291");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 91");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 973");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 466");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 1);
    }



    @Test
    public void test_29() throws Exception {

        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 541");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 40");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 692");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 914");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 788");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 967");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 393");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 767");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 279");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 90");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
    }



    @Test
    public void test_30() throws Exception {

        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 12");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 67");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 902");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 263");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 872");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 415");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 328");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 335");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 28");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 463");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 1);
    }



    @Test
    public void test_31() throws Exception {

        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 657");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 69");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 307");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 335");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 471");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 967");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 317");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 63");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 170");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 804");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
    }



    @Test
    public void test_32() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 320");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 472");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 821");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 330");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 756");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 876");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 867");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 470");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 134");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 294");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 6);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 1);
    }



    @Test
    public void test_33() throws Exception {

        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 44");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 543");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 739");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 935");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 550");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 280");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 572");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 291");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 485");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 282");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 1);
    }



    @Test
    public void test_34() throws Exception {

        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 344");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 843");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 980");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 529");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 967");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 576");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 436");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 756");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 595");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 411");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 4);
    }



    @Test
    public void test_35() throws Exception {

        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 782");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 794");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 370");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 252");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 813");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 283");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 220");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 533");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 450");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 577");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
    }



    @Test
    public void test_36() throws Exception {

        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 250");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 907");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 46");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 323");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 289");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 60");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 617");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 603");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 741");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 560");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 3);
    }



    @Test
    public void test_37() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 413");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 104");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 478");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 234");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 69");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 816");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 87");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 898");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 57");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 746");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 4);
    }



    @Test
    public void test_38() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 642");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 802");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 241");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 306");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 734");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 860");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 431");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 122");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 556");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 919");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 5);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 1);
    }



    @Test
    public void test_39() throws Exception {

        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 421");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 467");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 451");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 658");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 329");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 26");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 189");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 751");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 826");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 710");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 5);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
    }



    @Test
    public void test_40() throws Exception {

        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 760");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 795");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 620");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 996");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 462");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 59");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 951");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 878");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 708");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 261");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 4);
    }



    @Test
    public void test_41() throws Exception {

        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 898");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 952");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 427");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 990");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 616");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 402");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 988");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 454");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 556");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 248");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 5);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 3);
    }



    @Test
    public void test_42() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 763");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 509");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 566");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 366");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 183");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 911");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 486");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 328");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 495");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 508");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 5);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 1);
    }



    @Test
    public void test_43() throws Exception {

        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 340");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 933");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 336");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 498");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 632");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 829");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 640");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 985");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 320");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 522");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
    }



    @Test
    public void test_44() throws Exception {

        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 243");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 292");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 557");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 369");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 674");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 184");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 637");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 668");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 657");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 310");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 5);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 1);
    }



    @Test
    public void test_45() throws Exception {

        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 548");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 304");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 865");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 514");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 859");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 616");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 195");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 808");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 5");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 478");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 6);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
    }



    @Test
    public void test_46() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 753");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 939");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 784");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 430");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 797");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 278");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 541");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 859");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 742");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 735");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 6);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 2);
    }



    @Test
    public void test_47() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 17");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 338");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 274");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 381");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 148");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 141");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 488");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 538");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 629");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 146");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 2);
    }



    @Test
    public void test_48() throws Exception {

        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 243");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 568");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 648");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 774");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 777");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 200");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 247");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 280");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 977");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 490");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 1);
    }



    @Test
    public void test_49() throws Exception {

        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 25");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 149");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 397");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 442");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 955");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 137");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 16");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 506");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 48");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 31");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 3);
    }



    @Test
    public void test_50() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 149");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 994");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 657");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 381");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 828");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 342");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 104");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 959");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 214");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 232");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 3);
    }



    @Test
    public void test_51() throws Exception {

        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 426");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 931");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 499");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 192");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 867");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 981");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 793");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 487");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 745");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 608");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 5);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 1);
    }



    @Test
    public void test_52() throws Exception {

        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 463");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 885");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 38");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 820");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 687");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 474");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 156");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 599");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 540");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 585");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 2);
    }



    @Test
    public void test_53() throws Exception {

        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 719");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 540");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 996");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 842");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 920");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 437");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 871");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 152");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 321");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 687");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 3);
    }



    @Test
    public void test_54() throws Exception {

        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 621");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 169");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 182");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 547");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 53");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 5");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 702");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 824");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 244");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 811");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
    }



    @Test
    public void test_55() throws Exception {

        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 527");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 335");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 859");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 395");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 167");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 214");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 828");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 251");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 876");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 581");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 1);
    }



    @Test
    public void test_56() throws Exception {

        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 331");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 261");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 341");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 300");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 244");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 236");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 576");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 546");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 423");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 542");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 3);
    }



    @Test
    public void test_57() throws Exception {

        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 474");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 527");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 536");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 21");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 451");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 950");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 259");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 489");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 400");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 519");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 2);
    }



    @Test
    public void test_58() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 33");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 282");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 329");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 546");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 452");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 754");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 130");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 576");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 609");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 694");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
    }



    @Test
    public void test_59() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 801");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 63");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 469");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 978");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 183");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 676");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 461");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 154");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 687");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 147");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 2);
    }



    @Test
    public void test_60() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 554");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 488");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 657");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 623");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 978");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 828");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 495");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 2");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 390");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 690");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
    }



    @Test
    public void test_61() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 339");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 79");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 812");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 995");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 805");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 15");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 25");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 157");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 711");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 706");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 2);
    }



    @Test
    public void test_62() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 655");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 810");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 263");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 322");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 996");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 265");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 211");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 613");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 716");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 838");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 5);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 1);
    }



    @Test
    public void test_63() throws Exception {

        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 232");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 340");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 676");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 237");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 275");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 532");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 300");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 822");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 615");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 911");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 1);
    }



    @Test
    public void test_64() throws Exception {

        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 334");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 948");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 890");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 209");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 22");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 756");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 806");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 491");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 335");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 901");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 5);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 1);
    }



    @Test
    public void test_65() throws Exception {

        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 344");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 958");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 502");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 662");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 422");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 569");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 740");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 558");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 214");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 414");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 3);
    }



    @Test
    public void test_66() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 104");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 687");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 886");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 885");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 545");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 345");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 351");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 920");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 329");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 977");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 5);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 1);
    }



    @Test
    public void test_67() throws Exception {

        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 213");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 66");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 575");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 173");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 263");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 275");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 381");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 233");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 239");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 366");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 1);
    }



    @Test
    public void test_68() throws Exception {

        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 370");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 890");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 27");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 790");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 34");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 763");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 997");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 796");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 203");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 913");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 3);
    }



    @Test
    public void test_69() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 774");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 968");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 328");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 887");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 331");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 993");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 748");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 199");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 340");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 814");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
    }



    @Test
    public void test_70() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 388");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 3");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 531");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 36");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 53");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 900");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 513");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 193");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 162");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 901");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 1);
    }



    @Test
    public void test_71() throws Exception {

        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 863");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 741");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 505");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 171");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 65");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 95");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 606");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 880");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 188");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 918");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 1);
    }



    @Test
    public void test_72() throws Exception {

        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 87");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 607");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 174");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 881");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 404");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 602");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 592");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 767");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 328");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 441");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 1);
    }



    @Test
    public void test_73() throws Exception {

        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 920");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 33");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 288");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 339");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 24");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 334");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 969");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 264");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 29");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 629");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 5);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 1);
    }



    @Test
    public void test_74() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 138");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 250");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 354");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 712");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 112");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 784");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 889");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 828");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 611");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 155");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 5);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 1);
    }



    @Test
    public void test_75() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 808");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 190");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 604");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 860");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 329");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 159");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 276");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 483");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 985");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 289");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
    }



    @Test
    public void test_76() throws Exception {

        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 922");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 390");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 296");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 553");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 521");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 257");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 657");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 19");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 109");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 237");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 5);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 2);
    }



    @Test
    public void test_77() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 337");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 363");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 602");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 785");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 1");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 452");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 805");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 776");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 925");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 853");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
    }



    @Test
    public void test_78() throws Exception {

        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 13");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 446");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 559");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 855");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 519");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 1");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 77");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 754");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 309");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 543");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 5);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 2);
    }



    @Test
    public void test_79() throws Exception {

        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 783");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 130");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 775");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 798");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 950");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 42");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 572");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 529");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 339");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 542");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 4);
    }



    @Test
    public void test_80() throws Exception {

        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 583");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 320");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 473");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 763");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 348");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 705");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 557");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 522");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 34");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 474");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 1);
    }



    @Test
    public void test_81() throws Exception {

        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 335");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 460");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 767");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 523");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 390");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 368");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 796");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 804");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 576");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 310");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 1);
    }



    @Test
    public void test_82() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 670");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 996");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 231");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 798");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 953");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 303");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 40");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 583");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 997");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 12");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
    }



    @Test
    public void test_83() throws Exception {

        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 21");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 610");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 334");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 7");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 730");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 745");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 327");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 947");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 279");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 843");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 2);
    }



    @Test
    public void test_84() throws Exception {

        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 86");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 967");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 407");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 840");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 315");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 260");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 520");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 279");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 424");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 872");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 5);
    }



    @Test
    public void test_85() throws Exception {

        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 107");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 791");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 796");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 744");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 252");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 839");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 142");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 475");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 394");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 436");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 5);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 1);
    }



    @Test
    public void test_86() throws Exception {

        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 904");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 739");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 402");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 91");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 225");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 469");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 951");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 357");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 995");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 803");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 5);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 2);
    }



    @Test
    public void test_87() throws Exception {

        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 395");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 334");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 211");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 364");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 276");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 277");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 750");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 103");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 897");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 206");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 5);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
    }



    @Test
    public void test_88() throws Exception {

        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 743");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 295");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 690");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 208");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 517");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 43");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 9");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 977");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 574");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 148");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 5);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 2);
    }



    @Test
    public void test_89() throws Exception {

        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 370");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 879");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 467");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 406");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 890");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 252");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 117");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 714");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 552");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 985");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 5);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 1);
    }



    @Test
    public void test_90() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 640");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 73");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 17");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 881");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 575");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 10");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 558");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 24");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 314");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 386");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
    }



    @Test
    public void test_91() throws Exception {

        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 816");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 383");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 266");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 695");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 927");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 385");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 397");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 559");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 232");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 623");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 6);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 1);
    }



    @Test
    public void test_92() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 734");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 655");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 680");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 798");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 528");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 228");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 855");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 548");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 363");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 464");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 2);
    }



    @Test
    public void test_93() throws Exception {

        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 83");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 54");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 884");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 645");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 149");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 155");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 831");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 443");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 127");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 365");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 6);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 1);
    }



    @Test
    public void test_94() throws Exception {

        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 52");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 171");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 792");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 581");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 111");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 856");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 229");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 705");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 580");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 490");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 3);
    }



    @Test
    public void test_95() throws Exception {

        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 909");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 248");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 457");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 269");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 752");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 512");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 512");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 600");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 210");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 639");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 1);
    }



    @Test
    public void test_96() throws Exception {

        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 545");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 197");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 404");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 930");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 772");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 609");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 410");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 15");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 535");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 269");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 3);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 3);
    }



    @Test
    public void test_97() throws Exception {

        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 98");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 295");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 954");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 851");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 797");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 808");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 121");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 901");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 501");
        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 6");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 5);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 4);
    }



    @Test
    public void test_98() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 294");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 667");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 847");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 894");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 333");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 716");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 983");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 180");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 824");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 106");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 5);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 3);
    }

    @Test
    public void test_99() throws Exception {

        stat.execute("SELECT b FROM " + TABLE_NAME + " WHERE b = 253");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 155");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 293");
        stat.execute("SELECT c FROM " + TABLE_NAME + " WHERE c = 244");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 857");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 931");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 888");
        stat.execute("SELECT d FROM " + TABLE_NAME + " WHERE d = 123");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 331");
        stat.execute("SELECT a FROM " + TABLE_NAME + " WHERE a = 850");

        FieldTracker tracker = con.getFieldTracker();
        assertTrue(tracker.getSelections(TABLE_NAME, "b") == 1);
        assertTrue(tracker.getSelections(TABLE_NAME, "a") == 4);
        assertTrue(tracker.getSelections(TABLE_NAME, "c") == 2);
        assertTrue(tracker.getSelections(TABLE_NAME, "d") == 3);
    }
}

