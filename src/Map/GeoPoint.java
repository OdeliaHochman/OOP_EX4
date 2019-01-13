package Map;

public class GeoPoint 
{
    /**
	 * 
	 */

	private double longitude;
    private double latitude;
    private double altitube;

    public GeoPoint( double latitude, double longitude, double altitube ) 
    {
    	this.latitude = latitude;
		this.longitude = longitude;
		this.altitube=altitube;
        
    }

    public double getLon() 
    {
        return this.longitude;
    }

    public double getLat() 
    {
        return this.latitude;
    }
    
    public double getAlt() 
    {
    	return this.altitube;
    }
}
