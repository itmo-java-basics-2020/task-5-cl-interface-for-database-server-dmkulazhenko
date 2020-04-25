package ru.andrey.kvstorage.logic;

import ru.andrey.kvstorage.exception.DatabaseException;

public interface Database {
    String getName();

    void createTableIfNotExists(String tableName) throws DatabaseException;

    void createTableIfNotExists(String tableName, int segmentSizeInBytes) throws DatabaseException;

    void write(String tableName, String objectKey, String objectValue) throws DatabaseException;

    String read(String tableName, String objectKey) throws DatabaseException;

    static DatabaseImpl newDatabase(String databaseName) throws DatabaseException {
        return new DatabaseImpl(databaseName);
    }

    class DatabaseImpl implements Database {
        private final String databaseName;

        public DatabaseImpl(String databaseName) {
            this.databaseName = databaseName;
        }

        @Override
        public String getName() {
            return databaseName;
        }

        @Override
        public void createTableIfNotExists(String tableName) throws DatabaseException {

        }

        @Override
        public void createTableIfNotExists(String tableName, int segmentSizeInBytes) throws DatabaseException {

        }

        @Override
        public void write(String tableName, String objectKey, String objectValue) throws DatabaseException {

        }

        @Override
        public String read(String tableName, String objectKey) throws DatabaseException {
            return null;
        }
    }
}