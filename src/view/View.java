package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import agent.structures.SearchTreeNode;
import models.WorldHandler;
import models.cells.DragonStoneCell;
import models.cells.EmptyCell;
import models.cells.MapCell;
import models.cells.ObstacleCell;
import models.cells.WalkerCell;

public class View extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private WorldHandler world;
	private Stack<SearchTreeNode> trace;
	private JButton next;
	private JTable table;
	private JPanel topPanel;
	private JPanel bottomPanel;

	public View(WorldHandler wrd, Stack<SearchTreeNode> trc) {
		world = wrd;
		trace = trc;
		
		JPanel marginPanel = new JPanel();
		topPanel = new JPanel(new BorderLayout());
		bottomPanel = new JPanel(new BorderLayout());
		
		next = new JButton("Next");
		table = new JTable(world.map.mapM, world.map.mapN);
		table.enableInputMethods(false);
		
		topPanel.add(table, BorderLayout.CENTER);
		bottomPanel.add(next, BorderLayout.CENTER);
		
		refreshMap();
		
		this.setTitle("SaveWestros");
		this.getContentPane().setLayout(new BorderLayout()); 
        this.setVisible(true);
        this.getContentPane().setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);           
        setSize(1500,500);
        this.getContentPane().add(marginPanel, BorderLayout.EAST);
        this.getContentPane().add(marginPanel, BorderLayout.WEST);
		this.getContentPane().add(topPanel, BorderLayout.CENTER);
		this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
	}

	public void displayNode(SearchTreeNode current) {
		switch(current.getOperatorApplied()){
		case DOWN: world.onMoveDown();break;
		case UP: world.onMoveUp();break;
		case LEFT: world.onMoveLeft();break;
		case RIGHT: world.onMoveRight();break;
		case KILL: world.onAttack();break;
		case PICKUP: world.onPickUp();break;
		}
		refreshMap();
	}
	
	private void refreshMap(){
		topPanel.remove(table);
		String[][] data = new String[world.map.mapM][world.map.mapN];
		for(int i=0; i<world.map.mapM; i++)
			for(int j=0; j<world.map.mapN; j++){
				MapCell temp = world.map.getMap()[i][j];
				data[i][j] = "";
				data[i][j] = (temp instanceof EmptyCell)?
						"_" : data[i][j];
				data[i][j] = (temp instanceof ObstacleCell)?
						"O" : data[i][j];
				data[i][j] = (temp instanceof WalkerCell)?
						"W" : data[i][j];
				data[i][j] = (temp instanceof DragonStoneCell)?
						"D" : data[i][j];
			}
		data[world.jon.getX()][world.jon.getY()] = "J";
		Object[] colNam = new Object[world.map.mapM];
		for(int i=0; i<colNam.length; i++)
			colNam[i] = " ";
		table = new JTable(data, colNam);
		table.setRowSelectionAllowed(false);
		table.setRowHeight(10);
		//table.setFillsViewportHeight(true);
		for(int i=0; i<table.getColumnCount(); i++)
			table.getColumnModel().getColumn(i).setPreferredWidth(2);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		topPanel.add(table, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() instanceof JButton){
			JButton temp = (JButton)event.getSource();
			if(temp.getText().contains("Next")){
				try{
					displayNode(trace.pop());
				}catch(Exception e){}
			}
		}
	}

}
