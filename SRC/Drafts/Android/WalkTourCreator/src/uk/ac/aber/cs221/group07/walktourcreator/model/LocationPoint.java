package uk.ac.aber.cs221.group07.walktourcreator.model;

import java.util.Date;


/**
 * This class stores a map position and records the time at which is was taken
 * @author HarryBuckley
 */
public class LocationPoint {
	
	/** holds the distance (east-west) from Greenwich) */
	protected double longitude;
	
	/** holds the distance (north-south) from the equator) */
	protected double latitude;
	
	/** keeps a record of when the location was captured  */
	private long time;
	
	/**
	 * creates new LoactionPoint
	 * 
	 * @param lat the longitude position
	 * @param lng the latitude position
	 */
	public LocationPoint(double lat,double lng){
		this(lat,lng,System.currentTimeMillis());
	}
	
	/**
	* creates a LocationPoint,
	* used to recreate a point stored in the database.
	* 
	*  @param lat the longitude position
	*  @param lng the latitude position
	*  @param time, the time in milliseconds that this point was reached
	*  @param id, used to to identify this point uniquely 
	*/
	public LocationPoint(double lat,double lng,long time){
		this.time = time;
		this.latitude = lat;
		this.longitude = lng;
	}
	
	/**
	 * @return the Time that the recording was made
	 */
	public long getTime(){
		return time;
	}
	
	/**
	 *  @return the longitude, the east/west distance from Greenwich.
	 */
	public double getLongitude(){
		return longitude;
	}
	
	/**
	 *  @return the latitude, the north/south distance from the equator.
	 */
	public double getLatitude(){
		return latitude;
	}
	
	/**
	 * works out the distance between two 
	 * @param point the Location the you want the know the distance to
	 * @return the distance between itself and the given Location
	 */
	public double distanceTo(LocationPoint point){
		//the following function works for a 2D plane
		// I think is should be different for the globe
		double tempX;
		double tempY;
		tempX = longitude-point.longitude;
		tempY = latitude-point.longitude;
		return Math.sqrt(tempX*tempX+tempY*tempY);
	}
}
