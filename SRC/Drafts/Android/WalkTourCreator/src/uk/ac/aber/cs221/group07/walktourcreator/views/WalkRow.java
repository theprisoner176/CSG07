package uk.ac.aber.cs221.group07.walktourcreator.views;

import uk.ac.aber.cs221.group07.walktourcreator.model.WalkModel;
import android.content.Context;
import android.widget.TableRow;
import android.widget.TextView;

public class WalkRow extends TableRow{

	private TextView title;
	private TextView short_desc;
	private TextView long_desc;
	private TextView distance;
	private TextView time_taken;
	
	
	
	public WalkRow(Context context,WalkModel walk) {
		super(context);
    	
    	title = new TextView(context);
    	title.setText(walk.getTitle());
    	
    	short_desc = new TextView(context);
    	short_desc.setText(makeTextFit(walk.getShortDescription(),10));
    	
    	long_desc = new TextView(context);
    	long_desc.setText(makeTextFit(walk.getLongDescription(),20));
    	
    	distance = new TextView(context);
    	distance.setText(""+walk.getDistance());
    	
    	time_taken = new TextView(context);
    	time_taken.setText(""+walk.getTimeTaken());

    	addView(title);
    	addView(short_desc);
    	addView(long_desc);
    	addView(distance);
    	addView(time_taken);
	}
	public String makeTextFit(String s,int size){
		if(s.length()<=size||size<5)
			return s;
		s= s.substring(0, size-4);
		s = s+"...|";
		return s;
	}

}
