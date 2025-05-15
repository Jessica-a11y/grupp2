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
    //Har skapat en map services jag tänker skapa ett interface
    //Tanken är att den ska ha alla de funktioner som vi skulle villja ha i vår meny. Typ boka ett bord/ redigera bokning/
    // 
}//Gå in i Bookingservi

//Yes!
