import java.awt.Color;
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

public class EditWordFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox wordCBX;
	private JCheckBox newEntityCB;
	private JTextField wordField;
	private JTextField descrField;
	private JComboBox<Object> genderCBX;
	private int entityId;
	
	public EditWordFrame(Words w, int entityID, Dictionary d){
		this.entityId = entityID;
		this.setTitle("Word edit");
		this.setSize(400, 200);
		this.setResizable(false);
		
		JPanel container = new JPanel();
		BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
		container.setLayout(layout);
		
		JPanel topBar = new JPanel();

		this.wordCBX = new JComboBox<Object>(w.getWords().toArray());
		this.wordCBX.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				wordField.setText(((Word) wordCBX.getSelectedItem()).GetWord());
				descrField.setText(((Word) wordCBX.getSelectedItem()).GetDescription());

			}
			
		});
		this.wordField = new JTextField(20);
		this.descrField = new JTextField(20);
		if(this.wordCBX.getSelectedItem()==null){
			JPanel asd = new JPanel();
			JLabel error = new JLabel("Cant edit null item");
			error.setForeground(Color.red);
			asd.add(error);
			this.add(asd);
			return;
			
		}
		
		wordField.setText(((Word) wordCBX.getSelectedItem()).GetWord());
		descrField.setText(((Word) wordCBX.getSelectedItem()).GetDescription());
		

		topBar.add(this.wordCBX);
		this.newEntityCB = new JCheckBox("In new entity");
		this.newEntityCB.setEnabled(false);
		topBar.add(this.newEntityCB);
		
		container.add(topBar);
		
		JPanel mainBar1 = new JPanel();
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
		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Word originalWord = (Word) wordCBX.getSelectedItem();
				Word myWord = new Word(wordField.getText(), originalWord.GetLanguage(), descrField.getText());
				try {
					myWord.postEdit(Application.user, originalWord);
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
		bottomBar.add(editButton);
		container.add(bottomBar);
		
		this.add(container);
		

	}
}
