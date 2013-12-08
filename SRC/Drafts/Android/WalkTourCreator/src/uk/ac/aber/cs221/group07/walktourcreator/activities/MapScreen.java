package uk.ac.aber.cs221.group07.walktourcreator.activities;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
/**
 * This class is responsible for displaying the map screen, and reacting to button presses
 * @author HarryBuckley
 */
public class MapScreen extends GeneralActivity{
	public final LatLng LOCATION_START = new LatLng(52.416204,-4.065419);
	
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
		initLocation();
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
	
	public void initLocation(){
		map.addMarker(new MarkerOptions().position(LOCATION_START).title("You are here"));
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_START,17);
		map.animateCamera(update);
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
