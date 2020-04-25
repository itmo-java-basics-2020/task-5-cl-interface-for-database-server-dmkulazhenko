package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.console.command.CreateDatabase;
import ru.andrey.kvstorage.console.command.CreateTable;
import ru.andrey.kvstorage.console.command.ReadKey;
import ru.andrey.kvstorage.console.command.UpdateKey;

import java.lang.reflect.Executable;

public enum DatabaseCommands {
    CREATE_DATABASE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            if (args.length != 1) {
                return () -> DatabaseCommandResult.error(createDatabaseArgsError);
            }
            return new CreateDatabase(args[0], env);
        }
    },
    CREATE_TABLE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            if (args.length != 2) {
                return () -> DatabaseCommandResult.error(createTableArgsError);
            }
            return new CreateTable(args[0], args[1], env);
        }
    },
    READ_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            if (args.length != 3) {
                return () -> DatabaseCommandResult.error(readKeyArgsError);
            }
            return new ReadKey(args[0], args[1], args[2], env);
        }
    },
    UPDATE_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            if (args.length != 4) {
                return () -> DatabaseCommandResult.error(updateKeyArgsError);
            }
            return new UpdateKey(args[0], args[1], args[2], args[3], env);
        }
    };

    static final String createDatabaseArgsError = "CREATE_DATABASE command has one positional argument: databaseName";
    static final String createTableArgsError = "CREATE_TABLE command has two positional arguments: databaseName, tableName";
    static final String readKeyArgsError = "READ_KEY command has three positional arguments: databaseName, tableName, objectKey";
    static final String updateKeyArgsError = "UPDATE_KEY command has four positional arguments: databaseName, tableName, objectKey, objectValue";

    public abstract DatabaseCommand getCommand(ExecutionEnvironment env, String... args);
}
