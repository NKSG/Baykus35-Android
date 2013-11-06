package com.onurersen.baykus35.sql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * 
 * @author onurersen
 * 
 */
public class SQLiteDatabaseHelper extends SQLiteAssetHelper implements SQLiteDatabaseConstants {

	public SQLiteDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
}