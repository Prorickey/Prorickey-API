package xyz.prorickey.api.chat;

import net.md_5.bungee.api.*;

public class Chat {

    public static String format(Character alt, String msg) { return ChatColor.translateAlternateColorCodes(alt, msg); }
    public static String format(String msg) { return format('&', msg); }

}
