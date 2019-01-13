package Game;

import java.awt.Point;
import Geom.Point3D;
import Map.MyMap;

public class MapObject { 
	private Point3D location3DPoint = null;
	private Point locationPoint = null;
	private int id = -1;	
 public static enum ObjectType{Fruit,Packman,Ghost,Player,Undefind};
 private ObjectType m_typeID = ObjectType.Undefind;
 public void SetObjectType(ObjectType type) {m_typeID = type;}
 public ObjectType GetObjectType() {return m_typeID;}
 
 public void SetPointLocation(Point pnt) 
	{
		this.locationPoint=pnt;
		this.location3DPoint=MyMap.getPositionOnMap(pnt);
	}
	
	public Point GetPointlocation() 
	{
		//return getlocationP();//
		return this.locationPoint;
	}
	////
	public void SetPoint3DLocation(Point3D pnt) 
    {
		this.location3DPoint=pnt;
		this.locationPoint = MyMap.getPositionOnScreen(pnt.y(), pnt.x());
	}
	
	public Point3D GetPoint3Dlocation() 
	{
		return location3DPoint;
	}
	
	public void SetId(int id) 
	{
		this.id=id;
	}
	
	public int GetId() 
	{
		return id;
	}
}
