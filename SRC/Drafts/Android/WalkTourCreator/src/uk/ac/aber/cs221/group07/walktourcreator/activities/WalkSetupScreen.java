package uk.ac.aber.cs221.group07.walktourcreator.activities;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import uk.ac.aber.cs221.group07.walktourcreator.R.layout;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

/**
 * This class is responsible for displaying the walk setup screen, and reacting to button presses
 */
public class WalkSetupScreen extends GeneralActivity {

	/**
	 * This method is called automatically when the activity is created, all it does is starts sets the layout as 
	 * specified in res/layout/activity_walk_setup_screen.xml
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_walk_setup_screen);
	}

	/**
	 * starts new MapScreen activity and passes information about the walk name.
	 * 
	 * @param v is the View that called this method
	 */
	public void submit(View v){
		Intent intent = new Intent(this, MapScreen.class);
	    startActivity(intent);
	}
}
