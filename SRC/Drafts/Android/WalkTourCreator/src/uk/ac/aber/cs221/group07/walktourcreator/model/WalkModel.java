package uk.ac.aber.cs221.group07.walktourcreator.model;

import java.io.Serializable;
import java.util.Vector;

/**
 * stores all information about a single walk
 * 
 * @author HarryBuckley
 * @author Martin Zokov
 * @since 0.1
 * @version 0.1
 * 
 *          Copyright (c) 2013 Aberystwyth University. All rights reserved.
 */
public class WalkModel implements Serializable {

   /** holds the name of the walk, is used as an identifier */
   private String title;

   /** store a long description of the walk, it is limited to 1000 characters */
   private String longDesc;

   /** store a short description of the walk, it is limited to 100 characters */
   private String shortDesc;

   /** stores the vector of LocationPoints that make up the walk */
   private Vector<LocationPoint> path;

   /**
    * Creates a new walking model with the title that is passed
    */
   public WalkModel() {
      path = new Vector<LocationPoint>();
   }

   /** returns the name of the walk. */
   public String getTitle() {
      return title;
   }

   /**
    * Sets the name of the walk
    * 
    * @param the
    *           new name of the walk
    * */
   public void setTitle(String newTitle) {
      title = newTitle;
   }

   /** returns a short description of the walk */
   public String getShortDescription() {
      return shortDesc;
   }

   /** set the short description of the walk. */
   public void setShortDescription(String newShortDesc) {
      shortDesc = newShortDesc;
   }

   /** returns a long description of the walk. */
   public String getLongDescription() {
      return longDesc;
   }

   /** set the long description for the walk. */
   public void setLongDescription(String newLongDesc) {
      longDesc = newLongDesc;
   }

   /**
    * returns a vector of all the LocationPoint in the walk. Including
    * PointsOfInterests
    */
   public Vector<LocationPoint> getRoutePath() {
      return path;
   }

   /** adds a LocationPoint to the walk. */
   public void addLocation(LocationPoint point) {
      path.add(point);
   }

   /**
    * Gets the last point of interest
    * 
    * @return PointOfInterest object
    */
   public PointOfInterest getLastPoi() {
      PointOfInterest retval = null;
      for (int i = path.size() - 1; 1 >= 0; i--) {
         if (path.get(i) instanceof PointOfInterest) {
            retval = (PointOfInterest) path.get(i);
            break;
         }
      }
      return retval;
   }

   /**
    * works out the total distance traveled along the walk.
    * 
    * @return the running total of km traveled. 
    */
   public double getDistance() {
      double total = 0;
      for (int i = 0; i < path.size() - 1; i++) {
         total += LocationPoint.distBetween(path.get(i), path.get(i + 1));
      }
      return total;
   }

   /** 
    * works out the total time taken.
    * 
    * @return the elapsed time since the walk was started
    */
   public double getTimeTaken() {
      if (path.size() == 0) {
         return 0;
      } else {
         return path.get(path.size() - 1).getTime() - path.get(0).getTime();
      }
   }
}
