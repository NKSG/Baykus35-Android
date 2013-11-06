package com.onurersen.baykus35.list.route;

import java.util.ArrayList;
import java.util.List;

import com.onurersen.baykus35.db.dao.RouteDAO;
import com.onurersen.baykus35.db.data.ClsRoutes;
import com.onurersen.baykus35.db.helper.RouteHelper;
import com.onurersen.baykus35.db.util.LogCat;
import com.onurersen.baykus35.sql.SQLiteDatabaseHelper;

/**
 * 
 * @author onurersen
 * 
 */
public class RouteModel {

	public static ArrayList<RouteItem> Items;

	public static void LoadModel(SQLiteDatabaseHelper helper) {
		RouteDAO dao = new RouteDAO(helper);
		List<ClsRoutes> routes = dao.getRoutes();
		Items = new ArrayList<RouteItem>();
		int routeIndex = 1;
		for (ClsRoutes clsRoutes : routes) {
			Items.add(new RouteItem(routeIndex++, String.valueOf(clsRoutes.getRouteNumber()), clsRoutes.getRouteName(),
					clsRoutes.getRouteDescription()));
		}
	}

	public static RouteItem GetbyId(int id) {
		for (RouteItem item : Items) {
			if (item.Id == id) {
				return item;
			}
		}
		return null;
	}

}
