package Algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;

import Coords.MyCoords;
import Coords.coords_converter;
import Game.Fruit;
import Game.Game;
import Game.Packman;
import Game.Path;
import Game.Solution;
import Geom.Point3D;
import Map.MyMap;

import java.awt.Point;
//Path Manager
/**
 * A department that receives a collection of robots and fruits and computers
 *The optimal route so that all the fruits will be "eaten" as quickly as possible -
 *this is the central algorithmic department and includes the calculation of "fruit tracks" for each of the producers.
 * @author Efrat Cohen and Odelia Hochman
 *
 */
public class ShortestPathAlgo {

	private Game game;
	public Path path;
	private double speed;
	private long gameTimeSec;
	ArrayList<Packman> packmanList;
	ArrayList<Fruit>fruitList;
	//private HashSet<Integer> hashAvailableFrt = new HashSet<>();
	private HashMap<Integer,Fruit> hashAvailableFruit = new HashMap<>();
	private HashMap<Integer,Packman> hashAvailablePackman = new HashMap<>();
	//double timeTotal = path.getTime0()+path.getDeltatime();//efrat 24.12.18
	Solution allPath = new Solution();
	//Path newPath=new Path();
    private int nAvailableFruit =0;

	
	
	public ShortestPathAlgo(Game game) 
	{
		this.game=game;
		//InitPacmansLocation();
		InitShortestPathAlgo();
	}

	private void InitPacmansLocation()
	{
		for (int index =0; index< packmanList.size();index++) 
		{
		  Packman pc = this.packmanList.get(index);	
		  hashAvailablePackman.put(pc.GetId(),pc);
		}
	}
	
	private void RemoveFruit(int idPc, int idFrut) 
	{
		Packman pcTst = hashAvailablePackman.get(idPc);
		
		if(hashAvailableFruit.containsKey(idFrut))
		{
			Fruit frtTst = hashAvailableFruit.get(idFrut);
			pcTst.SetPointLocation(frtTst.GetPointlocation());
		   hashAvailableFruit.remove(idFrut);
		  if(hashAvailableFruit.isEmpty()) 
		  {
	        	System.out.println("The minimum time to eat all fruits:"+ " "+ gameTimeSec);

		  }
	    
		}
	}
	private void InitFruitsLocation()
	{
		for (int index =0; index< fruitList.size();index++) 
		{
		 //hashAvailableFrt.add(index);
		 
		  Fruit frt = this.fruitList.get(index);
		  hashAvailableFruit.put(frt.GetId(),new Fruit(frt));
		}
		nAvailableFruit = fruitList.size();

	}
	boolean IsEatable(int idPc,int idFrut,double pathTotalTime)
	{
		boolean ans1= IsPackmanReachedFruit(gameTimeSec,pathTotalTime);
		Fruit frt = hashAvailableFruit.get(idFrut);
		Packman pc = hashAvailablePackman.get(idPc);
		boolean ans2= false;
		if(frt!= null && pc!= null)
		{
			ans2 =	IsPackmanReachedFruit2(pc.GetPoint3Dlocation(),
				frt.GetPoint3Dlocation(),1);
		}
		return ans1||ans2;
	}
	boolean IsEatable2(int idPc,int idFrut,double pathTotalTime)
	{
		boolean ans1= IsPackmanReachedFruit(gameTimeSec,pathTotalTime);
		Fruit frt = hashAvailableFruit.get(idFrut);
		Packman pc = hashAvailablePackman.get(idPc);
		boolean ans2= false;
		if(frt!=null&&pc!=null)
		{
			int distanceToInt=1;
			ans2 =	IsPackmanReachedFruit2(pc.GetPoint3Dlocation(),
				frt.GetPoint3Dlocation(),distanceToInt);
		}
		return ans1||ans2;
	}
	public void EatFruit(Fruit idFrut)
	{
		idFrut.SetVisible(false);
		

	}
	public void InitShortestPathAlgo()
	{
		gameTimeSec =0;
		setTime(gameTimeSec);
		this.fruitList=game.getArrListFruit();
		this.packmanList=game.getArrListPac();
		
		for (int index =0; index< packmanList.size();index++) 
		{
		  Packman pc = this.packmanList.get(index);	
		  hashAvailablePackman.put(pc.GetId(),new Packman(pc));
		}
		
		for (int index =0; index< fruitList.size();index++) 
		{
		  Fruit frt = this.fruitList.get(index);
		  hashAvailableFruit.put(frt.GetId(),new Fruit(frt));
		}
		
		nAvailableFruit = hashAvailableFruit.size();
		
		while(nAvailableFruit>0) 
		{
			if(gameTimeSec == 0)
				InitPathes();
			
			gameTimeSec++;
			for (Entry<Integer, Packman> subSet: hashAvailablePackman.entrySet()) 
			 {
				final Integer idPc = subSet.getKey();
				 //final Packman pac = subSet.getValue();
				Path path = allPath.GetLastPath(idPc);
				int idFrut = path.GetIDFruit();
				double pathTotalTime = path.GetTimeT();

				boolean ans = IsEatable(idPc,idFrut,pathTotalTime);
				
				if(ans== true)
				{
					RemoveFruit(idPc,idFrut);
					nAvailableFruit--;
					if(nAvailableFruit == 0)//no more fruits to eat
					{
			        	//System.out.println("The minimum time to eat all fruits:"+ " "+ gameTimeSec);

						break;
					}
					
					PathPac2Fruit(hashAvailablePackman.get(idPc));	
				}
				else
				{
					if(hashAvailablePackman.containsKey(idPc))
					{
						Packman pcTst = hashAvailablePackman.get(idPc);
						Fruit frtTst = hashAvailableFruit.get(idFrut);
						if(pcTst.GetPointlocation().y >=2147483647)
					    {
					    	int x=0;
					    	x++;
					    }
						else
							if(frtTst!=null)
						       SetPosition2Time(path,pcTst,frtTst , gameTimeSec);
						
							else
								PathPac2Fruit(hashAvailablePackman.get(idPc));
					}
				}
		
			}
		}
		int x =0;
	    x++;
	    InitPacmansLocation();
	    InitFruitsLocation();
	    gameTimeSec =0;
	}
	public void UpadteFruitsArray()
	{
//		for (Iterator iterator = packmanList.iterator(); iterator.hasNext();) {
//			Packman pc = (Packman) iterator.next();
//		}
		fruitList.clear();
		for (Entry<Integer, Fruit> subSet: hashAvailableFruit.entrySet()) 
		 {
			  //final Integer idFrt = subSet.getKey();
			  final Fruit frt = subSet.getValue();
			  fruitList.add(frt);
		 }
		
	}
	
	
	
	public boolean UpdateGameState(double gameTimeSec)
	{
		for (Iterator<Packman> iterator = packmanList.iterator(); iterator.hasNext();)
		{
			Packman pc = (Packman) iterator.next();
			Path currPath= allPath.GetPath(pc.GetId(), pc.GetCurrentPathIndex());
			boolean bCurrPathChange = GetPackCurrentPath(pc,gameTimeSec,currPath);
			if(currPath!=null)
			{
			   Fruit frt = hashAvailableFruit.get(currPath.GetIDFruit());
//			   if(frt == null)
//			   {
//				   pc.IncreasePathIndex();
//				   bCurrPathChange = GetPackCurrentPath(pc,gameTimeSec,currPath);
//				   frt = hashAvailableFruit.get(currPath.GetIDFruit());
//			   }
			
			   SetPosition2Time(currPath, pc, frt, gameTimeSec);
			   boolean ans = IsEatable2(pc.GetId(),frt.GetId(),currPath.GetTimeT());

				if(bCurrPathChange||ans==true)//distance2Eat
				{
					EatFruit(frt);
				    System.out.println("Packman ID:"+pc.GetId()+ " , " +"Fruit ID:"+ currPath.GetIDFruit()+" , "+"Time Start: "+ currPath.GetTime0()+", "+"Time Totale:"+ " , "+currPath.GetTimeT());
					UpadteFruitsArray();
					bCurrPathChange = GetPackCurrentPath(pc,gameTimeSec,currPath);
					SetPosition2Time(currPath, pc, frt, gameTimeSec);
					

			    }
				else 
			    if(hashAvailableFruit.isEmpty()) 
		        {
			    
			        	System.out.println("The minimum time to eat all fruits:"+ " "+ gameTimeSec);
			 	
		        
		        }
					


			}	
		}
	
		
		if(hashAvailableFruit.size()>0)
		
			return true;
		
		else
			return false;
			
	}
	
	
	private boolean GetPackCurrentPath(Packman pc,double gameTimeSec, Path path) 
	{
		
		boolean bchangePath=false;
		path = allPath.GetPath(pc.GetId(),pc.GetCurrentPathIndex());
	     //  System.out.println("Packman ID:"+pc.GetId()+ " , " +"Fruit ID:"+ path.GetIDFruit()+" , "+"Time Start: "+ path.GetTime0()+", "+"Time Totale:"+ " , "+path.GetTimeT());

		if(path != null&& pc!=null)
		{
			if(gameTimeSec < path.GetTimeT())//if time in path, get current path;
			{
			   return bchangePath;
		    }
		    else
			{
				pc.IncreasePathIndex();// get next path
				path  = allPath.GetPath(pc.GetId(),pc.GetCurrentPathIndex());

				
				 bchangePath=true;
				return bchangePath;	
	        }
        }

		return bchangePath;
	}
	


	/**
	 * Route allocation
	 */
	private void InitPathes() 
	{
		for (int idx = 0; idx < this.packmanList.size(); idx++)
		{
			PathPac2Fruit(this.packmanList.get(idx));
		}
	
	}


	/**
	 * The function calculates the distance between the fruit and the packman
	 * @param p-packman
	 * @param f-fruit
	 * @return distance
	 */
	public static double distanceInTimeP2F(Packman p, Fruit f)
	{
		double distance=0.0;
		Point pointPc=p.GetPointlocation();
		Point pointFr=f.GetPointlocation();
		distance=MyMap.DisBetweenPixels(pointPc, pointFr);
		double time= distance/p.getSpeed();
		
		return time;

	}
	/**
	 * The function calculates one Pacman path to one fruit
	 * @param p-packman
	 */
	public void PathPac2Fruit(Packman p) 
	{
		
	   double[] pathFun= ShortestPathAlgo.findClosestFruit(p, hashAvailableFruit/*fruitList*/);
	   int idPackman=(int)pathFun[0];
	   int idFruit=(int)pathFun[1];
	   double theShortsTime =pathFun[2];
	   Path path = new Path(idPackman,idFruit,theShortsTime);
	   allPath.add(path);
	  // allPath.Print();//*************
	  
	}
private static double[] findClosestFruit(Packman p, HashMap<Integer, Fruit> hashLeftFruits) {
		// TODO Auto-generated method stub
	 //ArrayList<Double> arrTimeOfPc= new ArrayList<>();
	HashMap<Double, Integer> hashTimePc2FruitID = new HashMap<>();
	 for (Entry<Integer, Fruit> subSet: hashLeftFruits.entrySet()) 
	 {
		    final Integer idFruit = subSet.getKey();
		    final Fruit fruit = subSet.getValue();
		    
			double timeP2F=distanceInTimeP2F(p, fruit);
			hashTimePc2FruitID.put(timeP2F, fruit.GetId());
	  }
	   double theShortsTime=Double.MAX_VALUE;//arrTimeOfPc.get(0);
	   int idFruit=-1;
	   //for(int nextTime=1; nextTime<arrTimeOfPc.size(); nextTime++) 
	   for (Entry<Double, Integer> subSet: hashTimePc2FruitID.entrySet()) 
		 {
		   final Double time = subSet.getKey();
		  
		   if(time<theShortsTime) 
		   {
			   theShortsTime=time;
			   idFruit = subSet.getValue();
		   }
		   
	    }
	   double[] pathData= {(int)p.GetId(), (int)idFruit, theShortsTime};
	   
	   return pathData;
	}

//selectpathdata
	/**
	 * the function find the closest fruit to the packman
	 * @param p-packman
	 * @param fruit - arraylist of fruit
	 * @return array[id of packman , id of fruit, the shortest time]
	 */
   public static double[] findClosestFruit(Packman p, ArrayList<Fruit> fruit) 
   {
	  
	   ArrayList<Double> arrTimeOfPc= new ArrayList<>();
	   for (Iterator<Fruit> iterator = fruit.iterator(); 
			   iterator.hasNext();) 
	   {
		Fruit fr = (Fruit) iterator.next();
		double timeFP=distanceInTimeP2F(p, fr);
		arrTimeOfPc.add(timeFP);
	  }
	   double theShortsTime=arrTimeOfPc.get(0);
	   int idxFruit=0;
	   for(int nextTime=1; nextTime<arrTimeOfPc.size(); nextTime++) 
	   {
		   if(arrTimeOfPc.get(nextTime)<theShortsTime) 
		   {
			   theShortsTime=arrTimeOfPc.get(nextTime);
			   idxFruit=nextTime;
		   }
		   
	    }
	   double[] pathData= {(int)p.GetId(), (int)idxFruit, theShortsTime};
	   
	   return pathData;

	}
   
   /**
    * The function updates the Pacman position by time
    * @param path
    * @param pc-packman
    * @param frt-fruit
    * @param time
    */
	public void SetPosition2Time(Path path,Packman pc, Fruit frt,double time)
	{
		double proportionTime=(time-path.GetTime0())/(path.GetDeltatime()) ;
//		if(time==path.GetDeltatime()) 
//		{
//			time=path.GetDeltatime();
//		}
		double dx=frt.GetPoint3Dlocation().x()-pc.GetPoint3Dlocation().x();//Math.abs(frt.GetPoint3Dlocation().x()-pc.GetPoint3Dlocation().x());
		double dy=frt.GetPoint3Dlocation().y()-pc.GetPoint3Dlocation().y();//Math.abs(frt.GetPoint3Dlocation().y()-pc.GetPoint3Dlocation().y());
		double x=pc.GetPoint3Dlocation().x()+(proportionTime* dx);
		double y=pc.GetPoint3Dlocation().y()+(proportionTime* dy);
		
		 Point newPackman= new Point(MyMap.getPositionOnScreen(y, x));
//		 if((Math.abs(dx)<1) && (Math.abs(dy)<1))
//		 {
//			 newPackman.x = frt.GetPointlocation().x; newPackman.y = frt.GetPointlocation().y;
//		 }else
		// if(newPackman.x<1498)
			//{
			 	pc.SetPointLocation(new Point(newPackman.x,newPackman.y));
		   //}
		  // else
		  // {
			//	int xx=0;
			//	xx++;
			//}
		} 
	
 /**
  * The function checks whether the Pacman has reached fruit
  * @param time
  * @param timeTotal
  * @return true if the packman reach the fruit
  */
	public boolean IsPackmanReachedFruit(double time, double timeTotal)
	{
		if(time<timeTotal) 
		
			return false;
			
			else
				return true;
	}
	public boolean IsPackmanReachedFruit2(Point3D pntPc,Point3D pntFrt,double minDist2Eat)
	{
		MyCoords coords = new MyCoords();
		double disPc2Frt= coords.distance3d(pntPc, pntFrt);
		if(disPc2Frt>minDist2Eat) 
			return false;
		else
			return true;
	}

	
	
	
	public Game getGame() {
		return game;
	}

	public Path getPath() {
		return path;
	}


	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

//	public long getTime() {
//		return getTime();
//	}

	public void setTime(long gameTimeSec) {
		this.gameTimeSec = gameTimeSec;
	}
}
