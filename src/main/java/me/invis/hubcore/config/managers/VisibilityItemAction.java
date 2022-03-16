package me.invis.hubcore.config.managers;

import me.invis.hubcore.HubCore;
import me.invis.hubcore.util.ItemAction;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class VisibilityItemAction {

    public VisibilityItemAction(Player target) {
        VisibilityItem visibilityItem = HubCore.CONFIG_MANAGER.visibilityItem(target);
        ItemStack item = visibilityItem.item().keySet().toArray(new ItemStack[0])[0];
        ItemMeta itemMeta = item.getItemMeta();

        if(itemMeta.getDisplayName().equalsIgnoreCase(itemMeta.getDisplayName())) {
            new ItemAction(target, visibilityItem.itemAction());
            target.getInventory().setItem(visibilityItem.item().get(item), item);
            return;
        }

        new ItemAction(target, visibilityItem.itemAction());
    }

}
