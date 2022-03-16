package me.invis.hubcore.config.managers;

import me.invis.hubcore.HubCore;
import me.invis.hubcore.util.ItemAction;
import org.bukkit.entity.Player;

public class ServerListItemAction {
    public ServerListItemAction(Player target) {
        new ItemAction(target, HubCore.CONFIG_MANAGER.serversListItem(target).action());
    }
}
