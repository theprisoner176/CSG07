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
	
	/**
	 * 
	 * @param p is the location of the POI
	 */
	public PointOfInterest(LocationPoint p){
		super(p);
	}
}
