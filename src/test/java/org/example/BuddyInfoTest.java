package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuddyInfoTest {
    private BuddyInfo buddyInfo;
    @BeforeEach
    public void setUp() throws Exception {
        buddyInfo = new BuddyInfo("Dave", "613 - 600 - 5987");
    }

    @AfterEach
    public void tearDown() throws Exception {
        buddyInfo = null;
    }

    @Test
    public void getName() {
        assertEquals("Dave", buddyInfo.getName());
    }

    @Test
    public void getPhone() {
        assertEquals("613 - 600 - 5987", buddyInfo.getPhone());
    }

    @Test
    public void setName() {
        buddyInfo.setName("Tyler");
        assertEquals("Tyler", buddyInfo.getName());
    }

    @Test
    public void setPhone() {
        buddyInfo.setPhone("613 - 583 - 5987");
        assertEquals("613 - 583 - 5987", buddyInfo.getPhone());
    }
}