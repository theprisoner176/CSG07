package uk.ac.aber.cs221.group07.walktourcreator.views;

import android.content.DialogInterface;
import android.content.Intent;
import uk.ac.aber.cs221.group07.walktourcreator.activities.MainMenu;
import uk.ac.aber.cs221.group07.walktourcreator.activities.WalkScreen;
import uk.ac.aber.cs221.group07.walktourcreator.services.RouteRecorder;

public class CancelWalkView extends DialogView{
	
	private WalkScreen walk;
	
	public CancelWalkView(WalkScreen context, int viewLayout,WalkScreen w) {
		super(context, viewLayout);
		walk = w;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if(which == DialogInterface.BUTTON_POSITIVE){
			deleteWalk();
		}
	}
	
	public void deleteWalk(){
		walk.recorder.finishWalk();
		walk.finish();
		walk.stopService(new Intent(walk,RouteRecorder.class)); 
		Intent intent = new Intent(walk, MainMenu.class);
		walk.startActivity(intent);
	}
}
