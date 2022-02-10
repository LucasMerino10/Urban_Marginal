package vue;



import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JScrollPane;

public class Arene extends JFrame {

	/**
	 * Panel général
	 */
	private JPanel contentPane;
	/**
	 * Zone de saisie du Chat
	 */
	private JTextField txtSaisie;
	/**
	 * Zone d'affichage du Chat
	 */
	private JTextArea txtChat;


	/**
	 * Create the frame.
	 */
	public Arene() {
		// Dimension de la frame en fonction de son contenu
		this.getContentPane().setPreferredSize(new Dimension(800, 600 + 25 + 140));
		this.pack();
		// Interdiction de changer la taille
		this.setResizable(false);
		setTitle("Arena");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel backgroundImg = new JLabel("");
		String chemin = "fonds/fondarene.jpg";
		String ressource = getClass().getClassLoader().getResource(chemin).getPath();
		backgroundImg.setIcon(new ImageIcon(ressource));
		backgroundImg.setBounds(0, 0, 800, 600);
		contentPane.add(backgroundImg);
		
		txtSaisie = new JTextField();
		txtSaisie.setFont(new Font("Dialog", Font.PLAIN, 12));
		txtSaisie.setBounds(0, 600, 800, 25);
		contentPane.add(txtSaisie);
		txtSaisie.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 625, 800, 140);
		contentPane.add(scrollPane);
		
		JTextArea txtChat = new JTextArea();
		scrollPane.setViewportView(txtChat);
		
	}
}
