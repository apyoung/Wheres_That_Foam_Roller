package gui;

import helper.queryDB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class showAllItems extends JFrame {
    private JButton backButton;
    private JPanel panel1;

    public showAllItems() {
        add(panel1);
        setTitle("Show All Items");
        setSize(800, 600);
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
            "SELECT DISTINCT item.`Name`, item.`Condition`, item.MadeIn, item.UPC FROM item,itemcategory WHERE item.UPC = itemcategory.UPC AND item.`Condition` = itemcategory.`Condition`";
        queryDB queryDB = new queryDB();
        add(queryDB.getScrollPane(query));
    }

}
