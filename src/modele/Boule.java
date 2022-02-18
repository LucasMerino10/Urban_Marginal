package modele;

import java.net.URL;
import java.util.Collection;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import controleur.Global;

/**
 * Gestion de la boule
 *
 */
public class Boule extends Objet implements Global, Runnable {

	/**
	 * instance de JeuServeur pour la communication
	 */
	private JeuServeur jeuServeur ;
	/**
	 * Collection de murs
	 */
	private Collection lesMurs;
	/**
	 * Instance du joueur qui lance une boule 
	 */
	private Joueur attaquant;
	
	/**
	 * Constructeur
	 */
	public Boule(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur;
		super.jLabel = new JLabel();
		super.jLabel.setVisible(false);
		URL resource = getClass().getClassLoader().getResource(BOULE);
		super.jLabel.setIcon(new ImageIcon(resource));
		super.jLabel.setBounds(0, 0, LARGEURBOULE, HAUTEURBOULE);
	}
	
	/**
	 * Tire d'une boule
	 */
	public void tireBoule(Joueur attaquant, Collection lesMurs, Collection lesJoueurs) {
		this.lesMurs = lesMurs;
		this.attaquant = attaquant;
		// positionnement de la boule
		if(attaquant.getOrientation() == 0) {
			posX = attaquant.getPosX() - LARGEURBOULE - 1;
		}else {
			posX = attaquant.getPosX() + LARGEURPERSO + 1;
		}
		posY = attaquant.getPosY() + HAUTEURPERSO/2;
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		// Son du tir
		this.jeuServeur.envoi(FIGHT);
		this.attaquant.affiche(MARCHE, 1);
		super.jLabel.setVisible(true);
		Joueur victime = null;
		int lePas;
		if(attaquant.getOrientation() == 0) {		
			lePas = -PAS;
		}else {
			lePas = PAS;
		}
		// déplacement de la boule
		do {
			posX += lePas;
			jLabel.setBounds(posX, posY, LARGEURBOULE, HAUTEURBOULE);
			this.jeuServeur.envoiATous();
			Collection lesJoueurs = this.jeuServeur.getLesJoueurs();
			victime = (Joueur)super.toucheCollectionObjets(lesJoueurs);
		}while(posX > 0 && posX < LARGEURARENE && victime == null && this.toucheCollectionObjets(lesMurs) == null);
		if(victime != null && !victime.estMort()){
			// son du joueur blessé
			this.jeuServeur.envoi(HURT);
			victime.perteVie();
			attaquant.gainVie();
			// animation joueur blessé
			for(int k = 1; k <= 2; k++) {
				victime.affiche(TOUCHE, k);
				pause(80, 0);
			}
			// animation joueur mort
			if(victime.estMort()) {
				// Son du joueur mort
				this.jeuServeur.envoi(DEATH);
				for(int k = 1; k <= 2; k++) {
					victime.affiche(MORT, k);
					pause(80, 0);
				}
			}else {
				victime.affiche(MARCHE, 1);
			}
		}
		this.jLabel.setVisible(false);
		this.jeuServeur.envoiATous();
	}
	
	public void pause(long milliseconds, int nanoseconds) {
		try {
			Thread.sleep(milliseconds, nanoseconds);
		}catch (InterruptedException e){
			System.out.println("erreur pause");
		}
	}
	
}
