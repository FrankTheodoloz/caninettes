import javax.swing.*;

public class CaniCrottes {

    public static void main(String[] args) {
        Fenetre f = new Fenetre("CaniCrottes");

        SwingUtilities.invokeLater(() -> {
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });

    }

}
