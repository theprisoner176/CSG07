package uk.ac.aber.cs221.group07.walktourcreator.views;

import android.content.DialogInterface;
import android.content.Intent;
import uk.ac.aber.cs221.group07.walktourcreator.activities.MainMenu;
import uk.ac.aber.cs221.group07.walktourcreator.activities.WalkScreen;
import uk.ac.aber.cs221.group07.walktourcreator.services.RouteRecorder;

/** 
 * 
 * 
 * @author HarryBuckley
 * @author Martin Zokov
 * @since 0.1
 * @version 0.1
 * 
 * Copyright (c) 2013 Aberystwyth University.
 * All rights reserved.
 */
public class CancelWalkView extends DialogView{
	
	private WalkScreen walk;
	
	public CancelWalkView(WalkScreen context, int viewLayout) {
		super(context, viewLayout);
		walk = context;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if(which == DialogInterface.BUTTON_POSITIVE){
			deleteWalk();
		}
	}
	
	public void deleteWalk(){
		walk.returnToStart(true);
	}
}
