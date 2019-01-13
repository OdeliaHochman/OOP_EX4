package Map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.RepaintManager;

import Coords.MyCoords;
import GUI.JFrmaeGraphics;
import Geom.Point3D;

public final class MyMap extends JPanel{
  private  BufferedImage myImage;
	
	static final int mapWidth =1360/*1372*/ , mapHeight =657;//635
	//offsets
	static final double mapLongitudeStart =35.202261/*35.202303*/, mapLatitudeStart =32.105817;//32.105731
	//length of map in long/lat
	static final double mapLongitudeStop=35.213001,mapLatitudeStop=32.102116 ; 
	static final double mapLongitudeWidth =mapLongitudeStop-mapLongitudeStart, 
			// invert because it decreases as you go down
			
			//mapLatitudeHeight = mapLatitudeStart -mapLatitudeStop;
	
			mapLatitudeHeight = mapLatitudeStart-32.101869;

	
	
public MyMap() 
{
	try {
		myImage = ImageIO.read(new File("Ariel1.jpg"));
		//BufferedImage image= new BufferedImage(200, 200, myImage);
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

  public BufferedImage GetImage() 
  {
	  
	  return myImage;
  }
  
  public void SetImage(BufferedImage myImg) 
  {
	 
	  this.myImage=myImg;
  }

public void paint(Graphics g)
{
//			int w = mapWidth;
//			int h = mapHeight;
//			g.setColor(Color.blue);
//			String s = " ["+w+","+h+"]";
//		    g.drawString(s, w/3, h/2);
//		
}

	/**
	 * The function calculates the point on the screen (pixel)
	 * @param latA
	 * @param longA
	 * @return new point by pixel
	 */
	public static Point getPositionOnScreen(double latA, double longA)
	{
		// use offsets
		double longWidthA=Math.abs(longA- mapLongitudeStart);
		// do inverse because the latitude increases as we go up but the y decreases as we go up.
		// if we didn't do the inverse then all the y values would be negative.
		double latHeigthA = Math.abs(mapLatitudeStart-latA);

		// set x & y using conversion
		int x = (int) (mapWidth*(longWidthA/mapLongitudeWidth));
		int y = (int) (mapHeight*(latHeigthA/mapLatitudeHeight));

		return new Point(x, y);
	}

	
	/**
	 * The function calculates the point on the map (coordinates)
	 * @param p- point by pixel
	 * @return new point by coordinates
	 */
	public static Point3D getPositionOnMap(Point p)
	{
		double latA,lonA, altA;
		//	int x = (int) (mapWidth*(longWidthA/mapLongitudeWidth));
		//	 int y = (int) (mapHeight*(latHeigthA/mapLatitudeHeight));
		lonA= mapLongitudeStart+ (p.getX()*mapLongitudeWidth)/mapWidth;
		latA= mapLatitudeStart+ (p.getY()*mapLatitudeHeight)/mapHeight;
		altA=0;

		return new Point3D(lonA,latA,altA);

	}
	
	
	/**
	 * The function calculates distance between two points of a pixel
	 * @param p1-point by pixel
	 * @param p2-point by pixel
	 * @return distance
	 */
	public static double DisBetweenPixels(Point p1, Point p2) 
	{
		MyCoords coordsUtil= new MyCoords();
		Point3D newCoordinte1= MyMap.getPositionOnMap(p1);
		Point3D newCoordinte2= MyMap.getPositionOnMap(p2);	
		double distancePoints=coordsUtil.distance3d(newCoordinte1, newCoordinte2);
		return distancePoints;	
	}
	
	/**
	 * The function calculates an angle between two points of a pixel
	 * @param p1-point by pixel
	 * @param p2-point by pixel
	 * @return angle between two points of a pixel
	 */
	public static double AngleBetweenPixels(Point p1, Point p2) 
	{
		MyCoords coordsUtil= new MyCoords();
		Point3D newCoordinte1= MyMap.getPositionOnMap(p1);
		Point3D newCoordinte2= MyMap.getPositionOnMap(p2);	
		double [] distancePoints=coordsUtil.azimuth_elevation_dist(newCoordinte1,newCoordinte2);
		double angle = distancePoints[0];
		
		return angle;	
	}

}

