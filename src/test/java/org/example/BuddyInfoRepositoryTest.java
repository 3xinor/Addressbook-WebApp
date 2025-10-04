package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class BuddyInfoRepositoryTest {
//
//    @Autowired
//    private BuddyInfoRepository repository;
//
//    @BeforeEach
//    public void cleanDatabase() {
//        repository.deleteAll();
//    }
//
//    @Test
//    public void testSaveAndFindAll() {
//        // Creating objects representing BuddyInfo
//        BuddyInfo buddyPersist1 = new BuddyInfo();
//        buddyPersist1.setName("Dave");
//        buddyPersist1.setPhone("613 - 600 - 5987");
//
//        BuddyInfo buddyPersist2 = new BuddyInfo();
//        buddyPersist2.setName("James");
//        buddyPersist2.setPhone("613 - 589 - 4902");
//
//        // persist data
//        repository.save(buddyPersist1);
//        repository.save(buddyPersist2);
//
//        // query database
//        List<BuddyInfo> results = (List<BuddyInfo>) repository.findAll();
//
//        assertEquals(2, results.size());
//
//        for (BuddyInfo buddyInfo : results) {
//            if (buddyInfo.getName().equals("Dave")) {
//                assertEquals(buddyPersist1.getPhone(), buddyInfo.getPhone());
//            } else {
//                assertEquals(buddyPersist2.getPhone(), buddyInfo.getPhone());
//            }
//        }
//
//    }
}
