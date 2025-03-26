package RestoSwing;

import java.util.ArrayList;

public class Commande {

    // Propriétés
    private int id_commande;
    private int id_user;
    private int id_etat;
    private String date;
    private double total_commande;
    private int type_conso;
    private String loginUser;
    private String emailUser;
    private ArrayList<Ligne> lignes = new ArrayList<>();

    // Constructeur classique
    public Commande(int id_commande, int id_user, int id_etat, String date, double total_commande,
                    int type_conso, String loginUser, String emailUser, ArrayList<Ligne> lignes) {
        this.id_commande = id_commande;
        this.id_user = id_user;
        this.id_etat = id_etat;
        this.date = date;
        this.total_commande = total_commande;
        this.type_conso = type_conso;
        this.loginUser = loginUser;
        this.emailUser = emailUser;
        this.lignes = lignes;
    }

    public int get_id_commande() {
        return id_commande;
    }

    public void set_id_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int get_id_user() {
        return id_user;
    }

    public void set_id_user(int id_user) {
        this.id_user = id_user;
    }

    public int get_id_etat() {
        return id_etat;
    }

    public void set_id_etat(int id_etat) {
        this.id_etat = id_etat;
    }

    public String get_date() {
        return date;
    }

    public void set_date(String date) {
        this.date = date;
    }

    public double get_total_commande() {
        return total_commande;
    }

    public void set_total_commande(double total_commande) {
        this.total_commande = total_commande;
    }

    public int get_type_conso() {
        return type_conso;
    }

    public void set_type_conso(int type_conso) {
        this.type_conso = type_conso;
    }

    public String get_loginUser() {
        return loginUser;
    }

    public void set_loginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String get_emailUser() {
        return emailUser;
    }

    public void set_emailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public ArrayList<Ligne> get_lignes() {
        return lignes;
    }

    public void set_lignes(ArrayList<Ligne> lignes) {
        this.lignes = lignes;
    }

    // Affiche le contenu de l'objet
    public void afficher() {
        System.out.println(""); // Saut de ligne
        System.out.println("ID Commande : " + id_commande);
        System.out.println("Date : " + date);
        System.out.println("Total Commande : " + total_commande);
        System.out.println("Lignes : ");
        for (int i = 0; i < lignes.size(); i++) {
            Ligne ligne = lignes.get(i);
            ligne.afficher();
        }
    }
}
