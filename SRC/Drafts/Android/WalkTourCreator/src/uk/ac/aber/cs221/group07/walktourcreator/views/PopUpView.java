package uk.ac.aber.cs221.group07.walktourcreator.views;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;


/** 
 * provides an overlay panel that other objects can implement
 * @author HarryBuckley
 */
public abstract class PopupView extends PopupWindow{



	public PopupView(Context context,Activity owner,int layoutFile) {
		super(context);
		
	}
}

