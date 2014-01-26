package uk.ac.aber.cs221.group07.walktourcreator.views;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import uk.ac.aber.cs221.group07.walktourcreator.activities.GeneralActivity;
import uk.ac.aber.cs221.group07.walktourcreator.model.ImageHandler;
import uk.ac.aber.cs221.group07.walktourcreator.services.RouteRecorder;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;

/**
 * AddPoiView creates popupView that prompts the user to enter information 
 * about a new place of interest.
 * @author HarryBuckley
 *
 */
public class AddPoiView extends PopupView{

	/**
	 * displays an place description input popup, 
	 * and gives it a link to the RouteRecorder 
	 * @param context, provides the context for the view.
	 * @param recorder, the RouteRecorder that ...
	 */
	public AddPoiView(GeneralActivity context,RouteRecorder recorder){
		super(context);		
		
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	    View popupView = layoutInflater.inflate(R.layout.popup_finish_walk, null);  
		super.setContentView(popupView);
	}
	
	/**
	 * creates a PointOfInterst out of the given
	 * data (from text fields) and add the point the the WalkModel 
	 * @param The parameter v, is the object that called the method.
	 */
	public void submit(View v){
		
	}
	
	/**
	 * uses ImageHandler to open the photoLibrary,
	 * the selected photo is then added to the PointOfInterest. 
	 * @param The parameter v, is the object that called the method.
	 */
	public void getPhotoFromLibrary(View v){
		ImageHandler image = new ImageHandler(owner);
		image.getPhotoFromLibrary();
	}
	
	/**
	 * uses ImageHandler to open the camera app,
	 * the taken photo is then added to the PointOfInterest. 
	 * @param The parameter v, is the object that called the method. 
	 */
	public void getPhotoFromCamera(View v){
		ImageHandler image = new ImageHandler(owner);
		image.getPhotoFromCamera();
	}


}
