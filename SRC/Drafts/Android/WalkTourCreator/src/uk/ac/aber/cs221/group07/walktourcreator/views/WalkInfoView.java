package uk.ac.aber.cs221.group07.walktourcreator.views;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import uk.ac.aber.cs221.group07.walktourcreator.model.WalkModel;
import android.app.Activity;
import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;

/**
 * 
 * @author HarryBuckley
 *
 */
public class WalkInfoView extends PopupView{
	
	
	/**
	 * creates a WalkInfoView instance.
	 *  The WalkModel that is passed to it
	 *  is displayed in in the popup.
	 */
	public WalkInfoView(WalkModel walk,Activity owner){
		super(null, owner, R.layout.popup_finish_walk);
	}

}