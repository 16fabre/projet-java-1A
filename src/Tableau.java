import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

public class Tableau extends JPanel implements MouseListener, MouseMotionListener, Serializable {

	// ATTRIBUTS

	private Fenetre fen;
	private String titre;
	private Color couleur;
	private LinkedList<Postit> listePostit = new LinkedList<Postit>();

	// CONSTRUCTEURS

	public Tableau() {
		fen = new Fenetre();
		titre = "Sans titre";
		couleur = Color.WHITE;
		setBackground(couleur);
		listePostit = null;
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public Tableau(String txt, Color col, Fenetre f) {
		fen = f;
		titre = txt;
		couleur = col;
		setBackground(couleur);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public Tableau(String txt, Color col, LinkedList<Postit> lst, Fenetre f) {
		titre = txt;
		fen = f;
		couleur = col;
		setBackground(couleur);
		listePostit = lst;
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	// ASSESSEURS et MUTATEURS

	public String getTitre() {
		return titre;
	}

	public void setTitre(String txt) {
		titre = txt;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color col) {
		couleur = col;
		setBackground(couleur);
	} 
	public LinkedList<Postit> getListePostit() { 
		return listePostit;
	}

	public void setListePostit(LinkedList<Postit> lst) {
		listePostit = lst;
	}

	// METHODES

	public void addPostit(Postit pos) {
		listePostit.addLast(pos);
	}

	public LinkedList<Integer> getSelectedPostit(LinkedList<Postit> lst) {
		LinkedList<Integer> selec = new LinkedList<Integer>();
		for (int i = 0; i < listePostit.size(); i++) {
			if (listePostit.get(i).getSelectionne())
				selec.add(i);
		}
		return selec;
	}

	public void removePostit() {
		// On récupère les indices des postits sélectionnés
		LinkedList<Integer> selec = getSelectedPostit(listePostit);
		// On parcourt la liste de Postit à l'envers pour éviter les problèmes
		// de décalage d'indices à la suppression d'un élément
		for (int i = selec.size() - 1; i > -1; i--) {
			listePostit.remove((int) (selec.get(i)));
		}
		fen.getOnglet().getSelectedComponent().repaint();
	}

	public void paintComponent(Graphics g) {

		// Fond du tableau

		g.setColor(couleur);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		// On dessine tous les postits en parcourant la liste

		ListIterator<Postit> iter = listePostit.listIterator();
		if (listePostit.size() >= 1) {
			while (iter.hasNext()) {
				Postit p = iter.next();
				g.setColor(p.getCouleur());

				// Cas des postits sélectionnés

				if (p.getSelectionne()) {
					g.fill3DRect(p.getX(), p.getY(), p.getLargeur(), p.getHauteur(), true);
					g.setColor(Color.BLACK);
					g.fillRect(p.getX() - 2, p.getY() - 2, 5, 5);
					g.fillRect(p.getX() + p.getLargeur() - 2, p.getY() - 2, 5, 5);
					g.fillRect(p.getX() + p.getLargeur() - 2, p.getY() + p.getHauteur() - 2, 5, 5);
					g.fillRect(p.getX() - 2, p.getY() + p.getHauteur() - 2, 5, 5);
				}

				// Cas des postits non sélectionnés

				else {
					g.fillRect(p.getX(), p.getY(), p.getLargeur(), p.getHauteur());
				}

				// Affichage du titre

				g.setColor(p.getColorTitre());
				if (p.getFontTitre()==null) p.setFontTitre(new Font("TimesRoman", Font.BOLD, 18));
				g.setFont(p.getFontTitre());
				FontMetrics fmtitre = getFontMetrics(p.getFontTitre());
				int largeurTitre = fmtitre.stringWidth(p.getTitre());
				int hauteurTitre = fmtitre.getHeight();
				if (p.getLargeur() < largeurTitre + 5)
					p.setLargeur(largeurTitre + 8);
				g.drawString(p.getTitre(), p.getX() + 5, p.getY() + hauteurTitre);

				// Affiche du texte

				g.setColor(p.getColorTexte());
				if (p.getFontTexte()==null) p.setFontTexte(new Font("TimesRoman", Font.PLAIN, 12));
				g.setFont(p.getFontTexte());
				FontMetrics fmtexte = getFontMetrics(p.getFontTexte());
				int hauteurTexte = fmtexte.getHeight();
				int nbChar = p.getTexte().length(), ideb = 0, ifin = 0, n = 0;
				while (ifin < nbChar - 1) {
					while (fmtexte.stringWidth(p.getTexte().substring(ideb, ifin)) < (p.getLargeur() - 12)
							&& ifin < nbChar) {
						ifin++;
					}
					g.drawString(p.getTexte().substring(ideb, ifin), p.getX() + 5,
							p.getY() + hauteurTitre + (n + 1) * hauteurTexte);
					ideb = ifin;
					n++;
				}
				if (p.getHauteur() < hauteurTitre + (n + 1) * hauteurTexte)
					p.setHauteur(hauteurTitre + (n + 1) * hauteurTexte);
			}
		}
		fen.getOnglet().getSelectedComponent().repaint();
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void mousePressed(MouseEvent e) {
		ListIterator<Postit> iter = listePostit.listIterator();
		if (listePostit.size() >= 1) {
			// Si la liste est non vide on la parcourt
			while (iter.hasNext()) {
				int i = iter.nextIndex();
				Postit p = iter.next();
				// Cas où on clique sur un postit
				if (verifClicPostit(e, p)) {
					listePostit.get(i).setHauteurPressed(e.getY() - p.getY());
					listePostit.get(i).setLargeurPressed(e.getX() - p.getX());
				}
				if (p.getSelectionne()) {
					if (verifCarreDimBD(e, p))
						p.dragBD = true;
					if (verifCarreDimHD(e, p))
						p.dragHD = true;
					if (verifCarreDimBG(e, p))
						p.dragBG = true;
					if (verifCarreDimHG(e, p))
						p.dragHG = true;
					if (verifClicPostit(e, p))
						p.deplacement = true;
				}
				// Cas où on clique ailleurs, A FAIRE PLUS TARD (
				else {
					// .............................
				}
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
		ListIterator<Postit> iter = listePostit.listIterator();
		if (listePostit.size() >= 1) {
			// Si la liste est non vide on la parcourt
			while (iter.hasNext()) {
				int i = iter.nextIndex();
				Postit p = iter.next();
				p.dragBD = false;
				p.dragBG = false;
				p.dragHD = false;
				p.dragHG = false;
				p.deplacement = false;
			}
		}
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {
		ListIterator<Postit> iter = listePostit.listIterator();
		if (listePostit.size() >= 1) {
			// Si la liste est non vide on la parcourt
			while (iter.hasNext()) {
				int i = iter.nextIndex();
				Postit p = iter.next();
				// Cas du Ctrl+clic gauche :
				if (e.isControlDown() && SwingUtilities.isLeftMouseButton(e)) {
					// Cas où on clique sur un postit
					if (verifClicPostit(e, p)) {
						// On change l'état de sélection du postit
						boolean tmp = p.getSelectionne();
						if (tmp == true)
							p.setSelectionne(false);
						if (tmp == false)
							p.setSelectionne(true);
					}
				}
				// Cas du double-clic :
				if (e.getClickCount() == 2) {
					if (verifClicPostit(e, p))
						new BoiteDialogueEditionPostit(null, "Edition du postit", true, fen, p);
				}
				// Cas d'un simple clic gauche :
				if (!e.isControlDown() && SwingUtilities.isLeftMouseButton(e)) {
					// Cas où on clique sur un postit
					if (verifClicPostit(e, p)) {
						// S'il est désélectionné il devient sélectionné
						if (!p.getSelectionne())
							listePostit.get(i).setSelectionne(true);
					}
					// Cas où on clique ailleurs, on désélectionne
					else
						listePostit.get(i).setSelectionne(false);
				}
			}
		}
		fen.getOnglet().getSelectedComponent().repaint();
	}

	public void mouseDragged(MouseEvent e) {
		if (contains(e.getX(), e.getY())) {
			ListIterator<Postit> iter = listePostit.listIterator();
			if (listePostit.size() >= 1) {
				// Si la liste est non vide on la parcourt
				while (iter.hasNext()) {
					int i = iter.nextIndex();
					Postit p = iter.next();
					if (p.getSelectionne()) {
						if (p.deplacement) {
							listePostit.get(i).setX(e.getX() - p.getLargeurPressed());
							listePostit.get(i).setY(e.getY() - p.getHauteurPressed());
						}
						if (p.dragHD) {
							int YcoinBG = p.getY() + p.getHauteur();
							listePostit.get(i).setLargeur(Math.abs(e.getX() - p.getX()));
							listePostit.get(i).setHauteur(Math.abs(YcoinBG - e.getY()));
							listePostit.get(i).setY(e.getY());
						}
						if (p.dragHG) {
							int YcoinBD = p.getY() + p.getHauteur();
							int XcoinBD = p.getX() + p.getLargeur();
							listePostit.get(i).setLargeur(Math.abs(XcoinBD - e.getX()));
							listePostit.get(i).setHauteur(Math.abs(YcoinBD - e.getY()));
							listePostit.get(i).setX(e.getX());
							listePostit.get(i).setY(e.getY());
						}
						if (p.dragBG) {
							int XcoinHD = p.getX() + p.getLargeur();
							listePostit.get(i).setLargeur(Math.abs(XcoinHD - e.getX()));
							listePostit.get(i).setHauteur(Math.abs(e.getY() - p.getY()));
							listePostit.get(i).setX(e.getX());
						}
						if (p.dragBD) {
							listePostit.get(i).setLargeur(Math.abs(e.getX() - p.getX()));
							listePostit.get(i).setHauteur(Math.abs(e.getY() - p.getY()));
						}
					}
				}
				fen.getOnglet().getSelectedComponent().repaint();
			}
		}

	}

	public void mouseMoved(MouseEvent e) {

	}

	public boolean verifClicPostit(MouseEvent e, Postit p) {
		return (e.getX() >= p.getX()) && (e.getX() <= (p.getX() + p.getLargeur())) && (e.getY() >= p.getY())
				&& (e.getY() <= (p.getY() + p.getHauteur()) && !verifCarreDimHD(e, p) && !verifCarreDimHG(e, p)
						&& !verifCarreDimBG(e, p) && !verifCarreDimBD(e, p));
	}

	public boolean verifCarreDimHD(MouseEvent e, Postit p) {
		return (e.getX() >= (p.getX() + p.getLargeur() - 2) && e.getX() <= (p.getX() + p.getLargeur() + 3)
				&& e.getY() <= (p.getY() + 3) && e.getY() >= (p.getY() - 2));
	}

	public boolean verifCarreDimHG(MouseEvent e, Postit p) {
		return (e.getX() >= (p.getX() - 2) && e.getX() <= (p.getX() + 3) && e.getY() <= (p.getY() + 3)
				&& e.getY() >= (p.getY() - 2));
	}

	public boolean verifCarreDimBG(MouseEvent e, Postit p) {
		return (e.getX() >= (p.getX() - 2) && e.getX() <= (p.getX() + 3) && e.getY() <= (p.getY() + p.getHauteur() + 3)
				&& e.getY() >= (p.getY() + p.getHauteur() - 2));
	}

	public boolean verifCarreDimBD(MouseEvent e, Postit p) {
		return (e.getX() >= (p.getX() + p.getLargeur() - 2) && e.getX() <= (p.getX() + p.getLargeur() + 3)
				&& e.getY() <= (p.getY() + p.getHauteur() + 3) && e.getY() >= (p.getY() + p.getHauteur() - 2));
	}
}