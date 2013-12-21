package uk.ac.aber.cs221.group07.walktourcreator.activities;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import uk.ac.aber.cs221.group07.walktourcreator.R.layout;
import uk.ac.aber.cs221.group07.walktourcreator.views.WalkFinishedView;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;

/**
 * This class is responsible for displaying the the options for the app, and reacting to button presses
 * @author HarryBuckley
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
	 * Starts a new MyWalkScreen activity,
	 * and displays it to the user.
	 * The parameter v, is the object that called the method.
	 * @param v is the object that called the method
	 */
	public void changeBackgroundColor(View v){
		super.setBackgroundColor(Color.CYAN);
	}

	
}
