package uk.ac.aber.cs221.group07.walktourcreator.activities;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import uk.ac.aber.cs221.group07.walktourcreator.model.DialogClickListener;
import uk.ac.aber.cs221.group07.walktourcreator.model.ImageHandler;
import uk.ac.aber.cs221.group07.walktourcreator.model.LocationPoint;
import uk.ac.aber.cs221.group07.walktourcreator.model.PointOfInterest;
import uk.ac.aber.cs221.group07.walktourcreator.model.WalkManager;
import uk.ac.aber.cs221.group07.walktourcreator.model.WalkModel;
import uk.ac.aber.cs221.group07.walktourcreator.services.PositionListener;
import uk.ac.aber.cs221.group07.walktourcreator.services.RouteRecorder;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * This class is responsible for displaying the map screen, and reacting to button presses.
 * @author HarryBuckley
 */
public class MapScreen extends GeneralActivity {
	
	/** the initial starting point that is displayed on the map*/
	public final LatLng LOCATION_START = new LatLng(52.416204,-4.065419);
	
	/** holds the map object that is displayed in the screen */
	private GoogleMap map;
	 
	private LocationClient mLocationClient;
	private TextView mMessageView;
	private Marker currentPosition;
	
	/** holds the object that is responsible for tracking the path of the walk*/
	private RouteRecorder recorder;
	
	/** stores the walk that the user is currently on.*/
	 private WalkModel walk;
	
	/**
	 * This method is called automatically when the activity is created, all it does is starts sets the layout as 
	 * specified in res/layout/activity_map_screen.xml
	 * 
	 * @param savedInstanceState is not used in this case
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_screen);
		
		setUpMapIfNeeded();
		
		//get a reference to the intent used to instantiate the class
		//and get the WalkModel object that was created
		Intent intent = getIntent();
		walk = (WalkModel) intent.getSerializableExtra("walk");
		
		//location manager to get location data
		LocationManager manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		
		//position listener used to process action when location data is gathered
		PositionListener posListener = new PositionListener();
		posListener.setMap(this);
		
		//create recorder and pass listener and manager
		recorder = new RouteRecorder(posListener,manager);
		
		//give control of the map
		recorder.setWalk(walk);
		posListener.setRecorder(recorder);
		
		//stores a reference of the map to an instance variable
		//map = (MapView) findViewById(R.id.map_view);
	}
	

	
	/**
	 * Created the map if it is not already created
	 */
	private void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated the map.
	    if (map == null) {
	        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
	        // Check if we were successful in obtaining the map.
	        if (map != null) {
	            // The Map is verified. It is now safe to manipulate the map.

	        }
	    }
	}
	
	/**
	 * creates and displays a AddPoiView.
	 * @param v, is the object that called the method.
	 */
	public void addPOI(View v){
		/*LocationManager poiManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		
		PositionListener poiListener = new PositionListener();
		poiListener.setMap(this);
		
		RouteRecorder poiRec = new RouteRecorder(poiListener, poiManager,true);
		if(walk!=null){
			poiRec.setWalk(walk);
		}
		poiListener.setRecorder(poiRec);
		
		*/
		ImageHandler image_saver = new ImageHandler(this);
		PointOfInterest newPoi = new PointOfInterest(1.0,2.0);
		newPoi.addImage(image_saver.getPhotoFromLibrary());
		newPoi.setTitle("A great place to go");
		newPoi.setDescription("it is blah blah blah ...");
		walk.addLocation(newPoi);
		/*
		//for testing only this will be done in another screen/popup
		ImageHandler image_saver = new ImageHandler(this);
		PointOfInterest newPoi = new PointOfInterest(1.0,2.0);
		newPoi.addImage(image_saver.getPhotoFromCamera());
		newPoi.setTitle("A great place to go");
		newPoi.setDescription("it is blah blah blah ...");
		walk.addLocation(newPoi);
		*/
	}
	
	
	/**
	* creates and displays a WalkFinishedView.
	* @param v, is the object that called the method.
	*/
	public void finishWalk(View v){
		//test, will perhaps be moved elsewhere

		WalkManager manager = new WalkManager(this);
//		
		LocationPoint p = new LocationPoint(3,3);
		PointOfInterest poi = new PointOfInterest(6,7);
		poi.setDescription("This is a place of interest");
		
		walk.addLocation(p);
		walk.addLocation(poi);
//		manager.addWalkModel(walk);
		manager.uploadWalk(walk);
//		manager.uploadWalk(walk);
		
		Intent intent = new Intent(this, MainMenu.class);
		recorder.finishWalk();
		finish();
		startActivity(intent);
		
		
		//test will perhaps be moved elsewhere
		


	}
	
	/**
	* creates and displays a PlacesVisitedView.
	* @param v, is the object that called the method. 
	*/
	public void showPlacesVisited(View v){
		
	}
	
	
	/**
	 * Called whenever location data is gathered
	 */
	public void updatePosition(LocationPoint loc){
		//Takes the lat and lng of the LocationPoint and creates a LatLng object
		LatLng pos = new LatLng(loc.getLatitude(),loc.getLongitude());
		//Focus camera on user location
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(pos,17);
		map.animateCamera(update);
		if(currentPosition!=null){
			currentPosition.remove();
		}
		//put marker to show current user location
		currentPosition = map.addMarker(new MarkerOptions().position(pos));
		String str =  "added point"+loc.getLatitude()+";"+loc.getLongitude();
		//Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
	}
	
	public void newPoi(PointOfInterest poi){
		LatLng pos = new LatLng(poi.getLatitude(),poi.getLongitude());
		map.addMarker(new MarkerOptions().position(pos).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
		
	}
	
	
	/*
	protected void onDestroy(){
		Toast.makeText(getApplicationContext(), "DESTROYED", Toast.LENGTH_LONG).show();
	}
	*/


	public void showDialog(final PointOfInterest poi) {
		LayoutInflater inflater = (LayoutInflater)MapScreen.this.getSystemService(LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.activity_add_poi_dialog, null);
		
		DialogClickListener okListener = new DialogClickListener();
		okListener.setPointOfInterest(poi);
		okListener.setInflaterView(inflater, layout);
		
		new AlertDialog.Builder(this)
	    .setMessage("Enter details for the Point of Interest")
	    .setView(layout)
	    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // do nothing
	        }
	     })
	     .setPositiveButton(android.R.string.yes, okListener)
	     .show();
	}

}
