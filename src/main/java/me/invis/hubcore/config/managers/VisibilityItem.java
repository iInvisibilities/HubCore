package me.invis.hubcore.config.managers;

import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public class VisibilityItem {
    private final boolean enabled;
    private final Map<ItemStack, Integer> item;
    private final List<String> itemAction;

    public VisibilityItem(boolean enabled, Map<ItemStack, Integer> item, List<String> itemAction) {
        this.enabled = enabled;
        this.item = item;
        this.itemAction = itemAction;
    }

    public boolean enabled() {
        return this.enabled;
    }

    public Map<ItemStack, Integer> item() {
        return this.item;
    }

    public List<String> itemAction() {
        return this.itemAction;
    }
}
