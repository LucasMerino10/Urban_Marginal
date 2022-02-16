package controleur;

public interface Global {
	/**
	 * Charact�re de s�paration pour les cha�nes envoy�es
	 */
	String STRINGSEPARE = "~";
	/**
	 * N� de port
	 */
	int PORT = 6666;
	/**
	 * Message pseudo
	 */
	String PSEUDO = "pseudo";
	/**
	 * hauteur de la zone de jeu de l'ar�ne
	 */
	int HAUTEURARENE = 600;
	/**
	 * largeur de la zone de heu de l'ar�ne
	 */
	int LARGEURARENE = 800;
	/**
	 * hauteur d'un mur
	 */
	int HAUTEURMUR = 35;
	/**
	 * largeur d'un mur
	 */
	int LARGEURMUR = 34;
	/**
	 * Chemin dossier mur
	 */
	String CHEMINMUR = "murs/";
	/**
	 * Image mur
	 */
	String IMAGEMUR = CHEMINMUR + "mur.gif";
	/**
	 * ordre pour ajouter un mur dans l'ar�ne du serveur
	 */
	String AJOUTMUR = "ajout mur";
	/**
	 * ordre pour ajouter un panel de murs dans l'ar�ne du client
	 */
	String AJOUTPANELMURS = "ajout panel murs";
	/**
	 * vie de d�part pour tous les joueurs
	 */
	int MAXVIE = 10 ;
	/**
	 * gain de points de vie lors d'une attaque
	 */
	int GAIN = 1 ; 
	/**
	 * perte de points de vie lors d'une attaque
	 */
	int PERTE = 2 ;
	/**
	 * position x min pour le placement du mur
	 */
	int XMIN = 10;
	/**
	 * position x max pour le placement du mur
	 */
	int XMAX = 756;
	/**
	 * position y min pour le placement du mur
	 */
	int YMIN = 11;
	/**
	 * position y max pour le placement du mur
	 */
	int YMAX = 554;

}
