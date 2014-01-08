package uk.ac.aber.cs221.group07.walktourcreator.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author HarryBuckley
 *
 */
public class FileTransferManager{

	private final static String dataServer = "http://users.aber.ac.uk/hfb1/this.php";
			
	/**
	* makes a connection to data server and
	* uploads all files belonging to the given
	* file, the return values will be zero if
	* the method succeeded without problems.
	*/
	public int uploadWalk(WalkModel walk){		
		//create a new thread for each upload
		new Uploader(walk).start();
		return 0;
	}
	
	private static JSONObject packageData(WalkModel data){
		JSONObject walkData = new JSONObject();
		try{
			//add walk data
			walkData.put("title"		, data.getTitle());
			walkData.put("short_desc"	, data.getShortDescription());
			walkData.put("long_desc"	, data.getLongDescription());
			walkData.put("hours"		, data.getTimeTaken());
			walkData.put("distance"		, data.getDistance());
			//add all location points
			walkData.put("locations", packageRoute(data.getRoutePath()));
			//add all places of interest
			walkData.put("places", packagePlaces(data.getPoiList()));
					
		}catch (JSONException e){ }
		return walkData;
	}
	private static JSONArray packagePlaces(Vector<PointOfInterest> poiList) throws JSONException{
		JSONArray routeData = new JSONArray();
		for(PointOfInterest poi: poiList){
			routeData.put(packagePoi(poi));
		}
		return routeData;
	}
	
	private static JSONArray packageRoute(Vector<LocationPoint> route) throws JSONException{
		JSONArray routeData = new JSONArray();
		for(LocationPoint point: route){
			routeData.put(packageLocationPoint(point));
		}
		return routeData;
	}
	
	
	private static JSONObject packageLocationPoint(LocationPoint point) throws JSONException{
		JSONObject location = new JSONObject();

		location.put("longitude", point.getLongitude());
		location.put("latitude", point.getLatitude());
		location.put("time", point.getTime());
		return location;
	}
	
	private static JSONObject packagePoi(PointOfInterest poi) throws JSONException{
		JSONObject place = new JSONObject();
		place.put("description",poi.getDescription() );
		
		JSONArray images = new JSONArray();
		for(ImageInformation image:poi.getImages()){
			images.put(image.getImageAsString());
		}
		
		place.put("images",images);
		return place;
	}
	
	private static void post(JSONObject pakagedData) {
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost post = new HttpPost(dataServer);
	    try {
	    	//add walk data to message	    	
	        List<NameValuePair> dataPairs = new Vector<NameValuePair>();
	        
	        dataPairs.add(new BasicNameValuePair("JSON",pakagedData.toString()));
	      	post.setEntity(new UrlEncodedFormEntity(dataPairs));
	        //send message
	        HttpResponse response = httpclient.execute(post);
	        
	        //do something to check the response
	        //do some error handling
	    } catch (IOException e) { }
	} 

	private class Uploader extends Thread{
		private WalkModel data;
		
		public Uploader(WalkModel walk){
			data = walk;
		}
		public void run(){
			post(packageData(data));
		}
	}
}
