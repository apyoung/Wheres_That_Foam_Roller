package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BuildDB {

    /**
     * Builds a new buildDB object with the default parameters outlined
     * below:
     * driverName: com.mysql.jdbc.Driver
     * url: jdbc:mysql://localhost/?useSSL=false
     * username: root
     * password: root
     */
    public static void buildDB() {
        buildDB("com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost/?useSSL=false",
                "root",
                "root");
    }

    public static void buildDB(String driverName, String url, String username,
                               String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            System.out.println("Checking for DB");
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.prepareStatement("CREATE DATABASE " +
                    "WTFoamroller");
            System.out.println("Sending statement");
            stmt.executeUpdate();
            System.out.println("Building sample Database");
            InsertData insertData = new InsertData();
            insertData.build(conn);
        } catch (SQLException ex) {

            //TODO What is this error code?
            if (ex.getErrorCode() == 1007) {
                InsertData insertData = new InsertData();
                insertData.build(conn);
            }
            System.out.println("Oops\n" + ex.getErrorCode() + ex);
        } catch (ClassNotFoundException ce) {
            System.out.println("class not found" + ce.getStackTrace());
        } finally {
            try {
                if (conn != null)
                    conn.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
