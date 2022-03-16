package me.invis.hubcore.util.tablist;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import me.invis.hubcore.HubCore;
import me.invis.hubcore.config.managers.TabList;
import org.bukkit.entity.Player;

public class setTabList {
    public setTabList(Player target, TabList tabList) {
        String header = tabList.header();
        String footer = tabList.footer();

        sendTablist(target, header, footer);
    }

    public void sendTablist(Player player, String header, String footer){
        PacketContainer pc = HubCore.PROTOCOL_MANAGER.createPacket(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);

        pc.getChatComponents().write(0, WrappedChatComponent.fromText(header))
                .write(1, WrappedChatComponent.fromText(footer));
        try {
            HubCore.PROTOCOL_MANAGER.sendServerPacket(player, pc);
        }
        catch (Exception ex) {
        ex.printStackTrace();
        }
    }
}
