package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;

public class InsertData {
    /**
     * Callable method for InserData to build tables and populate with data
     * @param conn Connection used from calling application
     */
    void build(Connection conn) {
        populate("sql/Create Tables.sql", conn);
        populate("sql/Insert Data.sql", conn);
    }

    /**
     * populate method does the actual population of the tables/data to the
     * tables
     * @param fileName the .sql file containing queries to be run
     * @param con Connection from calling application
     */
    private void populate(String fileName, Connection con) {
        try {
            String s;
            FileReader f = new FileReader(new File(fileName));
            BufferedReader b = new BufferedReader(f);
            StringBuffer sb = new StringBuffer();
            while ((s = b.readLine()) != null) {
                sb.append(s);
            }
            String[] tableData = sb.toString().split(";");
            for (String aTableData : tableData) {
                try {
                    Statement st = con.createStatement();
                    st.executeUpdate(aTableData);
                    st.close();
                } catch (SQLException ex) {
                    if (ex.getErrorCode() == 1050) {
                        System.out.println("Table already exists");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Failure" + e);
        }
    }
}

