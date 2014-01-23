package uk.ac.aber.cs221.group07.walktourcreator.views;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;


/** 
 * provides an general overlay panel that other classes can extend
 * @author HarryBuckley
 */
public abstract class PopupView extends PopupWindow{

	public View parent;
	
	/**
	 * 
	 * @param context, provides the context for the view.
	 * @param parent 
	 */
	public PopupView(Context context, View parent) {
		super(context);
		this.parent = parent;
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
