package me.mao.minigame.core.utils;

import me.mao.minigame.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatUtils {

    public static String colorize(String s) {
        String colored = ChatColor.translateAlternateColorCodes('&',s);
        return colored;
    }

    public static void broadcast(String message) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(colorize(message));
        }
    }

    public static void debug(String message) {
        Core.getInstance().getServer().getConsoleSender().sendMessage(" ");
        Core.getInstance().getServer().getConsoleSender().sendMessage(ChatUtils.colorize("&c[SGR DEBUG}: &r" + message));
        Core.getInstance().getServer().getConsoleSender().sendMessage(" ");
    }

    public static void dataInfo(String message) {
        Core.getInstance().getServer().getConsoleSender().sendMessage(" ");
        Core.getInstance().getServer().getConsoleSender().sendMessage(ChatUtils.colorize("&e[SGR DATA}: &r" + message));
        Core.getInstance().getServer().getConsoleSender().sendMessage(" ");
    }

    public static String timeToString(long millis) {
        String output = "";

        try {
            long seconds = millis / 1000L;
            long minutes = seconds / 60L;
            long hours = seconds / 3600L;
            long days = seconds / 86400L;
            seconds %= 60L;
            minutes %= 60L;
            hours %= 24L;
            days %= 30L;
            String sec = String.valueOf(seconds);
            String min = String.valueOf(minutes);
            String hur = String.valueOf(hours);
            String day = String.valueOf(days);
            if (days > 0L) {
                output = output + day + " days, ";
            }

            if (hours > 0L) {
                output = output + hur + " hours, ";
            }

            if (minutes > 0L) {
                output = output + min + " minutes, ";
            }

            if (seconds > 0L) {
                output = output + sec + " seconds";
            }

            if (output == null || output.length() < 2) {
                output = "0 seconds";
            }
        } catch (Exception var15) {
            var15.printStackTrace();
        }

        return output;
    }

}
