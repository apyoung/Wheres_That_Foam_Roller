package gui;

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

    /**
     * Constructor for the BuildDB class, creates the MySQL database with the
     * passed parameters
     * @param driverName name of the driver for SQL
     * @param url Url for the database
     * @param username login username
     * @param password login password for username
     */
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
            stmt.close();
            conn.close();
            //rebuild the url string for the database that was just made
            String[] splitUrl = url.split("/");
            String newUrl = splitUrl[0]+ "//" + splitUrl[2] + "/wtfoamroller" +
                    splitUrl[3];
            MainScreen.url = newUrl;
            conn = DriverManager.getConnection(newUrl, username, password);
            System.out.println("Building sample Database");
            InsertData insertData = new InsertData();
            insertData.build(conn);
        } catch (SQLException ex) {

            //ErrorCode 1007 is the error thrown when the database already
            // exists
            if (ex.getErrorCode() == 1007) {
                System.out.println("Database already exists");
            }
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
