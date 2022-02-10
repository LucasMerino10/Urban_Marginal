package vue;


import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class ChoixJoueur extends JFrame {

	/**
	 * Panel Général
	 */
	private JPanel contentPane;
	/**
	 * Zone de saisie du Pseudo
	 */
	private JTextField txtPseudo;
	
	
	/**
	 * Click sur Go
	 */
	private void lblGo_Clic() {
		(new Arene()).setVisible(true);
		dispose();
	}
	
	/**
	 * Click sur flèche "Précédent"
	 */
	private void lblPrecedent_Clic() {
		System.out.println("Clic sur precedent");
	}
	
	/**
	 * Clic sur flèche "Suivant"
	 */
	private void lblSuivant_Clic() {
		System.out.println("Clic sur suivant");
	}

	/**
	 * Create the frame.
	 */
	public ChoixJoueur() {
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
		});
				
		JLabel lblSuivant = new JLabel("");
		lblSuivant.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblSuivant_Clic();
			}
		});
		
		JLabel lblGo = new JLabel("");
		lblGo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblGo_Clic();
			}
		});
				
		lblPrecedent.setBounds(65, 145, 32, 44);
		contentPane.add(lblPrecedent);
		lblSuivant.setBounds(298, 145, 32, 44);
		contentPane.add(lblSuivant);
		lblGo.setBounds(308, 194, 67, 70);
		contentPane.add(lblGo);
		
		// Positionnement sur la zone de saisie
		txtPseudo.requestFocus();
	}
	
}
