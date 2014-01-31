package uk.ac.aber.cs221.group07.walktourcreator.model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import android.util.Base64;

/**
 * This class stores information about a single image
 * 
 * @author HarryBuckley
 * @author Martin Zokov
 * @since 0.1
 * @version 0.1
 * 
 *          Copyright (c) 2013 Aberystwyth University. All rights reserved.
 */
public class ImageInformation {

   /**
    * The name of the actual file, is has to be unique to all other photos
    * stored by the app
    */
   private String fileName;

   /**
    * Creates the ImageInformation and set name
    * 
    * @param name
    *           the name of the new photo
    */
   public ImageInformation(String filename) {
      fileName = filename;
   }

   /**
    * Open file and returns it as Base64
    * 
    * @return file contents as Base64
    */
   public String getImageAsString() {
      byte[] fileData = null;
      String retval = null;
      try {
         File f = new File(fileName);

         fileData = new byte[(int) f.length()];

         BufferedInputStream buf = new BufferedInputStream(new FileInputStream(
               f));
         buf.read(fileData, 0, fileData.length);
         buf.close();
         retval = Base64.encodeToString(fileData, Base64.NO_WRAP);
         // retval = new String(fileData, "UTF-8");
      } catch (Exception exc) {
      }

      return retval;
   }
}
