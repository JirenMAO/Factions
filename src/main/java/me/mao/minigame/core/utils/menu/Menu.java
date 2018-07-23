package me.mao.minigame.core.utils.menu;



import me.mao.minigame.core.Core;
import me.mao.minigame.core.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public abstract class Menu implements InventoryHolder {

    private int size;
    private Inventory inventory;

    public Menu(Object key, String title, int size) {
        this.inventory = Bukkit.createInventory(null, size, ChatUtils.colorize(title));
        this.size = size;
        Core.getInstance().getMenuManager().addMenu(key, this);
    }

    public void show(Player p) {
        p.openInventory(inventory);
    }

    public abstract void click(Player p, ItemStack item);

    protected String getRawName(ItemStack item) {
        if(item == null) {
            return null;
        }

        ItemMeta meta = item.getItemMeta();

        if(meta == null || !meta.hasDisplayName()) {
            return null;
        }

        String rawname = ChatColor.stripColor(meta.getDisplayName());

        return rawname;
    }

    public Inventory getInventory() {
        return inventory;
    }

}
