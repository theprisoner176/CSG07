package uk.ac.aber.cs221.group07.walktourcreator.services;

import uk.ac.aber.cs221.group07.walktourcreator.activities.MapScreen;
import uk.ac.aber.cs221.group07.walktourcreator.model.LocationPoint;
import uk.ac.aber.cs221.group07.walktourcreator.model.PointOfInterest;
import uk.ac.aber.cs221.group07.walktourcreator.model.WalkModel;
import uk.ac.aber.cs221.group07.walktourcreator.views.WalkView;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.IBinder;

/**
 * 
 * @author HarryBuckley
 *
 */
public class RouteRecorder extends Service{

	/**
	 * the current walk that's being made
	 */
	private WalkModel walk;
	
	/**
	 * managers the getting of gps location
	 */
	private LocationManager locationManager;
	/**
	 * holds the number of milliseconds the app wait before getting a location
	 */
	private long frequencyOfRecorder;
	/**
	 * PositionListener to process new location data.
	 */
	private PositionListener posListener;
	/**
	 * The map screen.
	 */
	private MapScreen gMap;
	
	/**
	* creates a RouteRecorder instance,
	* with the MapView that will display the walk.
	*/
	public RouteRecorder(WalkView map){
		
	}
	
	/**
	 * Constructor for the recorder. Takes a PositionListener and a LocationManager
	 * to start listening for location updates.
	 */
	public RouteRecorder(PositionListener posListener,LocationManager manager){
		locationManager = manager;
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3, 0, posListener);
	}

	/**
	 * Makes a reference of the WalkModel for the current walk.
	 * @param w
	 */
	public void setWalk(WalkModel w){
		walk = w;
	}
	
	/**
	 * adds a PointOfInterest to the recorded path. 
	 */
	public void savePoi(PointOfInterest poi){
		
	}
	
	/**
	 * saves the location in the WalkModel object
	 * @param loc
	 */
	public void newLocation(LocationPoint loc){
		walk.addLocation(loc);
	}

	/**
	 * stops the recoding of locations. 
	 */
	public void finish(){
		
	}
	
	
	private void monitorMovement(){
		Location l = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		System.out.println(l);
		walk.addLocation(new LocationPoint(l.getLongitude(),l.getLatitude()));
		
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		
		walk = new WalkModel("test");//string will be from bundled info from intent
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 1, posListener);
		monitorMovement();
		return null;
	}

	

}
