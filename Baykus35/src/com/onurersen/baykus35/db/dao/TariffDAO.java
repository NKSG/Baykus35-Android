package com.onurersen.baykus35.db.dao;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.util.Log;

import com.onurersen.baykus35.db.data.ClsTariffs;
import com.onurersen.baykus35.sql.SQLiteDatabaseHelper;

/**
 * 
 * @author onurersen
 * 
 */
public class TariffDAO {

	private SQLiteDatabaseHelper dbHelper;

	public TariffDAO(SQLiteDatabaseHelper dbHelper) {
		this.dbHelper = dbHelper;
	}

	public List<ClsTariffs> getTariffsByRouteId(int routeId) {
		List<ClsTariffs> tariffList = new ArrayList<ClsTariffs>();
		try {
			String[] tableColumns = new String[] { "TariffId", "RouteId", "Time1", "Time2" };

			Cursor cursor = getDbHelper().getWritableDatabase().query("Tariffs", tableColumns, "RouteId = ?",
					new String[] { String.valueOf(routeId) }, null, null, null, null);
			cursor.moveToFirst();

			int cursorIndex = 0;
			while (!cursor.isAfterLast()) {
				cursorIndex = 0;
				ClsTariffs tariff = new ClsTariffs();
				tariff.setTariffId(cursor.getInt(cursorIndex++));
				tariff.setRouteId(cursor.getInt(cursorIndex++));
				tariff.setTime1(cursor.getString(cursorIndex++));
				tariff.setTime2(cursor.getString(cursorIndex++));
				tariffList.add(tariff);
				cursor.moveToNext();
			}

		} catch (Exception exception) {
			Log.e(this.getClass().getName(), exception.getMessage());
		} finally {
			getDbHelper().close();
		}

		return tariffList;
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
