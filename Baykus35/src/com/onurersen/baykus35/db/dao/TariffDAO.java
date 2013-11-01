package com.onurersen.baykus35.db.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.onurersen.baykus35.db.data.ClsTariffs;
import com.onurersen.baykus35.db.sql.RouteHelper;

/**
 * 
 * @author onurersen
 * 
 */
public class TariffDAO {

	private SQLiteDatabase db;
	private RouteHelper dbHelper;

	public TariffDAO(Context context) {
		dbHelper = new RouteHelper(context);
		db = dbHelper.getReadableDatabase();
	}

	public void close() {
		db.close();
	}

	public List<ClsTariffs> getTariffsByRouteId(int routeId) {
		List<ClsTariffs> tariffList = new ArrayList<ClsTariffs>();

		String[] tableColumns = new String[] { "TariffId", "RouteId", "Time1",
				"Time2" };

		Cursor cursor = db.query("Tariffs", tableColumns, null, null, null,
				null, null);
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

		return tariffList;
	}

}
