package com.onurersen.baykus35.db.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.onurersen.baykus35.db.data.ClsRoutes;
import com.onurersen.baykus35.db.sql.RouteHelper;

/**
 * 
 * @author onurersen
 * 
 */
public class RouteDAO {

	private SQLiteDatabase db;
	private RouteHelper dbHelper;

	public RouteDAO(Context context) {
		dbHelper = new RouteHelper(context);
	}
	
	public void open() {
		db = dbHelper.getReadableDatabase();
	}

	public void close() {
		db.close();
	}

	public List<ClsRoutes> getRoutes() {
		List<ClsRoutes> routeList = new ArrayList<ClsRoutes>();

		String[] tableColumns = new String[] { "RouteId", "RouteNumber",
				"RouteName", "RouteDescription", "FirstStopName",
				"LastStopName" };

		Cursor cursor = db.query("Routes", tableColumns, null, null, null,
				null, null);
		cursor.moveToFirst();

		int cursorIndex = 0;
		while (!cursor.isAfterLast()) {
			cursorIndex = 0;
			ClsRoutes route = new ClsRoutes();
			route.setRouteId(cursor.getInt(cursorIndex++));
			route.setRouteNumber(cursor.getInt(cursorIndex++));
			route.setRouteName(cursor.getString(cursorIndex++));
			route.setRouteDescription(cursor.getString(cursorIndex++));
			route.setFirstStopName(cursor.getString(cursorIndex++));
			route.setLastStopName(cursor.getString(cursorIndex++));
			routeList.add(route);
			cursor.moveToNext();
		}

		return routeList;
	}

	public ClsRoutes getRouteById(int routeId) {

		String[] tableColumns = new String[] { "RouteId", "RouteNumber",
				"RouteName", "RouteDescription", "FirstStopName",
				"LastStopName" };

		Cursor cursor = db.query("Routes", tableColumns, null, null, null,
				null, null);

		cursor.moveToFirst();
		int cursorIndex = 0;
		ClsRoutes route = new ClsRoutes();
		route.setRouteId(cursor.getInt(cursorIndex++));
		route.setRouteNumber(cursor.getInt(cursorIndex++));
		route.setRouteName(cursor.getString(cursorIndex++));
		route.setRouteDescription(cursor.getString(cursorIndex++));
		route.setFirstStopName(cursor.getString(cursorIndex++));
		route.setLastStopName(cursor.getString(cursorIndex++));

		return route;
	}

}
