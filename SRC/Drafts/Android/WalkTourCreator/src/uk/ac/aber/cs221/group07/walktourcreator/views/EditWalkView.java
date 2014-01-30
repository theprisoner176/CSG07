package uk.ac.aber.cs221.group07.walktourcreator.views;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import uk.ac.aber.cs221.group07.walktourcreator.activities.WalkScreen;
import uk.ac.aber.cs221.group07.walktourcreator.model.WalkModel;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditWalkView extends DialogView{

	private WalkModel walk;
	private LayoutInflater inflater;
	private View layout;
	
	public EditWalkView(WalkScreen context, int viewLayout,WalkModel w) {
		super(context, viewLayout);
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//layout = inflater.inflate(viewLayout, null); 
		this.layout = this.getLayout();
		walk = w;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		editWalk();
	}
	
	public void editWalk(){
		String walkTitle = ((EditText)layout.findViewById(R.id.edit_walk_title)).getText().toString();
		String shortDesc = ((EditText)layout.findViewById(R.id.edit_walk_short_description)).getText().toString();
		String longDesc = ((EditText)layout.findViewById(R.id.edit_walk_long_description)).getText().toString();
		if(!WalkModel.isValidTitle(walkTitle)){
			Toast.makeText(activity,"A title must contains no white spaces\nAnd Must not be empty",
					Toast.LENGTH_LONG).show();
		}
		else if(!WalkModel.isValidShortDesc(shortDesc)){
			Toast.makeText(activity,"Description has to be under 100 characters\nAnd Must not be empty",
					Toast.LENGTH_LONG).show();
		}
		else if(!WalkModel.isValidLongDesc(longDesc)){
			Toast.makeText(activity,"Detailed Description has to be under 1000 characters\nAnd Must not be empty",
					Toast.LENGTH_LONG).show();
		}
		else{
		walk.setTitle(walkTitle);
		walk.setShortDescription(shortDesc);
		walk.setLongDescription(longDesc);
		}
	}

}
