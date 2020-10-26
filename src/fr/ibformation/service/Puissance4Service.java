package fr.ibformation.service;

import java.awt.Color;

import fr.ibformation.bo.Grille;
import fr.ibformation.dao.GrilleDAO;
import fr.ibformation.dao.GrilleDAOImpl;
import fr.ibformation.ihm.PionButton;

public class Puissance4Service {
	private int joueurEnCours = 1;
	private String joueur1;
	private String joueur2;
	private final int NBPIONSGAGNANTS = 4;
	private int NBCOLONNE = 7;
	private int NBLIGNE = 6;
	private Boolean victoire = false;
	private GrilleDAO grilleDao = new GrilleDAOImpl();

	public Puissance4Service() {
		// TODO Auto-generated constructor stub
	}

	public int PlacerPion(PionButton[][] cases, int colonne, int ligne) {
		int pionPlace = -1;
		for (int i = 0; i < 6; i++) {
			if (cases[colonne][i].getForeground() == Color.WHITE && pionPlace == -1) {
				if (joueurEnCours == 1) {
					cases[colonne][i].setForeground(Color.YELLOW);

				} else {
					cases[colonne][i].setForeground(Color.RED);
				}

				pionPlace = i;
			}
		}
		/*
		 * if (pionPlace) { joueur = (joueur % 2) + 1; }
		 */
		return pionPlace;

	}

	/**
	 * @return the joueur
	 */
	public int getJoueur() {
		return joueurEnCours;
	}

	/**
	 * @param joueur the joueur to set
	 */
	public void setJoueur(int joueur) {
		this.joueurEnCours = joueur;
	}

	/**
	 * @return the joueur1
	 */
	public String getJoueur1() {
		return joueur1;
	}

	/**
	 * String = nom du joueur 1.
	 * 
	 * @param joueur1 the joueur1 to set
	 */
	public void setJoueur1(String joueur1) {
		this.joueur1 = joueur1;
	}

	/**
	 * String = Nom du joueur 2
	 * 
	 * @return the joueur2
	 */
	public String getJoueur2() {
		return joueur2;
	}

	/**
	 * @param joueur2 the joueur2 to set
	 */
	public void setJoueur2(String joueur2) {
		this.joueur2 = joueur2;
	}

	/**
	 * @return the victoire
	 */
	public Boolean getVictoire() {
		return victoire;
	}

	/**
	 * @param victoire the victoire to set
	 */
	public void setVictoire(Boolean victoire) {
		this.victoire = victoire;
	}

	/**
	 * @return the nBPIONSGAGNANTS
	 */
	public int getNBPIONSGAGNANTS() {
		return NBPIONSGAGNANTS;
	}

	/**
	 * @return the nBCOLONNE
	 */
	public int getNBCOLONNE() {
		return NBCOLONNE;
	}

	/**
	 * @param nBCOLONNE the nBCOLONNE to set
	 */
	public void setNBCOLONNE(int nBCOLONNE) {
		NBCOLONNE = nBCOLONNE;
	}

	/**
	 * @return the nBLIGNE
	 */
	public int getNBLIGNE() {
		return NBLIGNE;
	}

	/**
	 * @param nBLIGNE the nBLIGNE to set
	 */
	public void setNBLIGNE(int nBLIGNE) {
		NBLIGNE = nBLIGNE;
	}

	/**
	 * @return the joueurEnCours
	 */
	public int getJoueurEnCours() {
		return joueurEnCours;
	}

	/**
	 * Prend le dernier joueur ayant jou�, ainsi que les coordonn�es du dernier
	 * coup. V�rifie si ce coup d�clenche une victoire.
	 * 
	 * @param cases
	 * @param colonne
	 * @param ligne
	 * @return 0 si pas de victoire. 1 si joueur 1 gagne, 2 si joueur 2 gagne.
	 */
	public int VerifierVictoire(PionButton[][] cases, int colonne, int ligne) {
		String streak = "";
		for (int i = 0; i < this.NBPIONSGAGNANTS; i++) {
			streak += String.valueOf(joueurEnCours);
		}
		System.out.println("verif victoire, sur case:"+colonne+"/"+ligne);
		System.out.println(streak);
		// V�rifier si la ligne contient quatre pions cons�cutifs du joueur
		System.out.println(Ligne(cases, ligne));
		if (Ligne(cases, ligne).indexOf(streak) != -1) {
			victoire = true;
			return this.joueurEnCours;
		}
		// V�rifier si la colonne contient quatre pions cons�cutifs du joueur
		if (Colonne(cases, colonne).indexOf(streak) != -1) {
			victoire = true;
			return this.joueurEnCours;
		}
		// V�rifier si la diagonale slash (/) contient quatre pions cons�cutifs du
		// joueur
		if (DiagonaleSlash(cases, colonne, ligne).indexOf(streak) != -1) {
			victoire = true;
			return this.joueurEnCours;
		}
		// V�rifier si la diagonale antislash (/) contient quatre pions cons�cutifs du
		// joueur
		if (DiagonaleAntiSlash(cases, colonne, ligne).indexOf(streak) != -1) {
			victoire = true;
			return this.joueurEnCours;
		}
	
		return 0;
	}

	/**
	 * DiagonaleAntiSlash(PionPanel[][] cases, int colonne, int ligne) permet de
	 * transformer en String la diagonale \ contenant la case s�lectionn�e. Elle
	 * renvoie un string qui est une suite de chiffre: 0 pour une case vide, 1 pour
	 * un pion jaune, 2 pour un pion rouge.
	 * 
	 * @param cases
	 * @param colonne
	 * @param ligne
	 * @return
	 */
	private String DiagonaleAntiSlash(PionButton[][] cases, int colonne, int ligne) {

		String diagonaleAntiSlash = "";
		int min = Math.min(this.NBCOLONNE - (colonne + 1), ligne);
		colonne += min;
		ligne -= min;
		while (colonne >= 0 && ligne < this.NBLIGNE) {
			diagonaleAntiSlash += cases[colonne][ligne].caseToString();
			colonne--;
			ligne++;
		}

		return diagonaleAntiSlash;

	}

	/**
	 * DiagonaleSlash(PionPanel[][] cases, int colonne, int ligne) permet de
	 * transformer en String la diagonale / contenant la case s�lectionn�e. Elle
	 * renvoie un string qui est une suite de chiffre: 0 pour une case vide, 1 pour
	 * un pion jaune, 2 pour un pion rouge.
	 * 
	 * @param cases
	 * @param colonne
	 * @param ligne
	 * @return
	 */
	private String DiagonaleSlash(PionButton[][] cases, int colonne, int ligne) {
		String diagonaleSlash = "";
		int min = Math.min(colonne, ligne);
		colonne -= min;
		ligne -= min;
		while (colonne < this.NBCOLONNE && ligne < this.NBLIGNE) {
			diagonaleSlash += cases[colonne][ligne].caseToString();
			colonne++;
			ligne++;
		}
		return diagonaleSlash;
	}

	/**
	 * Colonne(PionPanel[][] cases, int colonne) permet de transformer la colonne
	 * s�lectionn�e en string Elle renvoie un string qui est une suite de chiffre: 0
	 * pour une case vide, 1 pour un pion jaune, 2 pour un pion rouge.
	 * 
	 * @param cases
	 * @param colonne
	 * @return
	 */
	private String Colonne(PionButton[][] cases, int colonne) {
		String colonneString = "";
		for (int i = 0; i < this.NBLIGNE; i++) {
			colonneString += cases[colonne][i].caseToString();
		}
		return colonneString;

	}

	/**
	 * Colonne(PionPanel[][] cases, int ligne) permet de transformer la ligne
	 * s�lectionn�e en string Elle renvoie un string qui est une suite de chiffre: 0
	 * pour une case vide, 1 pour un pion jaune, 2 pour un pion rouge.
	 * 
	 * @param cases
	 * @param ligne
	 * @return
	 */
	private String Ligne(PionButton[][] cases, int ligne) {
		String ligneString = "";
		for (int i = 0; i < this.NBCOLONNE; i++) {
			ligneString += cases[i][ligne].caseToString();
		}
		return ligneString;
	}

	/**
	 * M�thode pour enregistrer en BDD. Transforme la grille de jeu en tableau de
	 * int. Chaque case du tableau repr�sente une case du jeu, avec 0 si la case est
	 * vide, 1 s'il y a un pion jaune, 2 s'il y a une pion rouge. Ensuite, envoit ce
	 * tableau � GrilleDao pour g�rer l'enregistrement en BDD
	 * 
	 * @param cases
	 */
	public void createGrille(PionButton[][] cases) {
		System.out.println("saving....");
		int[] grille = new int[this.NBCOLONNE * this.NBLIGNE];
		int index = 0;
		for (int i = 0; i < this.NBCOLONNE; i++) {
			for (int j = 0; j < this.NBLIGNE; j++) {
				grille[index] = Integer.parseInt(cases[i][j].caseToString());
				System.out.println(grille[index]);
				index++;
			}
		}
		grilleDao.createGrille(grille);
	}

	/**
	 * transforme le tableau de int r�cup�r� de BDD en tableau de JButton � 2
	 * dimensions Chaque case du tableau correspond � un JButton. Si la valeur est
	 * 0, le bouton est blanc, si la valeur est 1 le bouton est jaune, si la valeur
	 * est 2 le bouton est rouge.
	 * 
	 * @param grille
	 * @return
	 */
	public PionButton[][] getGrille() {
		System.out.println("get grille!");
		int[] grille=grilleDao.getGrille(this.NBCOLONNE,this.NBLIGNE);
		PionButton[][] cases = new PionButton[this.NBCOLONNE][this.NBLIGNE];
		int index = 0;
		for (int i=0;i<grille.length;i++) {
			System.out.println(grille[i]);
		}
		for (int i = 0; i < this.NBCOLONNE; i++) {
			for (int j = 0; j < this.NBLIGNE; j++) {
				cases[i][j] = new PionButton(i, j);
				if (grille[index] == 1) {
					cases[i][j].setForeground(Color.YELLOW);
				} else if (grille[index] == 2) {
					System.out.println("red");
					cases[i][j].setForeground(Color.RED);
				}
				index++;
			}
		}
		return cases;
	}
	

}
