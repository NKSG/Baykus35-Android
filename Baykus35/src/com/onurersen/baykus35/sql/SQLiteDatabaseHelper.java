package com.onurersen.baykus35.sql;

import android.content.Context;

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