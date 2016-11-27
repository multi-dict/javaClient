import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class EntityJTable extends JTable {
	public EntityJTable(AbstractTableModel am){
		super(am);
	}
    @Override           
    public String getToolTipText(MouseEvent e) {
        String tip = null;
        java.awt.Point p = e.getPoint();
        int rowIndex = rowAtPoint(p);
        int colIndex = columnAtPoint(p);

        try {
            tip = ((Words)getValueAt(rowIndex, colIndex)).getToolTip();
        } catch (RuntimeException e1) {
            //catch null pointer exception if mouse is over an empty line
        }

        return tip;
    }

}
