package GIS;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import GIS.Meta_data;
import Geom.Point3D;

public class MyMeta_data implements Meta_data 
{
	private String Type = "";
    private String Id = "";
	private String SpeedWeight = "";
	private String Raduis = "";
	private String Time = "";
	
	
	private long duration = (4 * 60 * 60 * 1000);

	
	public MyMeta_data(String[] arrStrGisElem)
	{
	    Type = arrStrGisElem[0];
		Id= arrStrGisElem[1];
		SpeedWeight= arrStrGisElem[5];
		if(Type.equals("P") || Type.equals("p") || Type.equals("G") || Type.equals("g")) 
		{
			Raduis=arrStrGisElem[6];
		}
		
	}
		
	/** returns the Universal Time Clock associated with this data; */
	@Override
	public long getUTC()
	{
	  
	//2017-12-01 10:49:08
	SimpleDateFormat newDate = new SimpleDateFormat("YYYY-MM-DD HH:mm:SS", Locale.getDefault());
	
	
	try {
		
		 java.util.Date dt = new Date();
		  dt = newDate.parse(Time);
		
		long timeUtc = duration+dt.getTime();
		return timeUtc;
	}
	catch (ParseException e) 
	{
		e.printStackTrace();
	}
	

     return 0;
	}
	
	public String getType() 
	{
		return this.Type;
	}

	public String getId() {
		return this.Id;
	}

	public String getSpeedWeight() 
	{
		return this.SpeedWeight;
	}
	
	public String getRaduis() 
	{
		return this.Raduis;
	}
	
	/** return a String representing this data */
	@Override
	public String toString() 
	{
		return "Type:" + Type +"Id:"+ Id +"SpeedWeight:"+ SpeedWeight+ "Raduis"+ Raduis;
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}

}
