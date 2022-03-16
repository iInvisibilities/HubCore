package me.invis.hubcore.listener;

import me.invis.hubcore.HubCore;
import me.invis.hubcore.config.ConfigManager;
import me.invis.hubcore.config.managers.*;
import me.invis.hubcore.util.tablist.setTabList;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        ConfigManager configManager = HubCore.CONFIG_MANAGER;
        Player player = event.getPlayer();

        Welcome welcome = configManager.welcome(player);
        HubItem hubItem = configManager.hubItem(player);
        Title title = configManager.title(player);
        TabList tabList = configManager.tabList(player);

        if(welcome.isEnabled()) welcome.content().forEach(player::sendMessage);

        if(hubItem.enabled()) player.getInventory().setItem(hubItem.slot(), hubItem.itemStack());

        if(title.enabled()) player.sendTitle(title.header(), title.footer());

        if(tabList.enabled()) new setTabList(player, tabList);

    }
}
