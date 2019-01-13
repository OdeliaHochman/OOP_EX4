package Coords;

import java.awt.Point;

import javax.management.RuntimeErrorException;

import Geom.Point3D;
import Map.MyMap;

/**
 * This interface represents a basic coordinate system converter, including:
 * 1. The 3D vector between two lat,lon, alt points 
 * 2. Adding a 3D vector in meters to a global point.
 * 3. convert a 3D vector from meters to polar coordinates
 * @author Efrat Cohen and Odelia Hochman
 *
 */

public class MyCoords implements coords_converter {

	/* links:
	Calculation of azimuth, distance, elevation:
	https://www.omnicalculator.com/other/azimuth
	cosinekitty.com/compass.html
	 */


	public final double RADIUS_EARTH =6371000; //Earth radius in meters


	/**
	 * the function computes a new point which is the gps point transformed by a 3D vector (in meters)
	 * @return gps point
	 */
	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {

	    if(!isValid_GPS_Point(gps))
		{
			throw new RuntimeException("Error: GPS point values are invalid");
		}

		else 
		{

			double meter2radLat= Math.asin(local_vector_in_meter.x()/RADIUS_EARTH);
			double meter2radLon= Math.asin(local_vector_in_meter.y()/(RADIUS_EARTH*(Math.cos(local_vector_in_meter.x()*Math.PI/180))));
			double r2dLat=Point3D.r2d(meter2radLat);
			double r2dLon=Point3D.r2d(meter2radLon);  

			Point3D point = new Point3D(gps.x()+r2dLat ,gps.y()+r2dLon ,gps.z()+local_vector_in_meter.z());
			return point;
		}
	}

	/**
	 * the function computes the 3D distance (in meters) between the two gps like points
	 * @return distance
	 */
	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {

		if(!isValid_GPS_Point(gps0) || !isValid_GPS_Point(gps1))
		{
			throw new RuntimeException("Error: GPS point values are invalid");
		}
		else
		{
			double diffLat= gps1.y()-gps0.y();
			double diffLon= gps1.x()-gps0.x();

			double radLat=diffLat*Math.PI/180;  //radian
			double radLon= diffLon*Math.PI/180; //radian

			double lonNorm=Math.cos(gps0.x()*Math.PI/180);  

			double meterLat=Math.sin(radLat)*RADIUS_EARTH*lonNorm;
			double meterLon=Math.sin(radLon)*RADIUS_EARTH;

			double distance= Math.sqrt(Math.pow(meterLon,2) + Math.pow(meterLat, 2));
			return distance;
		}

	}
	
//	public double distance3d(Point3D gps0, Point3D gps1) {
//
//		if(!isValid_GPS_Point(gps0) || !isValid_GPS_Point(gps1))
//		{
//			throw new RuntimeException("Error: GPS point values are invalid");
//		}
//		else
//		{
//			double diffLat= gps1.x()-gps0.x();
//	    	double diffLon= gps1.y()-gps0.y();
//
//			double radLat=diffLat*Math.PI/180;  //radian
//			double radLon= diffLon*Math.PI/180; //radian
//
//			double lonNorm=Math.cos(gps0.x()*Math.PI/180);  
//
//			double meterLat=Math.sin(radLat)*RADIUS_EARTH;
//			double meterLon=Math.sin(radLon)*RADIUS_EARTH*lonNorm;
//
//			double distance= Math.sqrt(Math.pow(meterLon,2) + Math.pow(meterLat, 2));
//			return distance;
//		}
//
//	}

	/**
	 * the function computes the 3D vector (in meters) between two gps like points 
	 * @return new gps point in meter
	 */
	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {   

		if(!isValid_GPS_Point(gps0) || !isValid_GPS_Point(gps1))
		{
			throw new RuntimeException("Error: GPS point values are invalid");
		}
		else
		{
			double diffLat= gps1.y()-gps0.y();
			double diffLon= gps1.x()-gps0.x();
			//double diffLat= gps1.x()-gps0.x();
			//double diffLon= gps1.y()-gps0.y();
			double diffAlt= gps1.z()-gps0.z();

			double radLat=diffLat*Math.PI/180;  //radian
			double radLon= diffLon*Math.PI/180; //radian

			double lonNorm=Math.cos(gps0.x()*Math.PI/180); 

			double meterLat=Math.sin(radLat)*RADIUS_EARTH;
			double meterLon=Math.sin(radLon)*RADIUS_EARTH*lonNorm;

			Point3D vector = new Point3D(meterLat,meterLon,diffAlt);
			return vector;
		}
	}

	/**
	 * the function computes the polar representation of the 3D vector be gps0-->gps1 
	 * @return  array- azimuth (aka yaw), elevation (pitch), and distance.
	 */
	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {

		Point p1= new Point(666,224);
		gps0 = MyMap.getPositionOnMap(p1); //new Point3D(666,224);
		System.out.println(gps0.x()+", "+ gps0.y());
		if(!isValid_GPS_Point(gps0) || !isValid_GPS_Point(gps1))
		{
			throw new RuntimeException("Error: GPS point values are invalid");
		}

		else
		{
			Point3D p = vector3D(gps0, gps1);
			System.out.println("gps0"+" ["+ gps0.x()+gps0.y() +" ],"+ "gps1"+ " ["+ gps1.x()+gps1.y() +" ],");
			double deltaLat=p.y();
			double deltaLon=p.x();
			//System.out.println("x:"+ );
			double AZ=Math.toDegrees(Math.atan(Math.abs(deltaLat)/Math.abs(deltaLon))); //Azimuth angle
			double azimuth=0;
			
			
			if(deltaLat>0 && deltaLon>0)
			{
				azimuth=270;//+AZ;
				System.out.println("270+Az: "+ AZ + "=" + azimuth);
			}
			else if(deltaLat>0 && deltaLon<0)
			{
				azimuth=0;//AZ;
				
				System.out.println("Az: "+ AZ + "=" + azimuth);
				
			}
			else if(deltaLat<0 && deltaLon<0)
			{
				azimuth=90;//+AZ;
			
				System.out.println("90+Az: "+ AZ + "=" + azimuth);
				
			}
			else if(deltaLat<0 && deltaLon>0)
			{
				azimuth=180;//+AZ;
				System.out.println("180+Az: "+ AZ + "=" + azimuth);
				
				
				
			}

//			if(deltaLat<0 && deltaLon<0)
//			{
//				azimuth=180-AZ;
//			}
//			else if(deltaLat<0 && deltaLon>0)
//			{
//				azimuth=180+AZ;
//			}
//			else if(deltaLat>0 && deltaLon<0)
//			{
//				azimuth=360+AZ;
//			}
//			else
//			{
//				azimuth=AZ;
//			}

			double alt = gps1.z()-gps0.z();  

			double distance=distance3d(gps0, gps1);

			double elevation = Math.atan(alt/distance);
			double elevation2d= Math.toDegrees(elevation);

			double [] arr= {azimuth,elevation2d,distance};	
			return arr;
		}
	}




	/**
	 * return true if this point is a valid lat, lon , lat coordinate: [-180,+180],[-90,+90],[-450, +inf]
	 * @param p
	 * @return
	 */
	@Override
	public boolean isValid_GPS_Point(Point3D p)
	{

		if(p.y()<-180 || p.y()>180 || p.x()<-90 || p.x()>90 || p.z()<-450 )
		{
			return false;
		}
		return true;
	}
	
	
	
//	public boolean isValid_GPS_Point(Point3D p)
//	{
//
//		if(p.x()<-180 || p.x()>180 || p.y()<-90 || p.y()>90 || p.z()<-450 )
//		{
//			return false;
//		}
//		return true;
//	}
//	



}

