package Game;

import java.awt.Point;
import java.awt.geom.Point2D;
import Geom.Point3D;
import Map.MyMap;

public class Ghost extends MapObject {



	private Point3D locationGhost;
	private double speed;
	private double radius;
	final char type='G';
	
	
	
	public Ghost(Ghost g) 
	{
		this(g.GetPoint3Dlocation(),g.getSpeed(), g.GetId(),g.getRadius());
	}
	public Ghost(Point3D locationGhost,double speed,int id, double radius) 
	{
		
		this.speed=speed;
		super.SetPoint3DLocation(locationGhost);
		super.SetId(id);
		this.radius=radius;
	}
	public Ghost(Point locationGhost,double speed,int id, double radius) 
	{
		
		this.speed=speed;
		super.SetPointLocation(locationGhost);
		super.SetId(id);
		this.radius=radius;
	}

	public double latPointLocation()  
	{
		return locationGhost.y();
	}
	public double lonPointLocation() 
	{
		return locationGhost.x();
	}
	
	public void getLocationGhostInPixels()
	{
		Point3D point = new Point3D(latPointLocation(),lonPointLocation());
	    super.SetPoint3DLocation(point);
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

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public String toString()
	{
		return "type:"+type+"id:"+super.GetId()+ "location ghost:"+"x:"+ super.GetPointlocation().x + "y:"+ super.GetPointlocation().y+"speed:"+getSpeed()+"radius:"+getRadius();
	}
	
	public String[] ToStringArr() 
	{
		
		int alt=0;
		String [] sGArr=new String [] {String.valueOf(type),String.valueOf(super.GetId()),String.valueOf(super.GetPoint3Dlocation().y()) ,String.valueOf(super.GetPoint3Dlocation().x()) ,String.valueOf(alt),String.valueOf(speed), String.valueOf(radius)};
		return sGArr;
	}

}
