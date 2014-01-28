package uk.ac.aber.cs221.group07.walktourcreator.activities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import uk.ac.aber.cs221.group07.walktourcreator.model.ImageInformation;
import uk.ac.aber.cs221.group07.walktourcreator.model.WalkModel;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * The GeneralActivity class, used to specify some basic features
 * used by all screens that are displayed to the user 
 * 
 * @author HarryBuckley
 *
 */
public abstract class GeneralActivity extends Activity{
	
	public static int CAMERA_ACTIVITY_RESULT_CODE = 1984;
	public static int GALLERY_ACTIVITY_RESULT_CODE = 1993;
	
	/** stores the background color used by all GeneralActivitys*/
	protected static int backgroundColor = Color.BLUE;
	
	/** stores the foreground color used by all GeneralActivitys*/
	protected static int foregroundColor = Color.BLUE;
	
	/**The walk that is being */
	protected WalkModel walk;
	
	/** is called automatically on creations */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().getDecorView().setBackgroundColor(backgroundColor);		
	}
	
	/**
	* changes the background color of all GeneralActivity subclasses to the passed
	* value. The int c, represents a color.
	* 
	* @param c, is the new color that all backgrounds will be.
	*/
	public void setBackgroundColor(int c){
		backgroundColor = c;
		this.getWindow().getDecorView().setBackgroundColor(backgroundColor);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAMERA_ACTIVITY_RESULT_CODE) { //result from camera activity
	        if (resultCode == RESULT_OK) {	        	
	        	walk.getLastPoi().addImage(new ImageInformation(data.getData().toString()));
	        	Toast.makeText(this, "Image saved to:\n" + data.getData(), Toast.LENGTH_LONG).show();
	        } else {
	            Toast.makeText(this, "Image not saved\n", Toast.LENGTH_LONG).show();
	        }
	    }
		if (requestCode == GALLERY_ACTIVITY_RESULT_CODE) { //result from gallery activity
	        if (resultCode == RESULT_OK) {
	        	walk.getLastPoi().addImage(new ImageInformation(data.getData().toString()));
	            Toast.makeText(this, "Image added to walk:\n" + data.getData(), Toast.LENGTH_LONG).show();
	        } else {
	            Toast.makeText(this, "Image not added\n", Toast.LENGTH_LONG).show();
	        }
	    }
	}
}
