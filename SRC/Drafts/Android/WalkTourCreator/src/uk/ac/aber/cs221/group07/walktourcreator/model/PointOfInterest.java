package uk.ac.aber.cs221.group07.walktourcreator.model;

import java.util.Vector;

/**
 * Stores information about a place of interest 
 * @author HarryBuckley
 */
public class PointOfInterest extends LocationPoint{

	/** holds all the images that are related to this poi.*/
	private Vector<ImageInformation> images = new Vector<ImageInformation>();
	
	/** holds the name of the POI */
	private String title;
	
	/** holds a description of the POI*/
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
	public PointOfInterest(double x,double y,long time){
		super(x,y,time);
	}
	
	/**
	 * adds reference to an image to the poi.
	 * @param newImage,is the image that is to be added
	 */
	public void addImage(ImageInformation newImage){
		images.add(newImage);
	}
	
	/** returns all the images associated with this point. */
	public Vector<ImageInformation> getImages(){
		return images;
	}
	
	/** returns the description of this place.  */
	public String getDescription(){
		return description;
	}
	
	/** sets the description of this point. */
	public void setDescription(String desc){
		description = desc;
	}
	
	public String getTitle(){
		return title;
	}
	
	/** sets the title of this point. */
	public void setTitle(String title){
		this.title = title;
	}
}
