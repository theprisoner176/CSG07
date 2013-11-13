package uk.ac.aber.cs221.group07.walktourcreator.activities;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import uk.ac.aber.cs221.group07.walktourcreator.R.layout;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;

/**
 * This class is responsible for displaying the the options for the app, and reacting to button presses
 */
public class OptionsScreen extends GeneralActivity {
	
	/**
	 * This method is called automatically when the activity is created, all it does is starts sets the layout as 
	 * specified in res/layout/activity_options_screen.xml
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options_screen);
	}
	
	/**
	 * changes the background color of all GeneralActivity subclasses 
	 * @param v is the View that called the method
	 */
	public void changeBackgroundColor(View v){
		super.setBackgroundColor(Color.CYAN);
	}

}
