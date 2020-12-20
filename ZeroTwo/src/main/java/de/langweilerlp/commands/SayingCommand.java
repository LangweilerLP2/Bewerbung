package de.langweilerlp.commands;

import de.langweilerlp.Bot;
import de.langweilerlp.entity.Command;
import de.langweilerlp.event.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;

import java.util.Random;

/**
 * @author LangweilerLP2
 */

public class SayingCommand extends Command {

    public SayingCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandEvent event) {
        Guild guild = event.getGuild();
        EmbedBuilder info = new EmbedBuilder();
        final String prefix = Bot.getPrefix(guild);

        String[] sayings = new String[]{
                "Boku no Darling",
                "Darling? Ohayo!",
                "Mein Daaarling :heart:",
                "Darling?!"
        };

        Random r = new Random();

        event.reply(sayings[r.nextInt(sayings.length)]);

    }

}
