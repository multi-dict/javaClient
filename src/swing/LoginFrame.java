package swing;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import main.Application;
import models.LoginData;

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
	private JButton cancelButton;

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

		this.cancelButton = new JButton("Cancel");
		this.cancelButton.addActionListener(new CancelButtonListener());
		mainPanel.add(this.cancelButton, gbc);
		
		this.errorLabel = new JLabel();
		this.errorLabel.setSize(400,30);
		
		this.errorLabel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(this.errorLabel, gbc);

		this.add(this.mainPanel);
		
		//TODO remove from final version
		this.passwordTextField.setText("test1234");
		this.userNameTextField.setText("test");
		setErrorLabel(errorCode);
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem1, menuItem2;
		
		menuBar = new JMenuBar();

		//Build the first menu.
		menu = new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
		        "The only menu in this program that has menu items");
		menuBar.add(menu);

		//a group of JMenuItems
		menuItem1 = new JMenuItem("Need an account?",
		                         KeyEvent.VK_T);
		menuItem1.getAccessibleContext().setAccessibleDescription(
		        "This doesn't really do anything");
		menuItem1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Contact a developer at www.github.com/multi-dict");
			}
			
		});
		
		//a group of JMenuItems
		menuItem2 = new JMenuItem("About",
		                         KeyEvent.VK_T);
		menuItem2.getAccessibleContext().setAccessibleDescription(
		        "This doesn't really do anything");
		menuItem2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "This is a demostration client for a school project");
			}
			
		});
		
		menu.add(menuItem2);
		menu.add(menuItem1);
		
		menuBar.add(menu);
		
		this.setJMenuBar(menuBar);

		
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
			errorLabel.setText("              Wrong username or password            ");
			break;
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
			Application.user = new LoginData(userNameTextField.getText(), passwordTextField.getText());
			try {
				switch (Application.user.Login()) {
				case 200:
					errorLabel.setForeground(Color.GREEN);
					errorLabel.setText("Login success");
					Application.mf = new MainFrame();
					Application.mf.show(true);
					dispose();
					break;
				default:
					setErrorLabel(2);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	private class CancelButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
		}

	}
}
