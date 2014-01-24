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
	
	private final static String DATABASE_NAME = "walks.db"; 

	
	/**
	 * contains the SQL commands used to create the 'location' table
	 */
	private final static String CREATE_LOCATION_TABLE = 
			"CREATE TABLE location (" +
			"id INTEGER PRIMARY KEY AUTOINCREMENT," +
			"walk_id INTEGER NOT NULL REFERENCES walk(id) DEFAULT 0," +
			"latitude REAL DEFAULT 0," +
			"longitude REAL DEFAULT 0," +
			"timestamp REAL DEFAULT 0)";

	/**
	 * contains the SQL commands used to create the 'walk' table
	 */
	private final static String CREATE_WALK_TABLE = 
			"CREATE TABLE walk (" +
			"id INTEGER PRIMARY KEY AUTOINCREMENT," +
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
			"id INTEGER PRIMARY KEY AUTOINCREMENT," +
			"location_id INTEGER REFERENCES location(id) ON UPDATE CASCADE ON DELETE CASCADE NOT NULL," +
			"description INTEGER NOT NULL)";

	/**
	 * contains the SQL commands used to create the 'photo' table
	 */
	private final static String CREATE_PHOTO_TABLE =
			"CREATE TABLE photo (" +
			"id INTEGER PRIMARY KEY AUTOINCREMENT," +
			"place_id INTEGER REFERENCES photo(id) ON UPDATE CASCADE ON DELETE CASCADE NOT NULL," +
			"photo_name TEXT NOT NULL)";
	
	
	public WalkManager(Context context) {
		
		super(context, DATABASE_NAME, null, 13);
	}
	
	
	/**
	 * adds the passed WalkModel to the local database. 
	 */
	public void addWalkModel(WalkModel walk){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put("title", walk.getTitle());
		values.put("short_desc", walk.getShortDescription());
		values.put("long_desc", walk.getLongDescription());
		values.put("hours", 1.3);
		values.put("distance", 13.0);
		
		db.insert("walk", null, values);
		
		for(LocationPoint location : walk.getRoutePath()){
			if(location instanceof PointOfInterest){
				addPlace((PointOfInterest)location,db,addLocation(location,db,1));
			}
			else{
				addLocation(location,db,1);
			}
		}
		db.close();
	}
	
	/**
	 * 
	 * @param location the location to be added
	 * @param db the local database that is accessed
	 * @param walkId the unique id of the added location.
	 * @return returns 0 if no error.
	 */
	private int addLocation(LocationPoint location,SQLiteDatabase db,int walkId){
		ContentValues values = new ContentValues();
		values.put("latitude",location.getLatitude());
		values.put("longitude",location.getLongitude());
		values.put("time",location.getTime());
		db.insert("location", null, values);
		return (0);
	}
	
	/**
	 * 
	 * @param poi the poi to be added
	 * @param db db the local database that is accessed
	 * @param locationId the unique id of the added poi.
	 * @return returns 0 if no error.
	 */
	private int addPlace(PointOfInterest poi,SQLiteDatabase db,int locationId){
		ContentValues values = new ContentValues();
		values.put("location_id",locationId);
		values.put("description", poi.getDescription());
		for(ImageInformation image:poi.getImages()){
			addPhoto(image,db,0);
		}
		return (0);
	}
	
	/**
	 * 
	 * @param image the image to be added to database
	 * @param db the local database that is accessed
	 * @param placeId the unique id of the photo
	 * @return 0 if no error
	 */
	private int addPhoto(ImageInformation image,SQLiteDatabase db,int placeId){
		ContentValues values = new ContentValues();
		values.put("place_id",placeId);
		values.put("photo_data", image.getImageAsString());
		return (0);
	}
	
	/**
	 * returns the WalkModel with an id matching the passed one.
	 * @param index, the id of the walk you want.
	 */
	public WalkModel getWalkByID(int index){
		SQLiteDatabase db = this.getReadableDatabase();
		String select = "SELECT * FROM walk WHERE id="+index;

		Cursor cur = db.rawQuery(select, null);
		if(cur.getCount()<=0){
			cur.close();
			return null;
		}
		cur.moveToFirst();
		
		String title = cur.getString(cur.getColumnIndex("title"));
		int id = cur.getInt(cur.getColumnIndex("id"));
		String longDesc = cur.getString(cur.getColumnIndex("long_desc"));
		String shortDesc = cur.getString(cur.getColumnIndex("short_desc"));
		Vector<LocationPoint> path = getLocationFromWalkByWalkId(db,index);
		
		WalkModel retval = new WalkModel(id,title,path,shortDesc,longDesc);
	
		db.close();
		return retval;
	}
	
	/**
	 * 
	 * @param db the local database that is accessed 
	 * @param index the id of the walk that rout you want.
	 * @return a vector of all the point of the walk.
	 */
	private Vector<LocationPoint> getLocationFromWalkByWalkId(SQLiteDatabase db,int index){
		Vector<LocationPoint> retval = new Vector<LocationPoint>();
		String select = "SELECT * FROM location WHERE walk_id="+index;
		Cursor cur = db.rawQuery(select, null);
		while(cur.moveToNext()){
			double latitude = cur.getDouble(cur.getColumnIndex("latitude"));
			double longitude = cur.getDouble(cur.getColumnIndex("longitude"));
			double time = cur.getDouble(cur.getColumnIndex("timestamp"));
			LocationPoint location = new LocationPoint(latitude,longitude,time);
			retval.add(location);
		}
		return retval;
	}
	
	/**
	 *  will need to be changed I think the another parameter of walk id is needed., or return vector of all
	 *  points of intereest instead
	 * @param index 
	 * @return 
	 */
	private PointOfInterest getPlaceByLocationID(int index){
		return null;
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
	/**
	 * PERHAPS TO BE REMOVED
	 * 
	 * returns the requested walk from the server,
	 * the server interaction is handled by the FileTransferManager. 
	 */
	public WalkModel getWalkFromServerById(){
		return null;
	}
	
	
	
	
	
	
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_WALK_TABLE);
		db.execSQL(CREATE_LOCATION_TABLE);
		db.execSQL(CREATE_PLACE_TABLE);
		db.execSQL(CREATE_PHOTO_TABLE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS walk");
		db.execSQL("DROP TABLE IF EXISTS location");
		db.execSQL("DROP TABLE IF EXISTS place");
		db.execSQL("DROP TABLE IF EXISTS photo");
	 
		this.onCreate(db);
	}



}
