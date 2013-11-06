package com.onurersen.baykus35.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.onurersen.baykus35.sql.SQLiteDatabaseConstants;

/**
 * 
 * @author onurersen
 * 
 */
public class BusStopHelper extends SQLiteOpenHelper implements
		SQLiteDatabaseConstants {

	public BusStopHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
