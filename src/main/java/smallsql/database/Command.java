/* =============================================================
 * SmallSQL : a free Java DBMS library for the Java(tm) platform
 * =============================================================
 *
 * (C) Copyright 2004-2007, by Volker Berlin.
 *
 * Project Info:  http://www.smallsql.de/
 *
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation; either version 2.1 of the License, or 
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, 
 * USA.  
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 * ---------------
 * Command.java
 * ---------------
 * Author: Volker Berlin
 * 
 */
package smallsql.database;

import java.sql.*;
import java.util.ArrayList;

import smallsql.tools.language.Language;

import static java.lang.String.format;


abstract class Command {

    int type;
    String catalog;
    String name;

    SSResultSet rs;
    int updateCount = -1;

    /**	List of Columns */
    final Expressions columnExpressions; 

	/**	List of ExpressionValue */
    Expressions params  = new Expressions();

    /** List of Fields Used **/
    ArrayList<TrackerRecord> fieldsUsed = new ArrayList<>(32);

    final Logger log;
    
    Command(Logger log){
    	this.log = log;
		this.columnExpressions = new Expressions();
    }

	Command(Logger log, Expressions columnExpressions){
		this.log = log;
		this.columnExpressions = columnExpressions;
	}


    /**
     * Adds the field pairs under the columnPairs arraylist to the fieldsUsed
     * instance variable under the given operation type.
     * @param operationType: Operation type as defined by the operation type enum
     * @param columnPairs: Arraylist of Tablename, columnname pairs
     */
	protected void addFields(int operationType, ArrayList<String[]> columnPairs){
	    for (int i = 0; i < columnPairs.size(); i++){
	        String[] columnPair = columnPairs.get(i);
	        fieldsUsed.add(new TrackerRecord(operationType, columnPair[0], columnPair[1]));
        }
    }

    
    /**
     * Add a Expression that returns the value for Column. This method is used
     * from SQLParser for different Commands (CommandSelect, CommandInsert).
     * @see SQLParser#select()
     * @see SQLParser#insert()
     */
    void addColumnExpression( Expression column ) throws SQLException{
        columnExpressions.add( column );
    }


    void addParameter( ExpressionValue param ){
        params.add( param );
    }
	

    /**
     * check if all parameters are set
     */
    void verifyParams() throws SQLException{
        for(int p=0; p<params.size(); p++){
            if(((ExpressionValue)params.get(p)).isEmpty())
            	throw SmallSQLException.create(Language.PARAM_EMPTY, new Integer(p+1));
        }
    }

    /**
     * Clear all parameters of a PreparedStatement
     */
    void clearParams(){
        for(int p=0; p<params.size(); p++){
            ((ExpressionValue)params.get(p)).clear();
        }
    }

	/**
	 * Get a PreparedStatement parameter.
	 * The idx starts with 1.
	 */
	private ExpressionValue getParam(int idx) throws SQLException{
		if(idx < 1 || idx > params.size())
			throw SmallSQLException.create(Language.PARAM_IDX_OUT_RANGE, new Object[] { new Integer(idx), new Integer(params.size())});
		return ((ExpressionValue)params.get(idx-1));
	}
	
    /**
     * Set value of a PreparedStatement parameter.
     * The idx starts with 1.
     */
    void setParamValue(int idx, Object value, int dataType) throws SQLException{
		getParam(idx).set( value, dataType );
		if(log.isLogging()){
			log.println("param"+idx+'='+value+"; type="+dataType);
		}
    }

	/**
	 * Set value of a PreparedStatement parameter.
	 * The idx starts with 1.
	 */
	void setParamValue(int idx, Object value, int dataType, int length) throws SQLException{
		getParam(idx).set( value, dataType, length );
		if(log.isLogging()){
			log.println("param"+idx+'='+value+"; type="+dataType+"; length="+length);
		}
	}

    final void handleMostCommon(SSConnection con, SSStatement st) {
        String mostCommon = this.columnExpressions.getMostCommonField();
        if (mostCommon != null) {
            System.out.println("Most common field (columnExpressions): " + mostCommon);
            con.getMetaData();
        }
    }


    public void preCompileGetColumns(){}

    public void postCompileGetColumns(){}

    public void finalizeColumns(FieldTracker ft){
	    for (TrackerRecord tr : fieldsUsed){
	        ft.incrementCounter(tr.getOperationType(), tr.getTableName(), tr.getFieldName());
        }
    }


    /**
     * Prints fields used for debugging
     */
    public void printFieldsUsed(){
        for (TrackerRecord tr: fieldsUsed){
             String sf = format("Operation: %d; Table: %s; Field: %s", tr.getOperationType(), tr.getTableName(), tr.getFieldName());
             System.out.println(sf);
        }
    }

    final void execute(SSConnection con, SSStatement st) throws SQLException{
        //con.getMetaData().incrementColumnUsage(this.columnExpressions.getMostCommonField());
    	int savepoint = con.getSavepoint();
        try{
            executeImpl( con, st );
        }catch(Throwable e){
            con.rollback(savepoint);
            throw SmallSQLException.createFromException(e);
        }finally{
            if(con.getAutoCommit()) con.commit();
        }
    }

    abstract void executeImpl(SSConnection con, SSStatement st) throws Exception;

    SSResultSet getQueryResult() throws SQLException{
        if(rs == null)
        	throw SmallSQLException.create(Language.RSET_NOT_PRODUCED);
        return rs;
    }

    SSResultSet getResultSet(){
        return rs;
    }

    int getUpdateCount(){
        return updateCount;
    }
    
    
    /**
     * The default Command remove all results because there is only one result.
     * @return ever false
     */
    boolean getMoreResults(){
    	rs = null;
    	updateCount = -1;
    	return false;
    }
    
    
    /**
     * Set the max rows. Need to be override in the Commands that support it. 
     */
	void setMaxRows(int max){/* Empty because not supported for the most Commands */}
    
    
    int getMaxRows(){return -1;}
}
