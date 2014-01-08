package uk.ac.aber.cs221.group07.walktourcreator.model;

import java.util.Vector;

/**
 * Stores information about a place of interest 
 * @author HarryBuckley
 */
public class PointOfInterest extends LocationPoint{

	private Vector<ImageInformation> images = new Vector<ImageInformation>();
	
	/**
	 * holds the name of the POI
	 */
	private String title;
	
	/**
	 * holds a description of the POI
	 */
	private String description;
	
	/**
 	 * creates a PointOfInterest, at position x,y.
     * The time is set automatically
	 */
	public PointOfInterest(double x,double y){
		super(x,y);
	}
	
	/**
	* creates a PointOfInterest, at position x,y.
	* The time is also explicitly defined, this is
	* used when creating a PointOfInterest from a database entry. */
	public PointOfInterest(double x,double y,double time){
		super(x,y,time);
	}
	
	/**
	 * returns all the images associated with this point. 
	 */
	public Vector<ImageInformation> getImages(){
		return images;
	}
	
	/**
	 * returns the description of this place. 
	 */
	public String getDescription(){
		return description;
	}
	
	/**
	 * sets the description of this point. 
	 */
	public void setDescription(String desc){
		description = desc;
	}
}
