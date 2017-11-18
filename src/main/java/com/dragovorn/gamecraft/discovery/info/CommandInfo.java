package com.dragovorn.gamecraft.discovery.info;

import com.dragovorn.gamecraft.command.RawCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandInfo {

    private List<RawCommand> commands;

    public static class Builder {
        private List<RawCommand> commands;

        public Builder() {
            this.commands = new ArrayList<>();
        }

        public RawCommand insertNew(String name) {
            RawCommand command = new RawCommand(name);
            this.commands.add(command);

            return command;
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