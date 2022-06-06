package smallsql.database;

public class CommandClearMonitoring extends Command{
    public CommandClearMonitoring(Logger log){
        super(log);
    }

    /**
     * The main method to execute this Command and create a ResultSet.
     */
    void executeImpl(SSConnection con, SSStatement st) throws Exception{
        con.getFieldTracker().reset();
    }
}
