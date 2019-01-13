package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import Coords.MyCoords;
import Geom.Point3D;

class testCoords {

	@Test
	void testDistance3d() 
	{

		MyCoords c=  new MyCoords();
		Point3D p1 = new Point3D (32.103315,35.209039,670.0);
		Point3D p2 = new Point3D (32.106352,35.205225,650.0);
		double dis =c.distance3d(p1, p2);
		assertEquals(493.0523318324134,dis);
	}

	@Test
	void testAdd() 
	{
		MyCoords c = new MyCoords();
		Point3D v =  new Point3D(344,-400,0.0);
		Point3D p = new Point3D(32.103315,35.209039,670.0); 
		Point3D ans = c.add(p,v);
		Point3D expected = new Point3D(32.10640866632587,35.20529674496016,670.0);
		assertTrue(expected.equals(ans));

	}



	@Test
	void testVector3D() {

		Point3D pTest = new Point3D (32.103315,35.209039,670);
		Point3D p2Test = new Point3D (32.106352,35.205225,650);
		MyCoords m= new MyCoords();
		Point3D vecactual = m.vector3D(pTest, p2Test);
		Point3D expected = new Point3D(337.69899206128815,-359.24920693881893,-20.0);
		assertTrue(vecactual.equals(expected));
	}


	@Test
	void testAzimuth_elevation_dist() 
	{		
		MyCoords c= new MyCoords();
		Point3D p = new Point3D (32.105236,35.205369,520);
		Point3D p1 = new Point3D (32.105486,35.203691,600);
		
		double []  actual=c.azimuth_elevation_dist(p, p1); 
		double [] expected= {279.9753982257164, 26.496843532510326, 160.47731585395437};
		assertArrayEquals(expected, actual);

	
		Point3D p2 = new Point3D (32.03165,35.02697,520);
		Point3D p3 = new Point3D (32.03569,35.02147,630);
		
		double []  actual1=c.azimuth_elevation_dist(p2, p3); 
		double [] expected1= {310.90759918621586,9.109690318944924,686.0099839181388};
		assertArrayEquals(expected1, actual1);
		
	}


	@Test
	void testIsValid_GPS_Point() {
		MyCoords c=  new MyCoords();
		Point3D p1 = new Point3D (-100.5236,181.236,-451.233);
		boolean ans = c.isValid_GPS_Point(p1);
		assertFalse(ans);
	}


}
