
public class Ligne {
    private int id_ligne;
    private int id_produit;
    private String libelle_produit;

    private int qte;
    private double total_ligne_ht;

    public Ligne(int id_ligne, int id_produit, String libelleProduit, int qte, double total_ligne_ht) {
        this.id_ligne = id_ligne;
        this.id_produit = id_produit;
        this.libelle_produit = libelleProduit;
        this.qte = qte;
        this.total_ligne_ht = total_ligne_ht;
    }

    public String getLibelle_produit() {
        return libelle_produit;
    }

    public int getIdLigne() {
        return id_ligne;
    }

    public int getIdProduit() {
        return id_produit;
    }

    public int getQte() {
        return qte;
    }

    public double getTotalLigneHt() {
        return total_ligne_ht;
    }
}