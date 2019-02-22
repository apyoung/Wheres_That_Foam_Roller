package helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertData {

	public void build() {
		try {
			for (int j = 0; j < 1; j++) {
				System.out.println("Making tables");
				String s;
				FileReader f = new FileReader(new File("src/sql/Create Tables" +
						".sql"));
				BufferedReader b = new BufferedReader(f);
				StringBuffer sb = new StringBuffer();
				while((s = b.readLine()) != null){
					sb.append(s);
				}
				String[] tableData = sb.toString().split(";");
				try {
					Connection con = DriverManager.getConnection
							("jdbc:mysql://localhost/WTFoamroller?useSSL" +
									"=false","root","test");
					Statement st = con.createStatement();
					for (int i = 0; i < tableData.length; i++) {
						st.executeUpdate(tableData[i]);
					}
					st.close();
				} catch (SQLException ex) {
					System.out.println("Table creation failure " + ex);
				}
			}
			for (int j = 0; j < 1; j++) {
				System.out.println("Populating tables");
				String s;
				FileReader f = new FileReader(new File("src/sql/Insert Data" +
						".sql"));
				BufferedReader b = new BufferedReader(f);
				StringBuffer sb = new StringBuffer();
				while((s = b.readLine()) != null){
					sb.append(s);
				}
				String[] tableData = sb.toString().split(";");
				try {
					Connection con = DriverManager.getConnection
							("jdbc:mysql://localhost/WTFoamroller?useSSL" +
									"=false","root","test");
					Statement st = con.createStatement();
					for (int i = 0; i < tableData.length; i++) {
						st.executeUpdate(tableData[i]);
					}
					st.close();
				} catch (SQLException ex) {
					System.out.println("Table population failure " + ex);
				}
			}
			System.out.println("Tables populated");
		} catch (Exception e){
			System.out.println(e);
		}
	}
}
