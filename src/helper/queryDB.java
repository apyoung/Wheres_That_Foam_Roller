package helper;

import gui.startScreen;

import javax.swing.*;
import java.sql.*;
import java.util.Vector;

public class queryDB {

    public static JScrollPane getScrollPane(String query) {

        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;

        String url = startScreen.url;
        String user = startScreen.user;
        String password = startScreen.password;
        String driver = startScreen.driver;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            ResultSetMetaData metaData = resultSet.getMetaData();
            Vector<String> columnNames = new Vector<String>();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            while (resultSet.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(resultSet.getObject(columnIndex));
                }
                data.add(vector);
            }

            JScrollPane scrollPane = new JScrollPane(new JTable(data, columnNames));
            return scrollPane;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return null;
    }
}
