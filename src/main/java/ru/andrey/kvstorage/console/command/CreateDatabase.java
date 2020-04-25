package ru.andrey.kvstorage.console.command;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

public class CreateDatabase implements DatabaseCommand {

    private final String databaseName;
    private final ExecutionEnvironment env;

    public CreateDatabase(String databaseName, ExecutionEnvironment env) {
        this.databaseName = databaseName;
        this.env = env;
    }

    @Override
    public DatabaseCommandResult execute() {
        try {
            env.addDatabase(Database.newDatabase(databaseName));
        } catch (DatabaseException exc) {
            return DatabaseCommandResult.error(exc.getMessage());
        }
        return DatabaseCommandResult.success(databaseName);
    }
}
