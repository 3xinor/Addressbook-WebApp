package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/addressbooks")
public class AddressBookController {
    @Autowired
    private AddressBookRepository addressBookRepository;

    // create a new address book
    @PostMapping("/new")
    public AddressBook createAddressBook() {
        return addressBookRepository.save(new  AddressBook());
    }

    // add buddy to address book
    @PostMapping("/{id}/buddies")
    public AddressBook addBuddy(@PathVariable long id, @RequestBody BuddyInfo buddy) {
        Optional<AddressBook> optional = addressBookRepository.findById(id);
        if(optional.isPresent()) {
            AddressBook addressBook = optional.get();
            addressBook.addBuddy(buddy);
            return addressBookRepository.save(addressBook);
        }
        throw new RuntimeException("Address Book Not Found");
    }

    // Remove buddy from address book
    @DeleteMapping("/{id}/buddies/{buddyId}")
    public AddressBook removeBuddy(@PathVariable long id, @PathVariable long buddyId) {
        Optional<AddressBook> optional = addressBookRepository.findById(id);
        if(optional.isPresent()) {
            AddressBook addressBook = optional.get();
            addressBook.removeBuddy(buddyId);
            return addressBookRepository.save(addressBook);
        }
        throw new RuntimeException("Address Book Not Found");
    }

    @GetMapping("/{id}")
    public AddressBook getAddressBook(@PathVariable long id) {
        return addressBookRepository.findById(id).orElseThrow(() -> new RuntimeException("Address Book Not Found"));
    }
}
