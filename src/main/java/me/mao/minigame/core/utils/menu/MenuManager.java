package me.mao.minigame.core.utils.menu;




import me.mao.minigame.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MenuManager implements Listener {

    private Map<Object, Menu> menus = new HashMap<Object, Menu>();

    public MenuManager(Core plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void addMenu(Object o , Menu m) {
        if(menus.containsKey(o)) return;
        this.menus.put(o, m);
    }

    public Collection<Menu> getMenus() {
        return menus.values();
    }

    public Menu getMenu(Object o) {
        return menus.get(o);
    }


    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Inventory inv = e.getWhoClicked().getOpenInventory().getTopInventory();
        for(Menu menu : getMenus()) {
            if(menu.getInventory().getName().equalsIgnoreCase(inv.getName())) {
                e.setCancelled(true);
                menu.click(p, e.getCurrentItem());
            }
        }
    }

}
