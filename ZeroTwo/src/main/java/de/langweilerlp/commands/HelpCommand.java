package de.langweilerlp.commands;

import de.langweilerlp.Bot;
import de.langweilerlp.entity.Command;
import de.langweilerlp.event.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;

import java.awt.*;

/**
 * @author LangweilerLP2
 */

public class HelpCommand extends Command {

    public HelpCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandEvent event) {
        Guild guild = event.getGuild();
        EmbedBuilder info = new EmbedBuilder();
        final String prefix = Bot.getPrefix(guild);

        info.setColor(Color.cyan);

        event.reply("```" +
                "Eine Liste aller Befehle:" +
                "- zt!help" +
                "- zt!saying" +
                "```");

        event.reply(info.build());
    }
}
