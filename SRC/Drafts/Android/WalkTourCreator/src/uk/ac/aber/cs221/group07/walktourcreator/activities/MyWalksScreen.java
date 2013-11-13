package uk.ac.aber.cs221.group07.walktourcreator.activities;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.app.Activity;


public class MyWalksScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_walks_screen);
		updateList();
	}

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
