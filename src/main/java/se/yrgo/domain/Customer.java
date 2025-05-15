package se.yrgo.domain;

public class Customer {
    private String customerID;
    private String name;
    private String email;
    private String telephone;

    public Customer(String customerID, String name, String email, String telephone) {
        this.customerID = customerID;
        this.name = name;
        this.email = email;
        this.telephone = telephone;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    @Override
    public String toString(){ 
        return String.format("Customer ID: %s - Name: %s - email: %s - Telephone_Number: %s", customerID, name, email, telephone);
    }
   
}

