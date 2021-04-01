package com.dareit.common;


public class Customer {

    final String firstName;
    final String lastName;


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Customer {" +
                "server=" + firstName +
                "user=" + lastName +
                "}";
    }

}
