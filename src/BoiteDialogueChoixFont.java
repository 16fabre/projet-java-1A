import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BoiteDialogueChoixFont extends JDialog {

	// ATTRIBUS

	private JFontChooser fontChooser;
	private Font fontChoisie;

	// CONSTRUCTEUR

	public BoiteDialogueChoixFont(JFrame parent, String tit, boolean modal) {
		super(parent, tit, modal);
		this.setSize(new Dimension(420, 360));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.initComponent();
		this.setVisible(true);
	}

	// METHODES

	private void initComponent() {

		// Ajout du composant JFontChooser :

		JPanel choix = new JPanel();
		fontChooser = new JFontChooser();
		choix.add(fontChooser);

		// Ajout des boutons OK et Annuler :

		JPanel control = new JPanel();
		JButton okBouton = new JButton("OK");
		JButton cancelBouton = new JButton("Annuler");
		control.add(okBouton);
		control.add(cancelBouton);

		okBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				fontChoisie = fontChooser.getSelectedFont();
			}
		});
		cancelBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

		// Ajout des composants à la fenêtre :

		this.getContentPane().add(choix, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
	}

	public Font getFontChoisie() {
		return fontChoisie;
	}
}