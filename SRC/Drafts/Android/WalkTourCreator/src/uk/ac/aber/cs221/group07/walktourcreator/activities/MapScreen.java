package uk.ac.aber.cs221.group07.walktourcreator.activities;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import uk.ac.aber.cs221.group07.walktourcreator.R.layout;
import uk.ac.aber.cs221.group07.walktourcreator.views.AddPoiView;
import uk.ac.aber.cs221.group07.walktourcreator.views.MapView;
import uk.ac.aber.cs221.group07.walktourcreator.views.PopupView;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;


/**
 * This class is responsible for displaying the map screen, and reacting to button presses
 */
public class MapScreen extends GeneralActivity {
	
	/**
	 * holds reference to the 
	 */
	private MapView map;
	
	/**
	 * This method is called automatically when the activity is created, all it does is starts sets the layout as 
	 * specified in res/layout/activity_map_screen.xml
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_screen);
		//stores a reference of the map to an instance variable
		
		//map = (MapView) findViewById(R.id.map_view);
	}
	
	public void addPOI(View v){
		LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		AddPoiView pw = new AddPoiView(inflater.inflate(R.layout.popup_window_add_poi, null, false), 100, 100,true);
		// The code below assumes that the root container has an id called 'main'
		pw.showAtLocation(this.getCurrentFocus(), Gravity.CENTER, 0, 0); //this.findViewById(R.id.main), Gravity.CENTER, 0, 0)
	}

}
