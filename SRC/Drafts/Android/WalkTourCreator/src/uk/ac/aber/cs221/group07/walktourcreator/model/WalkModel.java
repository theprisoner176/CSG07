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
	
	/**holds and id that is unique on the device, eg another device could have the same id*/
	private long id;
	
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
	public WalkModel(String name){
		id = System.currentTimeMillis();
		title= name;
		path = new Vector<LocationPoint>();
	}
	
	public PointOfInterest getLastPoi(){
		for(int i=path.size();1>0;i--){
			if(path.get(i) instanceof PointOfInterest){
				return (PointOfInterest) path.get(i);
			}
		}
	}
	
	/**
	 * creates a WalkModel, with LocationPoints already set, it is used 
	 * by the WalkManager when loading walk from database.
	 */
	public WalkModel(int id,String title,Vector<LocationPoint> path,String shortDesc,String longDesc){
		this.id = System.currentTimeMillis();
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
	 * returns the running total of km traveled.
	 */
	public double getDistance(){
		double total =0;
		for(int i=0;i<path.size()-1;i++){
			total += path.get(i).distanceTo(path.get(i+1));
		}
		return total;
	}
	
	/**
	 * returns the elapsed time since the walk was started. 
	 */
	public double getTimeTaken(){
		return path.get(path.size()-1).getTime() - path.get(0).getTime();
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
	
	/**
	 *  not sure if this will be used.
	 * @return 
	 */
	public long getID(){
		return id;
	}
}
