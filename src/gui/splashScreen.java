package gui;

import javax.swing.*;

public class splashScreen extends JFrame {
    public JProgressBar progressBar;
    public JLabel LoadingProgressLabel;
    private JLabel splashImage;
    private JPanel splashPanel;

    public splashScreen() {
        add(splashPanel);
        setSize(490, 350);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
