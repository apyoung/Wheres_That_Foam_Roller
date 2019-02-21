package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class searchCategories extends JFrame {
    private JButton backButton;
    private JPanel panel1;

    public searchCategories() {
        add(panel1);
        setTitle("Search Categories");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        backButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                dispose();
                startScreen startScreen = new startScreen();
                startScreen.setVisible(true);
            }
        });
    }

}
