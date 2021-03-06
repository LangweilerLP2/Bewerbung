package de.langweilerlp;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

/**
 * @author LangweilerLP2
 */

public class Bot {

    public static void main(String[] args) {
        JDABuilder builder = JDABuilder.createDefault(Secrets.token);

        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        builder.setBulkDeleteSplittingEnabled(false);
        builder.setCompression(Compression.NONE);

        builder.addEventListeners(new CommandHandler());

        try {
            builder.build();
        } catch (LoginException ex) {
        }
    }

    public static String getPrefix(Guild guild) {
        return "zt!";
    }

}
