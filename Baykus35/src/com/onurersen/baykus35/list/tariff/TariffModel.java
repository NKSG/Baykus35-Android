package com.onurersen.baykus35.list.tariff;

import java.util.ArrayList;
import java.util.List;

import com.onurersen.baykus35.db.dao.RouteDAO;
import com.onurersen.baykus35.db.dao.TariffDAO;
import com.onurersen.baykus35.db.data.ClsRoutes;
import com.onurersen.baykus35.db.data.ClsTariffs;
import com.onurersen.baykus35.db.sql.SQLiteDatabaseHelper;

/**
 * 
 * @author onurersen
 * 
 */
public class TariffModel {

	public static ArrayList<TariffItem> Items;

	public static void LoadModel(SQLiteDatabaseHelper helper, int routeId) {
		TariffDAO tariffDao = new TariffDAO(helper);
		List<ClsTariffs> tariffs = tariffDao.getTariffsByRouteId(routeId);
		Items = new ArrayList<TariffItem>();
		int tariffIndex = 1;
		RouteDAO routeDao = new RouteDAO(helper);
		for (ClsTariffs clsTariffs : tariffs) {
			ClsRoutes route = routeDao.getRouteById(clsTariffs.getRouteId());
			Items.add(new TariffItem(tariffIndex++, clsTariffs.getTariffId(), clsTariffs.getRouteId(), clsTariffs
					.getTime1(), clsTariffs.getTime2(), route.getRouteDescription()));
		}
	}

	public static TariffItem GetbyId(int id) {
		for (TariffItem item : Items) {
			if (item.getIndex() == id) {
				return item;
			}
		}
		return null;
	}

}
