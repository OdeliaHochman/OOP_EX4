package Game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;

import Algorithms.ShortestPathAlgo;

//import java.util.Timer;
//import Coords.MyCoords;
//import java.awt.Point;
//import Map.Map;


/**
 * The department represents a path made up of a GPS point collection.
 * @author Efrat Cohen and Odelia Hochman
 *
 */
 
public class Path {//implements  Comparable{


	//ArrayList<Fruit> fruitList = new ArrayList<>();
	private double time0 = 0;
	private double timeT = -1;
	private double DeltaTime = -1;
	private int idPackman =-1;
	private int idFruit =-1;
	


	/**
	 * init
	 */
	public Path(int idPc,int idFrt, double DeltaTime)/*double time0,double timeT*/ 
	{

		idPackman = idPc;
		idFruit = idFrt;
		this.DeltaTime = DeltaTime-time0;
		//this.time0 = time0;
		this.timeT = DeltaTime+time0;
		
	}

	public int GetIDPackman() 
    {
    	return idPackman;
    }

    public int GetIDFruit() 
    {
    	return idFruit;
    }
    
   public double GetTimeT() 
   {
	   return timeT;
   }

   public double GetTime0() 
   {
	   return time0;
   }
   public void SetTimeT(double timeT) 
   {
	   this.timeT=timeT;
   }

   public void SetTime0(double time0) 
   {
	   this.time0= time0;
   }

   public void SetDeltatime(double DeltaTime) 
   {
	   this.DeltaTime=DeltaTime;
   }

   public double GetDeltatime() 
   {
	   return DeltaTime;
   }

	public String toString()
	{
		return "Path:\n packman ID: " + idPackman +"Fruit ID: "+ idFruit+", time total:" + timeT + ", Packman start time:" + time0 +"delta time:"+DeltaTime;
	}

	
    public static Comparator<Path> PathDTimeComparator = new Comparator<Path>() {

	public int compare(Path p1, Path p2) 
	{
		//  double delta1 = p1.GetDeltatime();
		  //double delta2 = p2.GetDeltatime();
           if(p1.DeltaTime<p2.DeltaTime ) return 1;
           if(p1.DeltaTime==p2.DeltaTime) return 0;
           else
           return -1;
		    }};
	

    /*Comparator for sorting the list by roll no*/
//    public static Comparator<Path> PathComparer = new Comparator<Path>() {
//
//	public int compare(Path p1, Path p2) {
//
//	   double d1 = p1.GetDeltatime();
//	   double d2 = p2.GetDeltatime();
//
//	   /*For ascending order*/
//	   return (int)(d1-d2);
//
//	   /*For descending order*/
//	   //rollno2-rollno1;
//   }};

	

}
