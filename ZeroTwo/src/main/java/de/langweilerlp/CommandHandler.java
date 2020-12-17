package de.langweilerlp;

import de.langweilerlp.commands.HelpCommand;
import de.langweilerlp.commands.SayingCommand;
import de.langweilerlp.entity.Command;
import de.langweilerlp.event.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.*;

/**
 * @author LangweilerLP2
 */

public class CommandHandler extends ListenerAdapter {

    private static final HashMap<String, Command> commands = new HashMap<String, Command>();

    public CommandHandler() {
        List<Command> botCommands = new ArrayList<>(Arrays.asList(new HelpCommand("help"), new SayingCommand("saying")));
        // botCommands.addAll(Arrays.asList(new InfoCommand("play"), new PrefixCommand("continue")));
        // for (String cnp : commandsNoPrefix) botCommands.add(new GameInputCommand(cnp));
        for (Command command : botCommands) commands.put(command.getName().toLowerCase(), command);
        System.out.println("[INFO] Loaded " + commands.size() + "de/langweilerlp/commands");
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        User user = event.getAuthor();
        Message message = event.getMessage();
        TextChannel channel = event.getChannel();
        Guild guild = event.getGuild();
        String msgRaw = message.getContentRaw();
        String[] args = msgRaw.split("\\s+");
        if (args.length > 0) {
            boolean isMention = msgRaw.equals("<@" + event.getJDA().getSelfUser().getId() + ">") || msgRaw
                    .equals("<@!" + event.getJDA().getSelfUser().getId() + ">");
            String prefix = Bot.getPrefix(guild);
            String arg = args[0].toLowerCase();
            boolean isCommand = false;
            if (isMention) isCommand = true;
            else {
                if (arg.startsWith(prefix)) {
                    String commandName = arg.substring(prefix.length()).toLowerCase();
                    isCommand = true;
                    if (isCommand) arg = commandName;
                }
            }
            if (isCommand) {
                if (!hasPermissions(guild, channel)) {
                    sendInvalidPermissionsMessage(user, channel);
                    return;
                }
                Command command = commands.get(arg);
                // if (isMention) command = commands.get("info");
                if (command == null) {
                    return;
                }
                command.execute(new CommandEvent(event, Arrays.copyOfRange(msgRaw.split("\\s+"), 1, args.length)));
            }
        }
    }

    private static final Collection<Permission> requiredPermissions = Arrays
            .asList(Permission.MESSAGE_ADD_REACTION, Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_MANAGE,
                    Permission.MESSAGE_WRITE);

    private boolean hasPermissions(Guild guild, TextChannel channel) {
        Member self = guild.getSelfMember();
        if (self.hasPermission(Permission.ADMINISTRATOR)) return true;
        return self.hasPermission(channel, requiredPermissions);
    }

    private void sendInvalidPermissionsMessage(User user, TextChannel channel) {
        if (channel.canTalk()) {
            StringBuilder requiredPermissionsDisplay = new StringBuilder();
            for (Permission requiredPermission : requiredPermissions) {
                requiredPermissionsDisplay.append("`").append(requiredPermission.getName()).append("`, ");
            }
            if (requiredPermissionsDisplay.toString().endsWith(", ")) requiredPermissionsDisplay = new StringBuilder(
                    requiredPermissionsDisplay.substring(0, requiredPermissionsDisplay.length() - 2));
            channel.sendMessage(user.getAsMention() + ", I don't have enough permissions to work properly.\nMake "
                    + "sure I have the following permissions: " + requiredPermissionsDisplay
                    + "\nIf you think this is "
                    + "an error, please contact a server administrator.").queue();
        }
    }

}
