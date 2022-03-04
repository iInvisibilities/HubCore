package me.invis.hubcore.config.managers;

import org.bukkit.entity.Player;

import java.util.List;
import static me.invis.hubcore.util.StringFormatter.format;

public class Welcome {
    private final boolean isEnabled, isCentered;
    private final List<String> messageContent;
    private final Player target;

    public Welcome(Boolean isEnabled, Boolean isCentered, List<String> messageContent, Player target) {
        this.isEnabled = isEnabled;
        this.isCentered = isCentered;
        this.messageContent = messageContent;
        this.target = target;

    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public boolean isCentered() {
        return isCentered;
    }

    public List<String> content() {
        List<String> messageContent = this.messageContent;
        messageContent.forEach(contentLine -> messageContent.set(messageContent.indexOf(contentLine), format(contentLine, this.isCentered, target, true)));
        return messageContent;
    }
}
