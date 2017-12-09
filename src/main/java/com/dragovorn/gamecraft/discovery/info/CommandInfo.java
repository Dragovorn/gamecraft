package com.dragovorn.gamecraft.discovery.info;

import com.dragovorn.gamecraft.command.RawCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandInfo {

    private List<RawCommand> commands;

    public static class Builder {

        private List<RawCommand> commands;

        Builder() {
            this.commands = new ArrayList<>();
        }

        public RawCommand insertNew(String name) {
            RawCommand command = new RawCommand(name, this);
            this.commands.add(command);

            return command;
        }

        public RawCommand findCommand(String path) {
            for (RawCommand command : this.commands) {
                if (command.getPath().equals(path)) {
                    return command;
                }
            }

            return null;
        }

        CommandInfo build() {
            return new CommandInfo(this);
        }
    }

    private CommandInfo(Builder builder) {
        this.commands = builder.commands;
    }

    public List<RawCommand> getCommands() {
        return this.commands;
    }
}