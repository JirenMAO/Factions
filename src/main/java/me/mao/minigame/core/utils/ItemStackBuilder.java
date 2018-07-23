package me.mao.minigame.core.utils;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemStackBuilder {

    private final ItemStack ITEM_STACK;

    public ItemStackBuilder(Material mat) {
        this.ITEM_STACK = new ItemStack(mat);
    }

    public ItemStackBuilder(ItemStack item) {
        this.ITEM_STACK = item;
    }

    public ItemStackBuilder withAmount(int amount) {
        ITEM_STACK.setAmount(amount);
        return this;
    }

    public ItemStackBuilder withName(String name) {
        final ItemMeta meta = ITEM_STACK.getItemMeta();
        meta.setDisplayName(ChatUtils.colorize(name));
        ITEM_STACK.setItemMeta(meta);
        return this;
    }

    public ItemStackBuilder withLore(String name) {
        final ItemMeta meta = ITEM_STACK.getItemMeta();
        List<String> lore = meta.getLore();
        if (lore == null) {
            lore = new ArrayList<String>();
        }
        lore.add(ChatUtils.colorize(name));
        meta.setLore(lore);
        ITEM_STACK.setItemMeta(meta);
        return this;
    }

    public ItemStackBuilder withDurability(int durability) {
        ITEM_STACK.setDurability((short) durability);
        return this;
    }

    public ItemStackBuilder withData(int data) {
        ITEM_STACK.setDurability((short) data);
        return this;
    }

    public ItemStackBuilder withEnchantment(Enchantment enchantment, final int level) {
        ITEM_STACK.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemStackBuilder withEnchantment(Enchantment enchantment) {
        ITEM_STACK.addUnsafeEnchantment(enchantment, 1);
        return this;
    }

    public ItemStackBuilder withType(Material material) {
        ITEM_STACK.setType(material);
        return this;
    }

    public ItemStackBuilder clearLore() {
        final ItemMeta meta = ITEM_STACK.getItemMeta();
        meta.setLore(new ArrayList<String>());
        ITEM_STACK.setItemMeta(meta);
        return this;
    }

    public ItemStackBuilder clearEnchantments() {
        for (Enchantment enchantment : ITEM_STACK.getEnchantments().keySet()) {
            ITEM_STACK.removeEnchantment(enchantment);
        }
        return this;
    }

    public ItemStackBuilder withColor(Color color) {
        Material type = ITEM_STACK.getType();
        if (type == Material.LEATHER_BOOTS || type == Material.LEATHER_CHESTPLATE || type == Material.LEATHER_HELMET || type == Material.LEATHER_LEGGINGS) {
            LeatherArmorMeta meta = (LeatherArmorMeta) ITEM_STACK.getItemMeta();
            meta.setColor(color);
            ITEM_STACK.setItemMeta(meta);
            return this;
        } else {
            throw new IllegalArgumentException("withColor is only applicable for leather armor!");
        }
    }

    public static ItemStack getAchievementItem(String name, String prefix, String description, int coins, double exp, boolean hasIt) {
        if (hasIt) {
            ItemStack item = new ItemStackBuilder(Material.INK_SACK).withData(10).withName(prefix).build();
            ItemMeta meta = item.getItemMeta();

            meta.setLore(Arrays.asList(
                    ChatColor.GRAY + "=========================",
                    ChatUtils.colorize("      &eName: &7" + name),
                    ChatColor.GRAY + "=========================",
                    ChatUtils.colorize("      &eDescription: "),
                    "",
                    "      " + description,
                    "",
                    ChatColor.GRAY + "=========================",
                    "",
                    ChatUtils.colorize("      &eCoins: &5" + coins),
                    "",
                    ChatColor.GRAY + "=========================",
                    "",
                    ChatUtils.colorize("      &eExp: &5" + exp),
                    "",
                    ChatColor.GRAY + "=========================",
                    ChatUtils.colorize("      &eHave it: &aTrue")
                    , ChatColor.GRAY + "========================="));

            item.setItemMeta(meta);
            return item;
        }

        ItemStack item = new ItemStackBuilder(Material.INK_SACK).withData(1).withName(prefix).build();
        ItemMeta meta = item.getItemMeta();


        meta.setLore(Arrays.asList(
                ChatColor.GRAY + "=========================",
                ChatUtils.colorize("      &eName: &7" + name),
                ChatColor.GRAY + "=========================",
                ChatUtils.colorize("      &eDescription: "),
                "",
                "      " + description,
                "",
                ChatColor.GRAY + "=========================",
                "",
                ChatUtils.colorize("      &eCoins: &5" + coins),
                "",
                ChatColor.GRAY + "=========================",
                "",
                ChatUtils.colorize("      &eExp: &5" + exp),
                "",
                ChatColor.GRAY + "=========================",
                ChatUtils.colorize("      &eHave it: &cFalse")
                , ChatColor.GRAY + "========================="));

        item.setItemMeta(meta);

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getRankItemStack(String name, String prefix, boolean staff, boolean donator) {
        ItemStack item = new ItemStackBuilder(Material.PAPER).withName(prefix).build();
        ItemMeta meta = item.getItemMeta();

        meta.setLore(Arrays.asList(
                ChatColor.GRAY + "=========================",
                ChatUtils.colorize("      &eName: &7" + name),
                ChatColor.GRAY + "=========================",
                ChatUtils.colorize("      &ePrefix: "),
                ChatUtils.colorize("      " + prefix),
                ChatColor.GRAY + "=========================",
                ChatUtils.colorize("      &eisStaff: &5" + staff),
                ChatColor.GRAY + "=========================",
                ChatUtils.colorize("      &eisDonator: &5" + donator),
                ChatColor.GRAY + "========================="));

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getAnimatedTitlesItemStack(String name, String description) {
        ItemStack item = new ItemStackBuilder(Material.PAPER).withName(name).build();
        ItemMeta meta = item.getItemMeta();

        meta.setLore(Arrays.asList(
                ChatColor.GRAY + "=========================",
                ChatUtils.colorize("      &eName: &7" + name),
                ChatColor.GRAY + "=========================",
                ChatUtils.colorize("      &edescription: &5" + description),
                ChatColor.GRAY + "=========================",
                ChatUtils.colorize("      &eClick to send to all the players!"),
                ChatColor.GRAY + "========================="));

        item.setItemMeta(meta);
        return item;
    }


    public ItemStack build() {
        return ITEM_STACK;
    }
}
