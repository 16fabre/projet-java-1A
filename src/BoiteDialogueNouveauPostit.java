import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BoiteDialogueNouveauPostit extends JDialog {

	// ATTRIBUTS

	private Fenetre fen;
	private JLabel titreLabel, couleurLabel, texteLabel, icon;
	private JTextField titre;
	private JTextArea texte;
	private Font ftitre = new Font("TimesRoman", Font.BOLD, 18), ftexte = new Font("TimesRoman", Font.PLAIN, 12);;

	// CONSTRUCTEUR

	public BoiteDialogueNouveauPostit(JFrame parent, String tit, boolean modal, Fenetre f) {
		super(parent, tit, modal);
		fen = f;
		this.setSize(600, 450);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.initComponent();
		this.setVisible(true);
	}

	// METHODE

	public void initComponent() {

		// Icône

		icon = new JLabel(new ImageIcon("images/iconeBoiteDialogueNouveauPostit.jpg"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.setLayout(new BorderLayout());
		panIcon.add(icon);

		// Le Titre

		JPanel panTitre = new JPanel();
		panTitre.setBackground(Color.white);
		panTitre.setPreferredSize(new Dimension(220, 110));
		titre = new JTextField();
		JButton styleTitre = new JButton("Style"), couleurTitre = new JButton();
		couleurTitre.setBackground(Color.BLACK);
		JLabel couleurTitreLabel = new JLabel("Couleur :");
		titre.setPreferredSize(new Dimension(190, 25));
		couleurTitre.setPreferredSize(new Dimension(50, 25));
		panTitre.setBorder(BorderFactory.createTitledBorder("Titre du postit"));
		titreLabel = new JLabel("Saisir un titre :");
		panTitre.add(titreLabel);
		panTitre.add(titre);
		panTitre.add(couleurTitreLabel);
		panTitre.add(couleurTitre);
		panTitre.add(styleTitre);

		// La couleur

		JPanel panCouleur = new JPanel();
		panCouleur.setBackground(Color.white);
		panCouleur.setPreferredSize(new Dimension(220, 60));
		panCouleur.setBorder(BorderFactory.createTitledBorder("Couleur du postit"));
		JButton choixCouleur = new JButton();
		choixCouleur.setBackground(new Color(246,222,20));
		choixCouleur.setPreferredSize(new Dimension(50, 25));
		couleurLabel = new JLabel("Couleur : ");
		panCouleur.add(couleurLabel);
		panCouleur.add(choixCouleur);

		// Le texte

		JPanel panTexte = new JPanel();
		panTexte.setBackground(Color.white);
		panTexte.setPreferredSize(new Dimension(220, 190));
		texte = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(texte);
		scrollPane.setPreferredSize(new Dimension(200, 100));
		panTexte.setBorder(BorderFactory.createTitledBorder("Texte du postit"));
		texteLabel = new JLabel("Saisir votre texte :");
		JButton styleTexte = new JButton("Style"), couleurTexte = new JButton();
		couleurTexte.setBackground(Color.black);
		couleurTexte.setPreferredSize(new Dimension(50, 25));
		JLabel couleurTexteLabel = new JLabel("Couleur :");
		panTexte.add(texteLabel);
		panTexte.add(scrollPane);
		panTexte.add(couleurTexteLabel);
		panTexte.add(couleurTexte);
		panTexte.add(styleTexte);

		// Ajout des panels, boutons "ok" et "annuler" et des ActionListener

		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panTitre);
		content.add(panCouleur);
		content.add(panTexte);

		JPanel control = new JPanel();
		JButton okBouton = new JButton("OK");
		JButton cancelBouton = new JButton("Annuler");

		okBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				int selected = fen.getOnglet().getSelectedIndex();
				fen.getListeTableau().get(selected)
						.addPostit(new Postit(fen.getOnglet().getComponent(selected).getWidth() / 2 - 40,
								fen.getOnglet().getComponent(selected).getHeight() / 2 - 40, 80, 80,
								choixCouleur.getBackground(), titre.getText(), texte.getText(),
								couleurTitre.getBackground(), couleurTexte.getBackground(), ftitre, ftexte));
				fen.getOnglet().getSelectedComponent().repaint();
			}
		});
		cancelBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		couleurTexte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c = JColorChooser.showDialog(couleurTexte, "Sélection de la couleur du texte du postit", null);
				couleurTexte.setBackground(c);
			}
		});
		styleTexte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BoiteDialogueChoixFont bTexte = new BoiteDialogueChoixFont(null, "Style du texte", true);
				ftexte = bTexte.getFontChoisie();
			}
		});
		choixCouleur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c = JColorChooser.showDialog(choixCouleur, "Sélection de la couleur du brainstorming", null);
				choixCouleur.setBackground(c);
			}
		});
		couleurTitre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c = JColorChooser.showDialog(couleurTitre, "Sélection de la couleur du titre du postit", null);
				couleurTitre.setBackground(c);
			}
		});
		styleTitre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BoiteDialogueChoixFont bTitre = new BoiteDialogueChoixFont(null, "Style du titre", true);
				ftitre = bTitre.getFontChoisie();
			}
		});

		control.add(okBouton);
		control.add(cancelBouton);

		this.getContentPane().add(panIcon, BorderLayout.WEST);
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
	}
}
