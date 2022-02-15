package controleur;

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

}
