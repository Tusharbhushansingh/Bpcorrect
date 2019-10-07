package com.sentinel.constant;
//STEP 1. Import required packages
import java.sql.*;

public class Logmanager {
   // JDBC driver name and database URL
	
	String url_param;
	String response;
	String url;
    String request_type;
	String error;
	String time;
	
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://10.138.1.48/bpconnect_prod?useSSL=false";

   //  Database credentials
   static final String USER = "bpconnect_prod";
   static final String PASS = "ATtaJM2dhMiVk$%2HDAyxO95#gu!9*eb";
   
   public Logmanager(String url_param, String response, String error, String time, String request_type, String url) {
	   this.url_param = url_param;
	   this.response = response;
	   this.error = error;
	   this.time= time;
	   this.request_type= request_type;
	   this.url = url;   
   }
   
   public void initiateData() {
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      System.out.println("Connecting to a selected database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      System.out.println("Connected database successfully...");
	      
	      //STEP 4: Execute a query
	      System.out.println("Inserting records into the table...");
	      stmt = conn.createStatement();
	      
	      String sql = "INSERT INTO logs_table(url_param,response,date,error,request_type,url) " +
	                   "VALUES (?,?,?,?,?,?)";
	      PreparedStatement preparedStmt = conn.prepareStatement(sql);
	      preparedStmt.setString (1, this.url_param);
	      preparedStmt.setString (2, this.response);
	      preparedStmt.setString (3, this.time);
	      preparedStmt.setString(4, this.error);
	      preparedStmt.setString (5, this.request_type);
	      preparedStmt.setString (6, this.url);

	      // execute the preparedstatement
	      preparedStmt.execute();
	      
	      System.out.println("Inserted records into the table...");

	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){
	      }// do nothing
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!"); 
   }
   
   
   public void initiateChronLogData() {
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      System.out.println("Connecting to a selected database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      System.out.println("Connected database successfully...");
	      
	      //STEP 4: Execute a query
	      System.out.println("Inserting records into the table...");
	      stmt = conn.createStatement();
	      
	      String sql = "INSERT INTO chron_job_logs(param,response,date_time,method) " +
	                   "VALUES (?,?,?,?)";
	      PreparedStatement preparedStmt = conn.prepareStatement(sql);
	      preparedStmt.setString (1, this.url_param);
	      preparedStmt.setString (2, this.response);
	      preparedStmt.setString (3, this.time);
	      preparedStmt.setString (4, this.url);
	      // execute the preparedstatement
	      preparedStmt.execute();
	      
	      System.out.println("Inserted records into the table...");

	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){
	      }// do nothing
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!"); 
   }
   
   //end main
}//end JDBCExample