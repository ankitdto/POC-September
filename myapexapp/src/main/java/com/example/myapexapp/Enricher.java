package com.example.myapexapp;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.DefaultOutputPort;
//import com.datatorrent.apps.User;
import com.datatorrent.common.util.BaseOperator;


public class Enricher extends BaseOperator {
	
	  private static final String db_driver = "org.postgresql.Driver";
	  private static final String url = "jdbc:postgresql://node35.morado.com:5432/testdb?user=postgres&password=postgres";
	  private static final String table_name = "userdetails";
	  public transient static Logger LOG = LoggerFactory.getLogger(Enricher.class);
	
	  Connection con;
	public transient DefaultInputPort<Object> configin = new DefaultInputPort<Object>() {
    
		@Override
		public void process(Object tuple) {
			// TODO Auto-generated method stub
	
			
			processTuple(tuple);
			//System.out.println(tuple.toString()+"hello");
		    
		    
			
		}
		
		 
	};
	
	protected void processTuple(Object tuple)
	{
		
		User u = (User) tuple;
	    //System.out.println(u.getUid());
	    //System.out.println(u.getUname());
		updateUserRecordFromDatabase(u);
	    configout.emit(u);
	}
	
	public void updateUserRecordFromDatabase(User u)
	{
		try {
			Class.forName(db_driver).newInstance();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 try {
			 
			con = DriverManager.getConnection(url);
			Statement stmt = con.createStatement();
			
			  String query = "SELECT uname from " + table_name +" where uid = "+u.getUid();
			  LOG.debug("Executing query : {}",query);
		      ResultSet resultSet = stmt.executeQuery(query);
		      if(resultSet.next())
		      {
		    	  u.setUname(resultSet.getString("uname"));
		      }
		      
		}
		 
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			LOG.error("Error Occured while updating User Record",e);
		}
		//return u;
	      
	}
	
	public final transient DefaultOutputPort<Object> configout = new DefaultOutputPort<Object>();

}
