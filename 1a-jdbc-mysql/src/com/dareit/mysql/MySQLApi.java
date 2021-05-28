package com.dareit.mysql;

import com.dareit.common.AbstractDBApi;
import com.dareit.common.Customer;

import java.sql.Connection;
import java.sql.DriverManager;


public class MySQLApi extends AbstractDBApi {

    private static final String CREATE_TABLE =
            "CREATE TABLE "
                    + Customer.class.getSimpleName()
                    + "(Id BIGINT(11) NOT NULL AUTO_INCREMENT, FirstName VARCHAR(255), LastName VARCHAR(255), PRIMARY KEY (Id))";

    private static MySQLApi INSTANCE;

    private final MySQLConnection mySQLConnection;


    public static MySQLApi getInstance(MySQLConnection mySQLConnection) {
        if (INSTANCE == null) {
            new MySQLApi(mySQLConnection);
        }

        return INSTANCE;
    }

    private MySQLApi(MySQLConnection mySQLConnection) {
        this.mySQLConnection = mySQLConnection;

        if (isConnectionValid()) {
            INSTANCE = this;
        } else {
            INSTANCE = null;
        }
    }

    protected String getDatabase() {
        return mySQLConnection.getDatabase();
    }

    protected String getTableName() {
        return mySQLConnection.getDatabase() + "." + mySQLConnection.getTable();
    }

    protected Connection openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://" +
                            mySQLConnection.getServer() +
                            "/" + mySQLConnection.getDatabase() + "?"
                            + "user=" + mySQLConnection.getUser() +
                            "&password=" + mySQLConnection.getPassword() +
                            "&autoReconnect=true");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected String getCreateTableStatement() {
        return CREATE_TABLE;
    }

}
