package com.dareit.hsqldb;

import com.dareit.common.AbstractDBApi;
import com.dareit.common.Customer;
import org.hsqldb.server.Server;

import java.sql.Connection;
import java.sql.DriverManager;


public class HSQLDBApi extends AbstractDBApi {

    private static final String CREATE_TABLE =
            "CREATE TABLE "
                    + Customer.class.getSimpleName()
                    + "(Id BIGINT NOT NULL IDENTITY, FirstName VARCHAR(255), LastName VARCHAR(255))";

    private static HSQLDBApi INSTANCE;


    public static HSQLDBApi getInstance() {
        if (INSTANCE == null) {
            new HSQLDBApi();
        }

        return INSTANCE;
    }

    private HSQLDBApi() {
        if (isConnectionValid()) {
            INSTANCE = this;
        } else {
            INSTANCE = null;
        }
    }

    protected String getDatabase() {
        return null;
    }

    protected String getTableName() {
        return Customer.class.getSimpleName();
    }

    protected Connection openConnection() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            return DriverManager.getConnection(
                    "jdbc:hsqldb:mem:dareitdb", "SA", "");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected String getCreateTableStatement() {
        return CREATE_TABLE;
    }

    public static Server startServer() {
        Server server = new Server();
        server.setDatabasePath(0, "mem:dareitdb");
        server.setDatabaseName(0, "dareitdb");
        server.setLogWriter(null);
        server.setErrWriter(null);
        server.setSilent(true);
        server.setRestartOnShutdown(false);
        server.start();

        return server;
    }

}
