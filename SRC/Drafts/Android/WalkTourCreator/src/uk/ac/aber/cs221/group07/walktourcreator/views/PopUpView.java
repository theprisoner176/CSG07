package uk.ac.aber.cs221.group07.walktourcreator.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * provides an overlay panel that other objects can implement
 */
public abstract class PopupView extends DialogFragment implements OnClickListener{

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("message 1")
               .setPositiveButton("message 2",this)
               .setNegativeButton("message 3",this);
        return builder.create();
    }
}
