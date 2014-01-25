package uk.ac.aber.cs221.group07.walktourcreator.model;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

public class ImageHandler {
	
	private Activity owner;

	
	public ImageHandler(Activity owner){
		this.owner = owner;
	}
	
	public ImageInformation getPhotoFromLibrary(){
		//i don't think this works
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
			}
		}
		return new ImageInformation(photoFile.getAbsolutePath());
	}
	
	/**
	 * creates an empty file where the new image will be saved, the filename is the time the file was created
	 * this is because is has to be unique.
	 * @return the name of the newly created file.
	 */
	private File createPhotoFile(){
		File image = null;
		
		
		
		File storageDir = owner.getApplicationContext().getFilesDir();
		
		//File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		String filename = Long.toString(System.currentTimeMillis());
		try{
			image = File.createTempFile(
					filename,  //unique file name
					".jpg",        
					storageDir
			);
		}
		catch(Exception e){	}
	    return image;
	}
}
