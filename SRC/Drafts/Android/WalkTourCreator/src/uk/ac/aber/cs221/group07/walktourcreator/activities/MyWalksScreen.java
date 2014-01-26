package uk.ac.aber.cs221.group07.walktourcreator.activities;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import uk.ac.aber.cs221.group07.walktourcreator.model.WalkManager;
import uk.ac.aber.cs221.group07.walktourcreator.model.WalkModel;
import uk.ac.aber.cs221.group07.walktourcreator.views.WalkRow;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

/**
 * This class is responsible for displaying the a two-tabbed screen with lists of walks, and reacting to button presses
 * @author HarryBuckley
 */
public class MyWalksScreen extends GeneralActivity {
	
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
	 */
	private void updateList(){
		TableLayout table = (TableLayout) findViewById(R.id.localWalks);
		WalkManager manager = new WalkManager(this);
	    
		for(WalkModel walk:manager.getAllWalks()){
			WalkRow row = new WalkRow(this,walk);
	    	table.addView(row);
		}
	}
	
	/**
	 * open a WalkInfoView popup on selected walk
	 * @param v, is the object that called the method.
	 */
	public void viewWalk(View v){
		//open another view show all the poi in the selected walk.
	}

}
