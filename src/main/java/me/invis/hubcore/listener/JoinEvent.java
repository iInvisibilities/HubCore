package me.invis.hubcore.listener;

import me.invis.hubcore.HubCore;
import me.invis.hubcore.config.ConfigManager;
import me.invis.hubcore.config.managers.*;
import me.invis.hubcore.util.tablist.setTabList;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class JoinEvent implements Listener {

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        ConfigManager configManager = HubCore.CONFIG_MANAGER;
        Player player = event.getPlayer();

        Welcome welcome = configManager.welcome(player);
        ServersListItem serversListItem = configManager.serversListItem(player);
        VisibilityItem visibilityItem = configManager.visibilityItem(player);
        Title title = configManager.title(player);
        TabList tabList = configManager.tabList(player);

        if(welcome.isEnabled()) welcome.content().forEach(player::sendMessage);

        if(serversListItem.enabled()) player.getInventory().setItem(serversListItem.slot(), serversListItem.itemStack());


        ItemStack item = visibilityItem.item().keySet().toArray(new ItemStack[0])[0];
        if(visibilityItem.enabled()) player.getInventory().setItem(visibilityItem.item().get(item), item);

        if(title.enabled()) player.sendTitle(title.header(), title.footer());

        if(tabList.enabled()) new setTabList(player, tabList);

    }
}
