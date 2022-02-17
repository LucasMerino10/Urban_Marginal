package controleur;

import java.awt.event.KeyEvent;

public interface Global {
	/**
	 * Charactère de séparation pour les chaînes envoyées
	 */
	String STRINGSEPARE = "~";
	/**
	 * N° de port
	 */
	int PORT = 6666;
	/**
	 * Message pseudo
	 */
	String PSEUDO = "pseudo";
	/**
	 * hauteur de la zone de jeu de l'arène
	 */
	int HAUTEURARENE = 600;
	/**
	 * Permet de définir le type d'instance d'arène
	 */
	String SERVEUR = "serveur";
	/**
	 * Permet de définir le type d'instance d'arène
	 */
	String CLIENT = "client";
	/**
	 * largeur de la zone de heu de l'arène
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
	 * Hauteur d'un personnage
	 */
	int HAUTEURPERSO = (44+41+42)/3;
	/**
	 * Largeur d'un personnage
	 */
	int LARGEURPERSO = (39+34+31)/3;
	/**
	 * Chemin dossier mur
	 */
	String CHEMINMUR = "murs/";
	/**
	 * Image mur
	 */
	String IMAGEMUR = CHEMINMUR + "mur.gif";
	/**
	 * Chemin dossier personnages
	 */
	String CHEMINPERSO = "personnages/";
	/**
	 * ordre pour ajouter une phrase au chat
	 */
	String CHAT = "tchat";
	/**
	 * ordre pour ajouter un mur dans l'arène du serveur
	 */
	String AJOUTMUR = "ajout mur";
	/**
	 * ordre pour ajouter un panel de murs dans l'arène du client
	 */
	String AJOUTPANELMURS = "ajout panel murs";
	/**
	 * ordre pour ajouter une phrase sur l'arène du serveur
	 */
	String AJOUTPHRASE = "ajout phrase";
	/**
	 * ordre pour modifier le contenu du chat
	 */
	String MODIFCHAT = "modif chat";
	/**
	 * ordre pour ajouter un panel jeu dans l'arène du client
	 */
	String AJOUTPANELJEU = "ajout panel jeu";
	/**
	 * ordre pour ajouter un jlabel jeu dans l'arene
	 */
	String AJOUTJLABELJEU = "ajout jlabel jeu";
	/**
	 * ordre pour effectuer un mouvement
	 */
	String ACTION = "action";
	/**
	 * Etat du personnage "marche"
	 */
	String MARCHE = "marche";
	/**
	 * vie de départ pour tous les joueurs
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
	/**
	 * Valeur d'un pas
	 */
	int PAS = 10;

}
