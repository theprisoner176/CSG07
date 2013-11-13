package uk.ac.aber.cs221.group07.walktourcreator.model;

public class ImageInformation {

	private String fileNamePath;
	private String title;
	private String description;
	
	public ImageInformation(String name){
		fileNamePath = name;
	}
	public String getTitle(){
		return title;
	}
}
