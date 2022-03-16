package me.invis.hubcore.util;

import me.invis.hubcore.util.enums.Action;
import me.invis.hubcore.util.inventory.HubGUI;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;

public class ItemAction {

    public ItemAction(Player target, List<String> actions) {
        actions.stream().map(actionString -> actionString.split("\\|")).forEach(stringParams -> {
            Action actionType = Action.fromChar(String.valueOf(stringParams[0].toCharArray()[1]));
            String value = null;
            if (actionType != Action.INVENTORY && actionType != Action.TOGGLE_PLAYERS_VISIBILITY) value = stringParams[1];

            if (actionType == Action.COMMAND) {
                String command = StringFormatter.format(stringParams[2], false, target, true);

                if (value.equalsIgnoreCase("player")) Bukkit.dispatchCommand(target, command);

                else Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
            } else if (actionType == Action.INVENTORY) new HubGUI(target);

            else if (actionType == Action.MESSAGE)
                target.sendMessage(StringFormatter.format(value, false, target, true));

            else if (actionType == Action.PARTICLE)
                target.getWorld().playEffect(target.getLocation(), Effect.valueOf(value), 1);

            else if (actionType == Action.SOUND)
                target.getWorld().playSound(target.getLocation(), Sound.valueOf(value), 1, 1);

            else if (actionType == Action.TOGGLE_PLAYERS_VISIBILITY) {
                Bukkit.getOnlinePlayers().forEach(onlinePlayer -> {
                    if (target.canSee(onlinePlayer)) target.hidePlayer(onlinePlayer);
                    else target.showPlayer(onlinePlayer);
                });
            }
        });

    }
}
