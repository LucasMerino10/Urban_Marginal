package controleur;

import outils.connexion.ServeurSocket;
import modele.Jeu;
import modele.JeuClient;
import modele.JeuServeur;
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
public class Controle implements AsyncResponse, Global {

	private EntreeJeu frmEntreeJeu ;
	private Arene frmArene;
	private ChoixJoueur frmChoixJoueur;
	private Jeu leJeu;
	
	/**
	 * 
	 */
	@Override
	public void reception(Connection connection, String ordre, Object info) {
		// TODO Auto-generated method stub
		switch(ordre) {
		case "connexion" :
			if (!(this.leJeu instanceof JeuServeur)) {
				this.leJeu = new JeuClient(this);
				this.leJeu.connexion(connection);
				this.frmEntreeJeu.dispose();
				this.frmArene = new Arene();
				this.frmChoixJoueur = new ChoixJoueur(this);
				this.frmChoixJoueur.setVisible(true);			
			} else {
				this.leJeu.connexion(connection);
			}
		break;
		case "réception" :
			this.leJeu.reception(connection, info);
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
		if (info.equals("serveur")) {
			new ServeurSocket(this, PORT);
			this.leJeu = new JeuServeur(this);
			this.frmEntreeJeu.dispose();
			this.frmArene = new Arene();
			this.frmArene.setVisible(true);			
		}
		else {
			// Connexion au client
			new ClientSocket(this, info, PORT);
		}
	}
	
	/**
	 * Reçoit le Pseudo et le numero de joueur
	 */
	public void evenementChoixJoueur(String pseudo, int numPerso) {
		this.frmChoixJoueur.dispose();
		this.frmArene.setVisible(true);
		((JeuClient)this.leJeu).envoi(PSEUDO + STRINGSEPARE + pseudo + STRINGSEPARE + numPerso);
	}

	public void envoi(Connection connection, Object info) {
		connection.envoi(info);
	}
	
}
