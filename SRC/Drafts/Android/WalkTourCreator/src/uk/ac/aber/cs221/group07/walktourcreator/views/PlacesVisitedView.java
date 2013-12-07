package uk.ac.aber.cs221.group07.walktourcreator.views;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import uk.ac.aber.cs221.group07.walktourcreator.model.WalkModel;
import android.app.Activity;
import android.content.DialogInterface;
import android.view.View;

/**
 * 
 * @author HarryBuckley
 *
 */
public class PlacesVisitedView extends PopupView{
	/**
	 * creates a PlacesVisitedView instance. 
	 * All the PointOfInterest from the 
	 * passed walk are displayed in a table.
	 */
	public PlacesVisitedView(WalkModel walk,Activity owner){
		super(null, owner, R.layout.popup_finish_walk);
	}
	
	/**
	 * opens a PoiInfoView.
	 * The parameter v, is the object that called the method. 
	 */
	public void getPoiInfo(View v){

	}
}
