package xyz.prorickey.api.item;

import org.bukkit.Material;

import java.util.ArrayList;

public class Item {

    public static String toKey(String notKey) {
        if(!notKey.startsWith("minecraft:")) {
            return "minecraft:" + notKey;
        }
        return notKey;
    }

    public static ArrayList<String> mcItems() {
        ArrayList<String> mcItems = new ArrayList<>();
        for(Material material : Material.values()) {
            if(!material.isBlock()) {
                mcItems.add(material.name());
            }
        }
        return mcItems;
    }

}
