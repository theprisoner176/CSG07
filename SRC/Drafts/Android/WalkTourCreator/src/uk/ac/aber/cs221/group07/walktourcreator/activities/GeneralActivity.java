package uk.ac.aber.cs221.group07.walktourcreator.activities;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

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
	
	protected int rootLayoutId  = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().getDecorView().setBackgroundColor(backgroundColor);		
	}
	
	public void setContentView(int layoutResID){
		super.setContentView(layoutResID);
		rootLayoutId = layoutResID;
	}
	
	public View getContentView(){
		return findViewById(rootLayoutId);
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
