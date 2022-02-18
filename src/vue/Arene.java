package vue;



import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controleur.Controle;
import controleur.Global;
import outils.son.Son;

import javax.swing.JTextArea;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

public class Arene extends JFrame implements Global {

	/**
	 * Panel général
	 */
	private JPanel contentPane;
	/**
	 * Panel d'affichage des murs
	 */
	private JPanel jpnMurs;
	/**
	 * Panel qui gère l'affichage des objets en mouvement sur la zone de jeu
	 */
	private JPanel jpnJeu;

	/**
	 * Zone de saisie du Chat
	 */
	private JTextField txtSaisie;
	/**
	 * Zone d'affichage du Chat
	 */
	private JTextArea txtChat;
	/**
	 * Instance du controleur
	 */
	private Controle controle;
	/**
	 * Type d'instance d'Arène (true si client, false si serveur)
	 */
	private boolean client;
	/**
	 * Son d'ambiance
	 */
	private Son ambiance;
	/**
	 * Tableau de sons
	 */
	private Son[] lesSons = new Son[SON.length];
	
	/**
	 * Evenement touche pressée dans la zone de texte
	 * @param e
	 */
	public void txtSaisie_KeyPressed(KeyEvent e) {
		// si validation
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			// si la zone de saisie n'est pas vide
			if(!this.txtSaisie.getText().equals("")) {
				this.controle.evenementArene(this.txtSaisie.getText());
				this.txtSaisie.setText("");
			}
			this.contentPane.requestFocus();
		}
	}
	
	/**
	 * Evenement touche pressée sur le panel général
	 * @param e touche pressée
	 */
	public void contentPane_KeyPressed(KeyEvent e) {
		int touche = -1;
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT :
		case KeyEvent.VK_RIGHT :
		case KeyEvent.VK_UP :
		case KeyEvent.VK_DOWN :		
		case KeyEvent.VK_SPACE :
			touche = e.getKeyCode();
			break;
		}
		if(touche != -1) {
			controle.evenementArene(touche);
		}
	}

	/**
	 * Create the frame.
	 */
	public Arene(Controle controle, String type) {
		this.client = type.equals(CLIENT);
		// Dimension de la frame en fonction de son contenu
		this.getContentPane().setPreferredSize(new Dimension(800, 600 + 25 + 140));
		this.pack();
		// Interdiction de changer la taille
		this.setResizable(false);
		setTitle("Arena");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				contentPane_KeyPressed(e);
			}
		});
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		jpnJeu = new JPanel();
		jpnJeu.setBounds(0, 0, LARGEURARENE, HAUTEURARENE);
		jpnJeu.setOpaque(false);
		jpnJeu.setLayout(null);
		contentPane.add(jpnJeu);
		
		jpnMurs = new JPanel();
		jpnMurs.setBounds(0, 0, LARGEURARENE, HAUTEURARENE);
		jpnMurs.setOpaque(false);
		jpnMurs.setLayout(null);
		contentPane.add(jpnMurs);
		
		JLabel backgroundImg = new JLabel("");
		String chemin = "fonds/fondarene.jpg";
		String ressource = getClass().getClassLoader().getResource(chemin).getPath();
		backgroundImg.setIcon(new ImageIcon(ressource));
		backgroundImg.setBounds(0, 0, 800, 600);
		contentPane.add(backgroundImg);
		
		if(this.client) {
			txtSaisie = new JTextField();
			txtSaisie.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					txtSaisie_KeyPressed(e);
				}
			});
			txtSaisie.setFont(new Font("Dialog", Font.PLAIN, 12));
			txtSaisie.setBounds(0, 600, 800, 25);
			contentPane.add(txtSaisie);
			txtSaisie.setColumns(10);
		}
	
		JScrollPane jspChat = new JScrollPane();
		jspChat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jspChat.setBounds(0, 625, 800, 140);
		contentPane.add(jspChat);
		
		txtChat = new JTextArea();
		txtChat.setEditable(false);
		jspChat.setViewportView(txtChat);	
		
		// remplissage du tableau de sons
		if(client) {
			URL ambiance = getClass().getClassLoader().getResource(SONAMBIANCE);
			this.ambiance = new Son(ambiance);
			this.ambiance.playContinue();
			for(int k = 0; k < SON.length; k++) {
				lesSons[k] = new Son(getClass().getClassLoader().getResource(SON[k]));
			}
		}
		this.controle = controle;
	}
	
	/**
	 * Permet de jouer un son
	 */
	public void joueSon(Integer son) {
		this.lesSons[son].play();
	}
	
	/**
	 * Ajoute un mur au Panel mur
	 * @param unMur
	 */
	public void ajoutMurs(Object unMur) {
		jpnMurs.add((JLabel)unMur);
		jpnMurs.repaint();
	}
	
	public void ajoutJLabelJeu(JLabel unJLabel) {
		this.jpnJeu.add(unJLabel);
		this.jpnJeu.repaint();
	}
	
	/**
	 * Getter sur jpnMur
	 * @return jpnMur
	 */
	public JPanel getJpnMur() {
		return jpnMurs;
	}

	/**
	 * Setter sur jpnMur
	 * @param jpnMur
	 */
	public void setJpnMur(JPanel jpnMur) {
		this.jpnMurs.add(jpnMur);
		this.jpnMurs.repaint();
	}
	
	/**
	 * 
	 * @return jpnJeu
	 */
	public JPanel getJpnJeu() {
		return jpnJeu;
	}

	/**
	 * Setter sur jpnJeu
	 * @param jpnJeu
	 */
	public void setJpnJeu(JPanel jpnJeu) {
		this.jpnJeu.removeAll();
		this.jpnJeu.add(jpnJeu);
		this.jpnJeu.repaint();
		this.contentPane.requestFocus();
	}

	/**
	 * 
	 * @return String
	 */
	public String getTxtChat() {
		return txtChat.getText();
	}

	/**
	 * setter sur txtChat
	 * @param txtChat
	 */
	public void setTxtChat(String txtChat) {
		this.txtChat.setText(txtChat);
		this.txtChat.setCaretPosition(this.txtChat.getDocument().getLength());
	}
	
	/**
	 * Gère l'ajout d'une phrase dans le champ txtChat
	 * @param phrase
	 */
	public void ajoutChat(String phrase) {
		this.txtChat.setText(this.txtChat.getText()+phrase+"\r\n");
		this.txtChat.setCaretPosition(this.txtChat.getDocument().getLength());
	}

}
