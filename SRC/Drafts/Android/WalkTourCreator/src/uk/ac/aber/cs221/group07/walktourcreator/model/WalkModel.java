package uk.ac.aber.cs221.group07.walktourcreator.model;

import java.util.Vector;

/**
 * stores all information about a single walk
 */
public class WalkModel {

	/**
	 * holds the name of the walk, is used as an identifier
	 */
	private String title;
	
	/**
	 * store a long description of the walk, it is limited to X characters
	 */
	private String longDesc;
	
	/**
	 * store a short description of the walk, it is limited to X characters
	 */
	private String shortDesc;
	
	/**
	 * stores the vector of LocationPoints that make up the walk
	 */
	private Vector<LocationPoint> path = new Vector<LocationPoint>();
	
	/**
	 * creates a new walking model with the title that is passed
	 * @param name is the title of the new walkModel
	 */
	public WalkModel(String name){
		title= name;
	}
	
	/**
	 * returns the path (so far) of the walk
	 */
	public Vector<LocationPoint> getRoutePath(){
		return path;
	}
	public void setTraveledRoute(Vector<LocationPoint> path){
		this.path = path;
	}
	
	public String getTitle(){
		return title;
	}
	public String getShortDescription(){
		return shortDesc;
	}
	public void setShortDescription(String newShortDesc){
		shortDesc =newShortDesc;
	}
	public String getLongDescription(){
		return longDesc;
	}
	public void setLongDescription(String newLongDesc){
		longDesc =newLongDesc;
	}
	//...
	//...
	public void addLocation(LocationPoint point){
		path.add(point);
	}
	
}
