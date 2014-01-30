package uk.ac.aber.cs221.group07.walktourcreator.views;

import uk.ac.aber.cs221.group07.walktourcreator.activities.WalkScreen;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class GpsCheckDialog implements DialogInterface.OnClickListener{
	
	private WalkScreen activity;
	
	  public GpsCheckDialog(WalkScreen context) {
		  activity = context;
		  new AlertDialog.Builder(context)
		    .setPositiveButton(android.R.string.yes, this)
		    .setNegativeButton(android.R.string.no, this)
		    .show();
		}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if(which == DialogInterface.BUTTON_POSITIVE){
			 activity.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
		}else if(which == DialogInterface.BUTTON_NEGATIVE){
			activity.returnToStart();
		}
	}
	
	  
}
