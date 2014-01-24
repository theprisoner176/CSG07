package uk.ac.aber.cs221.group07.walktourcreator.model;

/**
 * This class stores information about a single image
 *@author HarryBuckley
 */
public class ImageInformation {
	
	/**
	 * The name of the actual file, is has to be unique to all other photos stored by the app.
	 */
	private String fileName;
	
	/**
	 * creates the ImageInformation and set name
	 * 
	 * @param name the name of the new photo
	 */
	public ImageInformation(String filename){
		fileName = filename;
	}
	
	public String getImageAsString(){
		//open image at fileName.jpg
		return "AN IMAGE";
	}
	public void deleteImage(){
		//remove image file
	}
	
}
