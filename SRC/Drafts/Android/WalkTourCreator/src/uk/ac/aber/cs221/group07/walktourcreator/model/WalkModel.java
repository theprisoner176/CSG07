package uk.ac.aber.cs221.group07.walktourcreator.model;

import java.io.Serializable;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * stores all information about a single walk
 * @author HarryBuckley
 */
public class WalkModel implements Serializable{
	
	private static final int MAX_LONG_DESC_LENGTH = 999;
	private static int MAX_SHRT_DESC_LENGTH = 100;
	private static int MAX_TITLE_LENGTH = 100;
	
	
	/** holds the name of the walk, is used as an identifier */
	private String title;
	
	/** store a long description of the walk, it is limited to X characters */
	private String longDesc;
	
	/** store a short description of the walk, it is limited to X characters */
	private String shortDesc;
	
	/** stores the vector of LocationPoints that make up the walk*/
	private Vector<LocationPoint> path;
	
	/**
	 * creates a new walking model with the title that is passed
	 * @param name is the title of the new walkModel
	 */
	public WalkModel(){
		path = new Vector<LocationPoint>();
	}
	
	/** returns the name of the walk.*/
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String newTitle){
		title =newTitle;
	}
	
	/** returns a short description of the walk */
	public String getShortDescription(){
		return shortDesc;
	}
	
	/** set the short description of the walk.*/
	public void setShortDescription(String newShortDesc){
		shortDesc =newShortDesc;
	}
	
	/** returns a long description of the walk.*/
	public String getLongDescription(){
		return longDesc;
	}
	
	/** set the long description for the walk.*/
	public void setLongDescription(String newLongDesc){
		longDesc =newLongDesc;
	}
	
	/**returns a vector of all the LocationPoint in the walk. Including PointsOfInterests*/
	public Vector<LocationPoint> getRoutePath(){
		return path;
	}
	/**adds a LocationPoint to the walk. */
	public void addLocation(LocationPoint point){
		path.add(point);
	}
	
	
	public PointOfInterest getLastPoi(){
		PointOfInterest retval = null;
		for(int i=path.size()-1;1>=0;i--){
			if(path.get(i) instanceof PointOfInterest){
				retval = (PointOfInterest) path.get(i);
				break;
			}
		}
		return retval;
	}
	
	
    /** returns the running total of km traveled. */
	public double getDistance(){
		double total =0;
		for(int i=0;i<path.size()-1;i++){
			total +=  LocationPoint.distBetween(path.get(i),path.get(i+1));
		}
		return total;
	}
		
	/**  returns the elapsed time since the walk was started. */
	public double getTimeTaken(){
		if(path.size()==0){
			return 0;
		}else{
			return path.get(path.size()-1).getTime() - path.get(0).getTime();
		}
	}
}
