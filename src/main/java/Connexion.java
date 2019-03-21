import javax.swing.*;

public class Connexion {
    private boolean connected;
    private JButton btnConnexion;

    /**
     *
     * @param btnConnexion
     */
    public Connexion(JButton btnConnexion) {
        connected = false;
        this.btnConnexion = btnConnexion;
    }
    public void setConnection() {
        connected = true;
        btnConnexion.setText("Déconnexion");
    }
    public void setDeconnection() {
        connected = false;
        btnConnexion.setText("Connexion");
    }
    public boolean isConnected() {
        return connected;
    }
}
