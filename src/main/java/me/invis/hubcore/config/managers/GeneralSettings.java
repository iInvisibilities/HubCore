package me.invis.hubcore.config.managers;

import me.invis.hubcore.enums.GeneralSetting;

public class GeneralSettings {

    public GeneralSettings(Boolean... settings) {
        for (int id = 0; id < (settings.length - 1); id++) {
            GeneralSetting setting = GeneralSetting.fromID(id);
            setting.setEnabled(settings[id]);
        }
    }
}
