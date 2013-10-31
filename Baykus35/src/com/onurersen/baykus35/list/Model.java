package com.onurersen.baykus35.list;

import java.util.ArrayList;

public class Model {

    public static ArrayList<Item> Items;

    public static void LoadModel() {

        Items = new ArrayList<Item>();
        Items.add(new Item(1, "ic_list_bus.png", "63","Bornova - Konak","Osman Kibar - Manavkuyu - Zafer Payzın - Alsancak - Konak"));
        Items.add(new Item(2, "ic_list_bus.png", "104","Buca - Konak","Buca Cezaevi - Şirinyer - Eşrefpaşa - Varyant - Konak"));
        Items.add(new Item(3, "ic_list_bus.png", "152","Gaziemir - Konak","Sosyal Konutlar - Gaziemir - Karabağlar - Üçyol - Konak"));
        Items.add(new Item(4, "ic_list_bus.png", "180","Balçova - Konak","F.Altay - İnönü cad. - Varyant - Konak"));
        Items.add(new Item(5, "ic_list_bus.png", "542","Çiğli - Konak","Çiğli Merkez - Girne - Karşıyaka - Altınyol - Talatpaşa Konak"));

    }

    public static Item GetbyId(int id){

        for(Item item : Items) {
            if (item.Id == id) {
                return item;
            }
        }
        return null;
    }

}
