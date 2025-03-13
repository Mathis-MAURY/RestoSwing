import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class RestoSwingMain {

    public static void main(String[] args) {

        ArrayList<String> les_commandes = new ArrayList<>();
        ArrayList<Commande> les_commandes2 = new ArrayList<>();

        String json = "";
        String url ="http://localhost/Site-Resto/Site-Restaurant/api/";

        // Créer un HttpClient
        HttpClient client = HttpClient.newHttpClient();
        // Crée une requête HTTP GET
        try {
            // Construit l'URL de la requête
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .build();
            // Envoie la requête et attend la réponse
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // Vérifie que la réponse est normale
            if (response.statusCode() == 200) {
                json = response.body();
            } else {
                System.err.println("Erreur : Code statut " + response.statusCode());
            }

        } catch (Exception ex) {
            System.err.println("Erreur : " + ex.getMessage());
            //ex.printStackTrace();
        }
        //System.out.println(json);

        // Parse le fichier et remplit la collection d'objets métier
        try {
            JSONArray les_commandes_json = new JSONArray(json);
            for (int i = 0; i < les_commandes_json.length(); i++) {
                JSONObject commandes_json = les_commandes_json.getJSONObject(i);

                // Récupérer les informations principales de la commande
                Commande commandes = new Commande(
                        commandes_json.getInt("id_commande"),
                        commandes_json.getInt("id_user"),
                        commandes_json.getInt("id_etat"),
                        commandes_json.getString("date"),
                        commandes_json.getDouble("total_commande"),
                        commandes_json.getInt("type_conso"),
                        commandes_json.getString("loginUser"),
                        commandes_json.getString("emailUser"),
                        new ArrayList<>() // Initialisation vide de l'ArrayList de lignes
                );

                // Récupérer l'Array de lignes de commande
                JSONArray lignes_json = commandes_json.getJSONArray("lignes");

                // Boucle pour ajouter les lignes dans l'ArrayList de lignes
                for (int j = 0; j < lignes_json.length(); j++) {
                    JSONObject ligne_json = lignes_json.getJSONObject(j);

                    // Créer une instance de l'objet Ligne et ajouter à l'ArrayList de lignes
                    Ligne ligne = new Ligne(
                            ligne_json.getInt("id_ligne"),
                            ligne_json.getInt("id_produit"),
                            ligne_json.getString("libelle_produit"),
                            ligne_json.getInt("qte"),
                            ligne_json.getDouble("total_ligne_ht")
                    );
                    commandes.getLignes().add(ligne); // Ajout de la ligne à la commande
                }

                // Ajouter la commande avec ses lignes à la collection
                les_commandes2.add(commandes);
            }
        } catch (Exception ex) {
            System.err.println("Erreur : " + ex.getMessage());
            //ex.printStackTrace();
        }

        // Affichage des noces à partir de la collection d'objets métier
        System.out.println("-- Liste des commandes avec la collection d'objets métier --");
        for (int i = 0; i < les_commandes2.size(); i++) {
            les_commandes2.get(i).afficher();
        }

    } // main()

} // class Ja60b
