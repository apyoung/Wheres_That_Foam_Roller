import gui.DatabaseConnection;
import gui.SplashScreen;

import javax.swing.*;

public class WTF {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SplashScreen splashScreen = new SplashScreen();
        splashScreen.setVisible(true);
        try {
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(20);
                splashScreen.LoadingProgressLabel.setText("Loading " + i + "%");
                splashScreen.progressBar.setValue(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        splashScreen.dispose();

        SwingUtilities.invokeLater(new Runnable() {
            @Override public void run() {
                DatabaseConnection dbc = new DatabaseConnection();
                dbc.setVisible(true);
            }
        });
    }
}
