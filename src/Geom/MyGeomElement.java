package Geom;

public class MyGeomElement implements Geom_element 
{
	private Point3D point;

	@Override
	public double distance3D(Point3D p) 
	{
		
		return point.distance3D(p);
	}

	@Override
	public double distance2D(Point3D p) 
	{
		
		return point.distance2D(p);
	}


}
