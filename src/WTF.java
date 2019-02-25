import gui.DatabaseConnection;

import javax.swing.*;

public class WTF {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        DatabaseConnection dbc = new DatabaseConnection();
        dbc.setVisible(true);
    }
}
