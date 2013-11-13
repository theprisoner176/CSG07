package uk.ac.aber.cs221.group07.walktourcreator.model;

public class LocationPoint {

	private double longitude;
	private double latitude;
	
	//private Time timestamp;
	public LocationPoint(){};
	
	public LocationPoint(double x,double y){
		longitude=x;
		latitude=y;
	}
	
	protected double distanceTo(LocationPoint point){
		double tempX;
		double tempY;
		tempX = longitude-point.longitude;
		tempY = latitude-point.longitude;
		return Math.sqrt(tempX*tempX+tempY*tempY);
	}
}
