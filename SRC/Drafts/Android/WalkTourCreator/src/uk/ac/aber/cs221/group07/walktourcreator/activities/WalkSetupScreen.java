package uk.ac.aber.cs221.group07.walktourcreator.activities;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import uk.ac.aber.cs221.group07.walktourcreator.model.WalkModel;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * This class is responsible for displaying the walk setup screen, and reacting to button presses
 * @author HarryBuckley
 */
public class WalkSetupScreen extends GeneralActivity {

	/**
	 * This method is called automatically when the activity is created, all it does is starts sets the layout as 
	 * specified in res/layout/activity_walk_setup_screen.xml
	 * 
	 * @param savedInstanceState is not used.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_walk_setup_screen);
	}

	/**
	* Starts a new MapScreen activity, and displays it to the user. The detail that the user has input,
	* are passed to the new activity.

	* @param v is the View that called this method
	*/
	public void startWalk(View v){
		//get input from text fields
		String title = ((EditText) findViewById(R.id.title_input)).getText().toString();
		String short_desc =  ((EditText) findViewById(R.id.short_description_input)).getText().toString();
		String long_desc =  ((EditText) findViewById(R.id.long_description_input)).getText().toString();
		//clear text boxes
		((EditText) findViewById(R.id.title_input)).getText().clear();
		((EditText) findViewById(R.id.short_description_input)).getText().clear();
		((EditText) findViewById(R.id.long_description_input)).getText().clear();
		
		/*
		 * ADD SOME VALIDATION OF INPUT, 
		 * WILL NEED TO ADD SOME, ERROR OUTPUT TELLING THE USER 
		 * WHAT TO DO IF INVALID DATA IS ADDED
		 */
		
		//if input is fine
		walk = new WalkModel(title);
		walk.setShortDescription(short_desc);
		walk.setLongDescription(long_desc);
		//go to map screen and pass it the newly created walk
		Intent intent = new Intent(this, MapScreen.class);
		startActivity(intent);
	}
}
