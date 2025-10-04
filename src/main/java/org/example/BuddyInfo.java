package org.example;

import jakarta.persistence.*;

@Entity
public class BuddyInfo {
    private String name;
    private String phone;
    @ManyToOne
    private AddressBook addressBook;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /*
    Default constructor for BuddyInfo
     */
    public BuddyInfo(){}

    public BuddyInfo(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    public String getName() {return this.name;}

    public String getPhone() {return this.phone;}

    public Long getId() {return this.id;}

    public void setId(Long id) {this.id = id;}

    public void setName(String name) {this.name = name;}

    public void setPhone(String phone) {this.phone = phone;}

    @Override
    public String toString() {
        return "BuddyInfo{" + "name=" + name + ", phone=" + phone + '}';
    }
}
