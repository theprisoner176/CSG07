package uk.ac.aber.cs221.group07.walktourcreator.views;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import uk.ac.aber.cs221.group07.walktourcreator.activities.WalkScreen;
import uk.ac.aber.cs221.group07.walktourcreator.model.ImageHandler;
import uk.ac.aber.cs221.group07.walktourcreator.model.PointOfInterest;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class PoiDialogView extends DialogView{

	private PointOfInterest poi;
	private LayoutInflater inflater;
	private View view;
	private ImageHandler imageSaver;
	private WalkScreen activity;

	
	public PoiDialogView(WalkScreen context,int viewLayout, PointOfInterest poi) {
			super(context,viewLayout);
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(viewLayout, null);
			activity = context;
			this.setPointOfInterest(poi);
			this.setInflaterView(inflater, layout);
			imageSaver = new ImageHandler(activity);
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if(which == DialogInterface.BUTTON_POSITIVE){
			setPointInfo();
		}
	}
	
	public void setPointOfInterest(PointOfInterest point){
		poi = point;
	}
	
	public void addImage(View v){
		imageSaver.getPhotoFromLibrary();
	}
	
	public void takePhoto(View v){
		imageSaver.getPhotoFromCamera();
	}
	
	public void setInflaterView(LayoutInflater inf,View v){
		inflater = inf;
		view = v;
	}
	
	public void setPointInfo(){
		//inflater.inflate(R.layout.activity_add_poi_dialog, null);
		TextView poiTitle = (TextView)view.findViewById(R.id.poi_title);
		TextView poiDescription = (TextView)view.findViewById(R.id.poi_description);
		poi.setTitle(poiTitle.toString());
		poi.setDescription(poiDescription.toString());
	}
}


