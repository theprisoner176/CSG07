package uk.ac.aber.cs221.group07.walktourcreator.model;

/**
 * This class stores information about a single image
 *@author HarryBuckley
 */
public class ImageInformation {

	/**
	 * holds the title of an image, used 
	 */
	private String title;
	/**
	 * The name of the actual file, not the title because is has to be unique
	 */
	private String fileName;
	/**
	 * description of image
	 */
	private String description;
	
	/**
	 * creates the ImageInformation and set name
	 * 
	 * @param name the name of the new photo
	 */
	public ImageInformation(String name){
		title = name;
	}
	
	public String getImageAsString(){
		return "AN IMAGE";
	}
	
	public String getTitle(){
		return title;
	}
}
