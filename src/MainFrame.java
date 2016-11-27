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
import javax.swing.JTable;
import javax.swing.JTextField;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int PADDING = 30; // for example
	private ArrayList<Dictionary> dictionaries;
	private ArrayList<Entity> entities;
	private EntityTableModel eModel;
	private JScrollPane entityScrollPane;
	private JPanel container;
	private JPanel bottomMenu;
	private Dictionary loadedDict;
	
	private JTextField searchBar;
	private JComboBox dictCBX;
	private JTable entityTable;

	public MainFrame() throws Exception {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Dictionary Application");
		this.setSize(800, 640);
		this.setMinimumSize(new Dimension(650, 250));

		this.Refresh();
		this.container = new JPanel();
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
		dictRetriveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {

					CheaterReDraw();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		// dictMenu.setMaximumSize(new Dimension(1400, 100));
		dictMenu.add(dictRetriveButton);

		JButton dictInfoButton = new JButton("info");
		dictInfoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				DictInfoFrame dictInfo = new DictInfoFrame((Dictionary) dictCBX.getSelectedItem());
				dictInfo.show(true);
			}

		});
		dictMenu.add(dictInfoButton);
		container.add(dictMenu);
		this.loadedDict = (Dictionary) dictCBX.getSelectedItem();
		entities = Entity.GetEntities(Application.user, (Dictionary) dictCBX.getSelectedItem());
		eModel = new EntityTableModel((Dictionary) dictCBX.getSelectedItem(), entities);
		this.entityTable = new JTable(eModel);
		this.entityScrollPane = new JScrollPane(this.entityTable);
		container.add(this.entityScrollPane);
		this.add(container);

		bottomMenu = new JPanel();
		bottomMenu.setLayout(new FlowLayout(FlowLayout.LEFT, PADDING, 5));
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Words w = (Words) (entityTable.getValueAt(entityTable.getSelectedRow(),entityTable.getSelectedColumn()));
				if(w == null){
					return;
				}
				DeleteWordFrame dwf = new DeleteWordFrame(w);
				dwf.show(true);
				
			}
		});
		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Words w = (Words) (entityTable.getValueAt(entityTable.getSelectedRow(),entityTable.getSelectedColumn()));
				if(w == null){
					return;
				}
				EditWordFrame ewf = new EditWordFrame(w, w.GetEntityID(), loadedDict);
				ewf.show(true);
			}
		});
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Words w = (Words) (entityTable.getValueAt(entityTable.getSelectedRow(),entityTable.getSelectedColumn()));
				if(w == null){
					return;
				}
				AddWordFrame dwf = new AddWordFrame(w.GetEntityID(),loadedDict);
				dwf.show(true);
			}
		});
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					entities = Entity.Filter(entities, searchBar.getText());
					eModel = new EntityTableModel((Dictionary) dictCBX.getSelectedItem(), entities);
					entityTable = new JTable(eModel);
					container.remove(entityScrollPane);
					container.remove(bottomMenu);
					entityScrollPane = new JScrollPane(entityTable);
					container.add(entityScrollPane);
					container.add(bottomMenu);
					if (getSize().height % 2 == 0) {
						setSize(new Dimension(getSize().width + 1, getSize().height));
					} else {
						setSize(new Dimension(getSize().width - 1, getSize().height));
					}				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		bottomMenu.add(addButton);
		bottomMenu.add(deleteButton);
		bottomMenu.add(editButton);

		deleteButton.setEnabled(Application.user.GetPermissions().CanDelete());
		if(!Application.user.GetPermissions().CanDelete()){
			deleteButton.setToolTipText("No Permission");
		}
		addButton.setEnabled(Application.user.GetPermissions().CanAdd());
		editButton.setEnabled(Application.user.GetPermissions().CanEdit());

		bottomMenu.setBackground(Color.black);
		bottomMenu.setMaximumSize(new Dimension(1400, 100));
		this.searchBar = new JTextField(15);
		bottomMenu.add(this.searchBar);
		bottomMenu.add(searchButton);
		container.add(bottomMenu);

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
	public void CheaterReDraw() throws Exception{
		loadedDict = (Dictionary) dictCBX.getSelectedItem();
		entities = Entity.GetEntities(Application.user, (Dictionary) dictCBX.getSelectedItem());
		eModel = new EntityTableModel((Dictionary) dictCBX.getSelectedItem(), entities);
		entityTable = new JTable(eModel);
		container.remove(entityScrollPane);
		container.remove(bottomMenu);
		entityScrollPane = new JScrollPane(entityTable);
		container.add(entityScrollPane);
		container.add(bottomMenu);
		if (getSize().height % 2 == 0) {
			setSize(new Dimension(getSize().width + 1, getSize().height));
		} else {
			setSize(new Dimension(getSize().width - 1, getSize().height));
		}
	}

}
