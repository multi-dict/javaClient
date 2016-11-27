import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONException;

public class AddWordFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Object> languageCBX;
	private JCheckBox newEntityCB;
	private JTextField wordField;
	private JTextField descrField;
	private JComboBox<Object> genderCBX;
	private int entityId;
	
	public AddWordFrame(int entityID, Dictionary d){
		this.entityId = entityID;
		this.setTitle("Word addition");
		this.setSize(400, 200);
		this.setResizable(false);
		
		JPanel container = new JPanel();
		BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
		container.setLayout(layout);
		
		JPanel topBar = new JPanel();

		this.languageCBX = new JComboBox<Object>(d.GetLanguages().toArray());
		topBar.add(this.languageCBX);
		this.newEntityCB = new JCheckBox("In new entity");
		this.newEntityCB.setEnabled(false);
		topBar.add(this.newEntityCB);
		
		container.add(topBar);
		
		JPanel mainBar1 = new JPanel();
		this.wordField = new JTextField(20);
		this.descrField = new JTextField(20);
		String[] myStringArray = {"-", "Male","Female","Genderless"};
		this.genderCBX = new JComboBox<Object>(myStringArray);
		
		mainBar1.add(new JLabel("Word: "));
		mainBar1.add(this.wordField);
		
		JPanel mainBar2 = new JPanel();
		mainBar2.add(new JLabel("Description: "));
		mainBar2.add(this.descrField);
		
		JPanel mainBar3 = new JPanel();
		mainBar3.add(new JLabel("Gender: "));
		mainBar3.add(this.genderCBX);
		
		
		container.add(mainBar1);
		container.add(mainBar2);
		container.add(mainBar3);
		
		JPanel bottomBar = new JPanel();
		JButton deleteButton = new JButton("Add");
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Word myWord = new Word(wordField.getText(), (Language)languageCBX.getSelectedItem(), descrField.getText());
				try {
					myWord.postAdd(entityId, Application.user);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Application.mf.CheaterReDraw();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
			}

		});
		bottomBar.add(deleteButton);
		container.add(bottomBar);
		
		this.add(container);
	}
}
