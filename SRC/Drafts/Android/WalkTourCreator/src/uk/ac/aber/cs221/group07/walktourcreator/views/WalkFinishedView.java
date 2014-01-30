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
 * @author HarryBuckley
 * @author Martin Zokov
 * @since 0.1
 * @version 0.1
 * 
 * Copyright (c) 2013 Aberystwyth University.
 * All rights reserved.
 */
public class WalkFinishedView implements DialogInterface.OnClickListener{
	private WalkModel walk;
	private WalkScreen map;
	
	public WalkFinishedView(WalkScreen context, int viewLayout, WalkModel currentWalk,WalkScreen gMap) {
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
		map = gMap;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if(which == DialogInterface.BUTTON_POSITIVE){
			map.uploadWalk();
		}
		
	}


}


