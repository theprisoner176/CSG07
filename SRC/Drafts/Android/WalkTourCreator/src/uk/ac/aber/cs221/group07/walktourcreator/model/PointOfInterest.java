package uk.ac.aber.cs221.group07.walktourcreator.model;

import java.util.Vector;

/**
 * Stores information about a place of interest
 * 
 * @author HarryBuckley
 * @author Martin Zokov
 * @since 0.1
 * @version 0.1
 * 
 *          Copyright (c) 2013 Aberystwyth University. All rights reserved.
 */
public class PointOfInterest extends LocationPoint {

   /** holds all the images that are related to this poi. */
   private Vector<ImageInformation> images = new Vector<ImageInformation>();

   /** holds the name of the POI */
   private String title;

   /** holds a description of the POI */
   private String description;

   /**
    * creates a PointOfInterest, at position x,y. The time is set automatically
    * @param point, contains the location where this poi is.
    */
   public PointOfInterest(LocationPoint point) {
      super(point.latitude, point.longitude);
   }

   /**
    * adds reference to an image to the poi.
    * 
    * @param newImage,is the image that is to be added
    */
   public void addImage(ImageInformation newImage) {
      images.add(newImage);
   }

   /** returns all the images associated with this point. */
   public Vector<ImageInformation> getImages() {
      return images;
   }

   /** returns the description of this place. */
   public String getDescription() {
      return description;
   }

   /** sets the description of this point. */
   public void setDescription(String desc) {
      description = desc;
   }

   /**
    * gets the title of the POI
    * 
    * @return String the title of the walk
    */
   public String getTitle() {
      return title;
   }

   /** sets the title of this point. */
   public void setTitle(String title) {
      this.title = title;
   }

   /**
    * returns the longitude, the north/south distance from the equator.
    */
   @Override
   public double getLongitude() {
      return longitude;
   }

   /**
    * returns the latitude, the north/south distance from the equator.
    */
   @Override
   public double getLatitude() {
      return latitude;
   }
}
