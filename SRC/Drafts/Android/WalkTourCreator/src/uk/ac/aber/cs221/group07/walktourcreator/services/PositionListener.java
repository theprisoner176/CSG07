package uk.ac.aber.cs221.group07.walktourcreator.services;

import uk.ac.aber.cs221.group07.walktourcreator.activities.WalkScreen;
import uk.ac.aber.cs221.group07.walktourcreator.model.LocationPoint;
import uk.ac.aber.cs221.group07.walktourcreator.model.PointOfInterest;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

public class PositionListener implements LocationListener {
	
	private WalkScreen walkScreen;
	private RouteRecorder recorder;
	
	@Override
	public void onLocationChanged(Location loc) {
		//Create a LocationPoint and set lat and lng to the recieved location
		LocationPoint pos = new LocationPoint(loc.getLatitude(),loc.getLongitude());
		
		//send it to the recorder which saves it in the WalkModel
		if(recorder.poiRec==false){
			//update map camera to new position
			recorder.newLocation(pos);
		}else if(recorder.poiRec == true){
			PointOfInterest poi = new PointOfInterest(loc.getLatitude(),loc.getLongitude());
			walkScreen.showDialog(poi);
			recorder.savePoi(poi);
		}
		
	}
	
	public void setWalk(WalkScreen walk){
		walkScreen = walk;
	}
	
	public void setRecorder(RouteRecorder rec){
		recorder = rec;
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
