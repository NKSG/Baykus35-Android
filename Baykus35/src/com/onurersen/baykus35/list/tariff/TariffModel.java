package com.onurersen.baykus35.list.tariff;

import java.util.ArrayList;
import java.util.List;

import com.onurersen.baykus35.db.dao.TariffDAO;
import com.onurersen.baykus35.db.data.ClsTariffs;
import com.onurersen.baykus35.sql.SQLiteDatabaseHelper;

/**
 * 
 * @author onurersen
 * 
 */
public class TariffModel {

	public static ArrayList<TariffItem> Items;

	public static void LoadModel(SQLiteDatabaseHelper helper, int routeId) {
		TariffDAO dao = new TariffDAO(helper);
		List<ClsTariffs> tariffs = dao.getTariffsByRouteId(routeId);
		Items = new ArrayList<TariffItem>();
		int tariffIndex = 1;
		for (ClsTariffs clsTariffs : tariffs) {
			Items.add(new TariffItem(tariffIndex++, clsTariffs.getTariffId(), clsTariffs.getRouteId(), clsTariffs
					.getTime1(), clsTariffs.getTime2()));
		}
	}

	public TariffItem GetbyId(int id) {
		for (TariffItem item : getItems()) {
			if (item.index == id) {
				return item;
			}
		}
		return null;
	}

	public ArrayList<TariffItem> getItems() {
		return Items;
	}

	public void setItems(ArrayList<TariffItem> items) {
		Items = items;
	}

}
