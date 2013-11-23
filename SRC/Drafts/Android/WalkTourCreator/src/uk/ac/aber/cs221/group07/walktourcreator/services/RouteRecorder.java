package uk.ac.aber.cs221.group07.walktourcreator.services;

import uk.ac.aber.cs221.group07.walktourcreator.model.LocationPoint;
import uk.ac.aber.cs221.group07.walktourcreator.model.WalkModel;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;

public class RouteRecorder extends Service implements LocationListener{

	/**
	 * the current walk that's being made
	 */
	private WalkModel walk;
	
	/**
	 * managers the getting of gps location
	 */
	private LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);;
	/**
	 * holds the number of milliseconds the app wait before getting a location
	 */
	private long frequencyOfRecorder;
	
	@Override
	public IBinder onBind(Intent arg0) {
		
		walk = new WalkModel("test");//string will be from bundled info from intent
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 1, this);
		monitorMovement();
		return null;
	}
	
	private void monitorMovement(){
		Location l = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		System.out.println(l);
		walk.addLocation(new LocationPoint(l.getLongitude(),l.getLatitude()));
		
	}

	@Override
	public void onLocationChanged(Location arg0) {}

	@Override
	public void onProviderDisabled(String arg0) {}

	@Override
	public void onProviderEnabled(String arg0) {}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {}

}
