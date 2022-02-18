package vue;

import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.SwingConstants;
import controleur.Controle;
import controleur.Global;
import outils.son.Son;

public class ChoixJoueur extends JFrame implements Global {
	
	private Controle controle;
	/**
	 * Panel Général
	 */
	private JPanel contentPane;
	/**
	 * Zone de saisie du Pseudo
	 */
	private JTextField txtPseudo;
	/**
	 * Affichage du personnage
	 */
	private JLabel lblPersonnage;
	/**
	 * numero du personnage
	 */
	private int numPersonnage;
	/**
	 * Son welcome
	 */
	private Son welcome;
	/**
	 * Son précédent
	 */
	private Son precedent;
	/**
	 * Son suivant
	 */
	private Son suivant;
	/**
	 * Son Go
	 */
	private Son go;
	
	/**
	 * Gestion de l'affichage du personnage
	 */
	private void affichePerso(int numPersonnage) {
		String ressource = getClass().getClassLoader().getResource(CHEMINPERSO).getPath();
		lblPersonnage.setIcon(new ImageIcon(ressource+ "perso" + numPersonnage + "marche1d1.gif"));
	}
	
	/**
	 * Affiche de la souris normale
	 */
	private void sourisNormale(){
		contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	/**
	 * Affichage de la souris lors du survol des labels
	 */
	private void sourisDoigt() {
		contentPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	/**
	 * Click sur Go
	 */
	private void lblGo_Clic() {
		if (txtPseudo.getText().equals("")) {			
			JOptionPane.showMessageDialog(null, "La saisie du pseudo est obligatoire");
			txtPseudo.requestFocus();
		}
		else {
			URL go = getClass().getClassLoader().getResource(SONGO);
			this.go = new Son(go);
			this.go.play();
			this.controle.evenementChoixJoueur(txtPseudo.getText(), numPersonnage);
		}
	}
	
	/**
	 * Click sur flèche "Précédent"
	 */
	private void lblPrecedent_Clic() {
		URL precedent = getClass().getClassLoader().getResource(SONPRECEDENT);
		this.precedent = new Son(precedent);
		this.precedent.play();
		if (numPersonnage > 1) {
			numPersonnage --;
		}
		else {
			numPersonnage = 3;
		}
		affichePerso(numPersonnage);
	}
	
	/**
	 * Clic sur flèche "Suivant"
	 */
	private void lblSuivant_Clic() {
		URL suivant = getClass().getClassLoader().getResource(SONSUIVANT);
		this.suivant = new Son(suivant);
		this.suivant.play();
		if (numPersonnage < 3) {
			numPersonnage++;
		}
		else {
			numPersonnage = 1;
		}
		affichePerso(numPersonnage);
	}

	/**
	 * Create the frame.
	 */
	public ChoixJoueur(Controle controle) {
		this.controle = controle;
		// Dimension de la frame en fonction de son contenu
		this.getContentPane().setPreferredSize(new Dimension(400, 275));
		this.pack();
		// Interdiction de changer la taille
		this.setResizable(false);
		
		setTitle("Choix du Personnage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblPersonnage = new JLabel("");
		lblPersonnage.setBounds(142, 115, 120, 119);
		lblPersonnage.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblPersonnage);
		
		JLabel backgroundImg = new JLabel("");
		String chemin = "fonds/fondchoix.jpg";
		String ressource = getClass().getClassLoader().getResource(chemin).getPath();
		backgroundImg.setIcon(new ImageIcon(ressource));
		backgroundImg.setBounds(0, 0, 400, 275);
		contentPane.add(backgroundImg);
		
		txtPseudo = new JTextField();
		txtPseudo.setHorizontalAlignment(SwingConstants.CENTER);
		txtPseudo.setFont(new Font("Dialog", Font.BOLD, 12));
		txtPseudo.setBounds(142, 245, 120, 20);
		txtPseudo.setColumns(10);
		contentPane.add(txtPseudo);
		
		JLabel lblPrecedent = new JLabel("");
		lblPrecedent.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblPrecedent_Clic();
			}
			public void mouseEntered(MouseEvent e) {
				sourisDoigt();
			}
			public void mouseExited(MouseEvent e) {
				sourisNormale();
			}
		});
				
		JLabel lblSuivant = new JLabel("");
		lblSuivant.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblSuivant_Clic();
			}
			public void mouseEntered(MouseEvent e) {
				sourisDoigt();
			}
			public void mouseExited(MouseEvent e) {
				sourisNormale();
			}
		});
		
		JLabel lblGo = new JLabel("");
		lblGo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblGo_Clic();
			}
			public void mouseEntered(MouseEvent e) {
				sourisDoigt();
			}
			public void mouseExited(MouseEvent e) {
				sourisNormale();
			}
		});
				
		lblPrecedent.setBounds(65, 145, 32, 44);
		contentPane.add(lblPrecedent);
		lblSuivant.setBounds(298, 145, 32, 44);
		contentPane.add(lblSuivant);
		lblGo.setBounds(308, 194, 67, 70);
		contentPane.add(lblGo);
		
		this.numPersonnage = 1;
		affichePerso(numPersonnage);
		
		// lancement du son Welcome
		URL welcome = getClass().getClassLoader().getResource(SONWELCOME);
		this.welcome = new Son(welcome);
		this.welcome.play();
		
		// Positionnement sur la zone de saisie
		txtPseudo.requestFocus();
	}
}
