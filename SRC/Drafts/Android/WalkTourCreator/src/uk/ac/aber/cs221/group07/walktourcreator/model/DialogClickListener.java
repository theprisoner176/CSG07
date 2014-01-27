package uk.ac.aber.cs221.group07.walktourcreator.model;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class DialogClickListener implements DialogInterface.OnClickListener{

	private PointOfInterest poi;
	private LayoutInflater inflater;
	private View view;
	
	@Override
	public void onClick(DialogInterface dialog, int which) {
		setPoiInfo();
	}
	
	public void setPointOfInterest(PointOfInterest point){
		poi = point;
	}
	
	public void setInflaterView(LayoutInflater inf,View v){
		inflater = inf;
		view = v;
	}
	
	public void setPoiInfo(){
		inflater.inflate(R.layout.activity_add_poi_dialog, null);
		TextView poiTitle = (TextView)view.findViewById(R.id.poi_title);
		TextView poiDescription = (TextView)view.findViewById(R.id.poi_description);
		poi.setTitle(poiTitle.toString());
		poi.setDescription(poiDescription.toString());
	}

}
