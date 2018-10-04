import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;

public class Postit implements Serializable {

	// ATTRIBUTS

	private int x, y, largeur, hauteur, largeurPressed, hauteurPressed;
	private Color couleur, couleurTexte, couleurTitre;
	private String titre, texte;
	private Font fontTexte, fontTitre;
	private boolean selectionne;
	public boolean dragBD = false, dragBG = false, dragHD = false, dragHG = false, deplacement = false;

	// CONSTRUCTEURS

	public Postit() {
		couleur = Color.YELLOW;
		x = 0;
		y = 0;
		largeur = 50;
		hauteur = 50;
		titre = "Sans titre";
		texte = "Entrez du texte";
		selectionne = true;
		fontTexte = new Font("TimesRoman", Font.PLAIN, 12);
		fontTitre = new Font("TimesRoman", Font.BOLD, 18);
		couleurTexte = Color.BLACK;
		couleurTitre = Color.BLACK;
	}

	public Postit(int px, int py, int l, int h, Color c, String txt1, String txt2) {
		x = px;
		y = py;
		largeur = l;
		hauteur = h;
		couleur = c;
		titre = txt1;
		texte = txt2;
		selectionne = true;
		fontTexte = new Font("TimesRoman", Font.PLAIN, 12);
		fontTitre = new Font("TimesRoman", Font.BOLD, 18);
		couleurTexte = Color.BLACK;
		couleurTitre = Color.BLACK;
	}

	public Postit(int px, int py, int l, int h, Color c, String txt1, String txt2, Color c1, Color c2, Font f1,
			Font f2) {
		x = px;
		y = py;
		largeur = l;
		hauteur = h;
		couleur = c;
		titre = txt1;
		texte = txt2;
		selectionne = true;
		fontTexte = f2;
		fontTitre = f1;
		couleurTexte = c2;
		couleurTitre = c1;
	}

	// ASSESSEURS et MUTATEURS

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getLargeur() {
		return largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setX(int i) {
		x = i;
	}

	public void setY(int i) {
		y = i;
	}

	public void setLargeur(int i) {
		largeur = i;
	}

	public void setHauteur(int i) {
		hauteur = i;
	}

	public void setCouleur(Color c) {
		couleur = c;
	}

	public boolean getSelectionne() {
		return selectionne;
	}

	public void setSelectionne(boolean b) {
		selectionne = b;
	}

	public int getLargeurPressed() {
		return largeurPressed;
	}

	public void setLargeurPressed(int l) {
		largeurPressed = l;
	}

	public int getHauteurPressed() {
		return hauteurPressed;
	}

	public void setHauteurPressed(int l) {
		hauteurPressed = l;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String t) {
		titre = t;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String t) {
		texte = t;
	}

	public Font getFontTitre() {
		return fontTitre;
	}

	public void setFontTitre(Font f) {
		fontTitre = f;
	}

	public Font getFontTexte() {
		return fontTexte;
	}

	public void setFontTexte(Font f) {
		fontTexte = f;
	}

	public Color getColorTexte() {
		return couleurTexte;
	}

	public void setColorTexte(Color c) {
		couleurTexte = c;
	}

	public Color getColorTitre() {
		return couleurTitre;
	}

	public void setColorTitre(Color c) {
		couleurTitre = c;
	}
}