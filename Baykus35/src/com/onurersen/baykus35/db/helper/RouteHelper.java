package com.onurersen.baykus35.db.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.onurersen.baykus35.sql.SQLiteDatabaseConstants;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * 
 * @author onurersen
 * 
 */
public class RouteHelper extends SQLiteAssetHelper implements SQLiteDatabaseConstants {

	public RouteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public Cursor getRoutes() {
		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		String[] sqlSelect = { "0 _id", "RouteNumber", "RouteName", "RouteDescription", "FirstStopName", "LastStopName" };
		String sqlTables = "Routes";
		qb.setTables(sqlTables);
		Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
		return c;
	}

}
