import java.util.ArrayList;
import java.util.List;

public class Commande {
    private int id_commande;
    private int id_user;
    private int id_etat;
    private String date;
    private double total_commande;
    private int type_conso;

    private String loginUser;
    private String emailUser;

    private ArrayList<Ligne> lignes = new ArrayList<>();

    public Commande(int id_commande, int id_user, int id_etat, String date, double total_commande, int type_conso, String loginUser, String emailUser, ArrayList<Ligne> lignes) {
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

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_etat() {
        return id_etat;
    }

    public void setId_etat(int id_etat) {
        this.id_etat = id_etat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotal_commande() {
        return total_commande;
    }

    public void setTotal_commande(double total_commande) {
        this.total_commande = total_commande;
    }

    public int getType_conso() {
        return type_conso;
    }

    public void setType_conso(int type_conso) {
        this.type_conso = type_conso;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public List<Ligne> getLignes() {
        return lignes;
    }

    public void setLignes(ArrayList<Ligne> lignes) {
        this.lignes = lignes;
    }

    public void afficher(){
        System.out.println("id_commande: " + id_commande);
        System.out.println("id_user: " + id_user);
        System.out.println("id_etat: " + id_etat);
        System.out.println("date: " + date);
        System.out.println("total_commande: " + total_commande);
        System.out.println("type_conso: " + type_conso);
        System.out.println("loginUser: " + loginUser);
        System.out.println("emailUser: " + emailUser);
        System.out.println("lignes: " + lignes);
    }

    public void commande_refuser() {

    }

    public void commande_en_attente{

    }

    public void commande_accepter{

    }

}
