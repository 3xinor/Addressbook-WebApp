package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AddressBookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper; // for converting objects to JSON

    @Autowired
    AddressBookRepository addressBookRepository;

    @BeforeEach
    void setup() {
        // clean DB before each test to ensure isolation
        addressBookRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        addressBookRepository.deleteAll();
    }

    @Test
    void createAddressBook() throws Exception {
        mockMvc.perform(post("/addressbooks"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.id").exists());
    }

    @Test
    void testAddBuddy() throws Exception {
        String addressBookJson = mockMvc.perform(post("/addressbooks"))
                .andReturn().getResponse().getContentAsString();
        AddressBook created = objectMapper.readValue(addressBookJson, AddressBook.class);

        BuddyInfo buddy = new BuddyInfo();
        buddy.setName("Alice");
        buddy.setPhone("123-456");

        mockMvc.perform(post("/addressbooks/" + created.getId() + "/buddies")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(objectMapper.writeValueAsString(buddy)))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.peoples[0].name").value("Alice"))
                .andExpect((ResultMatcher) jsonPath("$.peoples[0].phone").value("123-456"));
    }

    @Test
    void testRemoveBuddy() throws Exception {
        String addressBookJson = mockMvc.perform(post("/addressbooks"))
                .andReturn().getResponse().getContentAsString();
        AddressBook created = objectMapper.readValue(addressBookJson, AddressBook.class);

        BuddyInfo buddy = new BuddyInfo();
        buddy.setName("Bob");
        buddy.setPhone("987-654");

        String updatedBookJson = mockMvc.perform(post("/addressbooks/" + created.getId() + "/buddies")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(objectMapper.writeValueAsString(buddy)))
                .andReturn().getResponse().getContentAsString();
        AddressBook updatedBook = objectMapper.readValue(updatedBookJson, AddressBook.class);

        Long buddyId = updatedBook.getPeoples().get(0).getId();

        mockMvc.perform(delete("/addressbooks/" + created.getId() + "/buddies/" + buddyId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.peoples").isEmpty());
    }

    @Test
    void testGetAddressBook() throws Exception {
        String addressBookJson = mockMvc.perform(post("/addressbooks"))
                .andReturn().getResponse().getContentAsString();
        AddressBook created = objectMapper.readValue(addressBookJson, AddressBook.class);

        mockMvc.perform(get("/addressbooks/" + created.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(created.getId()));
    }
}
