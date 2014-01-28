package uk.ac.aber.cs221.group07.walktourcreator.views;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import uk.ac.aber.cs221.group07.walktourcreator.model.DialogClickListener;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;


/** 
 * provides an general overlay panel that other classes can extend
 * @author HarryBuckley
 */
public abstract class PopupView {

	public PopupView(Context context){
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.activity_add_poi_dialog, null);
		
		DialogClickListener okListener = new DialogClickListener();
//		okListener.setPointOfInterest(poi);
//		okListener.setInflaterView(inflater, layout);
		
		new AlertDialog.Builder(context)
	    .setMessage("Enter details for the Point of Interest")
	    .setView(layout)
	    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // do nothing
	        }
	     })
	     .setPositiveButton(android.R.string.yes, okListener)
	     .show();
	}
	
}
