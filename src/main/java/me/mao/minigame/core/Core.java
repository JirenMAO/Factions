package me.mao.minigame.core;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import me.mao.minigame.core.sql.Initializer;
import me.mao.minigame.core.utils.ChatUtils;
import me.mao.minigame.core.utils.menu.MenuManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class Core extends JavaPlugin {

    public static Core instance;
    private Initializer initializer;
    private MenuManager menuManager;

    @Override
    public void onEnable() {
        ChatUtils.debug("INITIALIZING THE PLUGIN...");
        long time = System.currentTimeMillis();
        loadDepends();
        registerListeners();
        registerCommands();
        ChatUtils.debug("PLUGIN FINISHED ENABLING IN " + ChatUtils.timeToString((System.currentTimeMillis() - time)));
    }

    @Override
    public void onDisable() {
        ChatUtils.debug("INITIALIZING THE DISABLE CEREMONY...");
        long time = System.currentTimeMillis();
        setup();
        unloadDepends();
        ChatUtils.debug("DISABLING FINISHED IN " + ChatUtils.timeToString((System.currentTimeMillis() - time)));
    }

    private void loadDepends() {
        initializer = new Initializer(this);
        menuManager = new MenuManager(this);
    }

    private void unloadDepends() {
        menuManager = null;
        instance = null;
    }

    private void setup() {
        try {
            if (initializer.getConnection() == null) return;
            initializer.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void registerListeners() {

    }

    private void registerCommands() {

    }


    public static Core getInstance() {
        return instance;
    }

    public Initializer getInitializer() {
        return initializer;
    }

    public MenuManager getMenuManager() {
        return menuManager;
    }
}
