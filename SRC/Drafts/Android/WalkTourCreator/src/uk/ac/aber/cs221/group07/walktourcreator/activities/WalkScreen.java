package uk.ac.aber.cs221.group07.walktourcreator.activities;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import uk.ac.aber.cs221.group07.walktourcreator.model.FileTransferManager;
import uk.ac.aber.cs221.group07.walktourcreator.model.ImageHandler;
import uk.ac.aber.cs221.group07.walktourcreator.model.ImageInformation;
import uk.ac.aber.cs221.group07.walktourcreator.model.LocationPoint;
import uk.ac.aber.cs221.group07.walktourcreator.model.PointOfInterest;
import uk.ac.aber.cs221.group07.walktourcreator.model.WalkModel;
import uk.ac.aber.cs221.group07.walktourcreator.services.PositionListener;
import uk.ac.aber.cs221.group07.walktourcreator.services.RouteRecorder;
import uk.ac.aber.cs221.group07.walktourcreator.views.PoiDialogView;
import uk.ac.aber.cs221.group07.walktourcreator.views.WalkFinishedView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.LocationManager;
import android.net.Uri;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * This class is responsible for displaying the map screen, and reacting to button presses.
 * @author HarryBuckley
 */
public class WalkScreen extends Activity {
	 
	public static int CAMERA_ACTIVITY_RESULT_CODE = 1984;
	public static int GALLERY_ACTIVITY_RESULT_CODE = 1993;
	
	/**The walk that is being */
	protected static WalkModel walk;
	
	/**SHOULD BE IMPROVED JUST USED NOW TO MAKE IT WORK*/
	public static String temp;
	
	private LocationClient mLocationClient;
	private TextView mMessageView;
	private Marker currentPosition;
	private PoiDialogView poiDialog;
	private WalkFinishedView walkFinished;
	
	/** holds the object that is responsible for tracking the path of the walk*/
	private RouteRecorder recorder;
	
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
		
		//setUpMapIfNeeded();
		
        walk = (WalkModel) getIntent().getSerializableExtra("walk");
		
		//location manager to get location data
		LocationManager manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		
		//position listener used to process action when location data is gathered
		PositionListener posListener = new PositionListener();
		posListener.setWalk(this);
		
		//create recorder and pass listener and manager
		recorder = new RouteRecorder(posListener,manager);
		
		//give control of the map
		recorder.setWalk(walk);
		posListener.setRecorder(recorder);
	}
	
	/**
	 * creates and displays a AddPoiView.
	 * @param v, is the object that called the method.
	 */
	public void addPOI(View v){
		/*
		LocationManager poiManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		
		PositionListener poiListener = new PositionListener();
		poiListener.setMap(this);
		
		RouteRecorder poiRec = new RouteRecorder(poiListener, poiManager,true);
		if(walk!=null){
			poiRec.setWalk(walk);
		}
		
		/*/
		PointOfInterest poi = new PointOfInterest(-4,53);
		poi.setDescription("qwertyuiosdfgh ");
		walk.addLocation(poi);
		ImageHandler image = new ImageHandler(this);
		image.getPhotoFromCamera();
		//*/

	}

	
	/**
	* creates and displays a WalkFinishedView.
	* @param v, is the object that called the method.
	*/
	public void finishWalk(View v){
		WalkFinishedView finished = new WalkFinishedView(this,R.layout.walk_finished_dialog, walk,this);
		walkFinished = finished;
	}
	

	public void showDialog(PointOfInterest poi) {
		PoiDialogView poiAdd = new PoiDialogView(this,R.layout.activity_add_poi_dialog,poi);
		poiDialog = poiAdd;
	}
	
	public void cancelWalk(){
		recorder.finishWalk();
		finish();
		stopService(new Intent(this,RouteRecorder.class)); 
		Intent intent = new Intent(this, MainMenu.class);
		startActivity(intent);
	}
	
	public void uploadWalk(){
		FileTransferManager manager = new FileTransferManager();
		manager.uploadWalk(walk);
		//recorder.finishWalk();
		//finish();
		//Intent intent = new Intent(this, MainMenu.class);
		//startActivity(intent);
	}
	
	public void addImage(View v){
		poiDialog.addImage(v);
	}
	
	public void takePhoto(View v){
		poiDialog.takePhoto(v);
	}
	/**
	 * called automatically when the current activity is returned to after requesting a result.
	 * here it is used to add the a picture (from the camera or gallery) to the walk.
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAMERA_ACTIVITY_RESULT_CODE) { //result from camera activity
	        if (resultCode == RESULT_OK) {	        	
	        	PointOfInterest newPoi = walk.getLastPoi(); 
	            newPoi.addImage(new ImageInformation(temp));
	        	Toast.makeText(this, "Image added to walk\n", Toast.LENGTH_LONG).show();
	        } else {
	            Toast.makeText(this, "Image not saved\n", Toast.LENGTH_LONG).show();
	        }
	    }
		if (requestCode == GALLERY_ACTIVITY_RESULT_CODE) { //result from gallery activity
	        if (resultCode == RESULT_OK) {
	        	PointOfInterest newPoi = walk.getLastPoi();
	        	if(newPoi==null){
	        		return;
	        	}	      
	        	newPoi.addImage(new ImageInformation(getRealPathFromURI(data.getData())));
	        	
	            Toast.makeText(this, "Image added to walk\n", Toast.LENGTH_LONG).show();
	        } else {
	            Toast.makeText(this, "Image not added\n", Toast.LENGTH_LONG).show();
	        }
	    }
	}
	
	/*
	 * FROM http://stackoverflow.com/questions/2789276/android-get-real-path-by-uri-getpath 
	 */
	private String getRealPathFromURI(Uri contentURI) {
	    Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
	    if (cursor == null) { // Source is Dropbox or other similar local file path
	        return contentURI.getPath();
	    } else { 
	        cursor.moveToFirst(); 
	        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA); 
	        return cursor.getString(idx); 
	    }
	}
	
}
