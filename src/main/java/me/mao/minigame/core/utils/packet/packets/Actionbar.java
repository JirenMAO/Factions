package me.mao.minigame.core.utils.packet.packets;


import me.mao.minigame.core.utils.ChatUtils;
import me.mao.minigame.core.utils.packet.Packet;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class Actionbar extends Packet {

    private String text;

    public Actionbar(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void send(Player player) {
        try {
            Object packet;
            String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
            Object barText = this.getClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + ChatUtils.colorize(this.text) + "\"}");
            if (version.startsWith("v1_12")) {
                Constructor constructor = this.getClass("PacketPlayOutChat").getConstructor(this.getClass("IChatBaseComponent"), this.getClass("ChatMessageType").getDeclaredClasses()[0]);
                packet = constructor.newInstance(barText, this.getClass("ChatMessageType").getDeclaredClasses()[0].getField("GAME_INFO").get(null));
            } else {
                Constructor constructor = this.getClass("PacketPlayOutChat").getConstructor(this.getClass("IChatBaseComponent"), Byte.TYPE);
                packet = constructor.newInstance(barText, (byte)2);
            }
            this.sendPacket(player, packet);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
