package me.mao.minigame.core.utils.packet.packets;


import me.mao.minigame.core.utils.ChatUtils;
import me.mao.minigame.core.utils.packet.Packet;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class Tablist extends Packet {

    private String header;
    private String footer;

    public Tablist(String header, String footer) {
        this.header = header;
        this.footer = footer;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public void send(Player player) {
        try {
            Object tabHeader = this.getClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + ChatUtils.colorize(this.header) + "\"}");
            Object tabFooter = this.getClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + ChatUtils.colorize(this.footer) + "\"}");
            Constructor constructor = this.getClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(new Class[0]);
            Object packet = constructor.newInstance(new Object[0]);
            Field headerField = packet.getClass().getDeclaredField("a");
            headerField.setAccessible(true);
            headerField.set(packet, tabHeader);
            Field footerField = packet.getClass().getDeclaredField("b");
            footerField.setAccessible(true);
            footerField.set(packet, tabFooter);
            this.sendPacket(player, packet);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
