package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class startScreen extends JFrame {
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
    private JTextField urlField;
    private JTextField usernameField;
    private JTextField passwordField;
    private JLabel urlLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel driverLabel;
    private JTextField driverField;

    public startScreen() {
        add(startPanel);

        setTitle("Where's That Foam Roller?");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        searchStoresButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                setConnectionParameters();
                searchStores searchStores = new searchStores();
                //dispose();
                searchStores.setVisible(true);
            }
        });
        showAllStoresButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                setConnectionParameters();
                showAllStores showAllStores = new showAllStores();
                //dispose();
                showAllStores.setVisible(true);
            }
        });
        searchItemsButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                setConnectionParameters();
                searchItems searchItems = new searchItems();
                //dispose();
                searchItems.setVisible(true);
            }
        });
        showAllItemsButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                setConnectionParameters();
                showAllItems showAllItems = new showAllItems();
                //dispose();
                showAllItems.setVisible(true);
            }
        });
        searchCategoriesButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                setConnectionParameters();
                searchCategories searchCategories = new searchCategories();
                //dispose();
                searchCategories.setVisible(true);
            }
        });
        showAllCategoriesButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                setConnectionParameters();
                showAllCategories showAllCategories = new showAllCategories();
                //dispose();
                showAllCategories.setVisible(true);
            }
        });
    }

    public void setConnectionParameters() {
        url = urlField.getText().trim();
        user = usernameField.getText().trim();
        password = passwordField.getText().trim();
        driver = driverField.getText().trim();
    }
}
