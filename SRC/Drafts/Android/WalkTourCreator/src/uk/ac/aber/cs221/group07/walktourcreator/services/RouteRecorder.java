package uk.ac.aber.cs221.group07.walktourcreator.services;

import uk.ac.aber.cs221.group07.walktourcreator.activities.WalkScreen;
import uk.ac.aber.cs221.group07.walktourcreator.model.LocationPoint;
import uk.ac.aber.cs221.group07.walktourcreator.model.PointOfInterest;
import uk.ac.aber.cs221.group07.walktourcreator.model.WalkModel;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.IBinder;
import android.widget.Toast;

/**
 * 
 * @author HarryBuckley
 *
 */
public class RouteRecorder extends Service{

	/**the current walk that's being made*/
	private WalkModel walk;
	
	/** managers the getting of gps location */
	private LocationManager locationManager;
	
	/** holds the number of milliseconds the app wait before getting a location*/
	private long frequencyOfRecorder = 100;
	
	/** PositionListener to process new location data.*/
	private PositionListener posListener;
	
	private LocationPoint LastKnownPosition = null;
	
	private WalkScreen screen;
	
	
	/**
	 * Constructor for the recorder. Takes a PositionListener and a LocationManager
	 * to start listening for location updates.
	 */
	public RouteRecorder(WalkScreen screen,WalkModel walk,LocationManager manager){
		//locationManager  = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		this.screen = screen;
		this.walk = walk;
		locationManager = manager;
		posListener = new PositionListener(this);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, frequencyOfRecorder, 0, posListener);
	}
	
	private void setLastKnownPosition(LocationPoint newPoint){
		LastKnownPosition = newPoint;
	}
	
	public LocationPoint getLastKnownPosition(){
		return LastKnownPosition;
	}
	
	
	/**
	 * saves the location in the WalkModel object
	 * @param loc
	 */
	public void newLocation(LocationPoint loc){
		if(getLastKnownPosition()==null){
			Toast.makeText(screen,"GPS Signal has been found\nRecording has began\n",Toast.LENGTH_LONG).show();
		}
		else if(LocationPoint.distBetween(getLastKnownPosition(),loc)>0.05){
			walk.addLocation(loc);
		}
		setLastKnownPosition(loc);
	}

	/**
	 * stops the recoding of locations. 
	 */
	public void finishWalk(){
		locationManager.removeUpdates(posListener);
		locationManager = null;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
}
