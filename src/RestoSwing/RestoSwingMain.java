/*
 * RestoSwing
 * Classe contenant le "Main"
 */
package RestoSwing;

/**
 *
 * @author mathis
 */
public class RestoSwingMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Ouvre la fenêtre des commandes
        Commande_liste frame = new Commande_liste(); // créer une instance de la fenetre principale en appelant le constructeur de la classe Commande_liste
        frame.setTitle("RestoSwing"); // le titre de la fenetre
        frame.setResizable(false); //permet de réduire ou pas la fenetre
        frame.setVisible(true); // permet de faire apparaitre la fenetre

    } // main()

} // class RestoSwing
