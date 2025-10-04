package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AddressBookTest {
    private AddressBook addressBook;

    @BeforeEach
    public void setUp() throws Exception {
        addressBook = new AddressBook();
    }

    @AfterEach
    public void tearDown() throws Exception {
        addressBook = null;
    }

    @Test
    public void getPeoples() {
        Random random = new Random();
        int randFriends = random.nextInt(16);

        for  (int i = 0; i < randFriends; i++) {
            addressBook.addBuddy(new BuddyInfo("Name_" + i, "613 - " + i));
        }

        assertEquals(randFriends, addressBook.getPeoples().size());
    }

    @Test
    public void addBuddy() {
        Random random = new Random();
        int randFriends = random.nextInt(16);

        for  (int i = 0; i < randFriends; i++) {
            assertTrue(addressBook.addBuddy(new BuddyInfo("Name_" + i, "613 - " + i)));
        }

        assertEquals(randFriends, addressBook.getPeoples().size());
    }

    @Test
    public void removeBuddy() {
        addressBook.addBuddy(new BuddyInfo("Todd", "613 - 500 - 8888"));
        assertTrue(addressBook.removeBuddy("Todd", "613 - 500 - 8888"));
        assertEquals(0, addressBook.getPeoples().size());
    }
}