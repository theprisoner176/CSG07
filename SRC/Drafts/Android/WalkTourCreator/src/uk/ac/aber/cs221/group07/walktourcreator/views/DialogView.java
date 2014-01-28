package uk.ac.aber.cs221.group07.walktourcreator.views;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import uk.ac.aber.cs221.group07.walktourcreator.model.DialogClickListener;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

public abstract class DialogView implements DialogInterface.OnClickListener{

	public DialogView(Context context, int viewLayout){
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(viewLayout, null);
		
		new AlertDialog.Builder(context)
	    .setMessage("")
	    .setView(layout)
	    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // do nothing
	        }
	     })
	     .setPositiveButton(android.R.string.yes, this)
	     .show();
	}
	
	@Override
	public abstract void onClick(DialogInterface dialog, int which);
	
	
}
