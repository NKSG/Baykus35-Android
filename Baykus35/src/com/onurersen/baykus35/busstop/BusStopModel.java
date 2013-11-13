package com.onurersen.baykus35.busstop;

import java.util.List;

import com.onurersen.baykus35.db.dao.BusStopDAO;
import com.onurersen.baykus35.db.data.ClsBusStops;
import com.onurersen.baykus35.db.sql.SQLiteDatabaseHelper;

/**
 * 
 * @author onurersen
 * 
 */
public class BusStopModel {

	public static List<ClsBusStops> Items;

	public static void LoadModel(SQLiteDatabaseHelper helper, int routeId) {
		BusStopDAO dao = new BusStopDAO(helper);
		Items = dao.getBusStopsByRouteId(routeId);

	}

}
