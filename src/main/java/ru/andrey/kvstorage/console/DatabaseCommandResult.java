package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    Optional<String> getErrorMessage();

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }

    static DatabaseCommandResult success(String result) {
        return new DatabaseCommandResultImpl(result, DatabaseCommandStatus.SUCCESS);
    }

    static DatabaseCommandResult error(String errorMessage) {
        return new DatabaseCommandResultImpl(null, DatabaseCommandStatus.FAILED, errorMessage);
    }

    class DatabaseCommandResultImpl implements DatabaseCommandResult {
        private final String result;
        private final String errorMessage;
        private final DatabaseCommandStatus status;

        public DatabaseCommandResultImpl(String result, DatabaseCommandStatus status) {
            this(result, status, null);
        }

        public DatabaseCommandResultImpl(String result, DatabaseCommandStatus status, String errorMessage) {
            this.result = result;
            this.errorMessage = errorMessage;
            this.status = status;
        }

        @Override
        public Optional<String> getResult() {
            return Optional.ofNullable(result);
        }

        @Override
        public DatabaseCommandStatus getStatus() {
            return status;
        }

        @Override
        public boolean isSuccess() {
            return status.equals(DatabaseCommandStatus.SUCCESS);
        }

        @Override
        public Optional<String> getErrorMessage() {
            return Optional.ofNullable(errorMessage);
        }
    }
}