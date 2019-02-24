package gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import helper.QueryDB;

import javax.management.Query;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class ItemView extends JFrame {
    private JPanel mainPanel;
    private JTextField itemNameField;
    private JLabel itemNameLabel;
    private JTextField upcField;
    private JLabel upcLabel;
    private JLabel categoriesLabel;
    private JLabel conditionLabel;
    private JTextField conditionField;
    private JTextField madeInField;
    private JLabel madeInLabel;
    private JLabel reviewsLabel;
    private JPanel reviewsPanel;
    private JList categoryList;
    private JPanel categoriesPanel;
    private String upc;
    private String condition;

    public ItemView(String upc, String condition) {
        add(mainPanel);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.upc = upc;
        this.condition = condition;
        setItemInformation();
    }

    private void setItemInformation() {
        ResultSet itemResultSet = null;
        ResultSet categoryResultSet = null;
        ResultSet categoryCountResultSet = null;
        Statement itemStatement = null;
        Statement categoryStatement = null;
        Statement categoryCountStatement = null;
        Connection connection = null;
        String url = MainScreen.url;
        String user = MainScreen.user;
        String password = MainScreen.password;
        String driver = MainScreen.driver;
        String itemQuery =
                "SELECT item.name, item.madein " +
                        "FROM item " +
                        "WHERE item.upc=" + this.upc + " " +
                        "AND item.condition='" + this.condition + "';";
        String categoryQuery =
                "SELECT itemcategory.Category " +
                        "FROM itemcategory " +
                        "WHERE itemcategory.upc=" + this.upc + " " +
                        "AND itemcategory.condition='" + this.condition + "';";
        String categoryCountQuery =
                "SELECT Count(*) " +
                        "FROM itemcategory " +
                        "WHERE itemcategory.upc=" + this.upc + " " +
                        "AND itemcategory.condition='" + this.condition + "';";

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            itemStatement = connection.createStatement();
            categoryStatement = connection.createStatement();
            categoryCountStatement = connection.createStatement();
            itemResultSet = itemStatement.executeQuery(itemQuery);
            categoryResultSet = categoryStatement.executeQuery(categoryQuery);
            categoryCountResultSet = categoryCountStatement.executeQuery(categoryCountQuery);
            itemResultSet.next();
            categoryCountResultSet.next();

            // Set the field values
            String name = itemResultSet.getString(1);
            String madeIn = itemResultSet.getString(2);
            setTitle(name);
            itemNameField.setText(name);
            upcField.setText(upc);
            conditionField.setText(condition);
            madeInField.setText(madeIn);

            // Set the categories list
            int numCategories = categoryCountResultSet.getInt(1);
            String[] categories = new String[numCategories];
            for (int row = 0; row < numCategories; row++) {
                categoryResultSet.next();
                categories[row] = categoryResultSet.getString(1);
            }
            categoryList.setListData(categories);

            // Populate the reviews
            populateReviews();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (itemResultSet != null)
                    itemResultSet.close();
                if (categoryResultSet != null)
                    categoryResultSet.close();
                if (categoryCountResultSet != null)
                    categoryCountResultSet.close();
                if (categoryStatement != null)
                    categoryStatement.close();
                if (itemStatement != null)
                    itemStatement.close();
                if (categoryCountStatement != null)
                    categoryCountStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    private void populateCategories() {
        String categoryQuery = "SELECT itemcategory.Category " +
                "FROM itemcategory " +
                "WHERE itemcategory.upc=" + this.upc + " " +
                "AND itemcategory.condition='" + this.condition + "';";
        JScrollPane resultScrollPane = QueryDB.getScrollPane(categoryQuery);

        categoriesPanel.add(resultScrollPane,
                new GridConstraints(0,
                        0,
                        1,
                        1,
                        GridConstraints.ANCHOR_CENTER,
                        GridConstraints.FILL_BOTH,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null, 0, false));
        categoriesPanel.revalidate();
    }

    private void populateReviews() {
        String reviewQuery = "SELECT review.* " +
                "FROM itemreview, review " +
                "WHERE itemreview.upc=" + this.upc + " " +
                "AND itemreview.condition='" + this.condition + "' " +
                "AND itemreview.reviewid = review.reviewid";
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
        mainPanel.setLayout(new GridLayoutManager(7, 2, new Insets(10, 10, 10, 10), -1, -1));
        itemNameLabel = new JLabel();
        itemNameLabel.setText("Item Name");
        mainPanel.add(itemNameLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        itemNameField = new JTextField();
        itemNameField.setEditable(false);
        mainPanel.add(itemNameField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        upcLabel = new JLabel();
        upcLabel.setText("UPC");
        mainPanel.add(upcLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        upcField = new JTextField();
        upcField.setEditable(false);
        mainPanel.add(upcField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        categoriesLabel = new JLabel();
        categoriesLabel.setText("Categories:");
        mainPanel.add(categoriesLabel, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        conditionLabel = new JLabel();
        conditionLabel.setText("Condition");
        mainPanel.add(conditionLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        conditionField = new JTextField();
        conditionField.setEditable(false);
        mainPanel.add(conditionField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        madeInLabel = new JLabel();
        madeInLabel.setText("Made In");
        mainPanel.add(madeInLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        madeInField = new JTextField();
        madeInField.setEditable(false);
        mainPanel.add(madeInField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        reviewsPanel = new JPanel();
        reviewsPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(reviewsPanel, new GridConstraints(6, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        reviewsLabel = new JLabel();
        reviewsLabel.setText("Reviews:");
        mainPanel.add(reviewsLabel, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        categoryList = new JList();
        mainPanel.add(categoryList, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
