package uk.ac.aber.cs221.group07.walktourcreator.activities;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import uk.ac.aber.cs221.group07.walktourcreator.R.layout;
import uk.ac.aber.cs221.group07.walktourcreator.views.AddPoiView;
import uk.ac.aber.cs221.group07.walktourcreator.views.PopupView;

import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
/**
 * This class is responsible for displaying the map screen, and reacting to button presses
 * @author HarryBuckley
 */
public class MapScreen extends GeneralActivity{
	
	private GoogleMap map;
	
	/**
	 * This method is called automatically when the activity is created, all it does is starts sets the layout as 
	 * specified in res/layout/activity_map_screen.xml
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_screen);
		
		setUpMapIfNeeded();
		
		//stores a reference of the map to an instance variable
		//map = (MapView) findViewById(R.id.map_view);
	}
	
	
	/**
	* creates and displays a AddPoiView.
	* The parameter v, is the object that called the method. */
	public void addPOI(View v){
		
	}
	/**
	* creates and displays a WalkFinishedView.
	* The parameter v, is the object that called the method. */
	public void finishWalk(View v){
		
	}
	/**
	* creates and displays a PlacesVisitedView.
	* The parameter v, is the object that called the method. */
	public void showPlacesVisited(View v){
		
	}
	
	private void setUpMapIfNeeded() {
	    // Do a null check to confirm that we have not already instantiated the map.
	    if (map == null) {
	        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
	                            .getMap();
	        // Check if we were successful in obtaining the map.
	        if (map != null) {
	            // The Map is verified. It is now safe to manipulate the map.

	        }
	    }
	}


}
