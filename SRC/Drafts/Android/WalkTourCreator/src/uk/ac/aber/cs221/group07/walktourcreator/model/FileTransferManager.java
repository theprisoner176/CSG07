package uk.ac.aber.cs221.group07.walktourcreator.model;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
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
	
	private static void uploadWalkData(WalkModel data){
		post(packageData(data));
	}
	
	private static List<NameValuePair> packageData(WalkModel data){
		List<NameValuePair> dataPairs = new Vector<NameValuePair>();
		
		dataPairs.add(new BasicNameValuePair("title"		, data.getTitle()));
		dataPairs.add(new BasicNameValuePair("short_desc"	, data.getShortDescription()));
		dataPairs.add(new BasicNameValuePair("long_desc"	, data.getLongDescription()));
		dataPairs.add(new BasicNameValuePair("hours"		, Double.toString(data.getTimeTaken())));
		dataPairs.add(new BasicNameValuePair("distance"		, Double.toString(data.getDistance())));
		
		
		for(LocationPoint point:data.getRoutePath()){
			if(point instanceof LocationPoint){
				packageLocation(dataPairs,point);
			}
			else{
				packageLocation(dataPairs,point);
			}
		}
		//add other stuff to upload places and locations
        return dataPairs;
	}
	
	private static void packageLocation(List<NameValuePair> dataPairs,LocationPoint point){
		
		dataPairs.add(new BasicNameValuePair("latitude"		, Double.toString(point.getLatitude())));
		dataPairs.add(new BasicNameValuePair("longitude"	, Double.toString(point.getLongitude())));
		dataPairs.add(new BasicNameValuePair("time"			, Double.toString(point.getTime())));
	}
	
	private static void post(List<NameValuePair> pakagedData) {
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost(dataServer);
	    try {
	    	//add walk data to message
	        httppost.setEntity(new UrlEncodedFormEntity(pakagedData));
	        //send message
	        HttpResponse response = httpclient.execute(httppost);
	        
	        //do something to check the response
	        //...
	        //do some error handling
	    } catch (IOException e) { }
	} 

	private class Uploader extends Thread{
		private WalkModel data;
		
		public Uploader(WalkModel walk){
			data = walk;
		}
		public void run(){
			FileTransferManager.uploadWalkData(data);
	
		}
	}
	
	
	

}
