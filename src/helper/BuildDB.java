package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BuildDB {
	public BuildDB() {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			System.out.println("Checking for DB");
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection
					("jdbc:mysql://localhost/?useSSL=false","root",
							"root");
			stmt = con.prepareStatement("CREATE DATABASE " +
					"WTFoamroller");
			System.out.println("Sending statement");
			stmt.executeUpdate();
			System.out.println("Building sample Database");
			InsertData insertData = new InsertData();
			insertData.build();
		} catch (SQLException ex) {
			if(ex.getErrorCode() == 1007){
				InsertData insertData = new InsertData();
				insertData.build();
			}
			System.out.println("Oops\n" + ex.getErrorCode() + ex );
		} catch (ClassNotFoundException ce){

		} finally {
	    try {
	      if (con != null)
	        con.close();
	      if (stmt != null)
	        stmt.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
		}
	}
}
