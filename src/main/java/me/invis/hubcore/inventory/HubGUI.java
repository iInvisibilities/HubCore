package me.invis.hubcore.inventory;

import me.invis.hubcore.HubCore;
import me.invis.hubcore.config.ConfigManager;
import me.invis.hubcore.config.managers.GameMode;
import me.invis.hubcore.config.managers.HubInventory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class HubGUI {
    ConfigManager configManager = HubCore.CONFIG_MANAGER;

    public HubGUI(Player target) {
        HubInventory hubInventory = configManager.hubInventory(target);
        Inventory inventory = Bukkit.createInventory(target, hubInventory.size(), hubInventory.title());
        for(GameMode gameMode : hubInventory.gameModes()) inventory.setItem(gameMode.slot(), gameMode.itemStack());

        if(hubInventory.fill()) {
            int slot = 0;
            for(ItemStack item : inventory.getContents()) {
                if(item == null || item.getType().toString().contains("AIR")) {
                    inventory.setItem(slot, hubInventory.fillITem());
                }
                slot++;
            }
        }
        target.openInventory(inventory);
    }
}
