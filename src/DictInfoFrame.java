import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DictInfoFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DictInfoFrame(Dictionary dict) {
		this.setTitle(dict.toString() + " Info");
		this.setSize(300, 300);
		this.setMinimumSize(new Dimension(200,200));

		JPanel container = new JPanel();
		BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
		container.setLayout(layout);
		this.add(container);
		
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		namePanel.setMaximumSize(new Dimension(1000,100));
		namePanel.add(new JLabel("Name: " + dict.GetName()));
		container.add(namePanel);
		
		JPanel idPanel = new JPanel();
		idPanel.add(new JLabel ("ID: " +  dict.GetID()));
		idPanel.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		idPanel.setMaximumSize(new Dimension(1000,100));
		
		container.add(idPanel);
		
		container.add(new JScrollPane(new LanguagesPanel(dict.GetLanguages())));
	}
	public class LanguagesPanel extends JPanel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public LanguagesPanel(ArrayList<Language> langs){
			BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
			this.setLayout(layout);
		    this.setBorder(BorderFactory.createTitledBorder("Languages"));			
		    JPanel myPane = new JPanel();
			for (Language lan: langs){
				myPane = new JPanel();
				myPane.add(new LanguagePanel(lan));
				this.add(myPane);
			}

		}
		
	}
	
	public class LanguagePanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public LanguagePanel(Language lan) {
			BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
			this.setLayout(layout);
			this.add(new JLabel("Name: " + lan.GetName()));
			this.add(new JLabel("ISO_2: " + lan.GetISO()));
			this.add(new JLabel("ID: " + lan.GetID()));
			
		}
		
	}

}


