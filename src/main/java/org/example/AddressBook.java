package org.example;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
public class AddressBook {
    @OneToMany(mappedBy = "addressBook", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<BuddyInfo> peoples;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public AddressBook() {
        peoples = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {this.id = id;}

    public List<BuddyInfo> getPeoples() {return peoples;}

    public Boolean addBuddy(BuddyInfo buddy) {
        buddy.setAddressBook(this);
        return peoples.add(buddy);
    }

    public Boolean removeBuddy(String name, String phone) {
        Iterator<BuddyInfo> iterator = peoples.iterator();
        while (iterator.hasNext()) {
            BuddyInfo buddy = iterator.next();
            if (buddy.getName().equals(name) && buddy.getPhone().equals(phone)) {
                return peoples.remove(buddy);
            }
        }
        return false;
    }

    public void removeBuddy(Long buddyId) {
        this.peoples.removeIf(buddy -> buddy.getId().equals(buddyId));
    }

    @Override
    public String toString() {
        String output = "Address Book " + getId() + ":\n";
        for(BuddyInfo buddy : peoples) {
            output += buddy.toString() + "\n";
        }
        return output;
    }
}
