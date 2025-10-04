package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(SpringExtension.class)
public class AddressBookRepositoryTest {
//
//    @Autowired
//    private AddressBookRepository repository;
//
//    @BeforeEach
//    public void cleanDatabase() {
//        repository.deleteAll();
//    }
//
//    @Test
//    public void testSaveAndFind() {
//        // Creating objects representing BuddyInfos and AddressBooks
//        // Add Buddy's to AddressBook prior to persistence
//        BuddyInfo buddyPersist1 = new BuddyInfo();
//        buddyPersist1.setName("Tyler");
//        buddyPersist1.setPhone("613 - 600 - 5987");
//
//        BuddyInfo buddyPersist2 = new BuddyInfo();
//        buddyPersist2.setName("Bob");
//        buddyPersist2.setPhone("613 - 589 - 4902");
//
//        AddressBook addressBookPersist = new AddressBook();
//        addressBookPersist.addBuddy(buddyPersist1);
//        addressBookPersist.addBuddy(buddyPersist2);
//        Long ID = addressBookPersist.getId();
//        AddressBook savedAddressBook = repository.save(addressBookPersist);
//
//        // retrieve address book by Id
//        Optional<AddressBook> queryAddressBook = repository.findById(savedAddressBook.getId());
//
//        if  (queryAddressBook.isPresent()) {
//            AddressBook addressBook = queryAddressBook.get();
//            assertEquals(2, addressBook.getPeoples().size());
//
//            for (BuddyInfo buddyInfo : addressBook.getPeoples()) {
//                if (buddyInfo.getName().equals("Tyler")) {
//                    assertEquals(buddyPersist1.getPhone(), buddyInfo.getPhone());
//                } else {
//                    assertEquals(buddyPersist2.getPhone(), buddyInfo.getPhone());
//                }
//            }
//
//        } else {
//            fail();
//        }
//    }
}
