import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.*;

public class BarreOutils extends JToolBar {

	// ATTRIBUTS

	private Fenetre fen;
	private JButton nouveau = new JButton(), supprimer = new JButton(), ouvrir = new JButton(),
			sauvegarder = new JButton(), couleur = new JButton(), avancer = new JButton(), reculer = new JButton(),
			alignement = new JButton(), style = new JButton();
	private JPopupMenu nouveauMenu = new JPopupMenu(), supprimerMenu = new JPopupMenu(), avancerMenu = new JPopupMenu(),
			reculerMenu = new JPopupMenu(), couleurMenu = new JPopupMenu(), alignementMenu = new JPopupMenu(),
			saveMenu = new JPopupMenu(), styleMenu = new JPopupMenu();

	private JMenuItem nouveauPostit = new JMenuItem("Postit"), nouveauBrainstorming = new JMenuItem("Brainstorming"),
			supprimerBrainstorming = new JMenuItem("Brainstorming"), supprimerPostit = new JMenuItem("Postit"),
			avancerItem = new JMenuItem("Avancer"), avancerPremierPlan = new JMenuItem("Mettre au 1er plan"),
			reculerItem = new JMenuItem("Reculer"), reculerArrierePlan = new JMenuItem("Mettre � l'arri�re plan"),
			couleurPostit = new JMenuItem("Postit"), couleurBrainstorming = new JMenuItem("Brainstorming"),
			alignementGauche = new JMenuItem("Aligner � gauche"), alignementDroite = new JMenuItem("Aligner � droite"),
			alignementVertical = new JMenuItem("Aligner verticalement"),
			alignementHorizontal = new JMenuItem("Aligner horizontalement"),
			alignementHaut = new JMenuItem("Aligner en haut"), alignementBas = new JMenuItem("Aligner en bas"),
			save = new JMenuItem("Enregistrer"), saveAs = new JMenuItem("Enregistrer sous"),
			styleTitre = new JMenuItem("Titre"), styleTexte = new JMenuItem("Texte"),
			couleurTitre = new JMenuItem("Titre"), couleurTexte = new JMenuItem("Texte");

	private ImageIcon iconeNouveau = new ImageIcon("images/new.png"),
			iconeSupprimer = new ImageIcon("images/delete.png"), iconeOuvrir = new ImageIcon("images/open.png"),
			iconeSauvegarder = new ImageIcon("images/save.png"), iconeCouleur = new ImageIcon("images/color.png"),
			iconeAvancer = new ImageIcon("images/avancer.png"), iconeReculer = new ImageIcon("images/reculer.png"),
			iconeBrainstorming = new ImageIcon("images/brainstorming.png"),
			iconePostit = new ImageIcon("images/postit.png"),
			iconePremierPlan = new ImageIcon("images/premierPlan.png"),
			iconeArrierePlan = new ImageIcon("images/arrierePlan.png"),
			iconeAlignement = new ImageIcon("images/alignement.png"),
			iconeAlignementGauche = new ImageIcon("images/alignementGauche.png"),
			iconeAlignementDroite = new ImageIcon("images/alignementDroite.png"),
			iconeAlignementVertical = new ImageIcon("images/alignementVertical.png"),
			iconeAlignementHorizontal = new ImageIcon("images/alignementHorizontal.png"),
			iconeAlignementBas = new ImageIcon("images/alignementBas.png"),
			iconeAlignementHaut = new ImageIcon("images/alignementHaut.png"),
			iconeSaveAs = new ImageIcon("images/saveAs.png"), iconeTexte = new ImageIcon("images/texte.png"),
			iconeTitre = new ImageIcon("images/titre.png"), iconeStyle = new ImageIcon("images/style.png");

	// CONSTRUCTEURS

	public BarreOutils(Fenetre f) {
		fen = f;

		// Ajout des boutons

		nouveau.setIcon(iconeNouveau);
		nouveauBrainstorming.setIcon(iconeBrainstorming);
		nouveauMenu.add(nouveauBrainstorming);
		nouveauPostit.setIcon(iconePostit);
		nouveauMenu.add(nouveauPostit);

		supprimer.setIcon(iconeSupprimer);
		supprimerBrainstorming.setIcon(iconeBrainstorming);
		supprimerMenu.add(supprimerBrainstorming);
		supprimerPostit.setIcon(iconePostit);
		supprimerMenu.add(supprimerPostit);

		ouvrir.setIcon(iconeOuvrir);

		sauvegarder.setIcon(iconeSauvegarder);
		save.setIcon(iconeSauvegarder);
		saveMenu.add(save);
		saveAs.setIcon(iconeSaveAs);
		saveMenu.add(saveAs);

		couleur.setIcon(iconeCouleur);
		couleurBrainstorming.setIcon(iconeBrainstorming);
		couleurMenu.add(couleurBrainstorming);
		couleurPostit.setIcon(iconePostit);
		couleurMenu.add(couleurPostit);
		couleurTitre.setIcon(iconeTitre);
		couleurMenu.add(couleurTitre);
		couleurTexte.setIcon(iconeTexte);
		couleurMenu.add(couleurTexte);

		style.setIcon(iconeStyle);
		styleTitre.setIcon(iconeTitre);
		styleMenu.add(styleTitre);
		styleTexte.setIcon(iconeTexte);
		styleMenu.add(styleTexte);

		avancer.setIcon(iconeAvancer);
		avancerItem.setIcon(iconeAvancer);
		avancerMenu.add(avancerItem);
		avancerPremierPlan.setIcon(iconePremierPlan);
		avancerMenu.add(avancerPremierPlan);

		reculer.setIcon(iconeReculer);
		reculerItem.setIcon(iconeReculer);
		reculerMenu.add(reculerItem);
		reculerArrierePlan.setIcon(iconeArrierePlan);
		reculerMenu.add(reculerArrierePlan);

		alignement.setIcon(iconeAlignement);
		alignementVertical.setIcon(iconeAlignementVertical);
		alignementMenu.add(alignementVertical);
		alignementHorizontal.setIcon(iconeAlignementHorizontal);
		alignementMenu.add(alignementHorizontal);
		alignementGauche.setIcon(iconeAlignementGauche);
		alignementMenu.add(alignementGauche);
		alignementDroite.setIcon(iconeAlignementDroite);
		alignementMenu.add(alignementDroite);
		alignementHaut.setIcon(iconeAlignementHaut);
		alignementMenu.add(alignementHaut);
		alignementBas.setIcon(iconeAlignementBas);
		alignementMenu.add(alignementBas);

		this.add(nouveau);
		this.add(sauvegarder);
		this.add(ouvrir);
		this.addSeparator();
		this.add(supprimer);
		this.addSeparator();
		this.add(couleur);
		this.add(style);
		this.addSeparator();
		this.add(avancer);
		this.add(reculer);
		this.add(alignement);

		// Ajout des interactions avec les boutons de la barre d'outils.

		nouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nouveauMenu.show(nouveau, 0, 44);
			}
		});
		supprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supprimerMenu.show(supprimer, 0, 44);
			}
		});
		ouvrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser dialogue = new JFileChooser(new File("."));
					File fichier = null;
					if (dialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						fichier = dialogue.getSelectedFile();
						FileInputStream in = new FileInputStream(fichier.getPath());
						ObjectInputStream objIn = new ObjectInputStream(in);
						Tableau tab = (Tableau) objIn.readObject();
						fen.getListeTableau()
								.addLast(new Tableau(tab.getTitre(), tab.getCouleur(), tab.getListePostit(), fen));
						int i = fen.getListeTableau().size();
						fen.getOnglet().add(tab.getTitre(), fen.getListeTableau().get(i - 1));
						objIn.close();
					}

				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,
							"Un probl�me li� � la lecture / �criture de fichier est survenu.", "Attention",
							JOptionPane.WARNING_MESSAGE);
				} catch (ClassNotFoundException e2) {
					JOptionPane.showMessageDialog(null, "Un probl�me li� � la conversion d'un objet est survenu.",
							"Attention", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		couleur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				couleurMenu.show(couleur, 0, 44);
			}
		});
		style.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				styleMenu.show(style, 0, 44);
			}
		});
		avancer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				avancerMenu.show(avancer, 0, 44);
			}
		});
		reculer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reculerMenu.show(reculer, 0, 44);
			}
		});
		alignement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alignementMenu.show(alignement, 0, 44);
			}
		});
		sauvegarder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveMenu.show(sauvegarder, 0, 44);
			}
		});
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Ecriture de donn�ees binaires sur fichier
					FileOutputStream out = new FileOutputStream(System.getProperty("user.dir")
							+ fen.getListeTableau().get(fen.getOnglet().getSelectedIndex()).getTitre() + ".bin");
					ObjectOutputStream objOut = new ObjectOutputStream(out);
					objOut.writeObject(fen.getListeTableau().get(fen.getOnglet().getSelectedIndex()));
					objOut.close();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,
							"Un probl�me li� � la lecture / �criture de fichier est survenu.", "Attention",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		saveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser dialogue = new JFileChooser(new File("."));
					File fichier = null;
					if (dialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						fichier = dialogue.getSelectedFile();
					}
					// Ecriture de donn�ees binaires sur fichier
					FileOutputStream out = new FileOutputStream(fichier.getPath() + ".bin");
					ObjectOutputStream objOut = new ObjectOutputStream(out);
					objOut.writeObject(fen.getListeTableau().get(fen.getOnglet().getSelectedIndex()));
					objOut.close();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,
							"Un probl�me li� � la lecture / �criture de fichier est survenu.", "Attention",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		nouveauBrainstorming.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BoiteDialogueNouveauBrainstorming(null, "Propri�t�s du nouveau brainstorming", true, fen);
			}
		});
		supprimerBrainstorming.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!fen.getListeTableau().isEmpty()) {
					new BoiteDialogueConfirmationFermetureBrainstorming(fen);
				} else {
					JOptionPane.showMessageDialog(null, "Aucun brainstorming ouvert.", "Attention",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		nouveauPostit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!fen.getListeTableau().isEmpty()) {
					new BoiteDialogueNouveauPostit(null, "Propri�t�s du nouveau postit", true, fen);
				} else {
					JOptionPane.showMessageDialog(null, "Pas de brainstorming en cours.", "Attention",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		supprimerPostit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!fen.getListeTableau().isEmpty()) {
					if (!fen.getListeTableau().get(fen.getOnglet().getSelectedIndex()).getListePostit().isEmpty()) {
						new BoiteDialogueConfirmationSuppressionPostit(fen);
					} else
						JOptionPane.showMessageDialog(null, "Pas de postit s�lectionn�.", "Attention",
								JOptionPane.WARNING_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null, "Pas de brainstorming en cours.", "Attention",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		couleurBrainstorming.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!fen.getListeTableau().isEmpty()) {
					int i = fen.getOnglet().getSelectedIndex();
					Color c = JColorChooser.showDialog(fen.getListeTableau().get(i),
							"S�lection de la couleur du brainstorming", fen.getListeTableau().get(i).getCouleur());
					if (c != null) {
						fen.getListeTableau().get(i).setCouleur(c);
					}
				} else
					JOptionPane.showMessageDialog(null, "Pas de brainstorming en cours.", "Attention",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		couleurPostit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!fen.getListeTableau().isEmpty()) {
					int i = fen.getOnglet().getSelectedIndex();
					LinkedList<Integer> selectionnes = fen.getListeTableau().get(i)
							.getSelectedPostit(fen.getListeTableau().get(i).getListePostit());
					if (!selectionnes.isEmpty()) {
						ListIterator<Integer> iter = selectionnes.listIterator();
						Color c = JColorChooser.showDialog(fen.getListeTableau().get(i),
								"S�lection de la couleur des postits", null);
						while (iter.hasNext()) {
							fen.getListeTableau().get(i).getListePostit().get(iter.next()).setCouleur(c);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Pas de postit s�lectionn�.", "Attention",
								JOptionPane.WARNING_MESSAGE);
					}
					fen.getListeTableau().get(i).repaint();
				} else
					JOptionPane.showMessageDialog(null, "Pas de brainstorming en cours.", "Attention",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		avancerPremierPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!fen.getListeTableau().isEmpty()) {
					int i = fen.getOnglet().getSelectedIndex();
					LinkedList<Postit> listePostit = fen.getListeTableau().get(i).getListePostit();
					LinkedList<Integer> selectionnes = fen.getListeTableau().get(i).getSelectedPostit(listePostit);
					if (!selectionnes.isEmpty()) {
						ListIterator<Integer> iter = selectionnes.listIterator();
						while (iter.hasNext()) {
							listePostit.addLast(listePostit.get(iter.next()));
						}
						for (int j = selectionnes.size() - 1; j > -1; j--) {
							listePostit.remove((int) (selectionnes.get(j)));
						}
					} else {
						JOptionPane.showMessageDialog(null, "Pas de postit s�lectionn�.", "Attention",
								JOptionPane.WARNING_MESSAGE);
					}
					fen.getListeTableau().get(i).repaint();
				} else
					JOptionPane.showMessageDialog(null, "Pas de brainstorming en cours.", "Attention",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		avancerItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!fen.getListeTableau().isEmpty()) {
					int i = fen.getOnglet().getSelectedIndex();
					LinkedList<Postit> listePostit = fen.getListeTableau().get(i).getListePostit();
					LinkedList<Integer> selectionnes = fen.getListeTableau().get(i).getSelectedPostit(listePostit);
					if (!selectionnes.isEmpty()) {
						for (int j = selectionnes.size() - 1; j > -1; j--) {
							int k = (int) selectionnes.get(j);
							if (k != listePostit.size() - 1) {
								Postit tmp = listePostit.get(k);
								listePostit.set(k, listePostit.get(k + 1));
								listePostit.set(k + 1, tmp);
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Pas de postit s�lectionn�.", "Attention",
								JOptionPane.WARNING_MESSAGE);
					}
					fen.getListeTableau().get(i).repaint();
				} else
					JOptionPane.showMessageDialog(null, "Pas de brainstorming en cours.", "Attention",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		reculerArrierePlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!fen.getListeTableau().isEmpty()) {
					int i = fen.getOnglet().getSelectedIndex();
					LinkedList<Postit> listePostit = fen.getListeTableau().get(i).getListePostit();
					LinkedList<Integer> selectionnes = fen.getListeTableau().get(i).getSelectedPostit(listePostit);
					if (!selectionnes.isEmpty()) {
						LinkedList<Postit> mettrealarriere = new LinkedList<Postit>();
						for (int j = selectionnes.size() - 1; j > -1; j--) {
							mettrealarriere.addFirst(listePostit.get((int) selectionnes.get(j)));
							listePostit.remove((int) selectionnes.get(j));
						}
						listePostit.addAll(0, mettrealarriere);
					} else {
						JOptionPane.showMessageDialog(null, "Pas de postit s�lectionn�.", "Attention",
								JOptionPane.WARNING_MESSAGE);
					}
					fen.getListeTableau().get(i).repaint();
				} else
					JOptionPane.showMessageDialog(null, "Pas de brainstorming en cours.", "Attention",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		reculerItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!fen.getListeTableau().isEmpty()) {
					int i = fen.getOnglet().getSelectedIndex();
					LinkedList<Postit> listePostit = fen.getListeTableau().get(i).getListePostit();
					LinkedList<Integer> selectionnes = fen.getListeTableau().get(i).getSelectedPostit(listePostit);
					if (!selectionnes.isEmpty()) {
						for (int j = selectionnes.size() - 1; j > -1; j--) {
							int k = (int) selectionnes.get(j);
							if (k != 0) {
								Postit tmp = listePostit.get(k);
								listePostit.set(k, listePostit.get(k - 1));
								listePostit.set(k - 1, tmp);
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Pas de postit s�lectionn�.", "Attention",
								JOptionPane.WARNING_MESSAGE);
					}
					fen.getListeTableau().get(i).repaint();
				} else
					JOptionPane.showMessageDialog(null, "Pas de brainstorming en cours.", "Attention",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		alignementVertical.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!fen.getListeTableau().isEmpty()) {
					int i = fen.getOnglet().getSelectedIndex();
					LinkedList<Postit> listePostit = fen.getListeTableau().get(i).getListePostit();
					LinkedList<Integer> selectionnes = fen.getListeTableau().get(i).getSelectedPostit(listePostit);
					if (!selectionnes.isEmpty()) {
						int xmin = fen.getWidth();
						int nmin = 0;
						for (int j = selectionnes.size() - 1; j > -1; j--) {
							int k = (int) selectionnes.get(j);
							if (listePostit.get(k).getX() < xmin) {
								xmin = listePostit.get(k).getX();
								nmin = k;
							}
						}
						int xVertical = xmin + listePostit.get(nmin).getLargeur() / 2;
						for (int l = selectionnes.size() - 1; l > -1; l--) {
							if (!(l == nmin)) {
								int k = (int) selectionnes.get(l);
								listePostit.get(k).setX(xVertical - listePostit.get(k).getLargeur() / 2);
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Pas de postit s�lectionn�.", "Attention",
								JOptionPane.WARNING_MESSAGE);
					}
					fen.getListeTableau().get(i).repaint();
				} else
					JOptionPane.showMessageDialog(null, "Pas de brainstorming en cours.", "Attention",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		alignementHorizontal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!fen.getListeTableau().isEmpty()) {
					int i = fen.getOnglet().getSelectedIndex();
					LinkedList<Postit> listePostit = fen.getListeTableau().get(i).getListePostit();
					LinkedList<Integer> selectionnes = fen.getListeTableau().get(i).getSelectedPostit(listePostit);
					if (!selectionnes.isEmpty()) {
						int ymin = fen.getHeight();
						int nmin = 0;
						for (int j = selectionnes.size() - 1; j > -1; j--) {
							int k = (int) selectionnes.get(j);
							if (listePostit.get(k).getY() < ymin) {
								ymin = listePostit.get(k).getY();
								nmin = k;
							}
						}
						int yVertical = ymin + listePostit.get(nmin).getHauteur() / 2;
						for (int l = selectionnes.size() - 1; l > -1; l--) {
							if (!(l == nmin)) {
								int k = (int) selectionnes.get(l);
								listePostit.get(k).setY(yVertical - listePostit.get(k).getHauteur() / 2);
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Pas de postit s�lectionn�.", "Attention",
								JOptionPane.WARNING_MESSAGE);
					}
					fen.getListeTableau().get(i).repaint();
				} else
					JOptionPane.showMessageDialog(null, "Pas de brainstorming en cours.", "Attention",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		alignementGauche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!fen.getListeTableau().isEmpty()) {
					int i = fen.getOnglet().getSelectedIndex();
					LinkedList<Postit> listePostit = fen.getListeTableau().get(i).getListePostit();
					LinkedList<Integer> selectionnes = fen.getListeTableau().get(i).getSelectedPostit(listePostit);
					if (!selectionnes.isEmpty()) {
						int xmin = fen.getWidth();
						for (int j = selectionnes.size() - 1; j > -1; j--) {
							int k = (int) selectionnes.get(j);
							if (listePostit.get(k).getX() < xmin) {
								xmin = listePostit.get(k).getX();
							}
						}
						for (int l = selectionnes.size() - 1; l > -1; l--) {
							int k = (int) selectionnes.get(l);
							listePostit.get(k).setX(xmin);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Pas de postit s�lectionn�.", "Attention",
								JOptionPane.WARNING_MESSAGE);
					}
					fen.getListeTableau().get(i).repaint();
				} else
					JOptionPane.showMessageDialog(null, "Pas de brainstorming en cours.", "Attention",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		alignementDroite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!fen.getListeTableau().isEmpty()) {
					int i = fen.getOnglet().getSelectedIndex();
					LinkedList<Postit> listePostit = fen.getListeTableau().get(i).getListePostit();
					LinkedList<Integer> selectionnes = fen.getListeTableau().get(i).getSelectedPostit(listePostit);
					if (!selectionnes.isEmpty()) {
						int xmax = 0;
						int nmin = 0;
						for (int j = selectionnes.size() - 1; j > -1; j--) {
							int k = (int) selectionnes.get(j);
							if (listePostit.get(k).getX() + listePostit.get(k).getLargeur() > xmax) {
								xmax = listePostit.get(k).getX() + listePostit.get(k).getLargeur();
								nmin = k;
							}
						}
						for (int l = selectionnes.size() - 1; l > -1; l--) {
							if (!(l == nmin)) {
								int k = (int) selectionnes.get(l);
								listePostit.get(k).setX(xmax - listePostit.get(k).getLargeur());
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Pas de postit s�lectionn�.", "Attention",
								JOptionPane.WARNING_MESSAGE);
					}
					fen.getListeTableau().get(i).repaint();
				} else
					JOptionPane.showMessageDialog(null, "Pas de brainstorming en cours.", "Attention",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		alignementHaut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!fen.getListeTableau().isEmpty()) {
					int i = fen.getOnglet().getSelectedIndex();
					LinkedList<Postit> listePostit = fen.getListeTableau().get(i).getListePostit();
					LinkedList<Integer> selectionnes = fen.getListeTableau().get(i).getSelectedPostit(listePostit);
					if (!selectionnes.isEmpty()) {
						int ymin = fen.getHeight();
						for (int j = selectionnes.size() - 1; j > -1; j--) {
							int k = (int) selectionnes.get(j);
							if (listePostit.get(k).getY() < ymin) {
								ymin = listePostit.get(k).getY();
							}
						}
						for (int l = selectionnes.size() - 1; l > -1; l--) {
							int k = (int) selectionnes.get(l);
							listePostit.get(k).setY(ymin);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Pas de postit s�lectionn�.", "Attention",
								JOptionPane.WARNING_MESSAGE);
					}
					fen.getListeTableau().get(i).repaint();
				} else
					JOptionPane.showMessageDialog(null, "Pas de brainstorming en cours.", "Attention",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		alignementBas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!fen.getListeTableau().isEmpty()) {
					int i = fen.getOnglet().getSelectedIndex();
					LinkedList<Postit> listePostit = fen.getListeTableau().get(i).getListePostit();
					LinkedList<Integer> selectionnes = fen.getListeTableau().get(i).getSelectedPostit(listePostit);
					if (!selectionnes.isEmpty()) {
						int ymax = 0;
						int nmin = 0;
						for (int j = selectionnes.size() - 1; j > -1; j--) {
							int k = (int) selectionnes.get(j);
							if (listePostit.get(k).getY() + listePostit.get(k).getHauteur() > ymax) {
								ymax = listePostit.get(k).getY() + listePostit.get(k).getHauteur();
								nmin = k;
							}
						}
						for (int l = selectionnes.size() - 1; l > -1; l--) {
							if (!(l == nmin)) {
								int k = (int) selectionnes.get(l);
								listePostit.get(k).setY(ymax - listePostit.get(k).getHauteur());
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Pas de postit s�lectionn�.", "Attention",
								JOptionPane.WARNING_MESSAGE);
					}
					fen.getListeTableau().get(i).repaint();
				} else
					JOptionPane.showMessageDialog(null, "Pas de brainstorming en cours.", "Attention",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		styleTitre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!fen.getListeTableau().isEmpty()) {
					int i = fen.getOnglet().getSelectedIndex();
					LinkedList<Integer> selectionnes = fen.getListeTableau().get(i)
							.getSelectedPostit(fen.getListeTableau().get(i).getListePostit());
					if (!selectionnes.isEmpty()) {
						ListIterator<Integer> iter = selectionnes.listIterator();
						BoiteDialogueChoixFont choixStyleTitre = new BoiteDialogueChoixFont(null, "Style du titre",
								true);
						while (iter.hasNext()) {
							fen.getListeTableau().get(i).getListePostit().get(iter.next())
									.setFontTitre(choixStyleTitre.getFontChoisie());
						}
					} else {
						JOptionPane.showMessageDialog(null, "Pas de postit s�lectionn�.", "Attention",
								JOptionPane.WARNING_MESSAGE);
					}
					fen.getListeTableau().get(i).repaint();
				} else
					JOptionPane.showMessageDialog(null, "Pas de brainstorming en cours.", "Attention",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		styleTexte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!fen.getListeTableau().isEmpty()) {
					int i = fen.getOnglet().getSelectedIndex();
					LinkedList<Integer> selectionnes = fen.getListeTableau().get(i)
							.getSelectedPostit(fen.getListeTableau().get(i).getListePostit());
					if (!selectionnes.isEmpty()) {
						ListIterator<Integer> iter = selectionnes.listIterator();
						BoiteDialogueChoixFont choixStyleTexte = new BoiteDialogueChoixFont(null, "Style du texte",
								true);
						while (iter.hasNext()) {
							fen.getListeTableau().get(i).getListePostit().get(iter.next())
									.setFontTexte(choixStyleTexte.getFontChoisie());
						}
					} else {
						JOptionPane.showMessageDialog(null, "Pas de postit s�lectionn�.", "Attention",
								JOptionPane.WARNING_MESSAGE);
					}
					fen.getListeTableau().get(i).repaint();
				} else
					JOptionPane.showMessageDialog(null, "Pas de brainstorming en cours.", "Attention",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		couleurTitre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!fen.getListeTableau().isEmpty()) {
					int i = fen.getOnglet().getSelectedIndex();
					LinkedList<Integer> selectionnes = fen.getListeTableau().get(i)
							.getSelectedPostit(fen.getListeTableau().get(i).getListePostit());
					if (!selectionnes.isEmpty()) {
						ListIterator<Integer> iter = selectionnes.listIterator();
						Color c = JColorChooser.showDialog(fen.getListeTableau().get(i),
								"S�lection de la couleur du titre des postits", null);
						while (iter.hasNext()) {
							fen.getListeTableau().get(i).getListePostit().get(iter.next()).setColorTitre(c);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Pas de postit s�lectionn�.", "Attention",
								JOptionPane.WARNING_MESSAGE);
					}
					fen.getListeTableau().get(i).repaint();
				} else
					JOptionPane.showMessageDialog(null, "Pas de brainstorming en cours.", "Attention",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		couleurTexte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!fen.getListeTableau().isEmpty()) {
					int i = fen.getOnglet().getSelectedIndex();
					LinkedList<Integer> selectionnes = fen.getListeTableau().get(i)
							.getSelectedPostit(fen.getListeTableau().get(i).getListePostit());
					if (!selectionnes.isEmpty()) {
						ListIterator<Integer> iter = selectionnes.listIterator();
						Color c = JColorChooser.showDialog(fen.getListeTableau().get(i),
								"S�lection de la couleur du texte des postits", null);
						while (iter.hasNext()) {
							fen.getListeTableau().get(i).getListePostit().get(iter.next()).setColorTexte(c);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Pas de postit s�lectionn�.", "Attention",
								JOptionPane.WARNING_MESSAGE);
					}
					fen.getListeTableau().get(i).repaint();
				} else
					JOptionPane.showMessageDialog(null, "Pas de brainstorming en cours.", "Attention",
							JOptionPane.WARNING_MESSAGE);
			}
		});
	}
}
