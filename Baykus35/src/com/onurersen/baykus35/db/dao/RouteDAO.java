package com.onurersen.baykus35.db.dao;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.util.Log;

import com.onurersen.baykus35.db.data.ClsRoutes;
import com.onurersen.baykus35.db.util.LogCat;
import com.onurersen.baykus35.sql.SQLiteDatabaseHelper;

/**
 * 
 * @author onurersen
 * 
 */
public class RouteDAO {

	private SQLiteDatabaseHelper dbHelper;

	public RouteDAO(SQLiteDatabaseHelper dbHelper) {
		this.dbHelper = dbHelper;
	}

	public List<ClsRoutes> getRoutes() {
		LogCat.INSTANCE.info("RouteModel", "getRoutes()");
		List<ClsRoutes> routeList = new ArrayList<ClsRoutes>();
		try {
			String[] tableColumns = new String[] { "RouteId", "RouteNumber", "RouteName", "RouteDescription",
					"FirstStopName", "LastStopName" };

			Cursor cursor = getDbHelper().getWritableDatabase().query("Routes", tableColumns, null, null, null, null,
					null);
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
		} catch (Exception exception) {
			Log.e(this.getClass().getName(), exception.getMessage());
		} finally {
			getDbHelper().close();
		}
		return routeList;
	}

	public ClsRoutes getRouteById(int routeId) {
		ClsRoutes route = new ClsRoutes();
		try {

			String[] tableColumns = new String[] { "RouteId", "RouteNumber", "RouteName", "RouteDescription",
					"FirstStopName", "LastStopName" };

			Cursor cursor = getDbHelper().getWritableDatabase().query("Routes", tableColumns, null, null, null, null,
					null);

			cursor.moveToFirst();
			int cursorIndex = 0;

			route.setRouteId(cursor.getInt(cursorIndex++));
			route.setRouteNumber(cursor.getInt(cursorIndex++));
			route.setRouteName(cursor.getString(cursorIndex++));
			route.setRouteDescription(cursor.getString(cursorIndex++));
			route.setFirstStopName(cursor.getString(cursorIndex++));
			route.setLastStopName(cursor.getString(cursorIndex++));

		} catch (Exception exception) {
			Log.e(this.getClass().getName(), exception.getMessage());
		} finally {
			getDbHelper().close();
		}
		return route;
	}

	/**
	 * @return the dbHelper
	 */
	public SQLiteDatabaseHelper getDbHelper() {
		return dbHelper;
	}

	/**
	 * @param dbHelper
	 *            the dbHelper to set
	 */
	public void setDbHelper(SQLiteDatabaseHelper dbHelper) {
		this.dbHelper = dbHelper;
	}

}
