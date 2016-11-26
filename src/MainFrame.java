import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int PADDING = 30; // for example
	private ArrayList<Dictionary> dictionaries;
	private ArrayList<Entity> entities;

	private JComboBox dictCBX;

	public MainFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Dictionary Application");
		this.setSize(800, 640);
		this.setMinimumSize(new Dimension(400, 400));

		this.Refresh();
		JPanel container = new JPanel();
		BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
		container.setLayout(layout);
		JPanel topMenu = new JPanel();
		topMenu.setLayout(new FlowLayout(FlowLayout.RIGHT, PADDING, 5));
		JLabel userlogged = new JLabel("Logged in as (" + Application.user.GetName() + ")");
		userlogged.setForeground(Color.WHITE);
		topMenu.add(userlogged);
		JButton logoutButton = new JButton("Logout");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame loginWindow = new LoginFrame(0);
				loginWindow.show(true);
				dispose();
			}
		});
		JButton refreshButton = new JButton("Refresh");
		refreshButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Refresh();

			}
		});
		topMenu.add(logoutButton);
		topMenu.add(refreshButton);
		topMenu.setBackground(Color.black);
		topMenu.setMaximumSize(new Dimension(1400, 100));
		container.add(topMenu);

		JPanel dictMenu = new JPanel();
		dictMenu.setLayout(new FlowLayout(FlowLayout.LEFT, PADDING, 5));

		dictMenu.add(dictCBX);
		JButton dictRetriveButton = new JButton("Load");
		dictRetriveButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					entities=Entity.GetEntities(Application.user,(Dictionary) dictCBX.getSelectedItem());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		dictMenu.add(dictRetriveButton);
		
		JButton dictInfoButton = new JButton("info");
		dictInfoButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				DictInfoFrame dictInfo = new DictInfoFrame((Dictionary) dictCBX.getSelectedItem());
				dictInfo.show(true);
			}
			
		});
		dictMenu.add(dictInfoButton);
		container.add(dictMenu);
		this.add(container);

	}

	private void Refresh() {
		try {
			this.dictionaries = Dictionary.GetDictionaries(Application.user);
			this.dictCBX = new JComboBox<Object>(this.dictionaries.toArray());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
