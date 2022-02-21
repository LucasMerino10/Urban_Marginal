package modele;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Collection;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import controleur.Global;



/**
 * Gestion des joueurs
 *
 */
public class Joueur extends Objet implements Global {

	/**
	 * Jlabel contenant le nom du joueur et ses points de vie
	 */
	private JLabel message;
	
	/**
	 * pseudo saisi
	 */
	private String pseudo ;
	/**
	 * n° correspondant au personnage (avatar) pour le fichier correspondant
	 */
	private int numPerso ; 
	/**
	 * instance de JeuServeur pour communiquer avec lui
	 */
	private JeuServeur jeuServeur ;
	/**
	 * numéro d'étape dans l'animation (de la marche, touché ou mort)
	 */
	private int etape ;
	/**
	 * la boule du joueur
	 */
	private Boule boule ;
	/**
	 * vie restante du joueur
	 */
	private int vie ; 
	/**
	 * tourné vers la gauche (0) ou vers la droite (1)
	 */
	private int orientation ;
	
	/**
	 * Constructeur
	 */
	public Joueur(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur;
		this.vie = MAXVIE;
		this.etape = 1;
		this.orientation = 1;
	}

	/**
	 * Initialisation d'un joueur (pseudo et numéro, calcul de la 1ère position, affichage, création de la boule)
	 */
	public void initPerso(String pseudo, int numPerso, Collection lesJoueurs, Collection lesMurs) {
		this.pseudo = pseudo;
		this.numPerso = numPerso;
		System.out.println("joueur " + pseudo + " - num perso " + numPerso + " créé");
		// création du label du personnage
		super.jLabel = new JLabel();
		// création du label du message
		this.message = new JLabel();
		message.setFont(new Font("Dialog", Font.PLAIN, 12));
		message.setHorizontalTextPosition(SwingConstants.CENTER);
		// création de la boule
		this.boule = new Boule(this.jeuServeur);
		this.premierePosition(lesJoueurs, lesMurs);
		this.jeuServeur.ajoutJLabelJeuArene(jLabel);
		this.jeuServeur.ajoutJLabelJeuArene(message);
		this.jeuServeur.ajoutJLabelJeuArene(boule.getjLabel());
		this.affiche("marche", this.etape);
	}

	/**
	 * Calcul de la première position aléatoire du joueur (sans chevaucher un autre joueur ou un mur)
	 */
	private void premierePosition(Collection lesJoueurs, Collection lesMurs) {
		jLabel.setBounds(0, 0, LARGEURPERSO, HAUTEURPERSO);
		do {
			posX = (int) Math.round(Math.random() * (LARGEURARENE - LARGEURPERSO)) ;
			posY = (int) Math.round(Math.random() * (HAUTEURARENE - HAUTEURPERSO - 8)) ;
		}while(this.toucheCollectionObjets(lesJoueurs) != null || this.toucheCollectionObjets(lesMurs) != null);
	}
	
	/**
	 * Affiche le personnage et son message
	 */
	public void affiche(String etat, int etape) {
		super.jLabel.setBounds(posX, posY, LARGEURPERSO, HAUTEURPERSO);
		String chemin = CHEMINPERSO + "perso" + this.numPerso + etat + etape + "d" + this.orientation + ".gif"; 
		URL ressource = getClass().getClassLoader().getResource(chemin);
		super.jLabel.setIcon(new ImageIcon(ressource));
		this.message.setBounds(posX-10, posY+HAUTEURPERSO, LARGEURPERSO+40, 15);
		this.message.setText(this.pseudo + " : " + this.vie);
		this.jeuServeur.envoiATous();
	}

	/**
	 * Gère une action reçue et qu'il faut afficher (déplacement, tire de boule...)
	 */
	public void action(Integer action, Collection lesJoueurs, Collection lesMurs) {
		if(!this.estMort()) {
			switch(action) {
			case KeyEvent.VK_RIGHT :
				orientation = 1;
				posX = deplace(posX, action, PAS, LARGEURARENE-LARGEURPERSO, lesJoueurs, lesMurs);
				break;
			case KeyEvent.VK_LEFT :
				orientation = 0;
				posX = deplace(posX, action, -PAS, LARGEURARENE-LARGEURPERSO, lesJoueurs, lesMurs);
				break;
			case KeyEvent.VK_UP :
				posY = deplace(posY, action, -PAS, HAUTEURARENE-HAUTEURPERSO, lesJoueurs, lesMurs);
				break;
			case KeyEvent.VK_DOWN :
				posY = deplace(posY, action, PAS, HAUTEURARENE-HAUTEURPERSO, lesJoueurs, lesMurs);
				break;
			case KeyEvent.VK_SPACE :
				if(!this.boule.getjLabel().isVisible()) {
					this.boule.tireBoule(this, lesMurs, lesJoueurs);
				}
				break;
			}
			this.affiche(MARCHE, this.etape);
		}
	}

	/**
	 * Gère le déplacement du personnage
	 */
	private int deplace(int position, int action, int pas, int max, Collection lesJoueurs, Collection lesMurs) {
		int anciennePos = position;
		position += pas;
		position = Math.max(position, 0);
		position = Math.min(max, position);
		if(action == KeyEvent.VK_LEFT || action == KeyEvent.VK_RIGHT) {
			posX = position;
		}else {
			posY = position;
		}
		if(toucheCollectionObjets(lesJoueurs) != null || toucheCollectionObjets(lesMurs) != null){
			position = anciennePos;
		}
		etape = (etape % 4) + 1;
		return position;
	}
	
	/**
	 * Gain de points de vie après avoir touché un joueur
	 */
	public void gainVie() {
		this.vie = Math.min(this.vie + GAIN, MAXVIE);
	}
	
	/**
	 * Perte de points de vie après avoir été touché 
	 */
	public void perteVie() {
		this.vie = Math.max(0, this.vie - PERTE);
	}
	
	/**
	 * vrai si la vie est à 0
	 * @return true si vie = 0
	 */
	public Boolean estMort() {
		return (this.vie == 0);
	}
	
	/**
	 * Le joueur se déconnecte et disparait
	 */
	public void departJoueur() {
		if(jLabel != null) {
			this.jLabel.setVisible(false);
			this.boule.getjLabel().setVisible(false);
			this.message.setVisible(false);
			this.jeuServeur.envoiATous();
		}
	}

	/**
	 * @return pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}
	/**
	 * @return orientation
	 */
	public int getOrientation() {
		return orientation;
	}
	
}
