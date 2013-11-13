package uk.ac.aber.cs221.group07.walktourcreator.views;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

/**
 * provides an overlay panel that other objects can implement
 */
public abstract class PopupView extends PopupWindow {

	public PopupView(Context context) {
		super(context);
	}
	public PopupView(View inflate, int i, int j, boolean b) {
		super(inflate,i,j,b);
	}

}
