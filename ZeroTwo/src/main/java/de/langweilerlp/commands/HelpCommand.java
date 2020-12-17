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

        info.addField("" + event.getAuthor().getAsTag(), "Eine Liste aller Commands:", false);
        info.addField("", "-zt!help", true);
        info.addField("", "-zt!saying", true);

        info.setColor(Color.cyan);

        event.reply(info.build());
    }
}
