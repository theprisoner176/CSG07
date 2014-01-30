package uk.ac.aber.cs221.group07.walktourcreator.activities;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import uk.ac.aber.cs221.group07.walktourcreator.model.FileTransferManager;
import uk.ac.aber.cs221.group07.walktourcreator.model.ImageHandler;
import uk.ac.aber.cs221.group07.walktourcreator.model.ImageInformation;
import uk.ac.aber.cs221.group07.walktourcreator.model.LocationPoint;
import uk.ac.aber.cs221.group07.walktourcreator.model.PointOfInterest;
import uk.ac.aber.cs221.group07.walktourcreator.model.WalkModel;
import uk.ac.aber.cs221.group07.walktourcreator.services.RouteRecorder;
import uk.ac.aber.cs221.group07.walktourcreator.views.CancelWalkView;
import uk.ac.aber.cs221.group07.walktourcreator.views.EditWalkView;
import uk.ac.aber.cs221.group07.walktourcreator.views.GpsCheckDialog;
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
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

/**
 * This class is responsible for displaying the map screen, and reacting to button presses.
 * @author HarryBuckley
 */
public class WalkScreen extends Activity {
	 
	
	/**The walk that is being */
	private static WalkModel walk;
	
	/**SHOULD BE IMPROVED JUST USED NOW TO MAKE IT WORK*/
	public PointOfInterest nextPoi;
	
	/**SHOULD BE IMPROVED JUST USED NOW TO MAKE IT WORK*/
	public static String temp;
	
	/** holds the object that is responsible for tracking the path of the walk*/
	public RouteRecorder recorder;
	
	
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
		runOnInitialCreate();
	}
	
	public void runOnInitialCreate(){
		if(walk==null){
			walk = (WalkModel) getIntent().getSerializableExtra("walk");
		}
		if(recorder==null){
			//location manager to get location data
			LocationManager manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
			if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
		        new GpsCheckDialog(this);
		    }
			recorder = new RouteRecorder(this,walk,manager);
		}
	}
	
	/**
	 * creates and displays a AddPoiView.
	 * @param v, is the object that called the method.
	 */
	public void addPOI(View v){
		
		
		//LocationPoint point =  new LocationPoint(10,10);
		
		//used to test without gps location.
		LocationPoint point =  recorder.getLastKnownPosition();
		
		if(point==null){
			Toast.makeText(this,"Waiting for GPS\n",Toast.LENGTH_LONG).show();
			return;
		}
		
		new PoiDialogView(this,R.layout.activity_add_poi_dialog,point,walk);
	}
	

	public void getFromGallery(View v){
		ImageHandler image = new ImageHandler(this);
		image.getPhotoFromLibrary();
	}
	public void getFromCamera(View v){
		ImageHandler image = new ImageHandler(this);
		image.getPhotoFromCamera();
	}
	
	
	public void addPoi(){
		if(nextPoi!=null)
			walk.addLocation(nextPoi);
	}
	
	
	/**
	* creates and displays a WalkFinishedView.
	* @param v, is the object that called the method.
	*/
	public void finishWalk(View v){
		new WalkFinishedView(this,R.layout.walk_finished_dialog, walk,this);
	}
	
	public void returnToStart(){
		Intent intent = new Intent(this, MainMenu.class);
	    startActivity(intent);
	    finish();
	}
	
	public void editWalkDialog(View v){
		new EditWalkView(this,R.layout.edit_walk_dialog,walk);
	}
	
	public void cancelWalk(View v){
		new CancelWalkView(this, R.layout.cancel_walk_dialog);
		
	}
	
	public void uploadWalk(){
		new FileTransferManager(this,walk);
	}
	
	/**
	 * called automatically when the current activity is returned to after requesting a result.
	 * here it is used to add the a picture (from the camera or gallery) to the walk.
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
        	if(nextPoi==null)
        		return;
        	else if(requestCode == ImageHandler.CAMERA_ACTIVITY_RESULT_CODE)
        		nextPoi.addImage(new ImageInformation(temp));
        	else if(requestCode == ImageHandler.GALLERY_ACTIVITY_RESULT_CODE)
        		nextPoi.addImage(new ImageInformation(getRealPathFromURI(data.getData())));
  
        	Toast.makeText(this, "Image added to Location\n", Toast.LENGTH_LONG).show();
		}
		else{
			Toast.makeText(this, "Image not added\n", Toast.LENGTH_LONG).show();
		}
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
        	cancelWalk(null);
            return true;
        }
        return false;
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
