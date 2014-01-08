package uk.ac.aber.cs221.group07.walktourcreator.views;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import uk.ac.aber.cs221.group07.walktourcreator.activities.GeneralActivity;
import uk.ac.aber.cs221.group07.walktourcreator.model.PointOfInterest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

/**
 * 
 * @author HarryBuckley
 *
 */
public class PoiInfoView extends PopupView{
	/**
	 * creates a PoiInfoView instance. The PointOfInterest * that is passed to it
	 *is displayed in in the popup.
	 */
	public PoiInfoView(Context context,PointOfInterest point,GeneralActivity owner){
		super(context,owner.getContentView());			
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	    View popupView = layoutInflater.inflate(R.layout.popup_finish_walk, null);  
		super.setContentView(popupView);
	}

}
