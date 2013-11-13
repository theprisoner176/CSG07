package uk.ac.aber.cs221.group07.walktourcreator.activities;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

/**
 * This class is responsible for displaying the main menu screen, and reacting to button presses
 */
public class MainMenu extends GeneralActivity {

	/**
	 * This method is called automatically when the activity is created, all it does is starts sets the layout as 
	 * specified in res/layout/activity_main_menu.xml
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
	}
	/**
	 * start a new MyWalksScreen activity
	 * @param v is the View that is called the method
	 */
	public void startMyWalksScreen(View v){
		Intent intent = new Intent(this, MyWalksScreen.class);
	    startActivity(intent);
	}
	/**
	 * start a new WalkSetupScreen activity
	 * @param v is the View that is called the method
	 */
	public void startWalkSetupScreen(View v){
		Intent intent = new Intent(this, WalkSetupScreen.class);
	    startActivity(intent);
	}
	/**
	 * start a new OptionsScreen activity
	 * @param v is the View that is called the method
	 */
	public void startOptionsScreen(View v){
		Intent intent = new Intent(this, OptionsScreen.class);
	    startActivity(intent);
	}
	/**
	 * start a new LoginScreen activity
	 * @param v is the View that is called the method
	 */
	public void StartLoginScreen(View v){
		Intent intent = new Intent(this, LoginScreen.class);
	    startActivity(intent);
	}

}
