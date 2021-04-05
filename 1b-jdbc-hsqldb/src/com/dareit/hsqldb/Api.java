package com.dareit.hsqldb;

import com.dareit.common.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Api {

    private static Api INSTANCE;
    private Connection connection;


    public static Api getInstance() {
        if (INSTANCE == null) {
            new Api();
        }

        return INSTANCE;
    }

    private Api() {
        if (isConnectionValid()) {
            INSTANCE = this;
        } else {
            INSTANCE = null;
        }
    }

    private boolean isConnectionValid() {
        System.out.println("try to connect to hsqdb");
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
            Class.forName("org.hsqldb.jdbcDriver");
            return DriverManager.getConnection(
                    "jdbc:hsqldb:mem:dareitdb", "SA", "");
//            return DriverManager.getConnection(
//                    "jdbc:hsqldb:hsql://localhost/dareitdb", "dareituser", "dareitpassword");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public void createCustomer(Customer customer) {
        try {
            createCustomerTableIfNotExists();

            String sql = "INSERT INTO " + Customer.class.getSimpleName() + " (FirstName, LastName) VALUES (?,?)";

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
        ResultSet tables = dbm.getTables(null, "dareitdb", Customer.class.getSimpleName(), null);
        if (!tables.next()) {
            PreparedStatement create = getConnection().prepareStatement("create table " + Customer.class.getSimpleName() + "(FirstName varchar(255), LastName varchar(255))");
            create.executeUpdate();
        }
    }

    public List<Customer> readCustomers() {
        List<Customer> customersRead = new ArrayList<>();
        try {
            Statement statement = getConnection().createStatement();

            String sql = "SELECT * FROM " + Customer.class.getSimpleName();
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
