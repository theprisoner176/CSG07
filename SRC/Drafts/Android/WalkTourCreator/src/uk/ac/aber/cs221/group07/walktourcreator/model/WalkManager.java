package uk.ac.aber.cs221.group07.walktourcreator.model;

import java.util.Vector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * interact with database
 * saves and retrieved all walks
 * @author HarryBuckley
 */
public class WalkManager extends SQLiteOpenHelper{
	
	/**holds the name of the database store on the device*/
	private final static String DATABASE_NAME = "walks.db"; 
	
	/**
	 * contains the SQL commands used to create the 'location' table
	 */
	private final static String CREATE_LOCATION_TABLE = 
			"CREATE TABLE location (" +
			"time INTEGER PRIMARY KEY," +
			"walk_id INTEGER NOT NULL REFERENCES walk(id) DEFAULT 0," +
			"latitude REAL DEFAULT 0," +
			"longitude REAL DEFAULT 0)";

	/**
	 * contains the SQL commands used to create the 'walk' table
	 */
	private final static String CREATE_WALK_TABLE = 
			"CREATE TABLE walk (" +
			"id INTEGER PRIMARY KEY," +
			"title TEXT NOT NULL DEFAULT 'defaultWalk'," +
			"short_desc TEXT NOT NULL DEFAULT 'shrt'," +
			"long_desc TEXT NOT NULL DEFAULT 'lng'," +
			"hours REAL NOT NULL DEFAULT 0," +
			"distance REAL NOT NULL DEFAULT 0)";

	/**
	 * contains the SQL commands used to create the 'place' table
	 */
	private final static String CREATE_PLACE_TABLE =
			"CREATE TABLE place (" +
			"time INTEGER PRIMARY KEY," +
			"description INTEGER NOT NULL," +
			"walk_id INTEGER NOT NULL REFERENCES walk(id) DEFAULT 0," +
			"latitude REAL DEFAULT 0," +
			"longitude REAL DEFAULT 0)";

	/**
	 * contains the SQL commands used to create the 'photo' table
	 */
	private final static String CREATE_PHOTO_TABLE =
			"CREATE TABLE photo (" +
			"id INTEGER PRIMARY KEY AUTOINCREMENT," +
			"place_id INTEGER REFERENCES photo(id) ON UPDATE CASCADE ON DELETE CASCADE NOT NULL," +
			"photo_name TEXT NOT NULL)";
	
	
	public WalkManager(Context context) {
		super(context, DATABASE_NAME, null, 17);
	}
	
	
	/**
	 * adds the passed WalkModel to the local database. 
	 * @param walk, is the walk that will be added to the database
	 */
	public void addWalkModel(WalkModel walk){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		//don't add if already added...
		String select = "SELECT * FROM walk WHERE id = " + walk.getID();
		Cursor cur = db.rawQuery(select, null);
		if(cur.getCount()<=0){ //no matches
			cur.close();
			return;
		}
		
		//add to walk table
		values.put("id", walk.getID());
		values.put("title", walk.getTitle());
		values.put("short_desc", walk.getShortDescription());
		values.put("long_desc", walk.getLongDescription());
		values.put("hours", walk.getTimeTaken());
		values.put("distance", walk.getDistance());
		db.insert("walk", null, values);
		//add the path of the walk (locations and places)
		addPath(walk.getRoutePath(),db,walk.getID());
		db.close();
	}
	
	
	
	/**
	 * adds all the path data to the database
	 * 
	 * @param location the location to be added
	 * @param db the local database that is accessed
	 * @param walkId the unique id of the added location.
	 */
	private void addPath(Vector<LocationPoint> path,SQLiteDatabase db,long walkId){
		for(LocationPoint location: path){
			ContentValues values = new ContentValues();
			values.put("time",location.getTime());
			values.put("latitude",location.getLatitude());
			values.put("longitude",location.getLongitude());
		
			if(location instanceof PointOfInterest){
				values.put("description", ((PointOfInterest) location).getDescription());
				addPhotos((PointOfInterest) location,db,location.getTime());
				db.insert("place", null, values);
			}
			else{
				db.insert("location", null, values);
			}
		}
	}
	
	
	/**
	 * 
	 * @param image the image to be added to database
	 * @param db the local database that is accessed
	 * @param placeId the unique id of the photo
	 */
	private void addPhotos(PointOfInterest poi,SQLiteDatabase db,long placeId){
		for(ImageInformation image:poi.getImages()){
			ContentValues values = new ContentValues();
			values.put("place_id",placeId);
			values.put("photo_name", image.getFileName());
		}
	}
	
	/**
	 * returns the WalkModel with an id matching the passed one.
	 * @param index, the id of the walk you want.
	 */
	public Vector<WalkModel> getAllWalks(){
		SQLiteDatabase db = this.getReadableDatabase();
		String select = "SELECT * FROM walk";
		Vector<WalkModel> walks= new Vector<WalkModel>();
		
		Cursor cur = db.rawQuery(select, null);
		if(cur.getCount()<=0){ //no matches
			cur.close();
			return null;
		}
		cur.moveToFirst();
		do{
			String title = cur.getString(cur.getColumnIndex("title"));
			int id = cur.getInt(cur.getColumnIndex("id"));
			String longDesc = cur.getString(cur.getColumnIndex("long_desc"));
			String shortDesc = cur.getString(cur.getColumnIndex("short_desc"));
			Vector<LocationPoint> path = getPathFromWalk(db,id);
			
			WalkModel walk = new WalkModel(id,title,path,shortDesc,longDesc);
			walks.add(walk);
			
		}while(cur.moveToNext());
		db.close();
		return walks;
	}
	
	/**
	 * returns all the locations and places from a walk
	 * 
	 * @param db the local database that is accessed 
	 * @param index the id of the walk that rout you want.
	 * @return a vector of all the point of the walk.
	 */
	private Vector<LocationPoint> getPathFromWalk(SQLiteDatabase db,int index){
		double latitude;
		double longitude;
		long time;
		//CURRENTLY ONLY ADDS LOCATIONS NOT POIs 
		Vector<LocationPoint> retval = new Vector<LocationPoint>();
		String selectLocation = "SELECT * FROM location WHERE walk_id="+index;
		Cursor cur = db.rawQuery(selectLocation, null);
		while(cur.moveToNext()){
			latitude = cur.getDouble(cur.getColumnIndex("latitude"));
			longitude = cur.getDouble(cur.getColumnIndex("longitude"));
			time = cur.getLong(cur.getColumnIndex("time"));
			LocationPoint location = new LocationPoint(latitude,longitude,time);
			retval.add(location);
		}
		return retval;
	}
	
	
	/**
	* uploads the given walk to the server,
	* the server interaction is handled by the FileTransferManager. 
	* 
	* @param walk, the walk to be uploaded.
	*/
	public int uploadWalk(WalkModel walk){
		FileTransferManager transfer = new FileTransferManager();
		return transfer.uploadWalk(walk);
		
	}
	
	/**called when initialy creating a database, to built it from scratch*/
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_WALK_TABLE);
		db.execSQL(CREATE_LOCATION_TABLE);
		db.execSQL(CREATE_PLACE_TABLE);
		db.execSQL(CREATE_PHOTO_TABLE);
	}
	
	/**called when a new version of the database hase been created*/
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS walk");
		db.execSQL("DROP TABLE IF EXISTS location");
		db.execSQL("DROP TABLE IF EXISTS place");
		db.execSQL("DROP TABLE IF EXISTS photo");
		this.onCreate(db);
	}



}
