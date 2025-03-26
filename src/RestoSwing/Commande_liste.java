package RestoSwing;

import java.awt.event.ActionEvent;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;

public class Commande_liste extends javax.swing.JFrame {

    ArrayList<Commande> commandes; // Collection des commandes
    ArrayList<Ligne> lignes; // Collection des lignes

    static final String API_URL = "http://localhost/Site-Resto/Site-Restaurant/api/commande_en_attente.php";

    /**
     * Creates new form Commande_liste
     */
    public Commande_liste() {
        initComponents();
        setSize(900, 600);
        setLocationRelativeTo(null);
        get_data();
    }

    // Appelle l'API et remplit la table des commandes
    public void get_data() {
        commandes = new ArrayList<>(); // Réinitialise la collection des commandes

        String json = ""; // Le JSON brut

        // Créer un HttpClient
        HttpClient client = HttpClient.newHttpClient();
        try {
            // Crée une requête HTTP GET
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(API_URL))
                    .build();

            // Envoie la requête et attend la réponse
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                json = response.body();
            } else {
                System.err.println("Erreur : Code statut " + response.statusCode());
                return;
            }
        } catch (Exception ex) {
            System.err.println("Erreur : " + ex.getMessage());
            return;
        }

        // Parse le JSON et remplit la collection de commandes
        try {
            JSONObject jsonResponse = new JSONObject(json);
            JSONArray commandes_json = jsonResponse.getJSONArray("commandes");

            for (int i = 0; i < commandes_json.length(); i++) {
                JSONObject commande_json = commandes_json.getJSONObject(i);

                // Récupérer les lignes
                lignes = new ArrayList<>();
                JSONArray lignes_json = commande_json.getJSONArray("lignes");
                for (int j = 0; j < lignes_json.length(); j++) {
                    JSONObject ligne_json = lignes_json.getJSONObject(j);
                    Ligne ligne = new Ligne(
                            ligne_json.getInt("id_ligne"),
                            ligne_json.getInt("id_produit"),
                            ligne_json.getString("libelle_produit"),
                            ligne_json.getInt("qte"),
                            Double.parseDouble(ligne_json.getString("total_ligne_ht"))
                    );
                    lignes.add(ligne);
                }

                // Récupérer les informations utilisateur
                JSONObject user_json = commande_json.getJSONObject("user");
                String loginUser = user_json.getString("login");
                String emailUser = user_json.getString("email");

                // Créer la commande
                Commande commande = new Commande(
                        commande_json.getInt("id_commande"),
                        commande_json.getInt("id_user"),
                        commande_json.getInt("id_etat"),
                        commande_json.getString("date"),
                        Double.parseDouble(commande_json.getString("total_commande")),
                        commande_json.getInt("type_conso"),
                        loginUser,
                        emailUser,
                        lignes
                );
                commandes.add(commande);
            }
        } catch (Exception ex) {
            System.err.println("Erreur : " + ex.getMessage());
            ex.printStackTrace();
            return;
        }

        // Construit le tableau de données à partir de la collection
        Object[][] data = new Object[commandes.size()][5];
        for (int i = 0; i < commandes.size(); i++) {
            Commande commande = commandes.get(i);
            data[i][0] = commande.get_id_commande();
            data[i][1] = commande.get_date();
            data[i][2] = commande.get_id_etat();
            data[i][3] = commande.get_lignes().size(); // Nombre de plats
            data[i][4] = commande.get_total_commande();
        }

        // Construit le tableau des entêtes
        String[] cols = {"ID", "Date", "Etat", "Nb plats", "Montant"};

        // Construit le modèle
        DefaultTableModel model_commande = new DefaultTableModel(data, cols);

        // Met à jour le modèle dans le JTable
        table_commande.setModel(model_commande);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        label_titre = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_commande = new javax.swing.JTable();
        button_details = new javax.swing.JButton();
        button_quitter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Liste des Commandes");

        label_titre.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 24)); // Titre en gras
        label_titre.setText("Commandes");
        label_titre.setHorizontalAlignment(SwingConstants.CENTER); // Centrage du titre

        table_commande.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {},
                new String[] { "ID Commande", "Date", "Total" }
        ));

        table_commande.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        table_commande.setFillsViewportHeight(true);
        table_commande.setRowHeight(30);
        jScrollPane1.setViewportView(table_commande);

        button_details.setText("Détails");
        button_details.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int row = table_commande.getSelectedRow();
                if (row >= 0 && row < commandes.size()) {
                    Commande commande = commandes.get(row);
                    Commande_detail commandeDetail = new Commande_detail(commande);
                    commandeDetail.setVisible(true);
                    commandeDetail.addWindowListener(new java.awt.event.WindowAdapter() {
                        public void windowClosed(java.awt.event.WindowEvent e) {
                            get_data();
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(Commande_liste.this, "Veuillez sélectionner une commande.");
                }
            }
        });

        button_quitter.setText("Quitter");
        button_quitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_quitterActionPerformed(evt);
            }
        });

        // Utilisation d’un BorderLayout pour faciliter le centrage
        getContentPane().setLayout(new java.awt.BorderLayout(10, 10));
        getContentPane().add(label_titre, java.awt.BorderLayout.NORTH);
        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(button_details);
        bottomPanel.add(button_quitter);
        getContentPane().add(bottomPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }

    private void button_quitterActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Commande_liste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new Commande_liste().setVisible(true));
    }

    private javax.swing.JButton button_details;
    private javax.swing.JButton button_quitter;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_titre;
    private javax.swing.JTable table_commande;
}
