package uk.ac.aber.cs221.group07.walktourcreator.views;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import uk.ac.aber.cs221.group07.walktourcreator.activities.WalkScreen;
import uk.ac.aber.cs221.group07.walktourcreator.model.LocationPoint;
import uk.ac.aber.cs221.group07.walktourcreator.model.PointOfInterest;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 
 * @author HarryBuckley
 * @author Martin Zokov
 * @since 0.1
 * @version 0.1
 * 
 *          Copyright (c) 2013 Aberystwyth University. All rights reserved.
 */

public class PoiDialogView extends DialogView {

   /**
    * holds the inflator used to create the view from the xml file
    */
   private LayoutInflater inflater;
   private View view;
   private WalkScreen activity;

   public PoiDialogView(WalkScreen context, int viewLayout, LocationPoint point) {
      super(context, viewLayout);
      activity = context;
      this.setInflaterView(inflater, layout);
      if (activity.nextPoi == null) {
         activity.nextPoi = new PointOfInterest(point);
      } else {
         ((EditText) view.findViewById(R.id.poi_title))
               .setText(activity.nextPoi.getTitle());
         ((EditText) view.findViewById(R.id.poi_description))
               .setText(activity.nextPoi.getDescription());
      }
   }

   @Override
   public void onClick(DialogInterface dialog, int which) {
      if (which == DialogInterface.BUTTON_POSITIVE) {
         setPointInfo();
      } else {
         activity.nextPoi = null;
         Toast.makeText(activity, "Place was not added\n", Toast.LENGTH_LONG)
               .show();
      }

   }

   public void setInflaterView(LayoutInflater inf, View v) {
      inflater = inf;
      view = v;
   }

   public void setPointInfo() {
      String poiTitle = ((EditText) view.findViewById(R.id.poi_title))
            .getText().toString();
      String poiDescription = ((EditText) view
            .findViewById(R.id.poi_description)).getText().toString();

      activity.nextPoi.setTitle(poiTitle);
      activity.nextPoi.setDescription(poiDescription);

      if (poiTitle.length() != 0 && poiDescription.length() != 0) {
         activity.addPoi();
         return;
      }
      Toast.makeText(activity,
            "Place was not added,\nPlace must have a title and description",
            Toast.LENGTH_LONG).show();
      activity.addPOI(null);

   }
}
