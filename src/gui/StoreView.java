package gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import helper.QueryDB;

import javax.management.Query;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.sql.*;

public class StoreView extends JFrame {
    private JPanel mainPanel;
    private JTextField storeNameField;
    private JLabel storeNameLabel;
    private JTextField storeIDField;
    private JLabel storeIDLabel;
    private JLabel streetLabel;
    private JTextField streetField;
    private JLabel cityLabel;
    private JTextField cityField;
    private JLabel zipLabel;
    private JTextField zipField;
    private JTextField stateField;
    private JLabel stateLabel;
    private JLabel companyLabel;
    private JTextField companyField;
    private JTextField phoneField;
    private JLabel phoneLabel;
    private JLabel reviewsLabel;
    private JPanel reviewsPanel;
    private int storeID;

    public StoreView(int storeID) {
        add(mainPanel);

        setTitle("Search Stores");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.storeID = storeID;
        setStoreInformation();
    }

    private void setStoreInformation() {
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;
        String url = MainScreen.url;
        String user = MainScreen.user;
        String password = MainScreen.password;
        String driver = MainScreen.driver;
        String query =
                "SELECT store.name, company.name, street, city, zip, state, " +
                        "phone " +
                        "FROM store, company, owns " +
                        "WHERE store.storeid = owns.storeid " +
                        "AND store.storeid=" + storeID + " " +
                        "AND company.name = owns.CompanyName;";

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            resultSet.next();

            // Set the field values
            String storeName = resultSet.getString(1);
            String companyName = resultSet.getString(2);
            String street = resultSet.getString(3);
            String city = resultSet.getString(4);
            String zip = resultSet.getString(5);
            String state = resultSet.getString(6);
            String phone = resultSet.getString(7);
            setTitle(storeName);
            storeIDField.setText(storeID + "");
            storeNameField.setText(storeName);
            companyField.setText(companyName);
            streetField.setText(street);
            cityField.setText(city);
            zipField.setText(zip);
            stateField.setText(state);
            phoneField.setText(phone);
            populateReviews();
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
    }

    private void populateReviews() {
        String reviewQuery = "SELECT review.* " +
                "FROM storereview, review " +
                "WHERE storereview.storeid=" + storeID + " " +
                "AND storereview.reviewid = review.reviewid";
        JTable reviewTable = QueryDB.getJTable(reviewQuery);
        reviewTable.getColumnModel().getColumn(1)
                .setCellRenderer(new WordWrapCellRenderer());
        JScrollPane resultScrollPane = new JScrollPane(reviewTable);
        reviewsPanel.add(resultScrollPane,
                new GridConstraints(0,
                        0,
                        1,
                        1,
                        GridConstraints.ANCHOR_CENTER,
                        GridConstraints.FILL_BOTH,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null, 0, false));
        reviewsPanel.revalidate();
    }

    // Found on stack exchange here: https://stackoverflow.com/questions/37768335/how-to-word-wrap-inside-a-jtable-row
    static class WordWrapCellRenderer extends JTextArea implements TableCellRenderer {
        WordWrapCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value.toString());
            setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
            if (table.getRowHeight(row) != getPreferredSize().height) {
                table.setRowHeight(row, getPreferredSize().height);
            }
            return this;
        }
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
        mainPanel.setLayout(new GridLayoutManager(8, 4, new Insets(10, 10, 10, 10), -1, -1));
        storeNameLabel = new JLabel();
        storeNameLabel.setText("Store Name");
        mainPanel.add(storeNameLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        storeNameField = new JTextField();
        storeNameField.setEditable(false);
        mainPanel.add(storeNameField, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        storeIDLabel = new JLabel();
        storeIDLabel.setText("Store ID");
        mainPanel.add(storeIDLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        storeIDField = new JTextField();
        storeIDField.setEditable(false);
        mainPanel.add(storeIDField, new GridConstraints(1, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        streetLabel = new JLabel();
        streetLabel.setText("Street");
        mainPanel.add(streetLabel, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        zipLabel = new JLabel();
        zipLabel.setText("Zip Code");
        mainPanel.add(zipLabel, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        zipField = new JTextField();
        zipField.setEditable(false);
        mainPanel.add(zipField, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        stateLabel = new JLabel();
        stateLabel.setText("State");
        mainPanel.add(stateLabel, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        streetField = new JTextField();
        streetField.setEditable(false);
        mainPanel.add(streetField, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cityField = new JTextField();
        cityField.setEditable(false);
        mainPanel.add(cityField, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cityLabel = new JLabel();
        cityLabel.setText("City");
        mainPanel.add(cityLabel, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        stateField = new JTextField();
        stateField.setEditable(false);
        mainPanel.add(stateField, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        companyLabel = new JLabel();
        companyLabel.setText("Company Name");
        mainPanel.add(companyLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        companyField = new JTextField();
        companyField.setEditable(false);
        mainPanel.add(companyField, new GridConstraints(2, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        phoneLabel = new JLabel();
        phoneLabel.setText("Phone");
        mainPanel.add(phoneLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        phoneField = new JTextField();
        phoneField.setEditable(false);
        mainPanel.add(phoneField, new GridConstraints(3, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        reviewsPanel = new JPanel();
        reviewsPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(reviewsPanel, new GridConstraints(7, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        reviewsLabel = new JLabel();
        reviewsLabel.setText("Reviews:");
        mainPanel.add(reviewsLabel, new GridConstraints(6, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
