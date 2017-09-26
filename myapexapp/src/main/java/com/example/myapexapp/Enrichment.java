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
import com.datatorrent.common.util.BaseOperator;

public class Enrichment extends BaseOperator {
	
	  private static final String DB_DRIVER = "org.postgresql.Driver";
	  //private static final String URL = "jdbc:postgresql://master1.datatorrent.com:5432/testdb?user=postgres&password=postgres";
	  private static final String TABLE_NAME = "userdetails";
	  private static final String URL="jdbc:postgresql://";
	  public transient static Logger LOG = LoggerFactory.getLogger(Enrichment.class);
	  
	  private String databaseHostname,databasePortname,databaseName,databaseUser,databasePassword;
	  
	  private String postgresURL;
	  
	  
	  
    public String getDatabaseHostname() {
		return databaseHostname;
	}

	public void setDatabaseHostname(String databaseHostname) {
		this.databaseHostname = databaseHostname;
	}

	public String getDatabasePortname() {
		return databasePortname;
	}

	public void setDatabasePortname(String databasePortname) {
		this.databasePortname = databasePortname;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getDatabaseUser() {
		return databaseUser;
	}

	public void setDatabaseUser(String databaseUser) {
		this.databaseUser = databaseUser;
	}

	public String getDatabasePassword() {
		return databasePassword;
	}

	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}

		public String getPostgresURL() {
		return postgresURL;
	    }

	    public void setPostgresURL(String postgresURL) {
		this.postgresURL = postgresURL;
	    }
		Connection con;
		public transient DefaultInputPort<Object> enrichconfigin = new DefaultInputPort<Object>(){

			@Override
			public void process(Object tuple) {
				// TODO Auto-generated method stub
				processTupleEnrichment(tuple);
			}
			
		};
		
		void processTupleEnrichment(Object tuple)
		{
			RecommendationInput r = (RecommendationInput) tuple;
			updateUserRecordFromDatabase(r);
			enrichconfigout.emit(r);
			
		}
		
		public void updateUserRecordFromDatabase(RecommendationInput r)
		{
			URL.concat(databaseHostname+":"+databasePortname+"/"+databaseName+"?user="+databaseUser+"&password="+databasePassword);
			try {
				Class.forName(DB_DRIVER).newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				
				con = DriverManager.getConnection(URL);
				Statement stmt = con.createStatement();
				String query = "SELECT uname from " + TABLE_NAME +" where uid = "+r.getUid();
				  LOG.debug("Executing query : {}",query);
			      ResultSet resultSet = stmt.executeQuery(query);
			      if(resultSet.next())
			      {
			    	  r.setUname(resultSet.getString("uname"));
			      }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		public final transient DefaultOutputPort<Object> enrichconfigout = new DefaultOutputPort<Object>();

}
