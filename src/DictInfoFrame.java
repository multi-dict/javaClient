import java.awt.Dimension;

import javax.swing.JFrame;

public class DictInfoFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DictInfoFrame(Dictionary dict){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(dict.toString());
		this.setSize(800, 640);
		this.setMinimumSize(new Dimension(400, 400));
	}
}
