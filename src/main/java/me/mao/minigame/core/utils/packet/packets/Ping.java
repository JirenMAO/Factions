package me.mao.minigame.core.utils.packet.packets;


import me.mao.minigame.core.utils.packet.Packet;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Ping extends Packet {

    private int ping;

    public Ping(Player player) {
        try {
            String bukkitversion = Bukkit.getServer().getClass().getPackage().getName().substring(23);
            Class craftPlayer = Class.forName("org.bukkit.craftbukkit." + bukkitversion + ".entity.CraftPlayer");
            Object handle = craftPlayer.getMethod("getHandle", new Class[0]).invoke((Object)player, new Object[0]);
            Integer ping = (Integer)handle.getClass().getDeclaredField("ping").get(handle);
            this.ping = ping;
        }
        catch (Exception ex) {
            this.ping = -1;
        }
    }

    public int getPing() {
        return ping;
    }

    public void send(Player player) {

    }
}
