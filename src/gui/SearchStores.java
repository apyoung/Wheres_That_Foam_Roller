package gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import helper.QueryDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SearchStores extends JFrame {
    private JButton backButton;
    private JPanel mainPanel;
    private JButton searchButton;
    private JTextField storeIDField;
    private JLabel storeIDLabel;
    private JPanel queryResultPanel;
    private JLabel companyNameLabel;
    private JLabel storeNameLabel;
    private JTextField companyNameField;
    private JTextField storeNameField;
    private JLabel storeStreetLabel;
    private JTextField storeStreetField;

    public SearchStores() {
        add(mainPanel);
        setTitle("Search Stores");
        setSize(1300, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        /**
         * Closes the SearchStores window when the back button is clicked.
         */
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                //MainScreen MainScreen = new MainScreen();
                //MainScreen.setVisible(true);
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queryResultPanel.removeAll();
                queryResultPanel.add(QueryDB.getScrollPane(getQueryString()),
                        new GridConstraints(0,
                                0,
                                1,
                                1,
                                GridConstraints.ANCHOR_CENTER,
                                GridConstraints.FILL_BOTH,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                null, null, null, 0, false));
                queryResultPanel.revalidate();

                // List selection listener
            }
        });
    }

    private String getQueryString() {
        String result =
                "SELECT store.*, store.city, company.name AS 'Parent Company' " +
                "FROM store, company, owns " +
                "WHERE store.storeid = owns.storeid " +
                "AND owns.companyname = company.name ";

        System.out.println(storeIDField.getText());
        if (!storeIDField.getText().equals("")) {
            result += "AND store.storeid = '" + storeIDField.getText() + "' ";
        }
        if (!storeStreetField.getText().equals("")) {
            result += "AND store.Street = '" + storeStreetField.getText() + "' ";
        }
        if (!companyNameField.getText().equals("")) {
            result += "AND company.name = '" + companyNameField.getText() + "' ";
        }
        if (!storeNameField.getText().equals("")) {
            result += "AND store.name = '" + storeNameField.getText() + "' ";
        }

        result += ";";

        return result;
    }

    private JScrollPane getQueryScrollPane() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String url = MainScreen.url;
        String user = MainScreen.user;
        String password = MainScreen.password;
        String driver = MainScreen.driver;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            preparedStatement = connection.prepareStatement(
                    "SELECT store.*, company.name AS 'Parent Company' " +
                            "FROM store, company, owns " +
                            "WHERE store.storeid = owns.storeid " +
                            "AND owns.companyname = company.name " +
                            "AND store.storeid = ? " +
                            "AND company.name = ? " +
                            "AND store.Street = ?;");

            preparedStatement.clearParameters();
            preparedStatement.setString(1, storeIDField.getText());
            preparedStatement.setString(2, companyNameField.getText());
            preparedStatement.setString(3, storeStreetField.getText());
            ResultSet resultSet = preparedStatement.executeQuery();

            return QueryDB.getScrollPane(resultSet);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return null;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(7, 2, new Insets(0, 0, 0, 0), -1, -1));
        searchButton = new JButton();
        searchButton.setText("Search");
        mainPanel.add(searchButton, new GridConstraints(5, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        storeIDField = new JTextField();
        mainPanel.add(storeIDField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        storeIDLabel = new JLabel();
        storeIDLabel.setText("Store ID");
        mainPanel.add(storeIDLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        backButton = new JButton();
        backButton.setText("back");
        mainPanel.add(backButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        queryResultPanel = new JPanel();
        queryResultPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(queryResultPanel, new GridConstraints(6, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        companyNameField = new JTextField();
        mainPanel.add(companyNameField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        companyNameLabel = new JLabel();
        companyNameLabel.setText("Company Name");
        mainPanel.add(companyNameLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        storeStreetField = new JTextField();
        mainPanel.add(storeStreetField, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        storeStreetLabel = new JLabel();
        storeStreetLabel.setText("Store Street");
        mainPanel.add(storeStreetLabel, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        storeNameField = new JTextField();
        mainPanel.add(storeNameField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        storeNameLabel = new JLabel();
        storeNameLabel.setText("Store Name");
        mainPanel.add(storeNameLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
