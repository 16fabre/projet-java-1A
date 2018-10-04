import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class BoiteDialogueNouveauBrainstorming extends JDialog {

	// ATTRIBUTS

	private Fenetre fen;
	private JLabel titreLabel, couleurLabel, icon;
	private JTextField titre;

	// CONSTRUCTEURS

	public BoiteDialogueNouveauBrainstorming() {
		fen = new Fenetre();
		this.setSize(550, 270);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
		this.setVisible(true);
	}

	public BoiteDialogueNouveauBrainstorming(JFrame parent, String tit, boolean modal, Fenetre f) {
		super(parent, tit, modal);
		fen = f;
		this.setSize(550, 270);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.initComponent();
		this.setVisible(true);
	}

	// METHODES

	private void initComponent() {

		// Icône

		icon = new JLabel(new ImageIcon("images/iconeBoiteDialogueNouveauBrainstorming.jpg"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.setLayout(new BorderLayout());
		panIcon.add(icon);

		// Le titre

		JPanel panTitre = new JPanel();
		panTitre.setBackground(Color.white);
		panTitre.setPreferredSize(new Dimension(220, 80));
		titre = new JTextField();
		titre.setPreferredSize(new Dimension(190, 25));
		panTitre.setBorder(BorderFactory.createTitledBorder("Titre du brainstorming"));
		titreLabel = new JLabel("Saisir un titre :");
		panTitre.add(titreLabel);
		panTitre.add(titre);

		// La couleur

		JPanel panCouleur = new JPanel();
		panCouleur.setBackground(Color.white);
		panCouleur.setPreferredSize(new Dimension(220, 80));
		panCouleur.setBorder(BorderFactory.createTitledBorder("Couleur du tableau"));
		JButton choixCouleur = new JButton();
		choixCouleur.setBackground(new Color(14, 74, 40));
		choixCouleur.setPreferredSize(new Dimension(50, 40));
		couleurLabel = new JLabel("Couleur : ");
		panCouleur.add(couleurLabel);
		panCouleur.add(choixCouleur);

		// Ajout des panels, boutons "ok" et "annuler" et des ActionListener

		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panTitre);
		content.add(panCouleur);

		JPanel control = new JPanel();
		JButton okBouton = new JButton("OK");
		JButton cancelBouton = new JButton("Annuler");

		okBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				if (!titre.getText().equals("")) {
					fen.getListeTableau().addLast(new Tableau(titre.getText(), choixCouleur.getBackground(), fen));
					int i = fen.getListeTableau().size();
					fen.getOnglet().add(titre.getText(), fen.getListeTableau().get(i - 1));
				} else {
					fen.getListeTableau().addLast(new Tableau("Sans titre", choixCouleur.getBackground(), fen));
					int i = fen.getListeTableau().size();
					fen.getOnglet().add("Sans titre", fen.getListeTableau().get(i - 1));
				}
			}
		});
		choixCouleur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c = JColorChooser.showDialog(choixCouleur, "Sélection de la couleur du brainstorming", null);
				choixCouleur.setBackground(c);
			}
		});
		cancelBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

		control.add(okBouton);
		control.add(cancelBouton);

		this.getContentPane().add(panIcon, BorderLayout.WEST);
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
	}
}