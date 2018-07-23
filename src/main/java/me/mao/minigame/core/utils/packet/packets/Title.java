package me.mao.minigame.core.utils.packet.packets;


import me.mao.minigame.core.utils.ChatUtils;
import me.mao.minigame.core.utils.packet.Packet;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class Title extends Packet {
    private String title;
    private String subTitle;
    private int fadeIn;
    private int fadeOut;
    private int stay;

    public Title(String title, String subTitle, int fadeIn, int fadeOut, int stay) {
        this.title = title;
        this.subTitle = subTitle;
        this.fadeIn = fadeIn;
        this.fadeOut = fadeOut;
        this.stay = stay;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public int getFadeIn() {
        return fadeIn;
    }

    public void setFadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
    }

    public int getFadeOut() {
        return fadeOut;
    }

    public void setFadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
    }

    public int getStay() {
        return stay;
    }

    public void setStay(int stay) {
        this.stay = stay;
    }

    public void send(Player player) {
        try {
            Object enumTitle = this.getClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null);
            Object titleText = this.getClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + ChatUtils.colorize(this.title) + "\"}");
            Constructor titleConstructor = this.getClass("PacketPlayOutTitle").getConstructor(this.getClass("PacketPlayOutTitle").getDeclaredClasses()[0], this.getClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE);
            Object titlePacket = titleConstructor.newInstance(enumTitle, titleText, this.fadeIn, this.stay, this.fadeOut);
            Object enumSubTitle = this.getClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null);
            Object subtitleText = this.getClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + ChatUtils.colorize(this.subTitle)+ "\"}");
            Constructor subtitleConstructor = this.getClass("PacketPlayOutTitle").getConstructor(this.getClass("PacketPlayOutTitle").getDeclaredClasses()[0], this.getClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE);
            Object subtitlePacket = subtitleConstructor.newInstance(enumSubTitle, subtitleText, this.fadeIn, this.stay, this.fadeOut);
            this.sendPacket(player, titlePacket);
            this.sendPacket(player, subtitlePacket);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
