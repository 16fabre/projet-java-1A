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

public class BarreMenu extends JMenuBar {

	// ATTRIBUS

	private Fenetre fen;
	private JMenu fichier = new JMenu("Fichier");
	private JMenuItem ouvrir = new JMenuItem("Ouvrir");
	private JMenu fermer = new JMenu("Supprimer");
	private JMenuItem supBrainstorming = new JMenuItem("Brainstorming");
	private JMenuItem supPostit = new JMenuItem("Postits");
	private JMenuItem sauvegarder = new JMenuItem("Enregistrer");
	private JMenuItem saveAs = new JMenuItem("Enregistrer sous");
	private JMenuItem quitter = new JMenuItem("Quitter");
	private JMenu nouveau = new JMenu("Nouveau");
	private JMenuItem postit = new JMenuItem("Postit");
	private JMenuItem onglet = new JMenuItem("Brainstorming");
	private JMenu edition = new JMenu("Edition"), outils = new JMenu("Outils");
	private JMenu aligner = new JMenu("Aligner"), position = new JMenu("Position"), style = new JMenu("Styles"),
			couleur = new JMenu("Couleurs");
	private JMenuItem couleurBrainstorming = new JMenuItem("Brainstorming"), couleurPostit = new JMenuItem("Postit"),
			couleurTexte = new JMenuItem("Texte"), couleurTitre = new JMenuItem("Titre"),
			styleTitre = new JMenuItem("Titre"), styleTexte = new JMenuItem("Texte"),
			avancer = new JMenuItem("Avancer"), avancerPremierPlan = new JMenuItem("Mettre au 1er plan"),
			reculer = new JMenuItem("Reculer"), reculerArrierePlan = new JMenuItem("Mettre à l'arrière plan"),
			alignementGauche = new JMenuItem("Aligner à gauche"), alignementDroite = new JMenuItem("Aligner à droite"),
			alignementVertical = new JMenuItem("Aligner verticalement"),
			alignementHorizontal = new JMenuItem("Aligner horizontalement"),
			alignementHaut = new JMenuItem("Aligner en haut"), alignementBas = new JMenuItem("Aligner en bas");

	// CONSTRUCTEURS

	public BarreMenu(Fenetre f) {

		fen = f;

		ButtonGroup bg = new ButtonGroup();
		bg.add(postit);
		bg.add(onglet);
		nouveau.add(postit);
		nouveau.add(onglet);
		fichier.add(nouveau);
		fichier.add(ouvrir);
		ButtonGroup bg2 = new ButtonGroup();
		bg2.add(supBrainstorming);
		bg2.add(supPostit);
		fermer.add(supBrainstorming);
		fermer.add(supPostit);
		fichier.add(fermer);
		fichier.add(sauvegarder);
		fichier.add(saveAs);
		fichier.add(quitter);
		this.add(fichier);

		ButtonGroup bg3 = new ButtonGroup();
		bg3.add(couleurBrainstorming);
		bg3.add(couleurPostit);
		bg3.add(couleurTitre);
		bg3.add(couleurTexte);
		couleur.add(couleurBrainstorming);
		couleur.add(couleurPostit);
		couleur.add(couleurTitre);
		couleur.add(couleurTexte);
		edition.add(couleur);

		ButtonGroup bg4 = new ButtonGroup();
		bg4.add(styleTitre);
		bg4.add(styleTexte);
		style.add(styleTitre);
		style.add(styleTexte);
		edition.add(style);
		
		this.add(edition);

		ButtonGroup bg5 = new ButtonGroup();
		bg5.add(avancer);
		bg5.add(avancerPremierPlan);
		bg5.add(reculer);
		bg5.add(reculerArrierePlan);
		position.add(avancer);
		position.add(avancerPremierPlan);
		position.add(reculer);
		position.add(reculerArrierePlan);
		outils.add(position);

		ButtonGroup bg6 = new ButtonGroup();
		bg6.add(alignementGauche);
		bg6.add(alignementDroite);
		bg6.add(alignementHaut);
		bg6.add(alignementBas);
		bg6.add(alignementVertical);
		bg6.add(alignementHorizontal);
		aligner.add(alignementGauche);
		aligner.add(alignementDroite);
		aligner.add(alignementHaut);
		aligner.add(alignementBas);
		aligner.add(alignementVertical);
		aligner.add(alignementHorizontal);
		outils.add(aligner);
		
		this.add(outils);

		// Ajout des intéractions avec les boutons

		onglet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BoiteDialogueNouveauBrainstorming(null, "Propriétés du nouveau brainstorming", true, fen);
			}
		});
		postit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!fen.getListeTableau().isEmpty()) {
					new BoiteDialogueNouveauPostit(null, "Propriétés du nouveau postit", true, fen);
				} else {
					JOptionPane.showMessageDialog(null, "Pas de brainstorming en cours", "Attention",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		supBrainstorming.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BoiteDialogueConfirmationFermetureBrainstorming(fen);
			}
		});
		supPostit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!fen.getListeTableau().isEmpty()) {
					if (!fen.getListeTableau().get(fen.getOnglet().getSelectedIndex()).getListePostit().isEmpty()) {
						new BoiteDialogueConfirmationSuppressionPostit(fen);
					} else
						JOptionPane.showMessageDialog(null, "Pas de postit sélectionné.", "Attention",
								JOptionPane.WARNING_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null, "Pas de brainstorming en cours.", "Attention",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
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
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(null,
							"Un problème lié à la lecture / écriture de fichier est survenu.", "Attention",
							JOptionPane.WARNING_MESSAGE);
				} catch (ClassNotFoundException e2) {
					JOptionPane.showMessageDialog(null, "Un problème lié à la conversion d'un objet est survenu.",
							"Attention", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		sauvegarder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Ecriture de donnéees binaires sur fichier
					FileOutputStream out = new FileOutputStream(System.getProperty("user.dir")
							+ fen.getListeTableau().get(fen.getOnglet().getSelectedIndex()).getTitre() + ".bin");
					ObjectOutputStream objOut = new ObjectOutputStream(out);
					objOut.writeObject(fen.getListeTableau().get(fen.getOnglet().getSelectedIndex()));
					objOut.close();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,
							"Un problème lié à la lecture / écriture de fichier est survenu.", "Attention",
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
					// Ecriture de donnéees binaires sur fichier
					FileOutputStream out = new FileOutputStream(fichier.getPath() + ".bin");
					ObjectOutputStream objOut = new ObjectOutputStream(out);
					objOut.writeObject(fen.getListeTableau().get(fen.getOnglet().getSelectedIndex()));
					objOut.close();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,
							"Un problème lié à la lecture / écriture de fichier est survenu.", "Attention",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		couleurBrainstorming.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!fen.getListeTableau().isEmpty()) {
					int i = fen.getOnglet().getSelectedIndex();
					Color c = JColorChooser.showDialog(fen.getListeTableau().get(i),
							"Sélection de la couleur du brainstorming", fen.getListeTableau().get(i).getCouleur());
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
								"Sélection de la couleur des postits", null);
						while (iter.hasNext()) {
							fen.getListeTableau().get(i).getListePostit().get(iter.next()).setCouleur(c);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Pas de postit sélectionné.", "Attention",
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
						JOptionPane.showMessageDialog(null, "Pas de postit sélectionné.", "Attention",
								JOptionPane.WARNING_MESSAGE);
					}
					fen.getListeTableau().get(i).repaint();
				} else
					JOptionPane.showMessageDialog(null, "Pas de brainstorming en cours.", "Attention",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		avancer.addActionListener(new ActionListener() {
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
						JOptionPane.showMessageDialog(null, "Pas de postit sélectionné.", "Attention",
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
						JOptionPane.showMessageDialog(null, "Pas de postit sélectionné.", "Attention",
								JOptionPane.WARNING_MESSAGE);
					}
					fen.getListeTableau().get(i).repaint();
				} else
					JOptionPane.showMessageDialog(null, "Pas de brainstorming en cours.", "Attention",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		reculer.addActionListener(new ActionListener() {
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
						JOptionPane.showMessageDialog(null, "Pas de postit sélectionné.", "Attention",
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
						JOptionPane.showMessageDialog(null, "Pas de postit sélectionné.", "Attention",
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
						JOptionPane.showMessageDialog(null, "Pas de postit sélectionné.", "Attention",
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
						JOptionPane.showMessageDialog(null, "Pas de postit sélectionné.", "Attention",
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
						JOptionPane.showMessageDialog(null, "Pas de postit sélectionné.", "Attention",
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
						JOptionPane.showMessageDialog(null, "Pas de postit sélectionné.", "Attention",
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
						JOptionPane.showMessageDialog(null, "Pas de postit sélectionné.", "Attention",
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
						JOptionPane.showMessageDialog(null, "Pas de postit sélectionné.", "Attention",
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
						JOptionPane.showMessageDialog(null, "Pas de postit sélectionné.", "Attention",
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
								"Sélection de la couleur du titre des postits", null);
						while (iter.hasNext()) {
							fen.getListeTableau().get(i).getListePostit().get(iter.next()).setColorTitre(c);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Pas de postit sélectionné.", "Attention",
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
								"Sélection de la couleur du texte des postits", null);
						while (iter.hasNext()) {
							fen.getListeTableau().get(i).getListePostit().get(iter.next()).setColorTexte(c);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Pas de postit sélectionné.", "Attention",
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
