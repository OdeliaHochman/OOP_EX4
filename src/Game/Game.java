package Game;

import Game.Packman;

import Game.Fruit;
import Geom.Point3D;
import Map.MyMap;
import java.awt.Point;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.Iterator;

import File_Format.CSVReader;
import File_Format.csvWriter;

/**
 * A class that includes a collection of fruit and a collection of robots, 
 * the class has the ability to build from the csv file and save its information to such a file.
 * @author Efrat Cohen and Odelia Hochman
 *
 */
public class Game 
{
	ArrayList<Packman> packmansOnMap;
	ArrayList<Fruit> fruitsOnMap;
	ArrayList<BlackRectangle> rectanglesOnMap;
	ArrayList<Ghost> ghostsOnMap;
	Player player;





	void ReadCSV(String csvFileName)
	{
		boolean bIsTitle=true;
		ArrayList<String[]> alCsvData = CSVReader.ReadFile(csvFileName);
		for (Iterator<String[]> iterator = alCsvData.iterator(); iterator.hasNext();) 
		{
			if(!bIsTitle)
			{
				String[] strings = (String[]) iterator.next();
				AddGameItem(strings);	
			}

			else

				bIsTitle=false;
		}
	}

	public void UpLoadCsv(String csvFileName) 
	{
		ArrayList<String[]> al = BuidPackAndFruitList(packmansOnMap,fruitsOnMap,ghostsOnMap,rectanglesOnMap);
		SaveCsv(al,csvFileName);
		//ReadCSV(str);
	}
	public ArrayList<String[]> BuidPackAndFruitList(ArrayList<Packman> packmansOnMap,ArrayList<Fruit>fruitsOnMap,ArrayList<Ghost>ghostsOnMap,ArrayList<BlackRectangle>rectanglesOnMap)
	{
		ArrayList<String[]> alPackAndFruit= new ArrayList<>();

		for (Packman pac : packmansOnMap) 
		{

			alPackAndFruit.add(pac.ToStringArr());
		}

		for (Fruit frt : fruitsOnMap) 
		{
			alPackAndFruit.add(frt.ToStringArr());
		}

		for (Ghost g : ghostsOnMap) 
		{
			alPackAndFruit.add(g.ToStringArr());
		}

		for (BlackRectangle b : rectanglesOnMap) 
		{
			alPackAndFruit.add(b.ToStringArr());
		}


		return alPackAndFruit;
	}

	void SaveCsv(ArrayList<String[]> al,String csvFileName)
	{
		csvWriter.WriteFile(csvFileName, al);
	}

	public Game(String[] board_data) {
		AddGameItem(board_data);	
	}

	void  AddGameItem(String[] objString)  //// need for??
	{

		int id=Integer.parseInt(objString[1]);
		double lat=Double.parseDouble(objString[2]);
		double lon=Double.parseDouble(objString[3]);
		double alt=Double.parseDouble(objString[4]);
		Point pnt= MyMap.getPositionOnScreen(lat, lon);
		Point3D p3DR=new Point3D(lat,lon);


		int idxID = 0;
		switch(objString[idxID])
		{
		case "M":
		{
			Packman myPlay= new Packman(pnt, 20.0, 10.0, idxID);
			packmansOnMap.add(myPlay);
		
		}
		case "P":
		{
			double speed=Double.parseDouble(objString[5]);
			double radius=Double.parseDouble(objString[6]);
			Packman pc = new Packman(pnt,speed,radius,id); 
			packmansOnMap.add(pc) ;
			break;
		}
		case "F":
		{
			double weight=Double.parseDouble(objString[5]);
			Fruit ft =  new Fruit(pnt, id, weight);
			fruitsOnMap.add(ft);
			break;
		}
		case "G":
		{
			double speed=Double.parseDouble(objString[5]);
			double radius=Double.parseDouble(objString[6]);
			Ghost g = new Ghost(pnt, speed, id, radius); 
			ghostsOnMap.add(g) ;
			break;
		}
		case "B":
		{
			double latB=Double.parseDouble(objString[5]);
			double lonB=Double.parseDouble(objString[6]);
			double altB=Double.parseDouble(objString[7]);
			// Point pnt2=MyMap.getPositionOnScreen(latB, lonB);
			double radius=Double.parseDouble(objString[8]);
			Point3D p3DL=new Point3D(latB, lonB);

			// BlackRectangle b = new BlackRectangle(pnt, pnt2, id, radius);

			BlackRectangle b = new BlackRectangle(p3DR, p3DL, id, radius);
			rectanglesOnMap.add(b) ;
			break;
		}




		}
	}
	/**
	 * init
	 */
	public Game() 
	{

	}

	public Game(ArrayList<String> board_data)
	{
		packmansOnMap = new ArrayList<Packman>();
		fruitsOnMap = new ArrayList<Fruit>();
		rectanglesOnMap = new ArrayList<BlackRectangle>();
		ghostsOnMap = new ArrayList<Ghost>();
		player = new Player();
		for (String strItem : board_data) {
			String[] strItemArr = strItem.split(",");
			AddGameItem(strItemArr);
		}
	}

	public void LoadCsv(String csvFileName) 
	{
		packmansOnMap = new ArrayList<>();
		fruitsOnMap= new ArrayList<>();
		rectanglesOnMap=new ArrayList<>();
		ghostsOnMap=new ArrayList<>();

		String str =csvFileName;//"C:\\dataGame\\game_1543684662657.csv";//csv file full path
		ReadCSV(str);
	}


	/**
	 * The function adds Packman to the Packman collection
	 * @param packman
	 */
	public void AddPackman(Packman packman) 
	{
		if(packmansOnMap==null) 
		{
			packmansOnMap= new ArrayList<>();
		}
		packmansOnMap.add(packman);
	}

	/**
	 * The function adds fruit to the fruit collection
	 * @param fruit
	 */
	public void AddFruit(Fruit fruit) 
	{
		if(fruitsOnMap==null) 
		{
			fruitsOnMap= new ArrayList<>();
		}
		fruitsOnMap.add(fruit);
	}

	/**
	 * The function adds ghost to the ghost collection
	 * @param ghost
	 */
	public void AddGhost(Ghost ghost) 
	{
		if(ghostsOnMap==null) 
		{
			ghostsOnMap= new ArrayList<>();
		}
		ghostsOnMap.add(ghost);
	}

	/**
	 * The function adds rectangle to the rectangle collection
	 * @param rectangle
	 */
	public void AddRectangle(BlackRectangle rectangle) 
	{
		if(rectanglesOnMap==null) 
		{
			rectanglesOnMap= new ArrayList<>();
		}
		rectanglesOnMap.add(rectangle);
	}

//	public void AddUpdatePlayer(Player play) 
//	{
//		if(play==null) 
//		{
//			play=new Player();
//		}
//		
//		play.setLocationPlayer(locationPlayer);
//	}

	public ArrayList<Packman> getArrListPac() 
	{
		return this.packmansOnMap;
	}

	public ArrayList<Fruit> getArrListFruit() 
	{
		return this.fruitsOnMap;
	}

	public ArrayList<BlackRectangle> getArrListRectangle() 
	{
		return this.rectanglesOnMap;
	}
	public ArrayList<Ghost> getArrListGhost() 
	{
		return this.ghostsOnMap;
	}

	public int getSizeOfArrPackman() 
	{
		return packmansOnMap.size();
	}

	public int getSizeOfArrFruit() 
	{
		return fruitsOnMap.size();
	}
	public int getSizeOfArrRectangle() 
	{
		return rectanglesOnMap.size();
	}
	public int getSizeOfArrGhost() 
	{
		return ghostsOnMap.size();
	}


	public void removeFruit(Fruit f)
	{
		fruitsOnMap.remove(f);
	}
	public void removePackman(Packman p)
	{
		packmansOnMap.remove(p);
	}
	public void removeGhost(Ghost g)
	{
		ghostsOnMap.remove(g);
	}
	public void removeRectangle(BlackRectangle r)
	{
		rectanglesOnMap.remove(r);
	}


	public void clearGame()
	{
		this.packmansOnMap.clear();
		this.fruitsOnMap.clear();
		this.ghostsOnMap.clear();
		this.rectanglesOnMap.clear();
	}
	public Iterator<Fruit> itrFruit()
	{
		return this.fruitsOnMap.iterator();
	}
	public Iterator<Packman> itrPackman()
	{
		return this.packmansOnMap.iterator();
	}
	public Iterator<BlackRectangle> itrRectangle()
	{
		return this.rectanglesOnMap.iterator();
	}
	public Iterator<Ghost> itrGhost()
	{
		return this.ghostsOnMap.iterator();
	}

}

