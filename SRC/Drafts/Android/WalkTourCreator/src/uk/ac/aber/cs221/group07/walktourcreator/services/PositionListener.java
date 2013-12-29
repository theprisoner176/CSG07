package uk.ac.aber.cs221.group07.walktourcreator.services;

import uk.ac.aber.cs221.group07.walktourcreator.activities.MapScreen;
import uk.ac.aber.cs221.group07.walktourcreator.model.LocationPoint;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

public class PositionListener implements LocationListener {
	
	private MapScreen map;
	private RouteRecorder recorder;
	
	@Override
	public void onLocationChanged(Location loc) {
		LocationPoint pos = new LocationPoint(loc.getLatitude(),loc.getLongitude());
		map.updatePosition(pos);
		recorder.newLocation(pos);
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
	
	public void setMap(MapScreen gMap){
		map = gMap;
	}
	
	public void setRecorder(RouteRecorder rec){
		recorder = rec;
	}

}
