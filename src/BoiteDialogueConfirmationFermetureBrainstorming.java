import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class BoiteDialogueConfirmationFermetureBrainstorming extends JOptionPane {

	// ATTRIBUTS

	private Fenetre fen;

	// CONSTRUCTEUR

	public BoiteDialogueConfirmationFermetureBrainstorming(Fenetre f) {
		fen = f;
		int option = showConfirmDialog(null, "Etes-vous s�r de vouloir fermer ce brainstorming?",
				"Fermeture du brainstorming", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (option == JOptionPane.OK_OPTION) {
			// On r�cup�re l'index de l'onglet s�lectionn�
			int selected = fen.getOnglet().getSelectedIndex();
			// S'il n'y a plus d'onglet, la m�thode ci-dessus retourne -1
			if (selected > -1) {
				fen.getOnglet().remove(selected);
				fen.getListeTableau().remove(selected);
			}
		}
	}
}