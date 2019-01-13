package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import Algorithms.DijkstrasAlgorithm;
import Game.BlackRectangle;
import Game.Fruit;
import Game.Game;
import Game.Ghost;
import Game.Packman;
import Geom.Point3D;
import Map.MyMap;
import Robot.Play;


/**
 * A graphical class that allows robots and fruits to be displayed on the map,
 * displaying the activity of algorithms, saving data, and performing a reconstruction of data from csv files or creating a game by selecting robots and fruits and positioning them on the map.
 * @author Efrat Cohen and Odelia Hochman
 *
 */



class GuiThread extends Thread
{
	MyFrameEx4 m_frame = null;
	private double degree;
	public GuiThread(MyFrameEx4 frame)
	{
		m_frame = frame; 
	}
	
	public void setDegree(double degree)
	{
		this.degree = degree;
	}
	
	public double getDegree()
	{
		return degree;
	}
	
	public void run()
	{
		
//		String file_name = "data/Ex4_OOP_example8.csv";
//
//		String map_data = playGame.getBoundingBox();
//		System.out.println("Bounding Box info: "+map_data);




//		for(int i=0;i<board_data.size();i++) {
//			System.out.println(board_data.get(i));
//
//		}

		
		System.out.println(m_frame.player.GetPoint3Dlocation().y()+""+m_frame.player.GetPoint3Dlocation().x());
		m_frame.getPlay1().setInitLocation(m_frame.player.GetPoint3Dlocation().y(),m_frame.player.GetPoint3Dlocation().x());
       
		m_frame.getPlay1().start();

		
		while(m_frame.getPlay1().isRuning()) 
		{
			System.out.println(getDegree());
			m_frame.getPlay1().rotate(getDegree()); 
			String info = m_frame.getPlay1().getStatistics();
			System.out.println(info);

			ArrayList<String> board_data = m_frame.getPlay1().getBoard();
			m_frame.game=new Game(board_data);
//			for(int a=0;a<board_data.size();a++) 
//			{
//				System.out.println(board_data.get(a));
//			}

			System.out.println();
			m_frame.repaint();
			
			try
			{
					Thread.sleep(100);

			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

		System.out.println("**** Done Game (user stop) ****");
		String info = m_frame.getPlay1().getStatistics();
		System.out.println(info);

	}

}

public class MyFrameEx4 extends JFrame implements MouseListener
{
	public  boolean  bIsUpdating = false;
	private static final long serialVersionUID = 1L;

	public BufferedImage ghostImage;
	public BufferedImage fruitImage;
	public BufferedImage packmanImage;
	public BufferedImage playerImage;
	public BufferedImage boxImage;
	
	public MyMap map= new MyMap();
	Game game = new Game();
	DijkstrasAlgorithm pathManager;
	Packman player = new Packman();
	double direction = 0;
     private Play play1 ;
	private JTextField field;
	public  enum GameObjectType {PackmanType,FruitType,GhostType,PlayerType,BlackRectangleType,Undefined};
	GameObjectType gameType2Add =GameObjectType.PlayerType;
	private int idxP=0;
	private int idxF=0;
	public int action=0;
	long gameTimeSec = 0;
	GuiThread m_guiThrd;
	public boolean bIsRunning = false;

	public MyFrameEx4() 
	{
		m_guiThrd = new GuiThread(this);
		initGUI();		
		this.addMouseListener(this);
	}

	public void Repaint() 
	{

		gameTimeSec++;
		//boolean ans = pathManager.UpdateGameState(gameTimeSec);
		//if(ans == false)
		//pacsTimer.Stop();
		repaint();

	}

	private void initGUI() 
	{

		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu"); 
		Menu file= new Menu("File");
		Menu addItem= new Menu("Add item");
		MenuItem itemRun = new MenuItem("Run Game");
		MenuItem itemStop = new MenuItem("Stop Game");
		MenuItem itemPlayer=new MenuItem("Add Player");
		//MenuItem itemFruit=new MenuItem("Add Fruit");
		MenuItem itemClear=new MenuItem("clear screen");
		MenuItem itemFile= new MenuItem("Load File");
		MenuItem itemSave= new MenuItem("Save Game");

		menuBar.add(menu);
		menuBar.add(file);
		menuBar.add(addItem);
		 addItem.add(itemPlayer);
		menu.add(itemRun);
		menu.add(itemStop);
		menu.add(itemClear);
		file.add(itemFile);
		file.add(itemSave);
		//addItem.add(itemPackman);
		//addItem.add(itemFruit);
		this.setMenuBar(menuBar);


		ActionListener argRun = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{

				
				action = 2;
				String map_data = getPlay1().getBoundingBox();
				System.out.println("Bounding box information: " + map_data);
				bIsRunning = true;
				m_guiThrd.start();

			}

		};

		ActionListener argFile = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{

				if(e.getActionCommand()=="Load File")
				{
					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
					chooser.setFileFilter(filter);
					int returnVal = chooser.showOpenDialog(null);
					if(returnVal == JFileChooser.APPROVE_OPTION || true)

					{
						String csvFileName = chooser.getSelectedFile().getPath();
						//String csvFileName="game_1543693911932.csv";
						setPlay1(new Play(csvFileName));
						getPlay1().setIDs(18,73);
						game.LoadCsv(csvFileName);
						System.out.println("You chose to open file: " + chooser.getSelectedFile().getName());
						repaint();
						
						//pathManager= new algoEx4(game.getArrListRectangle());
						//pathManager.InitShortestPathAlgo();


						if(returnVal == JFileChooser.CANCEL_OPTION)
						{
							field.setText("cancel");
						}

					}
					
					action = 1;

				}
				else 
					if(e.getActionCommand()=="Save Game")
					{
						JFileChooser chooser = new JFileChooser();
						FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
						chooser.setFileFilter(filter);
						int returnVal = chooser.showOpenDialog(null);
						if(returnVal == JFileChooser.APPROVE_OPTION || true)

						{
							String csvFileName = chooser.getSelectedFile().getPath();
							game.UpLoadCsv(csvFileName);
						}

						System.out.println("You chose to save file: " + chooser.getSelectedFile().getName());
						repaint();
						// pathManager= new ShortestPathAlgo(game);
						// pathManager.InitShortestPathAlgo();


						if(returnVal == JFileChooser.CANCEL_OPTION)
						{
							field.setText("cancel");
						}
					}

			}
		};


		ActionListener argStop = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				getPlay1().stop();

			}
		};

		itemRun.addActionListener(argRun);
		itemStop.addActionListener(argStop);
		itemClear.addActionListener(userSelectionListener);
		itemPlayer.addActionListener(userSelectionListener);
		itemSave.addActionListener(argFile);
		itemFile.addActionListener(argFile);		

	}


	ActionListener userSelectionListener=new ActionListener() 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{ 
			//repaint()
			if(e.getActionCommand().equals("Clear Screen"))
			{
				game.clearGame();
				repaint();
			}
			
			if(e.getActionCommand().equals("Add Player")) 
			{
				gameType2Add = GameObjectType.PlayerType;
				repaint();
			}

		}
		
		
	};	


	public void paint(Graphics g) 
	{
		bIsUpdating = true;
		g.drawImage(map.GetImage(), 0, 0, this);

		ArrayList<Packman> packmanLst= game.getArrListPac();
		if(packmanLst!=null) 
			DrawPackmans(g,packmanLst);
		

		ArrayList<Fruit> fruitLst= game.getArrListFruit();	
		if(fruitLst!=null)
			DrawFruits(g,fruitLst);

		ArrayList<Ghost> ghostLst= game.getArrListGhost();	
		if(ghostLst!=null)
			DrawGhosts(g,ghostLst);

		ArrayList<BlackRectangle> rectangleLst= game.getArrListRectangle();	
		if(rectangleLst!=null)
			DrawRectangles(g,rectangleLst);


		bIsUpdating = false;
		//repaint();
	}


	private void DrawRectangles(Graphics g,ArrayList<BlackRectangle> rectangleLst) 
	{
		

		for (Iterator<BlackRectangle> iterator = rectangleLst.iterator(); iterator.hasNext();) 
		{

			BlackRectangle b = iterator.next();

		

			Point secondpointUpRight=b.getLocationPointUpRightInPixel();
			Point firstpointDownLeft=b.getLocationPointDownLeftInPixel();
			Point thirdpointUpLeft=b.getLocationPointUpLeftInPixel();

			double width=b.rectangleWidthPixel(thirdpointUpLeft, firstpointDownLeft);
			double length =b.rectangleLengthPixel(thirdpointUpLeft,secondpointUpRight);
			g.setColor(Color.BLACK);
			g.fillRect(thirdpointUpLeft.x,thirdpointUpLeft.y, (int)width , (int)length);

		}

	}

	private void DrawPackmans(Graphics g, ArrayList<Packman> packmanLst) {

		try { 
			packmanImage = ImageIO.read(new File("pacman3.png"));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		for (Iterator<Packman> iterator = packmanLst.iterator(); iterator.hasNext();) 
		{
			Packman p = iterator.next();
			Point pixelPoint =MyMap.getPositionOnScreen(p.GetPoint3Dlocation().y(), p.GetPoint3Dlocation().x());
			if(p.GetType()=='P')
			{
			g.drawImage(packmanImage,pixelPoint.x-25,pixelPoint.y-25,60,60,this);
			}
			if(p.GetType()=='M')
			   g.drawImage(playerImage,pixelPoint.x-15,pixelPoint.y-15,70,70,this);

				
		}

	}

	private void DrawFruits(Graphics g, ArrayList<Fruit> fruitLst) {

		for (Iterator<Fruit> iterator = fruitLst.iterator(); iterator.hasNext();) 
		{
			Fruit f = iterator.next();
			g.setColor(Color.green);
			Point pixelPoint =MyMap.getPositionOnScreen(f.GetPoint3Dlocation().y(), f.GetPoint3Dlocation().x());
			g.fillOval(pixelPoint.x, pixelPoint.y, 15, 15);
			//g.drawImage(fruitImage,pixelPoint.x-20,pixelPoint.y-20,20,20,this);

		}	
	}

	private void DrawGhosts(Graphics g, ArrayList<Ghost> ghostLst) {

		try {
			ghostImage = ImageIO.read(new File("ghost4.png")); 
		} 
		catch (IOException e) { 
			e.printStackTrace();
		}
		for (Iterator<Ghost> iterator = ghostLst.iterator(); iterator.hasNext();) 
		{
			Ghost ghost = iterator.next();
			Point pixelPoint =MyMap.getPositionOnScreen(ghost.GetPoint3Dlocation().y(), ghost.GetPoint3Dlocation().x());
			g.drawImage(ghostImage,pixelPoint.x-15,pixelPoint.y-15,60,60,this);
		}
	}

//	private void DrawPlayer(Graphics g,Packman myPlay)
//	{
//		try {
//			playerImage = ImageIO.read(new File("player.png"));
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		Point pixelPoint =MyMap.getPositionOnScreen(myPlay.GetPoint3Dlocation().y(),myPlay.GetPoint3Dlocation().x());
//		g.drawImage(playerImage,pixelPoint.x-15,pixelPoint.y-15,70,70,this);
//		repaint();
//	}

	@Override
	public void mouseClicked(MouseEvent arg) 
	{
		System.out.println("mouse Clicked");
		System.out.println("("+ arg.getX() + "," + arg.getY() +")");
		int x = arg.getX();
		int y = arg.getY();
		Point mouse= new Point(x,y);
		
		
		Point3D pnt3D = new Point3D(MyMap.getPositionOnMap(mouse));
		System.out.println(pnt3D.y() + " , " + pnt3D.x());
		Point point = MyMap.getPositionOnScreen(pnt3D.x(), pnt3D.y());
		System.out.println(point.y + " , " + point.x);

//		
//		Point p = MyMap.getPositionOnScreen(pnt3D.y(), pnt3D.x())
//System.out.println(p.getX() + " , " + p.getY());
		
//			 if(gameType2Add==GameObjectType.PlayerType) 
//			 {
//				 Packman MyPlayer= new Packman(pnt3D,20.0,10.0,idxP);
//				 game.AddPackman(MyPlayer);	
//				 
//				 gameType2Add=GameObjectType.Undefined;
//				
//			
//			 }
		if(action==1)
		{		
			ArrayList<String> board_data = getPlay1().getBoard();
			game=new Game(board_data);	
			
			 player = new Packman(pnt3D,20.0,10.0,idxP);
			 game.AddPackman(player);	
			 
			getPlay1().setInitLocation(32.1040,35.2061);
			//player.SetPoint3DLocation(pnt3D);
		}
		else if(action==2)
		{
			player.setAzimuth(mouse,player.GetPoint3Dlocation());
			direction=player.getAzimuth();
			//System.out.println("Degree:" + direction);
			m_guiThrd.setDegree(direction);
		}


		repaint();
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		//System.out.println("mouse entered");

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {


	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	public static void main(String[] args)
	{

		MyFrameEx4 window= new MyFrameEx4();
		window.setVisible(true);
		window.setSize(window.map.GetImage().getWidth(),window.map.GetImage().getHeight());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	}

	public Play getPlay1() {
		return play1;
	}

	public void setPlay1(Play play1) {
		this.play1 = play1;
	}






}



