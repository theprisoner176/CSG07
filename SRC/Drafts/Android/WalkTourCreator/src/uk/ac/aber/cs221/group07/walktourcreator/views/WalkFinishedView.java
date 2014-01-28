package uk.ac.aber.cs221.group07.walktourcreator.views;

import uk.ac.aber.cs221.group07.walktourcreator.activities.GeneralActivity;
import uk.ac.aber.cs221.group07.walktourcreator.activities.MapScreen;
import uk.ac.aber.cs221.group07.walktourcreator.model.FileTransferManager;
import uk.ac.aber.cs221.group07.walktourcreator.model.WalkModel;
import android.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

/**
 * 
 * @author HarryBuckley
 *
 */
public class WalkFinishedView implements DialogInterface.OnClickListener{
	private WalkModel walk;
	private MapScreen map;
	
	public WalkFinishedView(GeneralActivity context, int viewLayout, WalkModel currentWalk,MapScreen gMap) {
		
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(viewLayout, null);
		
		new AlertDialog.Builder(context)
	    .setView(layout)
	    .setPositiveButton(android.R.string.yes, this)
	    .setNegativeButton(android.R.string.no, this)
	    .show();
		
		walk = currentWalk;
		map = gMap;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if(which == DialogInterface.BUTTON_POSITIVE){
			map.uploadWalk();
		}else if(which == DialogInterface.BUTTON_NEGATIVE){
			map.cancelWalk();
		}
		
	}


}


