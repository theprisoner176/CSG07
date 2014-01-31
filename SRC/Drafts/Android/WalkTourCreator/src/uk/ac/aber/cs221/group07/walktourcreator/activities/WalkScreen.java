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
import android.provider.MediaStore.MediaColumns;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

/**
 * This class is responsible for displaying the map screen, and reacting to
 * button presses.
 * 
 * @author HarryBuckley
 * @author Martin Zokov
 * @since 0.1
 * @version 0.1
 * 
 *          Copyright (c) 2013 Aberystwyth University. All rights reserved.
 */
public class WalkScreen extends Activity {

   /** The walk that is being */
   private static WalkModel walk;

   /** used to share the next/current poi with the PoiDialogView */
   public PointOfInterest nextPoi;

   /** SHOULD BE IMPROVED JUST USED NOW TO MAKE IT WORK */
   public static String temp;

   /** holds the object that is responsible for tracking the path of the walk */
   private RouteRecorder recorder;

   /**Holds a reference to the add poi pop up window */
   private PoiDialogView poiDialog;

   /**
    * This method is called automatically when the activity is created, all it
    * does is starts sets the layout as specified in
    * res/layout/activity_map_screen.xml
    * 
    * @param savedInstanceState is not used in this case
    */
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_map_screen);
      setupIfNeeded();
   }

   /**
    * method, called onCreate, if certain values are null then they will be initialized
    * else they'll be remain unchanged.
    */
   private void setupIfNeeded() {
      if (walk == null)
         walk = (WalkModel) getIntent().getSerializableExtra("walk");
      if (recorder == null) {
         // location manager to get location data
         LocationManager manager = (LocationManager) this
               .getSystemService(Context.LOCATION_SERVICE);
         if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            new GpsCheckDialog(this);
         recorder = new RouteRecorder(this, walk, manager);
      }
   }

   /**
    * creates and displays a AddPoiView.
    * 
    * @param v, is the object that called the method.
    */
   public void addPOI(View v) {
      // used to test without gps location.
      // LocationPoint point = new LocationPoint(10,10);
      
      LocationPoint point = recorder.getLastKnownPosition();

      if (point == null) {
         Toast.makeText(this, "Waiting for GPS\n", Toast.LENGTH_LONG).show();
         return;
      }
      if (poiDialog == null || nextPoi == null) {
         poiDialog = new PoiDialogView(this, R.layout.activity_add_poi_dialog,
               point);
      }
      poiDialog.show();
   }

   /**
    * creates and displays a WalkFinishedView.
    * 
    * @param v, is the object that called the method.
    */
   public void finishWalk(View v) {
      new WalkFinishedView(this, R.layout.walk_finished_dialog, walk, this);
   }

   /**
    * creates and displays an EditWalkView.
    * 
    * @param v, is the object that called the method.
    */
   public void editWalkDialog(View v) {
      new EditWalkView(this, R.layout.edit_walk_dialog, walk).show();
   }

   /**
    * creates and displays a CancelWalkView.
    * 
    * @param v, is the object that called the method.
    */
   public void cancelWalk(View v) {
      new CancelWalkView(this, R.layout.cancel_walk_dialog).show();
   }

   /**
    * uses an ImageHandler to start a gallery intent
    * 
    * @param v, is the object that called the method.
    */
   public void addImageToPoiFromGallery(View v) {
      ImageHandler image = new ImageHandler(this);
      image.getPhotoFromLibrary();
      poiDialog.dismiss();

   }

   /**
    *  uses an ImageHandler to start a camera intent
    * 
    * @param v, is the object that called the method.
    */
   public void addImageToPoiFromCamera(View v) {
      ImageHandler image = new ImageHandler(this);
      image.getPhotoFromCamera();
      poiDialog.dismiss();
   }

   /**
    * called by PoiDialogView, used to add poi to the walkModel
    */
   public void addPoi() {
      if (nextPoi != null) {
         walk.addLocation(nextPoi);
         Toast.makeText(this,
               "Place of Interest " + nextPoi.getTitle() + "\n has been added",
               Toast.LENGTH_LONG).show();
      }
      nextPoi = null;
   }

   /**
    * starts the FileTransferManager, that will upload the walkModel to server.
    * and will display message to user saying that the upload has started
    */
   public void uploadWalk() {
      Toast.makeText(this, "Upload has Started, it may take a while",
            Toast.LENGTH_LONG).show();
      new FileTransferManager(this, walk);
   }

   /**
    * returns the user to the start screen, is called either after the upload has
    * finished or when the user cancels the walk.
    * 
    * @param status, holds the status of the upload.
    */
   public void returnToStart(boolean status) {
      if (recorder != null) {
         recorder.finishWalk();
      }
      Intent intent = new Intent(this, MainMenu.class);
      startActivity(intent);
      finish();
   }

   /**
    * called automatically when the current activity is returned to after
    * requesting a result from another activity. here it is used to add the
    * a picture (from the camera or gallery) to the walk.
    * 
    * @param requestCode, is the number that was given the started activity on startup.
    * @param resultCode, holds a status code from the Activity that was started.
    */
   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      if (resultCode == RESULT_OK) {
         if (nextPoi == null)
            return;
         else if (requestCode == ImageHandler.CAMERA_ACTIVITY_RESULT_CODE)
            nextPoi.addImage(new ImageInformation(temp));
         else if (requestCode == ImageHandler.GALLERY_ACTIVITY_RESULT_CODE)
            nextPoi.addImage(new ImageInformation(getRealPathFromURI(data
                  .getData())));

         Toast.makeText(this, "Image added to Location\n", Toast.LENGTH_LONG)
               .show();
      } else {
         Toast.makeText(this, "Image not added\n", Toast.LENGTH_LONG).show();
      }
      poiDialog.show();
   }

   /**
    * overrides the default behavior of the back/up button and maps it to 
    * open a cancel walk dialog instead.
    * 
    * @param keyCode, used to identify the pressed button.
    * @param event, not used but required by android. (can be null)
    */
   @Override
   public boolean onKeyDown(int keyCode, KeyEvent event) {
      if (keyCode == KeyEvent.KEYCODE_BACK) {
         cancelWalk(null);
         return true;
      }
      return false;
   }

   /**
    * converts a Uri into an absolute filepath.
    *  
    * @param contentURI the uri that the you want the absolute path of.
    * @return the filepath for the given Uri
    */
   private String getRealPathFromURI(Uri contentURI) {
      //FROM
      //http://stackoverflow.com/questions/2789276/android-get-real-path-by-uri
      //getpath
      Cursor cursor = getContentResolver().query(contentURI, null, null, null,
            null);
      if (cursor == null) { // Source is Dropbox or other similar local file
                            // path
         return contentURI.getPath();
      } else {
         cursor.moveToFirst();
         int idx = cursor.getColumnIndex(MediaColumns.DATA);
         return cursor.getString(idx);
      }
   }

}
