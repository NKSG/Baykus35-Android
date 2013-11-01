package com.onurersen.baykus35.list.route;

import java.util.ArrayList;
/**
 * 
 * @author onurersen
 *
 */
public class RouteModel {

    public static ArrayList<RouteItem> Items;

    public static void LoadModel() {

        Items = new ArrayList<RouteItem>();
        Items.add(new RouteItem(1, "ic_list_bus.png", "63","Bornova - Konak","Osman Kibar - Manavkuyu - Zafer Payzın - Alsancak - Konak"));
        Items.add(new RouteItem(2, "ic_list_bus.png", "104","Buca - Konak","Buca Cezaevi - Şirinyer - Eşrefpaşa - Varyant - Konak"));
        Items.add(new RouteItem(3, "ic_list_bus.png", "152","Gaziemir - Konak","Sosyal Konutlar - Gaziemir - Karabağlar - Üçyol - Konak"));
        Items.add(new RouteItem(4, "ic_list_bus.png", "180","Balçova - Konak","F.Altay - İnönü cad. - Varyant - Konak"));
        Items.add(new RouteItem(5, "ic_list_bus.png", "542","Çiğli - Konak","Çiğli Merkez - Girne - Karşıyaka - Altınyol - Talatpaşa Konak"));

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
