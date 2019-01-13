package Algorithms;


import java.awt.Point;
import java.awt.geom.Line2D;

import java.awt.geom.Line2D.Double;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import Coords.MyCoords;
import GUI.MyFrameEx4;
import Game.BlackRectangle;
import Game.Fruit;
import Game.Game;
import Game.Ghost;
import Game.Packman;
import Game.Path;
import Game.Player;
import Geom.Point3D;
import Map.MyMap;
import graph.Graph;
import graph.Graph_Algo;
import graph.Node;

/**
 *The class calculates an algorithm for the player.
 *The goal of the algorithm - the shortest and quickest way to eat the fruits with the highest score.
 *In this algorithm we used the Dijkstra algorithm
 * @author Efrat Cohen and Odelia Hochman
 *
 */
public class DijkstrasAlgorithm {


	Graph G = new Graph(); 
	String source = "a";
	String target = "b";
	//	String start = source;
	//	String end =target;
	Game game = new Game();
	ArrayList<BlackRectangle> rectangles=game.getArrListRectangle();
	Iterator<BlackRectangle> itrRec = game.itrRectangle();
	ArrayList<Ghost> ghost=game.getArrListGhost();
	ArrayList<Packman> packmans =game.getArrListPac();
	Iterator<Packman> itrPac = game.itrPackman();
	ArrayList<Fruit> fruits =game.getArrListFruit();
	Iterator<Fruit> itrFru = game.itrFruit();
	Player player= new Player();
	ShortestPathAlgo shortPathAlgo = new ShortestPathAlgo(game);
	private Point3D bestLocationPlayer3D;
	MyCoords coords = new MyCoords();
	private Point3D [] arrRecPoints = new Point3D[rectangles.size()*4];
	private final static double EPSILON = 0.00001;
	ArrayList<Line2D>lines = new ArrayList<>();





	public DijkstrasAlgorithm(ArrayList<BlackRectangle> rectangles)
	{
		this.rectangles=rectangles;
	}

	/**
	 * @return An array containing points (point3D) of all rectangles
	 */
	public Point3D[] arrRectanglePoint3D()
	{
		int i=0;
		while(itrRec.hasNext())
		{
			BlackRectangle b = itrRec.next();

			arrRecPoints[i]= new Point3D(b.getPointUpRight().x()+EPSILON,b.getPointUpRight().y()+EPSILON);
			arrRecPoints[i+1]=new Point3D(b.thirdpointUpLeft(b.getPointUpRight(),b.getPointDownLeft()).x()+EPSILON,b.thirdpointUpLeft(b.getPointUpRight(),b.getPointDownLeft()).y()+EPSILON);
			arrRecPoints[i+2]=new Point3D(b.pointDownRight(b.getPointUpRight(),b.getPointDownLeft()).x()+EPSILON,b.pointDownRight(b.getPointUpRight(),b.getPointDownLeft()).y()+EPSILON);
			arrRecPoints[i+3]=new Point3D(b.getPointDownLeft().x()+EPSILON,b.getPointDownLeft().y()+EPSILON);
			i=i+3;
		}

		return arrRecPoints;
	}

	/**
	 * The function calculates the sides of all the rectangles and map and enters the arraylist.
	 * @param rectangles-arraylist from game class
	 * @return arr lines
	 */
	public ArrayList<Line2D> arrLines(ArrayList<BlackRectangle>rectangles)
	{
		rectangles=this.rectangles;

		while(itrRec.hasNext())
		{
			BlackRectangle b =itrRec.next();
       
			Line2D lineUpRec = new Line2D.Double(b.getPointUpRight().x()+EPSILON,b.getPointUpRight().y()+EPSILON,b.thirdpointUpLeft(b.getPointUpRight(), b.getPointDownLeft()).x()+EPSILON,b.thirdpointUpLeft(b.getPointUpRight(), b.getPointDownLeft()).y()+EPSILON);
			Line2D lineDownRec = new Line2D.Double(b.getPointDownLeft().x()+EPSILON,b.getPointDownLeft().y()+EPSILON,b.pointDownRight(b.getPointUpRight(),b.getPointDownLeft()).x()+EPSILON,b.pointDownRight(b.getPointUpRight(),b.getPointDownLeft()).y()+EPSILON);
			Line2D lineRigthRec = new Line2D.Double(b.getPointUpRight().x()+EPSILON,b.getPointUpRight().y()+EPSILON,b.pointDownRight(b.getPointUpRight(),b.getPointDownLeft()).x()+EPSILON,b.pointDownRight(b.getPointUpRight(),b.getPointDownLeft()).y()+EPSILON);
			Line2D lineLeftRec = new Line2D.Double(b.thirdpointUpLeft(b.getPointUpRight(), b.getPointDownLeft()).x()+EPSILON,b.thirdpointUpLeft(b.getPointUpRight(), b.getPointDownLeft()).y()+EPSILON,b.getPointDownLeft().x()+EPSILON,b.getPointDownLeft().y()+EPSILON);
			lines.add(lineUpRec);
			lines.add(lineDownRec);
			lines.add(lineRigthRec);
			lines.add(lineLeftRec);
		}

		Line2D lineupMapEps=new Line2D.Double(32.105442,35.202346,32.105328, 35.211781);   
		Line2D lineDownMapEps=new Line2D.Double(32.102097, 35.202456,32.105328, 35.211781);
		Line2D lineRigthMapEps=new Line2D.Double(32.105328, 35.211781,32.105328, 35.211781);
		Line2D lineLeftMapEps=new Line2D.Double(32.105442,35.202346,32.102097, 35.202456);
		lines.add(lineupMapEps);
		lines.add(lineDownMapEps);
		lines.add(lineRigthMapEps);
		lines.add(lineLeftMapEps);

		return lines;
	}


	public void algo()
	{
		while(itrFru.hasNext())
		{
			Fruit WantedFruit =findClosestFruit(player,fruits); 
			Point3D closestFruit=WantedFruit.GetPoint3Dlocation();
			Point3D startP= player.getPointLocationPlayer();
			boolean isRectangle=IsCollisionWithRectangle(startP, closestFruit);
			double distanceP2F=0;

			if(isRectangle==false)  
			{
				distanceP2F=coords.distance3d(startP,closestFruit);
				player.setLocationPlayer(WantedFruit.GetPoint3Dlocation());
				fruits.remove(WantedFruit.GetId());

			}

			else //Dijkstras Algorithm
			{
				bestLocationPlayer3D=getBestLocationPlayer();
				Point3D[] pointsNotDisturb = new Point3D[arrRecPoints.length];
				G.add(new graph.Node(source)); //a

				for(int i=1;i<arrRecPoints.length;i++)  //Arr rectangle length
				{
					if(!(IsCollisionWithRectangle(player.getPointLocationPlayer(),arrRecPoints[i])))
					{
						graph.Node node = new graph.Node("B_N"+i);
						G.add(node);
						pointsNotDisturb[i]=arrRecPoints[i];
					}
				}
				
				G.add(new graph.Node(target)); //b
				
				int m =0;
				while(m<G.size())
				{
					for(int i=1;i<=G.size();i++)
					{
						//i>m
						graph.Node node1=G.getNodeByIndex(m); 
						graph.Node node2 =G.getNodeByIndex(i);
						Point3D node11=pointsNotDisturb[m];
						Point3D node22=pointsNotDisturb[i];	
						
						if(!(intersectsLine(node11,node22)))
						{
							double dis =coords.distance3d(node11,node22);
							G.addEdge(node1.get_name(),node2.get_name(),dis);
						}
					}
					m++;
				}
				


				Graph_Algo.dijkstra(G, source);
				Node b = G.getNodeByName(target);
				ArrayList<String> shortestPath = b.getPath();
				for(int n=0;n<shortestPath.size();n++) 
				{
					System.out.print(","+shortestPath.get(n));
				}

			}


		}
	}


	/**
	 * Check if there is a line that cuts our route from player to fruit
	 * @param startPlayer - player location
	 * @param endClosestFruit - fruit location
	 * @return
	 */
	public boolean intersectsLine(Point3D startPlayer , Point3D endClosestFruit)
	{
		boolean isIntersect=false;
		for(int i=0; i<lines.size();i++)
		{
			if(lines.get(i).intersectsLine(startPlayer.x(),startPlayer.y(),endClosestFruit.x(),endClosestFruit.y()));
			{
				isIntersect=true;
			}
		}
		return isIntersect;
	}


	/**
	 * The function checks whether there are rectangles that are in the middle of the path between the player and the nearest fruit
	 * @param startPlayer - player location
	 * @param endClosestFruit - fruit location
	 * @return true if there is a rectangle in the middle of the track that bothers
	 */
	public boolean IsCollisionWithRectangle(Point3D startPlayer , Point3D endClosestFruit)
	{
		boolean isDisturbingRectangle = false;
		for (int i = 0; i < arrRecPoints.length; i++) 
		{
			if(arrRecPoints[i].x()==startPlayer.x() && arrRecPoints[i].x()==endClosestFruit.x()) // Vertical line
			{
				if(startPlayer.y()<=arrRecPoints[i].y() && arrRecPoints[i].y()<=endClosestFruit.y())
				{
					isDisturbingRectangle=true;
					return isDisturbingRectangle;
				}
			}
			else if(arrRecPoints[i].y()==startPlayer.y() && arrRecPoints[i].y()==endClosestFruit.y()) // Horizontal line
			{
				if(startPlayer.x()<=arrRecPoints[i].x() && arrRecPoints[i].x()<=endClosestFruit.x())
				{
					isDisturbingRectangle=true;
					return isDisturbingRectangle;
				}
			}
			else      //A diagonal line with a slope
			{
				if(startPlayer.x()<=arrRecPoints[i].x() && arrRecPoints[i].x()<=endClosestFruit.x() && startPlayer.y()<=arrRecPoints[i].y() && arrRecPoints[i].y()<=endClosestFruit.y())
				{
					isDisturbingRectangle=true;
					return isDisturbingRectangle;
				}
			}

		}
		return isDisturbingRectangle;
	}

	/**
	 * the function find the closest fruit to the player
	 * @param p-player
	 * @param fruit
	 * @return closest fruit
	 */
	public Fruit findClosestFruit(Player p, ArrayList<Fruit> fruit) 
	{

		Fruit wantedFruit = new Fruit(fruits.get(0));
		double distance=0;
		double shortDis=MyMap.DisBetweenPixels(p.GetPointlocation(),fruits.get(0).GetPointlocation());;

		while(itrFru.hasNext())
		{
			Fruit fr = itrFru.next();

			Point pointPl=p.GetPointlocation();  
			Point pointFr=fr.GetPointlocation();
			distance=MyMap.DisBetweenPixels(pointPl, pointFr);

			if(distance<shortDis)
			{
				shortDis=distance;
				wantedFruit=fr;
			}
		}

		return wantedFruit;

	}

	/**
	 * The function calculates distance between two points of a pixel
	 * @param p1-point by pixel
	 * @param p2-point by pixel
	 * @return distance
	 */
	public double DisBetweenPixels(Point p1, Point p2) 
	{
		MyCoords coordsUtil= new MyCoords();
		Point3D newCoordinte1= MyMap.getPositionOnMap(p1);
		Point3D newCoordinte2= MyMap.getPositionOnMap(p2);	
		double distancePoints=coordsUtil.distance3d(newCoordinte1, newCoordinte2);
		return distancePoints;	
	}




	/**
	 * @return Point3D-best start location for the player
	 */
	public Point3D getBestLocationPlayer()  
	{
		int i=0;
		return this.bestLocationPlayer3D=packmans.get(i).GetPoint3Dlocation();
	}


	public String toString()
	{
		String ans ="shortestPath: ";
		Node b = G.getNodeByName(target);
		ArrayList<String> shortestPath = b.getPath();
		for(int n=0;n<shortestPath.size();n++) 
		{
			ans+=","+shortestPath.get(n);
		}
		return ans;
	}

}
