package uk.ac.aber.cs221.group07.walktourcreator.views;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import uk.ac.aber.cs221.group07.walktourcreator.activities.MapScreen;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

public abstract class DialogView implements DialogInterface.OnClickListener{

	protected MapScreen activity;
	
	public DialogView(MapScreen context, int viewLayout){
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(viewLayout, null);
		activity = context;
		
		new AlertDialog.Builder(context)
	    .setView(layout)
	    .setPositiveButton(android.R.string.yes, this)
	    .setNegativeButton(android.R.string.no, this)
	    .show();
	}
	
	@Override
	public abstract void onClick(DialogInterface dialog, int which);
	
	
}
