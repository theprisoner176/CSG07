package uk.ac.aber.cs221.group07.walktourcreator.views;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import uk.ac.aber.cs221.group07.walktourcreator.activities.GeneralActivity;
import uk.ac.aber.cs221.group07.walktourcreator.model.WalkModel;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Create a popupView that displays a list of all the points of interest
 * that have been visited along the walk.
 * 
 * @author HarryBuckley
 *
 */
public class PlacesVisitedView extends PopupView{
	/**
	 * creates a PlacesVisitedView instance. 
	 * All the PointOfInterest from the 
	 * passed walk are displayed in a table.
	 * @param context, 
	 * @param walk, the walk that contains the locations that are to be displayed
	 * @param owner, this is the view that this class is a subview of.
	 */
	public PlacesVisitedView(Context context,WalkModel walk,GeneralActivity owner){
		super(context,owner.getContentView());			
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	    View popupView = layoutInflater.inflate(R.layout.popup_finish_walk, null);  
		super.setContentView(popupView);
	}
	
	/**
	 * opens a PoiInfoView.
	 * @param The parameter v, is the object that called the method. 
	 */
	public void getPoiInfo(View v){

	}
}
