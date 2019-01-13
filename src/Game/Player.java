package Game;

import java.awt.Point;
import Game.Packman;
import Coords.MyCoords;
import Geom.Point3D;
import Map.MyMap;

public class Player extends MapObject {

	private double scorePlayer;
	private double speed;
    final char type='M';
	private double radius;
	private Point3D MylocationPlayer;
	private double azimuth;
	
	
	public Player(Player player)
	{
		this( player.GetId(),player.getPointLocationPlayer(),player.getSpeed(),player.getRadius());
	}
	
	public Player() 
	{
		scorePlayer=0;
		setSpeed(speed);
		super.GetId();  
		setRadius(radius);
		//MylocationPlayer=new Point3D(32.10459540,35.20882892);
		setLocationPlayer(MylocationPlayer);
		azimuth=0;
		
		
	}
	
	
//	public Player(Point3D locationPlayer,int id,int speed,int radius, double azimuth) 
//	{
//		this.speed=speed;
//		super.SetId(id);
//		this.radius=radius;
//		this.azimuth=azimuth;
//		setLocationPlayerInPixels(locationPlayer);
//		
//	}
	public Player(int id,Point3D locationPlayer,double speed,double radius) 
	{
		this.speed=speed;
		super.SetId(id);
		this.radius=radius;
		setLocationPlayerInPixels(locationPlayer);
		
	}
	
	public Player(int id, Point pnt, double speed, double radius) 
	{
		super.SetId(id);
		this.SetPointLocation(pnt);
		this.speed=speed;
		this.radius=radius;
	}

	
	public void setLocationPlayerInPixels(Point3D locationPlayer)
	{
		MyMap.getPositionOnScreen(locationPlayer.y(), locationPlayer.x());
		
	    
	}

	public double getScorePlayer() {
		return scorePlayer;
	}


	public void setScorePlayer(double scorePlayer) {
		this.scorePlayer = scorePlayer;
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


	
	public double getAzimuth() {
		return azimuth;
	}
	
	public void setLocationPlayer(Point3D locationPlayer)
	{
		this.MylocationPlayer=locationPlayer;
	}
	public Point3D getPointLocationPlayer()
	{
		return MylocationPlayer;
	}
	
	public void setAzimuth(Point mouseClicked) {
		MyCoords c= new MyCoords();
		Point3D mouse = MyMap.getPositionOnMap(mouseClicked);
		this.azimuth =c.azimuth_elevation_dist(getPointLocationPlayer(),mouse)[0]; 
		
	}
	
	public String toString()
	{
		return "player type:"+type+"id:"+GetId()+"location:"+"x:"+ getPointLocationPlayer().x() + "y:"+ getPointLocationPlayer().y()+"speed:"+getSpeed()+"radius:"+getRadius()+"azimuth:"+getAzimuth()+"score:"+getScorePlayer();
	}


}
