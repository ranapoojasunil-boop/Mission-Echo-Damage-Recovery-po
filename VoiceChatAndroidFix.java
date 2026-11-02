package com.ranapoojsunil;

import net.fabricmc.api.ClientModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VoiceChatAndroidFix implements ClientModInitializer {

    public static final String MOD_ID = "voicechat-android-fix";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("Mission Echo Fix loaded successfully!");
        LOGGER.info("Android AEC integration is not enabled yet.");
    }
}
