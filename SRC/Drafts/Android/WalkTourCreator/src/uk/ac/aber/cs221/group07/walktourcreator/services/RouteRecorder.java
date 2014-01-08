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
	private PositionListener posListener;
	private MapScreen gMap;
	
	/**
	* creates a RouteRecorder instance,
	* with the MapView that will display the walk.
	*/
	public RouteRecorder(WalkView map){
		
	}
	
	
	public RouteRecorder(PositionListener posListener,LocationManager manager){
		locationManager = manager;
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 0, posListener);
	}
	
	public void setMap(MapScreen map){
		gMap = map;
	}
	
	public void setWalk(WalkModel w){
		walk = w;
	}
	
	/**
	 * starts the recording of location points
	 */
	public void startRecording(){
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		posListener = new PositionListener();
		posListener.setMap(gMap);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 0, posListener);
	}
	
	/**
	 * adds a PointOfInterest to the recorded path. 
	 */
	public void savePoi(PointOfInterest poi){
		
	}
	
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
