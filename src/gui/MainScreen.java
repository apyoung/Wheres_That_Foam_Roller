package gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JFrame {
    public static String url;
    public static String user;
    public static String password;
    public static String driver;
    private JButton searchStoresButton;
    private JPanel startPanel;
    private JButton showAllStoresButton;
    private JButton searchItemsButton;
    private JButton showAllItemsButton;
    private JButton searchCategoriesButton;
    private JButton showAllCategoriesButton;
    private JLabel startPanelLabel;
    private JButton editConnectionInfoButton;
    private JTextField urlField;
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField driverField;

    public MainScreen() {
        add(startPanel);

        setTitle("Where's That Foam Roller?");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        searchStoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchStores searchStores = new SearchStores();
                //dispose();
                searchStores.setVisible(true);
            }
        });
        showAllStoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowAllStores showAllStores = new ShowAllStores();
                //dispose();
                showAllStores.setVisible(true);
            }
        });
        searchItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchItems searchItems = new SearchItems();
                //dispose();
                searchItems.setVisible(true);
            }
        });
        showAllItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowAllItems showAllItems = new ShowAllItems();
                //dispose();
                showAllItems.setVisible(true);
            }
        });
        searchCategoriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchCategories searchCategories = new SearchCategories();
                //dispose();
                searchCategories.setVisible(true);
            }
        });
        showAllCategoriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowAllCategories showAllCategories = new ShowAllCategories();
                //dispose();
                showAllCategories.setVisible(true);
            }
        });
        editConnectionInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseConnection dbc = new DatabaseConnection(false);
                dbc.setVisible(true);
            }
        });
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
        startPanel = new JPanel();
        startPanel.setLayout(new GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));
        showAllItemsButton = new JButton();
        showAllItemsButton.setText("Show All Items");
        startPanel.add(showAllItemsButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, 50), null, 0, false));
        searchItemsButton = new JButton();
        searchItemsButton.setHorizontalTextPosition(0);
        searchItemsButton.setText("Search Items");
        startPanel.add(searchItemsButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, 50), null, 0, false));
        searchStoresButton = new JButton();
        searchStoresButton.setText("Search Stores");
        startPanel.add(searchStoresButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, 50), null, 0, false));
        showAllStoresButton = new JButton();
        showAllStoresButton.setText("Show All Stores");
        startPanel.add(showAllStoresButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, 50), null, 0, false));
        showAllCategoriesButton = new JButton();
        showAllCategoriesButton.setText("Show All Categories");
        startPanel.add(showAllCategoriesButton, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, 50), null, 0, false));
        searchCategoriesButton = new JButton();
        searchCategoriesButton.setText("Search Categories");
        startPanel.add(searchCategoriesButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, 50), null, 0, false));
        startPanelLabel = new JLabel();
        Font startPanelLabelFont = this.$$$getFont$$$(null, Font.BOLD, 12, startPanelLabel.getFont());
        if (startPanelLabelFont != null)
            startPanelLabel.setFont(startPanelLabelFont);
        startPanelLabel.setText("Choose Search Method:");
        startPanel.add(startPanelLabel, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 2, false));
        editConnectionInfoButton = new JButton();
        editConnectionInfoButton.setText("Edit Connection Info");
        startPanel.add(editConnectionInfoButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_NORTHEAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return startPanel;
    }

    /*
     *//**
     * @noinspection ALL
     *//*
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null)
            return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(),
            size >= 0 ? size : currentFont.getSize());
    }*/

}
