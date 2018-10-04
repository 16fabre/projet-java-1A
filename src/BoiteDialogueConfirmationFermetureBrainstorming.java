import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class BoiteDialogueConfirmationFermetureBrainstorming extends JOptionPane {

	// ATTRIBUTS

	private Fenetre fen;

	// CONSTRUCTEUR

	public BoiteDialogueConfirmationFermetureBrainstorming(Fenetre f) {
		fen = f;
		int option = showConfirmDialog(null, "Etes-vous sûr de vouloir fermer ce brainstorming?",
				"Fermeture du brainstorming", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (option == JOptionPane.OK_OPTION) {
			// On récupère l'index de l'onglet sélectionné
			int selected = fen.getOnglet().getSelectedIndex();
			// S'il n'y a plus d'onglet, la méthode ci-dessus retourne -1
			if (selected > -1) {
				fen.getOnglet().remove(selected);
				fen.getListeTableau().remove(selected);
			}
		}
	}
}