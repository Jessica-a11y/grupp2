package se.yrgo.domain;

public class Customer {
    private String name;
    private String email;
    private String telephone;

    public Customer(String name, String email, String telephone) {
        this.name = name;
        this.email = email;
        this.telephone = telephone;
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

    
}
