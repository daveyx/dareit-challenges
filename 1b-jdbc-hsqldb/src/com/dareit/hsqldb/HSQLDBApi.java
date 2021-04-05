package com.dareit.hsqldb;

import com.dareit.common.AbstractDBApi;
import com.dareit.common.Customer;

import java.sql.Connection;
import java.sql.DriverManager;


public class HSQLDBApi extends AbstractDBApi {

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
        return "dareitdb";
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

}
