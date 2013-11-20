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
 */
public class WalkManager extends SQLiteOpenHelper{
	
	private final static String DATABASE_NAME = "walks.db"; 

	/**
	 * contains the SQL commands used to create the 'location' table
	 */
	private final static String CREATE_LOCATION_TABLE = 
			"CREATE TABLE location (" +
			"id INTEGER PRIMARY KEY UNIQUE NOT NULL," +
			"walk_id INTEGER NOT NULL REFERENCES walk(id) DEFAULT 0," +
			"latitude REAL DEFAULT 0," +
			"longitude REAL DEFAULT 0," +
			"timestamp REAL DEFAULT 0)";

	/**
	 * contains the SQL commands used to create the 'walk' table
	 */
	private final static String CREATE_WALK_TABLE = 
			"CREATE TABLE walk (" +
			"id INTEGER PRIMARY KEY UNIQUE NOT NULL," +
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
			"id INTEGER PRIMARY KEY NOT NULL UNIQUE," +
			"location_id INTEGER REFERENCES location(id) ON UPDATE CASCADE ON DELETE CASCADE NOT NULL," +
			"description INTEGER NOT NULL)";

	/**
	 * contains the SQL commands used to create the 'photo' table
	 */
	private final static String CREATE_PHOTO_TABLE =
			"CREATE TABLE photo (" +
			"id INTEGER PRIMARY KEY UNIQUE NOT NULL," +
			"place_id INTEGER REFERENCES photo(id) ON UPDATE CASCADE ON DELETE CASCADE NOT NULL," +
			"photo_name TEXT NOT NULL)";
	
	
	public WalkManager(Context context) {
		super(context, DATABASE_NAME, null, 8);
	}
	
	public void addWalkModel(WalkModel walk){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put("id", 10);
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
	private int addLocation(LocationPoint location,SQLiteDatabase db,int walkId){
		ContentValues values = new ContentValues();
		values.put("latitude",location.getLatitude());
		values.put("longitude",location.getLogitude());
		return 0;//return id
	}
	private int addPlace(PointOfInterest poi,SQLiteDatabase db,int LocationWalkId){
		return 0;//return id
	}
	
	
	public WalkModel getWalkByID(int index){
		SQLiteDatabase db = this.getReadableDatabase();
		String select = "SELECT * FROM walk WHERE id="+index;
		//db.se
		Cursor cur = db.rawQuery(select, null);
		cur.moveToFirst();
		
		String title = cur.getString(cur.getColumnIndex("title"));
	
		WalkModel retval = new WalkModel(title);
	
		retval.setShortDescription(cur.getString(cur.getColumnIndex("short_desc")));
		retval.setLongDescription(cur.getString(cur.getColumnIndex("long_desc")));
		retval.setTraveledRoute(getLocationFromWalkByWalkId(db,index));
		db.close();
		return retval;
	}
	private Vector<LocationPoint> getLocationFromWalkByWalkId(SQLiteDatabase db,int index){
		Vector<LocationPoint> retval = new Vector<LocationPoint>();
		String select = "SELECT * FROM location WHERE walk_id="+index;
		Cursor cur = db.rawQuery(select, null);
		while(cur.moveToNext()){
			double latitude = cur.getDouble(cur.getColumnIndex("latitude"));
			double longitude = cur.getDouble(cur.getColumnIndex("longitude"));
			LocationPoint location = new LocationPoint(latitude,longitude);
			
			retval.add(location);
			
		}
		return retval;
	}
	private PointOfInterest getPlaceByLocationID(int index){
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
