package swing;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Application;
import models.Word;
import models.Words;

public class DeleteWordFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Object> wordCBX;
	
	public DeleteWordFrame(Words w){
		this.setTitle(w.toString() + " deletion");
		this.setSize(250, 70);
		this.setResizable(false);
		
		JPanel container = new JPanel();
		if(w.getWords().size()==0){
			JPanel asd = new JPanel();
			JLabel error = new JLabel("Cant delete null item");
			error.setForeground(Color.red);
			asd.add(error);
			this.add(asd);
			return;
			
		}
		this.wordCBX = new JComboBox<Object>(w.getWords().toArray());
		container.add(this.wordCBX);
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					((Word)wordCBX.getSelectedItem()).postDelete(Application.user);
					Application.mf.CheaterReDraw();
					dispose();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		container.add(deleteButton);
		this.add(container);
	}
}
