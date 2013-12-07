package uk.ac.aber.cs221.group07.walktourcreator.activities;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

/**
 * The GeneralActivity class 
 * 
 * @author HarryBuckley
 *
 */
public abstract class GeneralActivity extends Activity{

	protected static String username;
	
	protected static int backgroundColor = Color.RED;
	protected static int foregroundColor = Color.BLUE;
	
	protected static int textColor = Color.BLACK;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().getDecorView().setBackgroundColor(backgroundColor);		
	}
	
	/**
	* changes the background color of all GeneralActivity subclasses to the passed
	* value. The int c, represents a color.
	*/
	public void setBackgroundColor(int c){
		backgroundColor = c;
	}
	
	/**
	* changes the foreground color of all
	* GeneralActivity subclasses to the passed value. The int c, represents a color.
	*/
	public void setForegroundColor(int c){
		foregroundColor = c;
	}
	
	/**
	* changes the text color of all GeneralActivity subclasses to the passed value.
	* The int c, represents a color.
	*/
	public void setTextColor(int c){
		textColor = c;
	}
}
