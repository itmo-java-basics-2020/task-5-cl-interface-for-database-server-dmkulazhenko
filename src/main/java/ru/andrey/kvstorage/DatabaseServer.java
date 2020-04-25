package ru.andrey.kvstorage;

import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.DatabaseCommands;
import ru.andrey.kvstorage.console.ExecutionEnvironment;

import java.util.Arrays;

public class DatabaseServer {

    private final ExecutionEnvironment env;

    public DatabaseServer(ExecutionEnvironment env) {
        this.env = env;
    }

    public static void main(String[] args) {

    }

    DatabaseCommandResult executeNextCommand(String commandText) {
        if (commandText == null) {
            return DatabaseCommandResult.error("Command must be not null");
        }

        String[] args = commandText.split(" ");
        if (args.length < 2) {
            return DatabaseCommandResult.error("Command parsing error");
        }

        try {
            return DatabaseCommands.valueOf(args[0]).getCommand(
                    env, Arrays.copyOfRange(args, 1, args.length)
            ).execute();
        } catch (Exception exc) {
            return DatabaseCommandResult.error(exc.getMessage());
        }
    }
}
