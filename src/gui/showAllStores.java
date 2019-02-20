package gui;

import helper.queryDB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class showAllStores extends JFrame {
    private JButton backButton;
    private JPanel panel1;
    private JTable table1;

    public showAllStores() {
        add(panel1);
        setTitle("Show All Stores");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        backButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                dispose();
                startScreen startScreen = new startScreen();
                startScreen.setVisible(true);
            }
        });

        String query =
            "SELECT store.*,company.name AS 'Parent Company' FROM store,company,owns WHERE store.storeid = owns.storeid AND owns.companyname = company.name";
        queryDB queryDB = new queryDB();
        add(queryDB.getScrollPane(query));
    }
}
