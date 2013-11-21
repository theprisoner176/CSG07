package uk.ac.aber.cs221.group07.walktourcreator.model;

import java.util.Vector;

/**
 * Stores information about a place of interest 
 */
public class PointOfInterest extends LocationPoint{

	private Vector<ImageInformation> images;
	
	/**
	 * holds the name of the POI
	 */
	private String title;
	/**
	 * holds a description of the POI
	 */
	private String description;
	

	public PointOfInterest(double x,double y){
		super(x,y);
		images = new Vector<ImageInformation>();
	}
	
	public Vector<ImageInformation> getImages(){
		return images;
	}
	public String getDescription(){
		return description;
	}
	public void setDescription(String desc){
		description = desc;
	}
}
