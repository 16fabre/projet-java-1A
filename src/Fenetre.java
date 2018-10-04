import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class Fenetre extends JFrame {

	// ATTRIBUTS

	private LinkedList<Tableau> listeTableau = new LinkedList<Tableau>();
	private BarreMenu menuBar = new BarreMenu(this);
	private JTabbedPane onglet = new JTabbedPane();
	private BarreOutils barreOutils;

	// CONSTRUCTEUR

	public Fenetre() {

		// Quelques propri�t�s de la fen�tre

		this.setTitle("Brainstormings par tableau de postits");
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		onglet.setPreferredSize(new Dimension(600, 500));

		// Ajout de la barre de menu.

		this.setJMenuBar(menuBar);

		// Placement de la barre d'outils et des onglets.

		barreOutils = new BarreOutils(this);
		this.setLayout(new BorderLayout());
		this.getContentPane().add(barreOutils, BorderLayout.NORTH);
		this.getContentPane().add(onglet, BorderLayout.CENTER);
		this.pack();
	}

	public static void main(String[] args) {
		new Fenetre();
	}

	// ASSESSEURS et MUTATEURS

	public JTabbedPane getOnglet() {
		return onglet;
	}

	public void setOnglet(JTabbedPane ong) {
		onglet = ong;
	}

	public LinkedList<Tableau> getListeTableau() {
		return listeTableau;
	}

	public void setListeTableau(LinkedList<Tableau> list) {
		listeTableau = list;
	}
}