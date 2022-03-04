package me.invis.hubcore.config.managers;

import org.bukkit.inventory.ItemStack;

public class GameMode {
    private final String name;
    private final int slot;
    private final ItemStack itemStack;

    public GameMode(String name, int slot, ItemStack itemStack) {
        this.name = name;
        this.slot = slot;
        this.itemStack = itemStack;
    }

    public String name() {
        return this.name;
    }

    public int slot() {
        return this.slot;
    }

    public ItemStack itemStack() {
        return this.itemStack;
    }

}
