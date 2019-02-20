package gui;

import helper.queryDB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class showAllCategories extends JFrame {
    private JButton backButton;
    private JPanel panel1;

    public showAllCategories() {
        add(panel1);
        setTitle("Show All Categories");
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

        String query = "SELECT Category FROM itemcategory GROUP BY Category";
        queryDB queryDB = new queryDB();
        add(queryDB.getScrollPane(query));
    }
}
