package uk.ac.aber.cs221.group07.walktourcreator.activities;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class MainMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
	}

	public void startMyWalksScreen(View v){
		Intent intent = new Intent(this, MyWalksScreen.class);
	    startActivity(intent);
	}
	public void startWalkSetupScreen(View v){
		Intent intent = new Intent(this, WalkSetupScreen.class);
	    startActivity(intent);
	}
	public void startOptionsScreen(View v){
		Intent intent = new Intent(this, OptionsScreen.class);
	    startActivity(intent);
	}
	public void StartLoginScreen(View v){
		Intent intent = new Intent(this, LoginScreen.class);
	    startActivity(intent);
	}

}
