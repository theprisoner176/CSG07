package uk.ac.aber.cs221.group07.walktourcreator.activities;

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
	protected static int backgroundColor = Color.RED;
	
	/** stores the foreground color used by all GeneralActivitys*/
	protected static int foregroundColor = Color.BLUE;
	
	/**store the color that all text will be in all GeneralActivitys*/
	protected static int textColor = Color.BLACK;
	
	/** CAN'T REMEMBER WHY THIS IS HERE*/
	protected int rootLayoutId  = 0;
	
	/**
	 * is called automatically on creations
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().getDecorView().setBackgroundColor(backgroundColor);		
	}
	
	
	public void setContentView(int layoutResID){
		super.setContentView(layoutResID);
		rootLayoutId = layoutResID;
	}
	
	/**
	 *  CAN'T REMEMBER WHY THIS IS HERE
	 * @return the root view
	 */
	public View getContentView(){
		return findViewById(rootLayoutId);
	}
	
	/**
	* changes the background color of all GeneralActivity subclasses to the passed
	* value. The int c, represents a color.
	* 
	* @param c, is the new color that all backgrounds will be.
	*/
	public void setBackgroundColor(int c){
		backgroundColor = c;
	}
	
	/**
	* changes the foreground color of all
	* GeneralActivity subclasses to the passed value. The int c, represents a color.
	* 
	* @param c, is the new color that all foregrounds will be.
	*/
	public void setForegroundColor(int c){
		foregroundColor = c;
	}
	
	/**
	* changes the text color of all GeneralActivity subclasses to the passed value.
	* The int c, represents a color.
	* 
	* @param c, the new color that all text will be.
	*/
	public void setTextColor(int c){
		textColor = c;
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1984) { //result from camera activity
	        if (resultCode == CAMERA_ACTIVITY_RESULT_CODE) {
	            Toast.makeText(this, "Image saved to:\n" + data.getData(), Toast.LENGTH_LONG).show();
	        } else {
	            Toast.makeText(this, "Image not saved\n", Toast.LENGTH_LONG).show();
	        }
	    }
		if (requestCode == GALLERY_ACTIVITY_RESULT_CODE) { //result from gallery activity
	        if (resultCode == RESULT_OK) {
	            Toast.makeText(this, "Image added to walk:\n" + data.getData(), Toast.LENGTH_LONG).show();
	        } else {
	            Toast.makeText(this, "Image not added\n", Toast.LENGTH_LONG).show();
	        }
	    }
	}
}
