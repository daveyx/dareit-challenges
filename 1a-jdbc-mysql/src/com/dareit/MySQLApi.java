package com.dareit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MySQLApi {

    private static MySQLApi INSTANCE;

    private final MySQLConnection mySQLConnection;
    private Connection connection;


    public static MySQLApi getInstance(MySQLConnection mySQLConnection) {
        if (INSTANCE == null) {
            new MySQLApi(mySQLConnection);
        }

        return INSTANCE;
    }

    private MySQLApi(MySQLConnection mySQLConnection) {
        this.mySQLConnection = mySQLConnection;

        if (isMySQLConnectionValid()) {
            INSTANCE = this;
        } else {
            INSTANCE = null;
        }
    }

    private boolean isMySQLConnectionValid() {
        System.out.println("try to connect to: " + mySQLConnection.toString());
        try {
            getConnection();
            System.out.println("connection is valid");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = openConnection();
        } else {
            if (!connection.isClosed()) {
                connection = openConnection();
            }
        }
        return connection;
    }

    private Connection openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://" +
                            mySQLConnection.server +
                            "/" + mySQLConnection.database + "?"
                            + "user=" + mySQLConnection.user +
                            "&password=" + mySQLConnection.password +
                            "&autoReconnect=true");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void createCustomer(Customer customer) {
        try {
            createCustomerTableIfNotExists();

            String sql = "INSERT INTO " + mySQLConnection.database + "." + mySQLConnection.table + " (FirstName, LastName) VALUES (?,?)";

            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, customer.firstName);
            preparedStatement.setString(2, customer.lastName);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private void createCustomerTableIfNotExists() throws SQLException {
        DatabaseMetaData dbm = getConnection().getMetaData();
        ResultSet tables = dbm.getTables(null, mySQLConnection.database, mySQLConnection.table, null);
        if (!tables.next()) {
            PreparedStatement create = getConnection().prepareStatement("create table " + mySQLConnection.table + "(FirstName varchar(255), LastName varchar(255))");
            create.executeUpdate();
        }
    }

    public List<Customer> readCustomers() {
        List<Customer> customersRead = new ArrayList<>();
        try {
            Statement statement = getConnection().createStatement();

            String sql = "SELECT * FROM " + mySQLConnection.database + "." + mySQLConnection.table;
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                customersRead.add(new Customer(firstName, lastName));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return customersRead;
    }
}
