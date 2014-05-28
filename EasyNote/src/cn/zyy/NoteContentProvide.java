package cn.zyy;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.net.Uri;
import android.text.TextUtils;

public class NoteContentProvide extends ContentProvider {

	public static final Uri CONTENT_URI = Uri.parse("content://cn.zyy.easynote");
	public static final String KEY_ID = "_id";
	public static final String KEY_ITEMONE = "itemone";
	public static final String KEY_ITEMTWO = "itemtwo";
	public static final String KEY_DATE = "date";
	
	MySQLOpenHelper dbHelper;
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase database = dbHelper.getWritableDatabase();
	    long rowID = database.insert(
	    		MySQLOpenHelper.EASYNOTE_TABLE, "quake", values);
	    if (rowID > 0) {
	        Uri uri1 = ContentUris.withAppendedId(CONTENT_URI, rowID);
	        getContext().getContentResolver().notifyChange(uri1, null);
	        return uri1;
	      }
		return null;
	}

	@Override
	public boolean onCreate() {
		dbHelper = new MySQLOpenHelper(getContext(), MySQLOpenHelper.DATABASE_NAME, null, MySQLOpenHelper.DATABASE_VERSION);		
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
		String[] selectionArgs, String sortOrder) {
		SQLiteDatabase database = dbHelper.getWritableDatabase(); 
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		qb.setTables(MySQLOpenHelper.EASYNOTE_TABLE);
		
	    String orderBy;
	    if (TextUtils.isEmpty(sortOrder)) {
	      orderBy = KEY_DATE;
	    } else {
	      orderBy = sortOrder;
	    }
	    Cursor c = qb.query(database,
                projection,
                selection, selectionArgs,
                null, null,
                orderBy);
	    c.setNotificationUri(getContext().getContentResolver(), uri);	    
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}

	
	private class MySQLOpenHelper extends SQLiteOpenHelper
	{
	    private static final String DATABASE_NAME = "easynote.db";
	    private static final int DATABASE_VERSION = 1;
	    private static final String EASYNOTE_TABLE = "easynote";
	    private static final String DATABASE_CREATE =
		      "create table " + EASYNOTE_TABLE + " ("
		      + KEY_ID + " integer primary key autoincrement, "
		      + KEY_DATE + " INTEGER, "
		      + KEY_ITEMONE + " TEXT, "
		      + KEY_ITEMTWO + " TEXT);";
	    
		public MySQLOpenHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		      db.execSQL("DROP TABLE IF EXISTS " + EASYNOTE_TABLE);
		      onCreate(db);	
		}
		
	}
}
