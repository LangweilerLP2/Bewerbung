package de.langweilerlp.entity;

import de.langweilerlp.event.CommandEvent;

/**
 * @author LangweilerLP2
 */

public abstract class Command {

    private final String name;

    public Command(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void execute(CommandEvent event);
}
