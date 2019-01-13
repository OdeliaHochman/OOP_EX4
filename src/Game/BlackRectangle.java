package Game;

import java.awt.Point;
import java.util.ArrayList;

import Geom.Point3D;
import Map.MyMap;

public class BlackRectangle {

	private Point3D pointUpRight3D;
	private Point3D pointDownLeft3D;
	private Point pointUpRightPoint;
	private Point pointDownLeftPoint;
	private double radius;
	private int id;	
	
	public BlackRectangle(Point pointUpRightPixel,Point pointDownLeftPixel,int id,double radius) {

		SetPointUpRightPixel(pointUpRightPixel);
		SetPointDownLeftPixel(pointDownLeftPixel);
		setRadius(radius);
		SetId(id);

	}
	public BlackRectangle(Point3D pointUpRight,Point3D pointDownLeft,int id,double radius) {

		setPointUpRight(pointUpRight);
	    setPointDownLeft(pointDownLeft);
	    setRadius(radius);
		SetId(id);
	}
	

	public Point3D thirdpointUpLeft(Point3D pointUpRight,Point3D pointDownLeft)
	{
		return new Point3D(pointDownLeft.x(),pointUpRight.y());
	}
	
	public Point3D pointDownRight(Point3D pointUpRight,Point3D pointDownLeft)
	{
		return new Point3D(pointUpRight.x(),pointDownLeft.y());
	}
	
	//////////////////////////////points in pixel/////////////////////////////////////////////////////
	
	public Point getLocationPointDownRightInPixel()
	{		
		Point3D point = pointDownRight(pointUpRight3D, pointDownLeft3D);
		Point point2=MyMap.getPositionOnScreen(point.y(), point.x());
	    return point2;
	}
	public Point getLocationPointUpLeftInPixel()
	{
		Point3D point = thirdpointUpLeft(pointUpRight3D, pointDownLeft3D);
		Point point2=MyMap.getPositionOnScreen(point.x(),point.y());
	    return point2;
	}
	public Point getLocationPointUpRightInPixel()//second
	{
	    Point p=MyMap.getPositionOnScreen(getPointUpRight().x(),getPointUpRight().y());
	    return p;
	}
	public Point getLocationPointDownLeftInPixel()//first
	{
		Point point =MyMap.getPositionOnScreen(getPointDownLeft().x(),getPointDownLeft().y());
	    return point;
	}
	
	///////////////////////////////length,width////////////////////////////////////////////////////////////
	
	public double rectangleLength(Point3D pointUpRight,Point3D pointDownLeft)
	{
		return Math.abs(pointUpRight.y()-thirdpointUpLeft( pointUpRight, pointDownLeft).y());
	}
	
	public double rectangleWidth(Point3D pointUpRight,Point3D pointDownLeft)
	{
		return Math.abs(thirdpointUpLeft(pointUpRight, pointDownLeft).x() - pointDownLeft.x());
	}
	
	
	///////////length,width in pixel//////////
	public double rectangleLengthPixel(Point pointUpRight,Point pointUpLeft)
	{
		return Math.abs(pointUpLeft.y - pointUpRight.y);
		
	}
	
	public double rectangleWidthPixel(Point pointUpLeft,Point pointDownLeft)
	{
		return Math.abs(pointUpLeft.x - pointDownLeft.x);
	}
	
	public void SetId(int id) 
	{
		this.id=id;
	}
	
	public int GetId() 
	{
		return id;
	}
	

	
	
	public void SetPointUpRightPixel(Point pointUpRightPixel) 
	{
		 this.pointUpRightPoint=pointUpRightPixel;
	}
	
	public Point GetPointUpRightPixel()
	{
	   return pointUpRightPoint;	
	}
	
	public void SetPointDownLeftPixel(Point pointDownLeftPixel) 
	{
		this.pointDownLeftPoint=pointDownLeftPixel;
	}
	
	public Point GetPointDownLeftPixel() 
	{
		return pointDownLeftPoint;
	}
	
	public Point3D getPointUpRight() {
		return pointUpRight3D;
	}


	public void setPointUpRight(Point3D pointUpRight) {
		this.pointUpRight3D = pointUpRight;
	}


	public Point3D getPointDownLeft() {
		return pointDownLeft3D;
	}


	public void setPointDownLeft(Point3D pointDownLeft) {
		this.pointDownLeft3D = pointDownLeft;
	}
	

	public double getRadius() {
		return radius;
	}


	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public String toString()
	{
		 char type='B';

		return "type:"+type+"id:"+GetId()+"radius"+getRadius()+"pointUpRight:"+getPointUpRight()+"pointUpLeft:"+thirdpointUpLeft(pointUpRight3D,pointDownLeft3D)+"pointDownRight:"+pointDownRight(pointUpRight3D,pointDownLeft3D)+"PointDownLeft:"+getPointDownLeft()
		        +"Rectangle width:"+rectangleWidth(pointUpRight3D,pointDownLeft3D)+"Rectangle length"+rectangleLength(pointUpRight3D,pointDownLeft3D);
	}
	
	public String[] ToStringArr() 
	{
		char type='B';
		int alt=0;
		String [] sBArr=new String [] {String.valueOf(type),String.valueOf(GetId()),String.valueOf(pointUpRight3D.y()) ,String.valueOf(pointUpRight3D.x()) ,String.valueOf(alt),String.valueOf(pointDownLeft3D.y()) ,String.valueOf(pointDownLeft3D.x()) ,String.valueOf(alt), String.valueOf(radius)};
		return sBArr;
	}





}
