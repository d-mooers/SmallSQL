package smallsql.database;

public class CommandStopMonitoring extends Command{

    public CommandStopMonitoring(Logger log){
        super(log);
    }

    /**
     * The main method to execute this Command and create a ResultSet.
     */
    void executeImpl(SSConnection con, SSStatement st) throws Exception{
        con.setMonitoring(false);
        System.out.println("Stop Monitoring");
    }
}
