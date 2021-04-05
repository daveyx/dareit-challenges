package com.dareit.common;


public class Customer {

    final String firstName;
    final String lastName;


    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Customer {" +
                "server=" + firstName +
                "user=" + lastName +
                "}";
    }

}
