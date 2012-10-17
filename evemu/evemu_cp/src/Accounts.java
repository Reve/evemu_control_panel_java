import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class Accounts extends JFrame {

	private JPanel contentPane;
	private JTextField username_field;
	private JTextField pass_field;
	private JTextField textField_2;
	private JTable table;
	private JComboBox comboBox;

	// roles
	private static final long PLAYER = 6917529027641081856L;
	private static final long DEV = 5003499186008621056L;

	public Accounts() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 586, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(6, 6, 162, 220);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 11, 134, 20);
		panel.add(lblUsername);

		username_field = new JTextField();
		username_field.setBounds(10, 31, 134, 20);
		username_field.setColumns(10);
		panel.add(username_field);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 62, 134, 14);
		panel.add(lblPassword);

		pass_field = new JTextField();
		pass_field.setBounds(10, 77, 134, 20);
		pass_field.setColumns(10);
		panel.add(pass_field);

		JLabel lblRole = new JLabel("Role");
		lblRole.setBounds(10, 108, 134, 20);
		panel.add(lblRole);

		comboBox = new JComboBox();
		comboBox.setBounds(10, 132, 134, 32);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Dev", "Player" }));
		comboBox.setSelectedIndex(0);
		panel.add(comboBox);

		JButton btnAddAccount = new JButton("Add Account");
		btnAddAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedIndex() == 0) {
					// dev role selected
					DBConnect.SimpleQuerry("INSERT INTO account (accountID, accountName, role, password, online, banned) VALUES(NULL,'"
							+ username_field.getText() + "' ," + DEV + " ,'" + pass_field.getText() + "' , 0, 0);");

				} else if (comboBox.getSelectedIndex() == 1) {
					// player role selected
					String query = "INSERT INTO account(accountID, accountName, role, password, online, banned) VALUES(NULL, '"
							+ username_field.getText() + "', " + PLAYER + ", '" + pass_field.getText() + "', 0, 0);";
					DBConnect.SimpleQuerry(query);
				}
			}
		});
		btnAddAccount.setBounds(10, 175, 108, 32);
		panel.add(btnAddAccount);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(178, 6, 397, 220);
		contentPane.add(panel_1);

		textField_2 = new JTextField();
		textField_2.setBounds(117, 7, 177, 20);
		textField_2.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// search database for account or accounts
			}
		});
		btnSearch.setBounds(304, 6, 83, 23);

		String[] columnNames = { "ID", "Username", "Role", "Location" };
		Object[][] data = { { "0", "admin", "1243151235", "Lonetrek" }, { "1", "reve", "1243151235", "Lonetrek" },
				{ "2", "aknor", "1243151235", "Lonetrek" }, };
		panel_1.setLayout(null);

		JLabel lblSearchForAccounts = new JLabel("Search for account");
		lblSearchForAccounts.setBounds(10, 10, 115, 14);
		lblSearchForAccounts.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(lblSearchForAccounts);
		panel_1.add(textField_2);
		panel_1.add(btnSearch);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 35, 377, 174);
		panel_1.add(scrollPane);

		table = new JTable(data, columnNames);
		table.setRowSelectionAllowed(false);
		scrollPane.setViewportView(table);
		table.setPreferredScrollableViewportSize(new Dimension(500, 100));
		table.setFillsViewportHeight(true);
	}
}
