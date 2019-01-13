package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Map;

import org.junit.jupiter.api.Test;

import Coords.MyCoords;

import Geom.Point3D;
import Map.MyMap;

class Test_Map {

	
	final Point3D start = new Point3D(32.10571,35.20237,0);
	final Point3D end = new Point3D(32.10189,35.21239,0);

	@Test
	void  azimuth() {
		MyCoords c = new MyCoords();
		double[] arr  = c.azimuth_elevation_dist(start, end);
		int actual = (int)arr[0];
		int excepted = 114;
		assertEquals(excepted, actual);
	}
	
	
	
	@Test
	void testConversions() {

		Point p = new Point(133,418);
		Point3D p1 = MyMap.getPositionOnMap(p);
		Point p2 = MyMap.getPositionOnScreen(32.10839536333334,35.20321455140187);
		
		Point3D expected1 = new Point3D(35.20321455140187,32.10839536333334,0.0);
		assertTrue(expected1.equals(p1));
		
		Point expected2=new Point(133,418);
		assertEquals(expected2, p2);
		assertTrue(expected2.equals(p2));

	}
	
	@Test
	void testDisBetweenPixels() 
	{
		double dis=(int) MyMap.DisBetweenPixels(new Point(520,328), new Point(725,537));  //(32.103793,35.205990)  (32.102503,35.207463)
		double disExpected=201;
		assertEquals(disExpected, dis);

	}
	
	@Test
	void testAngleBetweenPixels()
	{
		
		MyCoords c= new MyCoords();
		Point3D p = new Point3D (32.105236,35.205369,520);
		Point3D p1 = new Point3D (32.105486,35.203691,600);
		double actual=c.azimuth_elevation_dist(p, p1)[0]; 
		double expected= 279.9753982257164;
		assertEquals(expected, actual);;
		
//		Point p2=new Point(520,328);
//		Point p3= new Point(725,537);
//		Point p2 = Map.getPositionOnScreen(32.03165,35.02697);
//		Point p3 =  Map.getPositionOnScreen(32.03569,35.02147);
//		System.out.println(p2);
//		System.out.println(p3);
//		double expected1= 310.90759918621586;
		
//		Point p2=new Point(942,397);
//		Point p3= new Point(726,345);
//		double actual1=(int)Map.AngleBetweenPixels(p2, p3); 
//		double expected1=170;
//		assertEquals(expected1, actual1);
//		Point p2 = Map.getPositionOnScreen(32.103365,35.209017);
//		Point p3 =  Map.getPositionOnScreen(32.103687,35.207470);
		
		
		
//		Point p=new Point(433,94); //(32.105236,35.205369)
//		Point p1= new Point(199,53); //(32.105486,35.203691)
//		double expected1=280;
//		double actual1=(int)Map.AngleBetweenPixels(p, p1);
//		assertEquals(expected1, actual1);

		
//		Point p2=new Point(24449,12023); //(32.03165,35.02697)
//		Point p3= new Point(25216,11368); //(32.03569,35.02147)
//		double expected2=149;
//		double actual2=(int)Map.AngleBetweenPixels(p2, p3);  // if(p2 <--> p3) it works
//		assertEquals(expected2, actual2);
		

	}
	
	

}
