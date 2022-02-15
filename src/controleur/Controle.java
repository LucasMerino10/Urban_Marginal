package controleur;

import outils.connexion.ServeurSocket;
import outils.connexion.AsyncResponse;
import outils.connexion.ClientSocket;
import outils.connexion.Connection;
import vue.Arene;
import vue.ChoixJoueur;
import vue.EntreeJeu;


/**
 * Contrôleur et point d'entrée de l'applicaton 
 * @author emds
 *
 */
public class Controle implements AsyncResponse {

	private EntreeJeu frmEntreeJeu ;
	private Arene frmArene;
	private ChoixJoueur frmChoixJoueur;
	private String typeJeu;
	private final int PORT = 6666;
	
	/**
	 * 
	 */
	@Override
	public void reception(Connection connection, String ordre, Object info) {
		// TODO Auto-generated method stub
		switch(ordre) {
		case "connexion" :
			if (this.typeJeu == "client") {
				this.frmEntreeJeu.dispose();
				this.frmChoixJoueur = new ChoixJoueur(this);
				this.frmChoixJoueur.setVisible(true);
				this.frmArene = new Arene();
			}
		break;
		case "réception" :
		
		break;
		case "déconnexion" :
		
		break;
		}
	}

	/**
	 * Méthode de démarrage
	 * @param args non utilisé
	 */
	public static void main(String[] args) {
		new Controle();
	}
	
	/**
	 * Constructeur
	 */
	private Controle() {
		this.frmEntreeJeu = new EntreeJeu(this) ;
		this.frmEntreeJeu.setVisible(true);
	}
	
	/**
	 * Connexion au client ou au serveur
	 * @param info
	 */
	public void evenementEntreeJeu(String info) {
		// Connexion au serveur
		if (info == "serveur") {
			this.typeJeu = "serveur";
			this.frmArene = new Arene();
			this.frmArene.setVisible(true);
			new ServeurSocket(this, PORT);	
			this.frmEntreeJeu.dispose();
		}
		else {
			// Connexion au client
			this.typeJeu = "client";
			new ClientSocket(this, info, PORT);
		}
	}
	
	/**
	 * Reçoit le Pseudo et le numero de joueur
	 */
	public void evenementChoixJoueur(String pseudo, int numPerso) {
		this.frmChoixJoueur.dispose();
		this.frmArene.setVisible(true);
	}

	
}
