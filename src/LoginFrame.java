import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextField userNameTextField;
	public JPasswordField passwordTextField;
	public JLabel errorLabel;

	private JPanel mainPanel;
	private JButton loginButton;
	private LoginData loginData;

	public LoginFrame(int errorCode) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Login to Dictionary Application");
		this.setSize(400, 180);
		this.setResizable(true);

		this.mainPanel = new JPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		
		mainPanel.add(new JLabel("Username"));
		this.userNameTextField = new JTextField(30);
		mainPanel.add(this.userNameTextField, gbc);

		mainPanel.add(new JLabel("Password"));
		this.passwordTextField = new JPasswordField(30);
		mainPanel.add(this.passwordTextField, gbc);

		this.loginButton = new JButton("Login");
		this.loginButton.addActionListener(new LoginButtonListener());
		this.loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(this.loginButton, gbc);

		this.errorLabel = new JLabel();
		this.errorLabel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(this.errorLabel, gbc);

		this.add(this.mainPanel);
	}

	public void setErrorLabel(int errorCode) {
		switch (errorCode) {
		case 0:
			break;
		case 1:
			errorLabel.setForeground(Color.RED);
			errorLabel.setText("Error with config file");
			break;
		case 2:
			errorLabel.setForeground(Color.RED);
			errorLabel.setText("Wrong username or password");
		default:
			errorLabel.setForeground(Color.RED);
			errorLabel.setText("Unkown error");
			break;
		}

	}

	private class LoginButtonListener implements ActionListener {

		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent arg0) {
			loginData = new LoginData(userNameTextField.getText(), passwordTextField.getText());
			try {
				switch (loginData.Login()) {
				case 200:
					errorLabel.setForeground(Color.GREEN);
					errorLabel.setText("Login success");
					break;
				default:
					setErrorLabel(2);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
