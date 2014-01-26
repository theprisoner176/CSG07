package uk.ac.aber.cs221.group07.walktourcreator.model;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
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
	
	/**the activity that initialized the camera/gallery access*/ 
	private Activity owner;

	/**
	 * creates a new ImageHandler object
	 * @param owner the activity that initialized the object
	 */
	public ImageHandler(Activity owner){
		this.owner = owner;
	}
	
	/**
	 * starts the system photo gallery browser, where the user can select an image.
	 * @return information about the selected image
	 */
	public ImageInformation getPhotoFromLibrary(){
		//NONE OF THIS TESTED AT ALL
		Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        owner.startActivityForResult(Intent.createChooser(intent,"Select Picture"), 1);
		return new ImageInformation(null);
	}
	
	/**
	 * starts a take-picture-intent that saves the taken picture and returns the filename
	 * @return an image Information object that holds the filename.
	 */
	public ImageInformation getPhotoFromCamera(){
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		File photoFile = null;   
		if (takePictureIntent.resolveActivity(owner.getPackageManager()) != null) {
			photoFile = createPhotoFile();

			if (photoFile != null) {
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(photoFile));
				owner.startActivityForResult(takePictureIntent, 1);
				return new ImageInformation(photoFile.getAbsolutePath());
			}
		}
		//if some error
		return null;
	}
	
	/**
	 * creates an empty file where the new image will be saved, the filename is the time the file was created
	 * this is because is has to be unique.
	 * 
	 * @return the new file
	 */
	private File createPhotoFile(){
		File image = null;
		String filename = Long.toString(System.currentTimeMillis());
		image =  new File(owner.getFilesDir(), filename);
	    return image;
	}
}
