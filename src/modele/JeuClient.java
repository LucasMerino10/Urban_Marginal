package modele;

import javax.swing.JPanel;

import controleur.Controle;
import controleur.Global;
import outils.connexion.Connection;

/**
 * Gestion du jeu côté client
 *
 */
public class JeuClient extends Jeu implements Global {
	
	/**
	 * Objet de connexion
	 */
	private Connection connection;
	private boolean murOk = false;
	
	/**
	 * Controleur
	 */
	public JeuClient(Controle controle) {
		super.controle = controle;
	}
	
	@Override
	public void connexion(Connection connection) {	
		this.connection = connection;
	}

	@Override
	public void reception(Connection connection, Object info) {
		if (info instanceof JPanel) {
			if(!this.murOk) {
				this.controle.evenementJeuClient(AJOUTPANELMURS, info);
				this.murOk = true;
			}else {
				this.controle.evenementJeuClient(AJOUTPANELJEU, info);
			}
		}else if(info instanceof String) {
			this.controle.evenementJeuClient(MODIFCHAT, info);
		}else if(info instanceof Integer) {
			this.controle.evenementJeuClient(JOUESON, info);
		}
	}
	
	@Override
	public void deconnexion(Connection connection) {
		System.exit(0);
	}

	/**
	 * Envoi d'une information vers le serveur
	 * fais appel une fois à l'envoi dans la classe Jeu
	 */
	public void envoi(String info) {
		super.envoi(this.connection, info);
	}



	

}
