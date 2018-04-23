package com.jdbc.until;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.modle.Admin;
import com.ndktools.javamd5.Mademd5;

public class ConnectionTest {

	public static void main(String[] args) throws Exception {
		
		  String sql = "select * from admin";
	      Class.forName("com.mysql.jdbc.Driver");
	      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=root");
	      
	      Mademd5 md5 = new Mademd5();
	      

	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(sql);
	      while(rs.next()){
	    	  
	    	  Admin admin = new Admin();
	    	  admin.setId(rs.getInt(1));
	    	  admin.setUsername(rs.getString(2));
	    	  admin.setPassword(rs.getString(3));
	    	  System.out.println(admin.toString());
	    	  
	    	  System.out.println(md5.toMd5(rs.getString(3)));
	      }
	      
	      conn.close();
	  }
	
}
