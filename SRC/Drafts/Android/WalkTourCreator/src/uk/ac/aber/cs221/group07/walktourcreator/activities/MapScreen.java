package uk.ac.aber.cs221.group07.walktourcreator.activities;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
/**
 * This class is responsible for displaying the map screen, and reacting to button presses
 * @author HarryBuckley
 */
public class MapScreen extends FragmentActivity implements
ConnectionCallbacks,
OnConnectionFailedListener,
LocationListener,
OnMyLocationButtonClickListener{
	 public final LatLng LOCATION_START = new LatLng(52.416204,-4.065419);
	
	 private GoogleMap map;
	
	 private LocationClient mLocationClient;
	 private TextView mMessageView;
	 
	// These settings are the same as the settings for the map. They will in fact give you updates
	    // at the maximal rates currently possible.
	 private static final LocationRequest REQUEST = LocationRequest.create()
	            .setInterval(5000)         // 5 seconds
	            .setFastestInterval(16)    // 16ms = 60fps
	            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
	
	/**
	 * This method is called automatically when the activity is created, all it does is starts sets the layout as 
	 * specified in res/layout/activity_map_screen.xml
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_screen);
		
		setUpMapIfNeeded();
		//initLocation();
		//stores a reference of the map to an instance variable
		//map = (MapView) findViewById(R.id.map_view);
	}
	
	@Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        setUpLocationClientIfNeeded();
        mLocationClient.connect();
    }
    
    @Override
    public void onPause() {
        super.onPause();
        if (mLocationClient != null) {
            mLocationClient.disconnect();
        }
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
	            // Try to obtain the map from the SupportMapFragment.
	            map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
	                    .getMap();
	            // Check if we were successful in obtaining the map.
	            if (map != null) {
	                map.setMyLocationEnabled(true);
	                map.setOnMyLocationButtonClickListener(this);
	            }
	        }
	    }
	    
	    private void setUpLocationClientIfNeeded() {
	        if (mLocationClient == null) {
	            mLocationClient = new LocationClient(
	                    getApplicationContext(),
	                    this,  // ConnectionCallbacks
	                    this); // OnConnectionFailedListener
	        }
	    }
	    
	    /**
	     * Button to get current Location. This demonstrates how to get the current Location as required
	     * without needing to register a LocationListener.
	     */
	    public void showMyLocation(View view) {
	        if (mLocationClient != null && mLocationClient.isConnected()) {
	            String msg = "Location = " + mLocationClient.getLastLocation();
	            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
	        }
	    }
	    
	    @Override
	    public void onLocationChanged(Location location) {
	    	LatLng pos = new LatLng(location.getLatitude(),location.getLongitude());
	    	CameraUpdate update = CameraUpdateFactory.newLatLngZoom(pos,17);
			map.animateCamera(update);
	    }
	    
	    @Override
	    public void onConnected(Bundle connectionHint) {
	        mLocationClient.requestLocationUpdates(
	                REQUEST,
	                this);  // LocationListener
	    }
	    
	    @Override
	    public void onDisconnected() {
	        // Do nothing
	    }
	    
	    @Override
	    public void onConnectionFailed(ConnectionResult result) {
	        // Do nothing
	    }
	    
	    @Override
	    public boolean onMyLocationButtonClick() {
	        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
	        // Return false so that we don't consume the event and the default behavior still occurs
	        // (the camera animates to the user's current position).
	        return false;
	    }

}
