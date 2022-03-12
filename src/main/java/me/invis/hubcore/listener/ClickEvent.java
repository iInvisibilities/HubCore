package me.invis.hubcore.listener;

import me.invis.hubcore.HubCore;
import me.invis.hubcore.config.ConfigManager;
import me.invis.hubcore.config.managers.GameMode;
import me.invis.hubcore.config.managers.HubInventory;
import me.invis.hubcore.config.managers.HubItem;
import me.invis.hubcore.config.managers.HubItemAction;
import me.invis.hubcore.enums.Type;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClickEvent implements Listener {
    ConfigManager configManager = HubCore.CONFIG_MANAGER;

    @EventHandler
    private void onClick(PlayerInteractEvent event) {
        HubItem hubItem = configManager.hubItem(event.getPlayer());
        if((       event.getItem() != null
                && event.getItem().getType() != Material.AIR
                && event.getItem().hasItemMeta()
                && event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(hubItem.itemStack().getItemMeta().getDisplayName()))
                &&
                event.getAction().toString()
                        .contains(
                                hubItem.trigger().toString().split("_")[0])) {

            event.setCancelled(true);
            new HubItemAction(event.getPlayer());
        }
    }

    @EventHandler
    private void onInvClick(InventoryClickEvent event) {
        if(event.getCurrentItem() == null) return;
        if(!event.getCurrentItem().hasItemMeta()) return;
        Player clicker = (Player) event.getWhoClicked();
        HubInventory hubInventory = configManager.hubInventory(clicker);
        if(!event.getInventory().getName().equalsIgnoreCase(hubInventory.title())) return;
        event.setCancelled(true);
        for(GameMode gameMode : hubInventory.gameModes()) {
            if(gameMode.itemStack().getItemMeta().getDisplayName().equalsIgnoreCase(event.getCurrentItem().getItemMeta().getDisplayName())) {
                if(configManager.type() == Type.BUNGEECORD) Bukkit.dispatchCommand(clicker, "server " + gameMode.name());

                else {
                    World world = Bukkit.getWorld(gameMode.name());
                    Location l = world.getSpawnLocation();
                    clicker.teleport(new Location(world, l.getX(), l.getY(), l.getZ()));
                }
            }
        }

    }
}
