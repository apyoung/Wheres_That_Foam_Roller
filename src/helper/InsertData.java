package helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;

public class InsertData {

    void build(Connection conn) {
        populate("src/sql/Create Tables.sql", conn);
        populate("src/sql/Insert Data.sql", conn);
    }

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

