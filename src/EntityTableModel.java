import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class EntityTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dictionary dict;
	private ArrayList<Entity> entities;

	public EntityTableModel(Dictionary d, ArrayList<Entity> ent){
		this.dict = d;
		this.entities = ent;
	}
	@Override
	public int getColumnCount() {
		return dict.GetLanguages().size();
	}

	@Override
	public int getRowCount() {
		return this.entities.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		try{
		ArrayList<Word> temp = Word.GetWordByLanguage(entities.get(rowIndex).GetWords(),dict.GetLanguages().get(columnIndex));
		return new Words(temp,entities.get(rowIndex));
		}
		catch(Exception ex){
			return null;
		}
	}
	@Override
	public String getColumnName(int column) {
	    return dict.GetLanguages().get(column).GetName();
	}
    //Implement table cell tool tips.  
}
