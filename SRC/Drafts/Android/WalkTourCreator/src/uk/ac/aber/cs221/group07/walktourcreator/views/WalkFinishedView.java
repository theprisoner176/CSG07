package uk.ac.aber.cs221.group07.walktourcreator.views;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import uk.ac.aber.cs221.group07.walktourcreator.activities.GeneralActivity;
import uk.ac.aber.cs221.group07.walktourcreator.model.WalkManager;
import uk.ac.aber.cs221.group07.walktourcreator.model.WalkModel;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * 
 * @author HarryBuckley
 *
 */
public class WalkFinishedView extends PopupView{
	
	/**
	 * displays a screen displaying
	 * a summary of the finished walk, and
	 * shows various options to the user regarding the WalkModel.
	 * 
	 * @param walk, the walk that has just been finished
	 * @param owner, activity that owns this popup. 
	 */
	public WalkFinishedView(WalkModel walk,GeneralActivity owner ){
		super(owner);
		LayoutInflater layoutInflater = (LayoutInflater) owner.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	    View popupView = layoutInflater.inflate(R.layout.popup_finish_walk, null);  
		//super.setContentView(popupView);
	}
	
	/**
	 * open a PoiInfoView
	 * @param The parameter v, is the object that called the method. 
	 */
	public void uploadWalk(View v){
		//WalkManager manager = new WalkManager(owner);
		//manager.uploadWalk(walk);
		
	}
}


