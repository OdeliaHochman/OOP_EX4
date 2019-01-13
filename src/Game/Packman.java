package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import Coords.MyCoords;
import Geom.Point3D;
import Map.MyMap;

/**
 * A class representing a "robot" with location, orientation and mobility
 * @author Efrat Cohen and Odelia Hochman
 *
 */
public class Packman extends MapObject {

	
	private double speed;
	private double radius;
	private double timeInPath;
	private double azimuth;
	private int pathIdx =0;
	private char type='P';

	/**
	 * init
	 * @param locationPoint
	 * @param speed
	 * @param radius
	 * @param id
	 */
	public Packman(Packman pc)
    {
		this(pc.GetPoint3Dlocation(),pc.getSpeed(),pc.getRadius(), pc.GetId());
	}
	public Packman(Point3D pnt,double speed,double radius, int id)
	{
		super.SetPoint3DLocation(pnt);
		super.SetId(id);
		this.setSpeed(speed);
		this.setRadius(radius);
		
		
	}
	
	public int GetCurrentPathIndex()
	{
		return pathIdx;
	}
	public int IncreasePathIndex()
	{
		return pathIdx++;
	}
	
	public Packman(Point pnt,double speed,double radius,int id)
	{
		super.SetPointLocation(pnt);
		super.SetId(id);
		this.setSpeed(speed);
		this.setRadius(radius);
		

		
	}
	public Packman(Point pnt,double speed,double radius,int id, double azimuth) //player
	{
		super.SetPointLocation(pnt);
		super.SetId(id);
		this.setSpeed(speed);
		this.setRadius(radius);
		azimuth=0;
	}
	
	public Packman()
	{
		
	}
	public double getSpeed() {
		return speed;
	}

	

	public void setSpeed(double speed) {
		this.speed = speed;
	}


	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) 
	{
		this.radius = radius;
	}

	public double getAzimuth() {
		return azimuth;
	}
	public void setAzimuth(Point mouseClicked,Point3D location) {
		MyCoords c= new MyCoords();
		Point3D mouse3D = MyMap.getPositionOnMap(mouseClicked);
		Point3D pc3D = GetPoint3Dlocation();
		this.azimuth =c.azimuth_elevation_dist(location,mouse3D)[0]; 
		
	}
	
	public String toString()
	{
		return "Packman id=" + super.GetId() + ", location=" + "x:"+ super.GetPointlocation().x + "y:"+ super.GetPointlocation().y + ", speed=" + speed + ", radius="+ radius;
	}
	
	public String[] ToStringArr() 
	{
		int alt=0;
		String [] sPacArr=new String [] {String.valueOf(type),String.valueOf(super.GetId()),String.valueOf(super.GetPoint3Dlocation().y()) ,String.valueOf(super.GetPoint3Dlocation().x()) ,String.valueOf(alt),String.valueOf(speed), String.valueOf(radius)};
		return sPacArr;
	}

	MyMap map= new MyMap();
	Image packmenIcon;          
      public void Draw(Graphics g) 
    {
    	   	g.setColor(Color.red);
        	
   	g.fillOval(super.GetPointlocation().x,super.GetPointlocation().y,40,40);
        
    }
      
      public void SetType(char type) 
      {
    	  this.type=type;
      }
      
      public char GetType() 
      {
    	  return type;
      }
}

