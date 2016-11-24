import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class LoginFrame extends JFrame{
	public JTextField userNameTextField;
	public JTextField passwordTextField;
	public JLabel errorLabel;
	
	private JPanel mainPanel;
	private JButton loginButton;
	private LoginData loginData;
	
	public LoginFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Login to Dictionary Application");
		this.setSize(400, 140);
		this.setResizable(true);
		
		this.mainPanel = new JPanel();
		this.userNameTextField = new JTextField(30);
		mainPanel.add(this.userNameTextField);
		this.passwordTextField = new JTextField(30);
		mainPanel.add(this.passwordTextField);
		this.loginButton = new JButton("Login");
		this.loginButton.addActionListener(new LoginButtonListener());
		mainPanel.add(this.loginButton);
		this.errorLabel = new JLabel();
		mainPanel.add(this.errorLabel);
		
		this.add(this.mainPanel);
	}
	private class LoginButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			loginData = new LoginData(userNameTextField.getText(),passwordTextField.getText());
			try {
				if (loginData.Login()==200){
					errorLabel.setForeground(Color.GREEN);
					errorLabel.setText("Login success");
				}
				else{
					errorLabel.setForeground(Color.RED);
					errorLabel.setText("Login failed");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
