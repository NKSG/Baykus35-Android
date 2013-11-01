package com.onurersen.baykus35.list.tariff;

import java.util.ArrayList;
/**
 * 
 * @author onurersen
 *
 */
public class TariffModel {

    public static ArrayList<TariffItem> Items;

    public static void LoadModel() {

        Items = new ArrayList<TariffItem>();
        Items.add(new TariffItem(1, "ic_list_bus.png", "63","Bornova - Konak","Osman Kibar - Manavkuyu - Zafer Payzın - Alsancak - Konak"));
        Items.add(new TariffItem(2, "ic_list_bus.png", "104","Buca - Konak","Buca Cezaevi - Şirinyer - Eşrefpaşa - Varyant - Konak"));
        Items.add(new TariffItem(3, "ic_list_bus.png", "152","Gaziemir - Konak","Sosyal Konutlar - Gaziemir - Karabağlar - Üçyol - Konak"));
        Items.add(new TariffItem(4, "ic_list_bus.png", "180","Balçova - Konak","F.Altay - İnönü cad. - Varyant - Konak"));
        Items.add(new TariffItem(5, "ic_list_bus.png", "542","Çiğli - Konak","Çiğli Merkez - Girne - Karşıyaka - Altınyol - Talatpaşa Konak"));

    }

    public static TariffItem GetbyId(int id){

        for(TariffItem item : Items) {
            if (item.Id == id) {
                return item;
            }
        }
        return null;
    }

}
