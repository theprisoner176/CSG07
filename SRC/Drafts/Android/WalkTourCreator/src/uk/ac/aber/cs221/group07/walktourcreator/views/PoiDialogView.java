package uk.ac.aber.cs221.group07.walktourcreator.views;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import uk.ac.aber.cs221.group07.walktourcreator.activities.WalkScreen;
import uk.ac.aber.cs221.group07.walktourcreator.model.ImageHandler;
import uk.ac.aber.cs221.group07.walktourcreator.model.LocationPoint;
import uk.ac.aber.cs221.group07.walktourcreator.model.PointOfInterest;
import uk.ac.aber.cs221.group07.walktourcreator.model.WalkModel;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class PoiDialogView extends DialogView{

	private LocationPoint point;
	private LayoutInflater inflater;
	private View view;
	private WalkScreen activity;
	private WalkModel walk;

	
	public PoiDialogView(WalkScreen context,int viewLayout, LocationPoint point,WalkModel w) {
			super(context,viewLayout);
			walk = w;
			activity = context;
			this.point = point;
			this.setInflaterView(inflater, layout);
			activity.nextPoi= new PointOfInterest(point);
			//imageSaver = new ImageHandler(activity);
			
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if(which == DialogInterface.BUTTON_POSITIVE){
			setPointInfo();
		}else if(which == DialogInterface.BUTTON_NEGATIVE){
			
		}
	}
	
	
	public void setInflaterView(LayoutInflater inf,View v){
		inflater = inf;
		view = v;
	}
	
	public void setPointInfo(){		
		String poiTitle = ((EditText)view.findViewById(R.id.poi_title)).getText().toString();
		String poiDescription = ((EditText)view.findViewById(R.id.poi_description)).getText().toString();
		
		activity.nextPoi.setTitle(poiTitle);
		activity.nextPoi.setDescription(poiDescription);
		activity.addPoi();
	}
}


