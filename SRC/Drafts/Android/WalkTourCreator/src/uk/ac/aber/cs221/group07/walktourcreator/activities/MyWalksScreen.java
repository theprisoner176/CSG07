package uk.ac.aber.cs221.group07.walktourcreator.activities;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import uk.ac.aber.cs221.group07.walktourcreator.model.WalkManager;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.app.Activity;

/**
 * This class is responsible for displaying the a two-tabbed screen with lists of walks, and reacting to button presses
 */
public class MyWalksScreen extends Activity {

	private WalkManager walkDatabase;
	
	/**
	 * This method is called automatically when the activity is created, all it does is starts sets the layout as 
	 * specified in res/layout/activity_my_walks_screen.xml
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_walks_screen);
		updateList();
	}

	/**
	 * updateList populates TableLayouts from both Tabs, with walk data from 'walkDatabase'
	 *
	 */
	private void updateList(){
		CharSequence[] walkNames = { "Aberystwyth", "Borth", "aber->Borth" };
		CharSequence[] shrtDesc = {"Short 1","Short 2","Short 3"};
		
		TableLayout table = (TableLayout) findViewById(R.id.localWalks);
		//TableLayout table2 = (TableLayout) findViewById(R.id.serverSideWalks);
		
	    for (int walkIndex = 0; walkIndex < walkNames.length; walkIndex++) {
	    	TableRow row = new TableRow(this);
	    	
	    	TextView title = new TextView(this);
	    	title.setText(walkNames[walkIndex]);
	    	
	    	TextView description = new TextView(this);
	    	description.setText(shrtDesc[walkIndex]);

	    	row.addView(title);
	    	row.addView(description);
	    	//table2.addView(row);
	    	table.addView(row);
	    }
	}

}
