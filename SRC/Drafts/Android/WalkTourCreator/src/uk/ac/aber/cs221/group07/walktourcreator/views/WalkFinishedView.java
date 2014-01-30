package uk.ac.aber.cs221.group07.walktourcreator.views;

import uk.ac.aber.cs221.group07.walktourcreator.activities.WalkScreen;
import uk.ac.aber.cs221.group07.walktourcreator.model.FileTransferManager;
import uk.ac.aber.cs221.group07.walktourcreator.model.WalkModel;
import android.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * 
 * Dialog which is displayed when the walk is finished
 * 
 * @author HarryBuckley
 * @author Martin Zokov
 * @since 0.1
 * @version 0.1
 * 
 * Copyright (c) 2013 Aberystwyth University.
 * All rights reserved.
 * Dialog which is displayed when the walk is finished
 *
 */
public class WalkFinishedView implements DialogInterface.OnClickListener{
	/** Reference to the current walk*/
	private WalkModel walk;
	/** Reference to the activity that created the popup*/
	private WalkScreen activity;
	
	//public WalkFinishedView(WalkScreen context, int viewLayout, WalkModel currentWalk,WalkScreen gMap) {
	/** Constructor for the popup.
	 * @param context the activity that created the popup
	 * @param viewLayout id of the layout that will be used
	 * @param currentwalk the current walk object
	 * @param walkScreen the current walk screen*/
	public WalkFinishedView(WalkScreen context, int viewLayout, WalkModel currentWalk,WalkScreen walkScreen) {
		
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(viewLayout, null);
		
		//this.layout = this.getLayout();
		//((TextView)layout.findViewById(R.id.textView1)).getText().toString();
		
		new AlertDialog.Builder(context)
	    .setView(layout)
	    .setPositiveButton(android.R.string.yes, this)
	    .setNegativeButton(android.R.string.no, this)
	    .show();
		
		walk = currentWalk;
		activity = walkScreen;
	}
	
	/** Implementation of the listener's onClick method. If the user presses OK,
	 * the walk is uploaded to the server*/
	@Override
	public void onClick(DialogInterface dialog, int which) {
		if(which == DialogInterface.BUTTON_POSITIVE){
			activity.uploadWalk();
		}
		
	}


}


