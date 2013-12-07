package uk.ac.aber.cs221.group07.walktourcreator.views;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import uk.ac.aber.cs221.group07.walktourcreator.model.PointOfInterest;
import android.app.Activity;
import android.content.DialogInterface;

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
	public PoiInfoView(PointOfInterest point,Activity owner){
		super(null, owner, R.layout.popup_finish_walk);
	}

}
