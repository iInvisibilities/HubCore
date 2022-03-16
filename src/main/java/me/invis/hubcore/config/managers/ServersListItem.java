package me.invis.hubcore.config.managers;

import me.invis.hubcore.util.enums.Trigger;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ServersListItem {
    private final boolean enabled;
    private final ItemStack itemStack;
    private final int slot;
    private final String trigger;
    private final List<String> action;

    public ServersListItem(boolean enabled, ItemStack itemStack, int slot, String trigger, List<String> action) {
        this.itemStack = itemStack;
        this.slot = slot;
        this.trigger = trigger;
        this.action = action;
        this.enabled = enabled;
    }

    public ItemStack itemStack() {
        return this.itemStack;
    }

    public int slot() {
        return this.slot;
    }

    public Trigger trigger() {
        return Trigger.valueOf(this.trigger.replaceAll("-", "_"));
    }

    public List<String> action() {
        return this.action;
    }

    public boolean enabled() {
        return this.enabled;
    }
}
