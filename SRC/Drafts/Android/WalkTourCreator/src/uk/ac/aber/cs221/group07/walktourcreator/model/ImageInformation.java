package uk.ac.aber.cs221.group07.walktourcreator.model;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;

import android.provider.MediaStore.Files;

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
	
	public String getFileName(){
		return fileName;
	}
	
	/**
	 * open file and returns it as modified UTF-8 (see DataInputStream)
	 * @return file contents as modified UTF-8
	 */
	public String getImageAsString(){
		String retval = null;
        try {
        	FileInputStream file = new FileInputStream(fileName);
        	// Open file
        	DataInputStream inputStream = new DataInputStream(file);
        	retval = inputStream.readUTF();
        	inputStream.close();
        }
        catch(Exception exc){ }
        
		return retval;
	}
	
	/**
	 * delete image from device
	 */
	public void deleteImage(){
		File file = new File(fileName);
		file.delete();
	}
	
}
