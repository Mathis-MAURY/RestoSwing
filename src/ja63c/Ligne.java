package ja63c;

import org.json.JSONObject;

public class Ligne {

    // Propriétés
    private int id_ligne;
    private int id_produit;
    private String libelle_produit;
    private int qte;
    private double total_ligne_ht;

    // Constructeur classique
    public Ligne(int id_ligne, int id_produit, String libelle_produit, int qte, double total_ligne_ht) {
        this.id_ligne = id_ligne;
        this.id_produit = id_produit;
        this.libelle_produit = libelle_produit;
        this.qte = qte;
        this.total_ligne_ht = total_ligne_ht;
    }

    // Constructeur avec un objet JSON
    public Ligne(JSONObject json_ligne) {
        this.id_ligne = json_ligne.getInt("id_ligne");
        this.id_produit = json_ligne.getInt("id_produit");
        this.libelle_produit = json_ligne.getString("libelle_produit");
        this.qte = json_ligne.getInt("qte");
        this.total_ligne_ht = json_ligne.getDouble("total_ligne_ht");
    }

    // Getters et setters
    public int get_id_ligne() {
        return id_ligne;
    }

    public void set_id_ligne(int id_ligne) {
        this.id_ligne = id_ligne;
    }

    public int get_id_produit() {
        return id_produit;
    }

    public void set_id_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String get_libelle_produit() {
        return libelle_produit;
    }

    public void set_libelle_produit(String libelle_produit) {
        this.libelle_produit = libelle_produit;
    }

    public int get_qte() {
        return qte;
    }

    public void set_qte(int qte) {
        this.qte = qte;
    }

    public double get_total_ligne_ht() {
        return total_ligne_ht;
    }

    public void set_total_ligne_ht(double total_ligne_ht) {
        this.total_ligne_ht = total_ligne_ht;
    }

    // Affiche le contenu de l'objet
    public void afficher() {
        System.out.println("  ID Ligne: " + id_ligne + ", Produit: " + libelle_produit +
                ", Quantité: " + qte + ", Total HT: " + total_ligne_ht);
    }
}
