package com.dareit.common;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractDBApi {

    private Connection connection;


    protected abstract String getDatabase();
    protected abstract String getTableName();
    protected abstract Connection openConnection();

    protected boolean isConnectionValid() {
        System.out.println("try to connect to database");
        try {
            getConnection();
            System.out.println("database connection is valid");
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

    public void createCustomer(Customer customer) {
        try {
            createCustomerTableIfNotExists();

            String sql = "INSERT INTO " + getTableName() + " (FirstName, LastName) VALUES (?,?)";

            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private void createCustomerTableIfNotExists() throws SQLException {
        DatabaseMetaData dbm = getConnection().getMetaData();
        ResultSet tables = dbm.getTables(null, getDatabase(), Customer.class.getSimpleName(), null);
        if (!tables.next()) {
            PreparedStatement create = getConnection().prepareStatement("create table " + Customer.class.getSimpleName() + "(FirstName varchar(255), LastName varchar(255))");
            create.executeUpdate();
        }
    }

    public List<Customer> readCustomers() {
        List<Customer> customersRead = new ArrayList<>();
        try {
            Statement statement = getConnection().createStatement();

            String sql = "SELECT * FROM " + getTableName();
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
