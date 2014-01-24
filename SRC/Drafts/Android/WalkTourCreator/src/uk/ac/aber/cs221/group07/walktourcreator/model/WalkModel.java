package uk.ac.aber.cs221.group07.walktourcreator.model;

import java.io.Serializable;
import java.util.Vector;

import android.widget.Toast;
import android.content.ContextWrapper;

/**
 * stores all information about a single walk
 * @author HarryBuckley
 */
public class WalkModel implements Serializable{
	
	/** holds the name of the walk, is used as an identifier */
	private String title;
	
	/** store a long description of the walk, it is limited to X characters */
	private String longDesc;
	
	/** store a short description of the walk, it is limited to X characters */
	private String shortDesc;
	
	/**
	 * stores the vector of LocationPoints that make up the walk
	 * !note - changed to array list for easier testing of
	 * passing objects between activities
	 */
	private Vector<LocationPoint> path;
	
	/**
	 * creates a new walking model with the title that is passed
	 * @param name is the title of the new walkModel
	 */
	public WalkModel(String name){
		title= name;
		path = new Vector<LocationPoint>();
	}
	
	/**
	 * creates a WalkModel, with LocationPoints already set, it is used 
	 * by the WalkManager when loading walk from database.
	 */
	public WalkModel(int id,String title,Vector<LocationPoint> path,String shortDesc,String longDesc){
		this.title= title;
		this.path = path;
		this.shortDesc = shortDesc;
		this.longDesc = longDesc;
	}
	
	/**
	 * returns a vector of all the LocationPoint in the walk. Including PointsOfInterests
	 */
	public Vector<LocationPoint> getRoutePath(){
		return path;
	}
	
	/**
	 * returns a vector of all the places of interest in the walk. 
	 */
	public Vector<PointOfInterest> getPoiList(){
		Vector<PointOfInterest> poi = new Vector<PointOfInterest>();
		for(LocationPoint point:getRoutePath()){
			if(point instanceof PointOfInterest){
				poi.add((PointOfInterest) point);
			}
		}
		return poi;
	}
	
	
    /**
	 * returns the running total of km traveled.
	 */
	public double getDistance(){
		return 0;
	}
	
	/**
	 * returns the elapsed time since the walk was started. 
	 */
	public double getTimeTaken(){
		return 0;
	}
	
	/**
	* returns the name of the walk.
	*/
	public String getTitle(){
		return title;
	}
	
	/**
	 * returns a short description of the walk 
	 */
	public String getShortDescription(){
		return shortDesc;
	}
	
	/**
	 * set the short description of the walk. 
	 */
	public void setShortDescription(String newShortDesc){
		shortDesc =newShortDesc;
	}
	
	/**
	 * returns a long description of the walk. 
	 */
	public String getLongDescription(){
		return longDesc;
	}
	
	/**
	 * set the long description for the walk. 
	 */
	public void setLongDescription(String newLongDesc){
		longDesc =newLongDesc;
	}
	
	/**
	 * adds a LocationPoint to the walk. 
	 */
	public void addLocation(LocationPoint point){
		path.add(point);
	}
}
