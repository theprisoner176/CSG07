package uk.ac.aber.cs221.group07.walktourcreator.views;

import uk.ac.aber.cs221.group07.walktourcreator.activities.WalkScreen;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

public abstract class DialogView implements DialogInterface.OnClickListener{

	protected WalkScreen activity;
	protected View layout;
	protected AlertDialog dialog;
	
	
	public DialogView(WalkScreen context, int viewLayout){
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(viewLayout, null);
		this.layout = layout;
		activity = context;
		
		dialog = new AlertDialog.Builder(context)
	    .setView(layout)
	    .setPositiveButton(android.R.string.yes, this)
	    .setNegativeButton(android.R.string.no, this)
	    .create();
		
		
	}
	public void show(){
		dialog.show();
	}
	
	public void dismiss(){
		dialog.dismiss();
	}
	
	@Override
	public abstract void onClick(DialogInterface dialog, int which);
	
	public View getLayout(){
		return layout;
	}
	
}
