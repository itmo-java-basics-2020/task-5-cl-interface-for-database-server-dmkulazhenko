package ru.andrey.kvstorage.console.command;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class ReadKey implements DatabaseCommand {

    private final String databaseName;
    private final String tableName;
    private final String objectKey;
    private final ExecutionEnvironment env;

    public ReadKey(String databaseName, String tableName, String objectKey,
                   ExecutionEnvironment env) {
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.objectKey = objectKey;
        this.env = env;
    }

    @Override
    public DatabaseCommandResult execute() {
        Optional<Database> database = env.getDatabase(databaseName);
        if (database.isEmpty()) {
            return DatabaseCommandResult.error("No such database: '" + databaseName + "'");
        }

        try {
            return DatabaseCommandResult.success(database.get().read(tableName, objectKey));
        } catch (DatabaseException exc) {
            return DatabaseCommandResult.error(exc.getMessage());
        }
    }
}
