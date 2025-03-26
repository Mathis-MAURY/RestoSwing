package RestoSwing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Commande_detail extends JFrame {

    private Commande commande;

    public Commande_detail(Commande commande) {
        this.commande = commande;

        setTitle("Détails de la commande");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Titre
        JLabel titre = new JLabel("RestoSwing", JLabel.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel sousTitre = new JLabel("Détails d'une commande", JLabel.CENTER);
        sousTitre.setFont(new Font("Arial", Font.PLAIN, 16));

        // Informations commande
        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.add(new JLabel("ID commande"));
        JTextField txtId = new JTextField(String.valueOf(commande.get_id_commande()));
        txtId.setEditable(false);
        infoPanel.add(txtId);

        infoPanel.add(new JLabel("Date"));
        JTextField txtDate = new JTextField(commande.get_date());
        txtDate.setEditable(false);
        infoPanel.add(txtDate);

        infoPanel.add(new JLabel("Login"));
        JTextField txtLogin = new JTextField(commande.get_loginUser());
        txtLogin.setEditable(false);
        infoPanel.add(txtLogin);

        // Table
        String[] cols = {"ID", "Plat", "Quantité"};
        List<Ligne> lignes = commande.get_lignes();
        Object[][] data = new Object[lignes.size()][3];
        for (int i = 0; i < lignes.size(); i++) {
            Ligne l = lignes.get(i);
            data[i][0] = l.get_id_ligne();
            data[i][1] = l.get_libelle_produit();
            data[i][2] = l.get_qte();
        }
        JTable table = new JTable(new DefaultTableModel(data, cols));
        JScrollPane scrollPane = new JScrollPane(table);

        // Boutons d'action (droite)
        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.Y_AXIS));
        JButton btnAccepter = new JButton("Accepter");
        JButton btnRefuser = new JButton("Refuser");
        JButton btnPrete = new JButton("Prête");

        btnAccepter.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRefuser.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnPrete.setAlignmentX(Component.CENTER_ALIGNMENT);

        actionsPanel.add(btnAccepter);
        actionsPanel.add(Box.createVerticalStrut(10));
        actionsPanel.add(btnRefuser);
        actionsPanel.add(Box.createVerticalStrut(10));
        actionsPanel.add(btnPrete);

        // Action des boutons
        btnAccepter.addActionListener(e -> envoyerCommandeAction("accepter"));
        btnRefuser.addActionListener(e -> envoyerCommandeAction("refuser"));
        btnPrete.addActionListener(e -> envoyerCommandeAction("prete"));

        // Bouton retour
        JButton btnRetour = new JButton("Revenir");
        btnRetour.addActionListener(e -> dispose());

        // Organisation générale
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(titre, BorderLayout.NORTH);
        northPanel.add(sousTitre, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(infoPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(actionsPanel, BorderLayout.EAST);
        mainPanel.add(btnRetour, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void envoyerCommandeAction(String action) {
        String apiUrl = "http://localhost/Site-Resto/Site-Restaurant/api/commande_en_attente.php?action=" + action + "&id_commande=" + commande.get_id_commande();
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(apiUrl))
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            JOptionPane.showMessageDialog(this, "Commande " + action + " avec succès.");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'envoi de la commande.");
            ex.printStackTrace();
        }
    }
}
