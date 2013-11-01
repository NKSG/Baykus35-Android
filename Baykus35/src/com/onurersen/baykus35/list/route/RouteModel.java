package com.onurersen.baykus35.list.route;

import java.util.ArrayList;
import java.util.List;

import com.onurersen.baykus35.MainActivity;
import com.onurersen.baykus35.db.dao.RouteDAO;
import com.onurersen.baykus35.db.data.ClsRoutes;
/**
 * 
 * @author onurersen
 *
 */
public class RouteModel {

    public static ArrayList<RouteItem> Items;

    public static void LoadModel(MainActivity activity) {
    	
    	RouteDAO dao = new RouteDAO(activity);
    	dao.open();
    	List<ClsRoutes> routes = dao.getRoutes();
        Items = new ArrayList<RouteItem>();
        int routeIndex = 1;
        for (ClsRoutes clsRoutes : routes) {
        	Items.add(new RouteItem(routeIndex++, "ic_list_bus.png", String.valueOf(clsRoutes.getRouteNumber()),clsRoutes.getRouteName(),clsRoutes.getRouteDescription()));
		}
    }

    public static RouteItem GetbyId(int id){

        for(RouteItem item : Items) {
            if (item.Id == id) {
                return item;
            }
        }
        return null;
    }

}
