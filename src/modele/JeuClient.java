package modele;

import javax.swing.JPanel;

import controleur.Controle;
import controleur.Global;
import outils.connexion.Connection;

/**
 * Gestion du jeu c�t� client
 *
 */
public class JeuClient extends Jeu implements Global {
	
	/**
	 * Objet de connexion
	 */
	private Connection connection;
	
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
			boolean murOk = false;
			if(!murOk) {
				controle.evenementJeuClient(AJOUTPANELMURS, info);
				murOk = true;
			}else {
				controle.evenementJeuClient(AJOUTPANELJEU, info);
			}
		}
		if(info instanceof String) {
			controle.evenementJeuClient(MODIFCHAT, info);
		}
	}
	
	@Override
	public void deconnexion() {
	}

	/**
	 * Envoi d'une information vers le serveur
	 * fais appel une fois � l'envoi dans la classe Jeu
	 */
	public void envoi(String info) {
		super.envoi(this.connection, info);
	}



	

}
