package uk.ac.aber.cs221.group07.walktourcreator.views;

import uk.ac.aber.cs221.group07.walktourcreator.model.WalkModel;
import android.content.Context;
import android.widget.TableRow;
import android.widget.TextView;


/**
 * 
 * @author HarryBuckley
 *
 */
public class WalkRow extends TableRow{
	
	/** holds the title of a walk*/
	private TextView title;
	
	/** holds the short description for a walk*/
	private TextView short_desc;
	
	/** holds the long description for a walk*/
	private TextView long_desc;
	
	/** holds the distance of a walk*/
	private TextView distance;
	
	/** hols the time it take to complete a walk*/
	private TextView time_taken;
	
	
	/**
	 * creates a walkRow object that will contain, information about the given walk.
	 * 
	 * @param context
	 * @param walk, the walk that stats are to be displayed.
	 */
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
	
	/**
	 * reduces the length of a string so that it will fit into a column in the table.
	 * @param s the string to the shrunk.
	 * @param size the required string length.
	 * @return the new string of length 'size'
	 */
	public String makeTextFit(String s,int size){
		if(s.length()<=size||size<5)
			return s;
		s= s.substring(0, size-4);
		s = s+"...|";
		return s;
	}

}
