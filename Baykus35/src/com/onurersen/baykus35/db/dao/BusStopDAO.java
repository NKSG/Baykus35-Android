package com.onurersen.baykus35.db.dao;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.util.Log;

import com.onurersen.baykus35.db.data.ClsBusStops;
import com.onurersen.baykus35.db.sql.SQLiteDatabaseHelper;

/**
 * 
 * @author onurersen
 * 
 */
public class BusStopDAO {

	private SQLiteDatabaseHelper dbHelper;

	public BusStopDAO(SQLiteDatabaseHelper dbHelper) {
		this.dbHelper = dbHelper;
	}

	public List<ClsBusStops> getBusStopsByRouteId(int routeId) {
		List<ClsBusStops> busStopList = new ArrayList<ClsBusStops>();
		try {
			String[] tableColumns = new String[] { "StopId", "StopName", "RouteId", "Latitude", "Longitude" };

			Cursor cursor = getDbHelper().getWritableDatabase().query("BusStops", tableColumns, "RouteId = ?",
					new String[] { String.valueOf(routeId) }, null, null, null, null);

			cursor.moveToFirst();
			int cursorIndex = 0;
			while (!cursor.isAfterLast()) {
				cursorIndex = 0;
				ClsBusStops busStop = new ClsBusStops();
				busStop.setStopId(cursor.getInt(cursorIndex++));
				busStop.setStopName(cursor.getString(cursorIndex++));
				busStop.setRouteId(cursor.getInt(cursorIndex++));
				busStop.setLatitude(cursor.getFloat(cursorIndex++));
				busStop.setLongitude(cursor.getFloat(cursorIndex++));
				busStopList.add(busStop);
				cursor.moveToNext();
			}
		} catch (Exception exception) {
			Log.e(this.getClass().getName(), exception.getMessage());
		} finally {
			getDbHelper().close();
		}
		return busStopList;
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
