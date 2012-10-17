import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

public class MainWindow {

	private JFrame frame;
	private JLabel lblStatus;
	private JButton btnAccounts;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
					String path = XMLParser.class.getProtectionDomain().getCodeSource().getLocation().getPath();
					path = path.substring(0, path.length() - 12);

					if (new File(path + "/" + "settings.xml") != null)
						XMLParser.LoadSettingsFromFile(new File(path + "/" + "settings.xml"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);
		frame.setBounds(100, 100, 453, 306);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(6, 6, 438, 219);
		frame.getContentPane().add(panel);

		btnAccounts = new JButton("Accounts");
		btnAccounts.setEnabled(false);
		btnAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Accounts accounts = new Accounts();
				accounts.setAlwaysOnTop(true);
				accounts.setLocationRelativeTo(frame);
				accounts.setVisible(true);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup().addContainerGap().addComponent(btnAccounts).addContainerGap(313, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup().addContainerGap().addComponent(btnAccounts).addContainerGap(182, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmSettings = new JMenuItem("Settings...");
		mntmSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SettingsWindow settingsWnd = new SettingsWindow();
				settingsWnd.setAlwaysOnTop(true);
				settingsWnd.setSize(254, 259);
				settingsWnd.setLocationRelativeTo(frame);
				settingsWnd.setVisible(true);
			}
		});

		JMenuItem mntmCreateEvemuDb = new JMenuItem("Create EVEmu DB...");
		mntmCreateEvemuDb.setEnabled(false);
		mntmCreateEvemuDb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateDB createDBWnd = new CreateDB();
				createDBWnd.setAlwaysOnTop(true);
				createDBWnd.setSize(354, 291);
				createDBWnd.setLocationRelativeTo(frame);
				createDBWnd.setVisible(true);
			}
		});

		JMenuItem mntmConnect = new JMenuItem("Connect");
		mntmConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String path = XMLParser.class.getProtectionDomain().getCodeSource().getLocation().getPath();
				path = path.substring(0, path.length() - 12);
				XMLParser.LoadSettingsFromFile(new File(path + "/" + "settings.xml"));
				if (DBConnect.OpenConnection())
					lblStatus.setText("Connected!");
				btnAccounts.setEnabled(true);
			}
		});
		mnFile.add(mntmConnect);

		JMenuItem mntmDisconnect = new JMenuItem("Disconnect");
		mntmDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DBConnect.CloseConnection();
				if (DBConnect.connected == false) {
					lblStatus.setText("Disconnected...");
				}
				btnAccounts.setEnabled(false);
			}
		});
		mnFile.add(mntmDisconnect);

		JSeparator separator_2 = new JSeparator();
		mnFile.add(separator_2);
		mnFile.add(mntmCreateEvemuDb);

		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);
		mnFile.add(mntmSettings);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);

		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DBConnect.CloseConnection();
				System.exit(0);
			}
		});
		mnFile.add(mntmQuit);
		frame.getContentPane().setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(6, 227, 438, 23);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		lblStatus = new JLabel("Disconnected...");
		lblStatus.setBounds(3, 3, 98, 16);
		panel_1.add(lblStatus);
	}
}
