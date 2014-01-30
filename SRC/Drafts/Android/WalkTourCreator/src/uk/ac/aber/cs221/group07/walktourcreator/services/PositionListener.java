package uk.ac.aber.cs221.group07.walktourcreator.services;

import uk.ac.aber.cs221.group07.walktourcreator.model.LocationPoint;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class PositionListener implements LocationListener {
	
	private RouteRecorder recorder;
	
	public PositionListener(RouteRecorder rec){
		recorder = rec;
	}
	
	@Override
	public void onLocationChanged(Location loc) {
		//Create a LocationPoint and set lat and lng to the recieved location
		LocationPoint pos = new LocationPoint(loc.getLongitude(),loc.getLatitude());
		
		//send it to the recorder which saves it in the WalkModel
		recorder.newLocation(pos);
	}

	
	/**needed is class implements LocationListener but not used*/
	@Override
	public void onProviderDisabled(String arg0) {}
	/**needed is class implements LocationListener but not used*/
	@Override
	public void onProviderEnabled(String arg0) {}
	/**needed is class implements LocationListener but not used*/
	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {}
}
