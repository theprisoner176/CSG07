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
 * 
 * @author HarryBuckley
 *
 */
public class WalkFinishedView extends PopupView{
	
	/**
	 * displays a screen displaying
	 * a summary of the finished walk, and
	 * shows various options to the user regarding the WalkModel.
	 */
	public WalkFinishedView(Context context,WalkModel walk,GeneralActivity owner,View parent){
		super(context,parent);
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	    View popupView = layoutInflater.inflate(R.layout.popup_finish_walk, null);  
		super.setContentView(popupView);
	}
	
	/**
	 * open a PoiInfoView
	 * The parameter v, is the object that called the method. 
	 */
	public void uploadWalk(View v){
		
	}
}


