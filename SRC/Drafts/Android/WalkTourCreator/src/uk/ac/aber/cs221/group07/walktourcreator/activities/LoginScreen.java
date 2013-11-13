package uk.ac.aber.cs221.group07.walktourcreator.activities;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
/**
 * This class is responsible for displaying the login screen, and reacting to button presses
 */
public class LoginScreen extends GeneralActivity {
	/**
	 * This method is called automatically when the activity is created, all it does is starts sets the layout as 
	 * specified in res/layout/activity_login_screen.xml
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_screen);
	}

}
