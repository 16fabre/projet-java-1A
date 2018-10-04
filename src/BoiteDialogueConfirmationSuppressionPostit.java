import javax.swing.JOptionPane;

public class BoiteDialogueConfirmationSuppressionPostit extends JOptionPane {

	// ATTRIBUTS

	private Fenetre fen;

	// CONSTRUCTEUR

	public BoiteDialogueConfirmationSuppressionPostit(Fenetre f) {
		fen = f;
		int option = showConfirmDialog(null, "Etes-vous sûr de vouloir supprimer ces postits?",
				"Suppression de ces postits", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (option == JOptionPane.OK_OPTION) {
			int i = fen.getOnglet().getSelectedIndex();
			fen.getListeTableau().get(i).removePostit();
		}
	}
}