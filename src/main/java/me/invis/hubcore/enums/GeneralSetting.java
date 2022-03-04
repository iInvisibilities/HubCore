package me.invis.hubcore.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum GeneralSetting {
    JOIN_MESSAGE(0, false),
    LEAVE_MESSAGE(1, false),
    ENTITIES(2, false),
    MOBS(3, false),
    OTHER_ENTITIES(4, false),
    DAMAGE(5, false),
    HUNGER(6, false),
    EXPLOSION(7, false),
    BLOCK_BREAK(8, false),
    BLOCK_PLACE(9, false),
    PVP(10, false),
    ITEM_THROW(11, false);

    private final int id;
    private boolean enabled;
    private static final Map<Integer, GeneralSetting> ID_MAP = new HashMap<>();

    GeneralSetting(int id, boolean enabled) {
        this.id = id;
        this.enabled = enabled;
    }

    public int id() {
        return this.id;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public static GeneralSetting fromID(int id) {
        return ID_MAP.get(id);
    }


    static {
        Arrays.stream(values()).forEach(type -> ID_MAP.put(type.id, type));
    }
}
