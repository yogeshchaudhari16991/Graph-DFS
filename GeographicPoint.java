import java.awt.geom.Point2D.Double;

public class GeographicPoint extends Double {
	
	public GeographicPoint(double latitude, double longitude)
	{
		super(latitude, longitude);
	}

    public String toString()
    {
    	return "Lat: " + getX() + ", Lon: " + getY();
    }
	
	
}
