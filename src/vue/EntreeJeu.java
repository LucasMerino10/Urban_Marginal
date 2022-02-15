package vue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controleur.Controle;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.JButton;

public class EntreeJeu extends JFrame {

	private Controle controle;
	/**
	 * Panel général
	 */
	private JPanel contentPane;
	/**
	 * Zone de saisie de l'IP
	 */
	private JTextField txtIp;

	/**
	 * clic sur le boutton Start
	 */
	private void btnStart_Clic() {
		controle.evenementEntreeJeu("serveur");
	}

	/**
	 * clic sur le boutton Connect
	 */
	private void btnConnect_Clic() {
		controle.evenementEntreeJeu(txtIp.getText());
	}

	/**
	 * clic sur le boutton Exit
	 */
	private void btnExit_Clic() {
		System.exit(0);
	}

	/**
	 * Create the frame.
	 */
	public EntreeJeu(Controle controle) {
		setTitle("Urban Marginal");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 302, 159);
		contentPane = new JPanel();		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtIp = new JTextField();
		txtIp.setText("127.0.0.1");
		txtIp.setBounds(69, 58, 107, 20);
		contentPane.add(txtIp);
		txtIp.setColumns(10);
		
		JLabel lblIpServer = new JLabel("IP server :");
		lblIpServer.setFont(new Font("Dialog", Font.BOLD, 12));
		lblIpServer.setBounds(10, 61, 68, 14);
		contentPane.add(lblIpServer);
		
		JLabel lblConnect = new JLabel("Connect an existing server :");
		lblConnect.setFont(new Font("Dialog", Font.BOLD, 12));
		lblConnect.setBounds(10, 36, 197, 14);
		contentPane.add(lblConnect);
		
		JLabel lblStartAServer = new JLabel("Start a server :");
		lblStartAServer.setFont(new Font("Dialog", Font.BOLD, 12));
		lblStartAServer.setBounds(10, 11, 94, 14);
		contentPane.add(lblStartAServer);
		
		JButton btnStart = new JButton("Start");
		btnStart.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				btnStart_Clic();
			}
		});
		btnStart.setFont(new Font("Dialog", Font.BOLD, 12));
		btnStart.setBounds(186, 7, 89, 23);
		contentPane.add(btnStart);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				btnConnect_Clic();
			}
		});
		btnConnect.setFont(new Font("Dialog", Font.BOLD, 12));
		btnConnect.setBounds(186, 57, 89, 23);
		contentPane.add(btnConnect);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				btnExit_Clic();
			}
		});
		btnExit.setFont(new Font("Dialog", Font.BOLD, 12));
		btnExit.setBounds(186, 91, 89, 23);
		contentPane.add(btnExit);

		this.controle=controle;
		}
}


