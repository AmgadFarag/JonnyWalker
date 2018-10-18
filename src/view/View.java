package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;

import com.sun.glass.events.KeyEvent;

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
	private LinkedList<SearchTreeNode> trace;
	private JButton next;
	private JTable table;
	private JPanel topPanel;
	private JPanel bottomPanel;

	public View(WorldHandler wrd, LinkedList<SearchTreeNode> trace2) {
		world = wrd;
		trace = trace2;
		
		JPanel panel = new JPanel();
	    getContentPane().add(panel);

	    int row = world.map.mapM;
	    int col = world.map.mapN;
	    row = 10;
	    col = 10;
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(20, 20, 1800, 1000);
	    panel.setLayout(new GridLayout(row, col));

	    JLabel[][] grid= new JLabel[row+1][col+1];
	    for (int i = 0; i < row; i++){
	        for (int j = 0; j < col; j++){
	            grid[i][j] = new JLabel();
	            grid[i][j].setBorder(new LineBorder(Color.BLACK));
	            //grid[i][j].setBackground(Color.black);
	            grid[i][j].setOpaque(true);
	            panel.add(grid[i][j]);
	        }
	    }
	    ImageIcon icon = new ImageIcon("Jon.PNG");
	    icon.setImage(getScaledImage(icon.getImage(),50,50));
	    grid[world.map.mapM-1][world.map.mapN-1].setIcon(icon);
	    
	    next = new JButton("Next");
	    grid[9][9].add(next);
	    setTitle("SaveWestros");
	    InputMap im = panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = panel.getActionMap();

	    im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "space");
	    am.put("space", new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("sadadsa");
				 grid[world.map.mapM][world.map.mapN].setIcon(icon);
				
				
			}
		});
	    add(panel);
	    setVisible(true);
	    
	    
	    
	   
//		
//		JPanel marginPanel = new JPanel();
//		topPanel = new JPanel(new BorderLayout());
//		bottomPanel = new JPanel(new BorderLayout());
//		
//		next = new JButton("Next");
//		table = new JTable(world.map.mapM, world.map.mapN);
//		table.enableInputMethods(false);
//		
//		topPanel.add(table, BorderLayout.CENTER);
//		bottomPanel.add(next, BorderLayout.CENTER);
//		
//		refreshMap();
//		
//		this.setTitle("SaveWestros");
//		this.getContentPane().setLayout(new BorderLayout()); 
//        this.setVisible(true);
//        this.getContentPane().setVisible(true);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);           
//        setSize(1500,500);
//        this.getContentPane().add(marginPanel, BorderLayout.EAST);
//        this.getContentPane().add(marginPanel, BorderLayout.WEST);
//		this.getContentPane().add(topPanel, BorderLayout.CENTER);
//		this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
	}
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
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
