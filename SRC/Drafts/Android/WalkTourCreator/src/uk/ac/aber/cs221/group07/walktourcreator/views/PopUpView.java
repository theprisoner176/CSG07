package uk.ac.aber.cs221.group07.walktourcreator.views;

import uk.ac.aber.cs221.group07.walktourcreator.activities.GeneralActivity;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.PopupWindow;


/** 
 * provides an general overlay panel that other classes can extend
 * @author HarryBuckley
 */
public abstract class PopupView extends PopupWindow{

	/**provides link to the view that this popup is linked to*/
	private View parent;
	
	/**provides link to the activity that this popup is displayed in*/
	protected GeneralActivity owner;
	
	/**
	 * @param context, provides the context for the view.
	 * @param parent 
	 */
	public PopupView(GeneralActivity context) {
		super(context);
		owner = context;
		this.parent = context.getContentView();
	}
	
	/**
	 * 
	 */
	public void setContentView(View contentView){
		contentView.setBackgroundColor(Color.BLUE);
		super.setContentView(contentView);
		this.showAsDropDown(parent);
		/*update(
				(int)0.05*parent.getWidth(),
				(int)0.05*parent.getHeight(),
				(int)0.9*parent.getWidth(),
				(int)0.9*parent.getHeight()
				,true);*/
	}
	
	/**
	 * 
	 */
	public void cancel(){
		this.dismiss();
	}
}
