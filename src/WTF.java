import gui.splashScreen;
import gui.startScreen;

import javax.swing.*;

public class WTF {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        splashScreen splashScreen = new splashScreen();
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
                startScreen startScreen = new startScreen();
                startScreen.setVisible(true);
            }
        });
    }
}
