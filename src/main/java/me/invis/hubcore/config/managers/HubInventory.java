package me.invis.hubcore.config.managers;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public class HubInventory {
    private final String title;
    private final int size;
    private final boolean fill;
    private final ItemStack fillItem;
    private final List<GameMode> gameModes;

    public HubInventory(String title, int size, boolean fill, ItemStack fillItem, List<GameMode> gameModes) {
        this.title = title;
        this.size = size;
        this.fill = fill;
        this.fillItem = fillItem;
        this.gameModes = gameModes;
    }

    public String title() {
        return this.title;
    }

    public int size() {
        return this.size;
    }

    public boolean fill() {
        return this.fill;
    }

    public ItemStack fillITem() {
        return fillItem;
    }

    public List<GameMode> gameModes() {
        return gameModes;
    }
}
