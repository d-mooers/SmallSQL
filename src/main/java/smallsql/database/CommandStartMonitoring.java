package smallsql.database;

import java.sql.ResultSet;

public class CommandStartMonitoring extends Command{

    public CommandStartMonitoring(Logger log){
        super(log);
    }

    /**
     * The main method to execute this Command and create a ResultSet.
     */
    void executeImpl(SSConnection con, SSStatement st) throws Exception{
        con.getFieldTracker().reset();
        con.setMonitoring(true);
    }
}
