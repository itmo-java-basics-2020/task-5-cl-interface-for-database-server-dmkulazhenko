package ru.andrey.kvstorage.console.command;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class UpdateKey implements DatabaseCommand {

    private final String databaseName;
    private final String tableName;
    private final String objectKey;
    private final String objectValue;
    private final ExecutionEnvironment env;

    public UpdateKey(String databaseName, String tableName, String objectKey,
                     String objectValue, ExecutionEnvironment env) {
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.objectKey = objectKey;
        this.objectValue = objectValue;
        this.env = env;
    }

    @Override
    public DatabaseCommandResult execute() {
        Optional<Database> database = env.getDatabase(databaseName);
        if (database.isEmpty()) {
            return DatabaseCommandResult.error("No such database: '" + databaseName + "'");
        }

        try {
            database.get().write(tableName, objectKey, objectValue);
        } catch (DatabaseException exc) {
            return DatabaseCommandResult.error(exc.getMessage());
        }
        return DatabaseCommandResult.success(objectValue);
    }
}
