package uk.ac.aber.cs221.group07.walktourcreator.model;

import java.io.File;
import java.io.IOException;

import uk.ac.aber.cs221.group07.walktourcreator.activities.WalkScreen;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

/**
 * Handles interaction with the system camera and photo gallery access,
 * and saves the resulting image
 * 
 * @author HarryBuckley
 *
 */
public class ImageHandler {
	
	public static int CAMERA_ACTIVITY_RESULT_CODE = 1984;
	public static int GALLERY_ACTIVITY_RESULT_CODE = 1993;
	
	/**the activity that initialized the camera/gallery access*/ 
	private WalkScreen owner;

	/**
	 * creates a new ImageHandler object
	 * @param owner the activity that initialized the object
	 */
	public ImageHandler(WalkScreen owner){
		this.owner = owner;
	}
	
	/**
	 * starts the system photo gallery browser, where the user can select an image.
	 * @return information about the selected image
	 */
	public void getPhotoFromLibrary(){
		Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        owner.startActivityForResult(Intent.createChooser(intent,"Select Picture"), GALLERY_ACTIVITY_RESULT_CODE);
	}
	
	/**
	 * starts a take-picture-intent that saves the taken picture and returns the filename
	 * @return an image Information object that holds the filename.
	 */
	public void getPhotoFromCamera(){
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);	
		File temp = createFile();
		owner.temp=temp.getAbsolutePath();
		takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(temp));
		owner.startActivityForResult(takePictureIntent, CAMERA_ACTIVITY_RESULT_CODE);
	}
	
	/**
	 * creates the file that the camera will save the taken picture to. 
	 * @return a newly created file
	 */
	private File createFile(){
		
		File file = new File(Environment.getExternalStorageDirectory(),
				Long.toString(System.currentTimeMillis())+".jpeg");
		try {
			file.createNewFile();
			
		} catch (IOException e) { }
		return file;
	}
	
}
