package uk.ac.aber.cs221.group07.walktourcreator.activities;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import uk.ac.aber.cs221.group07.walktourcreator.model.ImageHandler;
import uk.ac.aber.cs221.group07.walktourcreator.model.LocationPoint;
import uk.ac.aber.cs221.group07.walktourcreator.model.PointOfInterest;
import uk.ac.aber.cs221.group07.walktourcreator.model.WalkManager;
import uk.ac.aber.cs221.group07.walktourcreator.model.WalkModel;
import uk.ac.aber.cs221.group07.walktourcreator.services.PositionListener;
import uk.ac.aber.cs221.group07.walktourcreator.services.RouteRecorder;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * This class is responsible for displaying the map screen, and reacting to button presses
 * @author HarryBuckley
 */
public class MapScreen extends GeneralActivity {
	 public final LatLng LOCATION_START = new LatLng(52.416204,-4.065419);
	
	 private GoogleMap map;
	
	 private LocationClient mLocationClient;
	 private TextView mMessageView;
	 private RouteRecorder recorder;
	 private Marker currentPosition;
	 private WalkModel walk;
	
	/**
	 * This method is called automatically when the activity is created, all it does is starts sets the layout as 
	 * specified in res/layout/activity_map_screen.xml
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_screen);
		
		setUpMapIfNeeded();
		
		Intent intent = getIntent();
		walk = (WalkModel) intent.getSerializableExtra("walk");
		
		LocationManager manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		
		PositionListener posListener = new PositionListener();
		posListener.setMap(this);
		recorder = new RouteRecorder(posListener,manager);
		recorder.setWalk(walk);
		posListener.setRecorder(recorder);
		
		//stores a reference of the map to an instance variable
		//map = (MapView) findViewById(R.id.map_view);
	}
	
	
	/**
	 * creates and displays a AddPoiView.
	 * The parameter v, is the object that called the method.
	 */
	public void addPOI(View v){
		//for testing only this will be done in another screen/popup
		ImageHandler image_saver = new ImageHandler(this);
		PointOfInterest newPoi = new PointOfInterest(1.0,2.0);
		newPoi.addImage(image_saver.getPhotoFromCamera());
		newPoi.setTitle("A great place to go");
		newPoi.setDescription("it is blah blah blah ...");
		walk.addLocation(newPoi);
	}
	
	/**
	* creates and displays a WalkFinishedView.
	* The parameter v, is the object that called the method. */
	public void finishWalk(View v){
		//test will perhaps be moved elsewhere
		//*
		WalkManager manager = new WalkManager(this);
		
		LocationPoint p = new LocationPoint(3,3);
		PointOfInterest poi = new PointOfInterest(6,7);
		poi.setDescription("This is a place of interest");
		
		walk.addLocation(p);
		walk.addLocation(poi);
		//manager.addWalkModel(walk);
		manager.uploadWalk(walk);//*/
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
	
	public void updatePosition(LocationPoint loc){
		LatLng pos = new LatLng(loc.getLatitude(),loc.getLongitude());
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(pos,17);
		map.animateCamera(update);
		if(currentPosition!=null){
			currentPosition.remove();
		}
		currentPosition = map.addMarker(new MarkerOptions().position(pos));
		String str =  "added point"+loc.getLatitude()+";"+loc.getLongitude();
		Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
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
