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

	private LocationPoint poi;
	private LayoutInflater inflater;
	private View view;
	private ImageHandler imageSaver;
	private WalkScreen activity;
	private WalkModel walk;

	
	public PoiDialogView(WalkScreen context,int viewLayout, LocationPoint point,WalkModel w) {
			super(context,viewLayout);
			walk = w;
			activity = context;
			poi = point;
			this.setInflaterView(inflater, layout);
			//imageSaver = new ImageHandler(activity);
			
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if(which == DialogInterface.BUTTON_POSITIVE){
			setPointInfo();
		}else if(which == DialogInterface.BUTTON_NEGATIVE){
			
		}
	}

<<<<<<< c98dda53440aeb89bf6aae400ca65075ba33c980
=======
//	public void addImage(View v){
//		imageSaver.getPhotoFromLibrary();
//		activity.getFromGallery();
//	}
//	
//	public void takePhoto(View v){
//		imageSaver.getPhotoFromCamera();
//		activity.getFromCamera();
//	}
	
>>>>>>> 814f6a9087b690e419458b2acd998097477d9ca5
	public void setInflaterView(LayoutInflater inf,View v){
		inflater = inf;
		view = v;
	}
	
	public void setPointInfo(){
		PointOfInterest newPoi= new PointOfInterest(poi);
		String poiTitle = ((EditText)view.findViewById(R.id.poi_title)).getText().toString();
		String poiDescription = ((EditText)view.findViewById(R.id.poi_description)).getText().toString();
		newPoi.setTitle(poiTitle);
		newPoi.setDescription(poiDescription);
		walk.addLocation(newPoi);
	}
}


