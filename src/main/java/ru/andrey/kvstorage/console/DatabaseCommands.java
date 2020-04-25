package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.console.command.CreateDatabase;
import ru.andrey.kvstorage.console.command.CreateTable;
import ru.andrey.kvstorage.console.command.ReadKey;
import ru.andrey.kvstorage.console.command.UpdateKey;

public enum DatabaseCommands {
    CREATE_DATABASE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            if (args.length != CREATE_DATABASE_ARGS.length) {
                return () -> DatabaseCommandResult.error(CREATE_DATABASE_ARGS.error);
            }
            return new CreateDatabase(args[0], env);
        }
    },
    CREATE_TABLE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            if (args.length != CREATE_TABLE_ARGS.length) {
                return () -> DatabaseCommandResult.error(CREATE_TABLE_ARGS.error);
            }
            return new CreateTable(args[0], args[1], env);
        }
    },
    READ_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            if (args.length != READ_KEY_ARGS.length) {
                return () -> DatabaseCommandResult.error(READ_KEY_ARGS.error);
            }
            return new ReadKey(args[0], args[1], args[2], env);
        }
    },
    UPDATE_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            if (args.length != UPDATE_KEY_ARGS.length) {
                return () -> DatabaseCommandResult.error(UPDATE_KEY_ARGS.error);
            }
            return new UpdateKey(args[0], args[1], args[2], args[3], env);
        }
    };

    private static final ArgsError CREATE_DATABASE_ARGS = new ArgsError(1,
            "CREATE_DATABASE command has one positional argument: databaseName");
    private static final ArgsError CREATE_TABLE_ARGS = new ArgsError(2,
            "CREATE_TABLE command has two positional arguments: databaseName, tableName");
    private static final ArgsError READ_KEY_ARGS = new ArgsError(3,
            "READ_KEY command has three positional arguments: databaseName, tableName, objectKey");
    private static final ArgsError UPDATE_KEY_ARGS = new ArgsError(4,
            "UPDATE_KEY command has four positional arguments: databaseName, tableName, objectKey, objectValue");

    public abstract DatabaseCommand getCommand(ExecutionEnvironment env, String... args);

    private static class ArgsError {
        private final Integer length;
        private final String error;

        public ArgsError(Integer length, String error) {
            this.length = length;
            this.error = error;
        }
    }
}
