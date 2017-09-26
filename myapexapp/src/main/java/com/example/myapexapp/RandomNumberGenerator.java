/**
 * Put your copyright and license info here.
 */
package com.example.myapexapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.api.InputOperator;
import com.datatorrent.common.util.BaseOperator;

/**
 * This is a simple operator that emits random number.
 */
public class RandomNumberGenerator extends BaseOperator implements InputOperator
{
  private int numTuples = 100;
  private transient int count = 0;
  
  private static final String DB_DRIVER = "org.postgresql.Driver";
  private static final String URL = "jdbc:postgresql://localhost:5432/testdb?user=postgres&password=postgres";
  private static final String TABLE_NAME = "userdetails";
  
  

  //public transient DefaultOutputPort<String> out = new DefaultOutputPort<String>();
  
  public transient DefaultInputPort<Object> configInputPort = new DefaultInputPort<Object>() {

	@Override
	public void process(Object config) {
		
		
		
	}
};
  
  
  Connection con;

  @Override
  public void beginWindow(long windowId)
  {
    count = 0;
  }

  @Override
  public void emitTuples()
  {
    //if (count++ < numTuples) {
      //out.emit(Math.random());
	  
	  
    //}
	  
	  
	
  }
  
  public String getNumOfEventsInStore(String tableName)
  {
    
    try {
      con = DriverManager.getConnection(URL);
      Statement stmt = con.createStatement();

      String countQuery = "SELECT count(*) from " + tableName +  "where uid = 2;";
      ResultSet resultSet = stmt.executeQuery(countQuery);
      resultSet.next();
      return resultSet.getString(2);
    } 
    catch (SQLException e) {
      throw new RuntimeException("fetching count", e);
    }
  }

  public int getNumTuples()
  {
    return numTuples;
  }

  /**
   * Sets the number of tuples to be emitted every window.
   * @param numTuples number of tuples
   */
  public void setNumTuples(int numTuples)
  {
    this.numTuples = numTuples;
  }
}
