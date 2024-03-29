import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class SettingsWindow extends JFrame {
	private JTextField user_field;
	private JTextField server_field;
	private JTextField pass_field;
	private JTextField port_field;
	private JTextField dbname_field;

	public SettingsWindow() {
		setResizable(false);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 238, 223);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnSaveConnection = new JButton("Save Connection");
		btnSaveConnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Settings.server = server_field.getText();
				Settings.user = user_field.getText();
				Settings.pass = pass_field.getText();
				Settings.port = port_field.getText();
				Settings.dbName = dbname_field.getText();

				XMLParser.WriteSettingsToFile();
				// save connection
			}
		});
		btnSaveConnection.setBounds(10, 192, 134, 23);
		panel.add(btnSaveConnection);

		server_field = new JTextField();
		server_field.setText(Settings.server);
		server_field.setBounds(94, 9, 134, 20);
		panel.add(server_field);
		server_field.setColumns(10);

		JLabel lblServer = new JLabel("Server:");
		lblServer.setBounds(0, 11, 87, 16);
		panel.add(lblServer);
		lblServer.setHorizontalAlignment(SwingConstants.RIGHT);

		user_field = new JTextField();
		user_field.setText(Settings.user);
		user_field.setBounds(94, 38, 134, 20);
		panel.add(user_field);
		user_field.setColumns(10);

		JLabel lblUser = new JLabel("User:");
		lblUser.setBounds(0, 38, 85, 16);
		panel.add(lblUser);
		lblUser.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(2, 65, 85, 16);
		panel.add(lblPassword);
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);

		pass_field = new JTextField();
		pass_field.setText(Settings.pass);
		pass_field.setBounds(94, 65, 134, 20);
		panel.add(pass_field);
		pass_field.setColumns(10);

		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(0, 92, 87, 16);
		panel.add(lblPort);
		lblPort.setHorizontalAlignment(SwingConstants.RIGHT);

		port_field = new JTextField();
		port_field.setText(Settings.port);
		port_field.setBounds(94, 92, 134, 20);
		panel.add(port_field);
		port_field.setColumns(10);

		JLabel lblDbName = new JLabel("DB Name:");
		lblDbName.setBounds(0, 119, 87, 16);
		panel.add(lblDbName);
		lblDbName.setHorizontalAlignment(SwingConstants.RIGHT);

		dbname_field = new JTextField();
		dbname_field.setText(Settings.dbName);
		dbname_field.setBounds(94, 119, 134, 20);
		panel.add(dbname_field);
		dbname_field.setColumns(10);
	}
}
