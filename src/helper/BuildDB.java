package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BuildDB {
	public BuildDB() {
		Connection con = null;
		try {
			System.out.println("Checking for DB");
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection
					("jdbc:mysql://localhost/?useSSL=false","root",
							"test");
			PreparedStatement s = con.prepareStatement("CREATE DATABASE " +
					"WTFoamroller");
			System.out.println("Sending statement");
			s.executeUpdate();
			System.out.println("Building sample Database");
			InsertData insertData = new InsertData();
			insertData.build();
			s.close();
		} catch (SQLException ex) {
			if(ex.getErrorCode() == 1007){
				InsertData insertData = new InsertData();
				insertData.build();
			}
			System.out.println("Oops\n" + ex.getErrorCode() + ex );
		} catch (ClassNotFoundException ce){

		}
	}
}
