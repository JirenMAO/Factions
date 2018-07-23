package me.mao.minigame.core.utils.packet;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public abstract class Packet {

    public Class<?> getClass(String className) throws ClassNotFoundException {
        String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
        String name = "net.minecraft.server." + version + className;
        Class nmsClass = Class.forName(name);
        return nmsClass;
    }

    public Object getConnection(Player player) throws SecurityException, NoSuchMethodException {
        try {
            Object nmsPlayer = this.getPlayer(player);
            Field connectionField = nmsPlayer.getClass().getField("playerConnection");
            Object playerConnection = connectionField.get(nmsPlayer);
            return playerConnection;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void sendPacket(Player player, Object packet) {
        try {
            Object handle = player.getClass().getMethod("getHandle", new Class[0]).invoke((Object)player, new Object[0]);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", this.getClass("Packet")).invoke(playerConnection, packet);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getPlayer(Player player) {
        try {
            Method getHandle = player.getClass().getMethod("getHandle", new Class[0]);
            Object nmsPlayer = getHandle.invoke((Object)player, new Object[0]);
            return nmsPlayer;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void sendAll() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            this.send(player);
        }
    }

    public abstract void send(Player player);
}

