package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.View;

import java.util.Optional;

@Controller
public class AddressBookViewController {

    @Autowired
    private AddressBookRepository addressBookRepository;

    // Display buddies for a given address book
    @GetMapping("/view/addressbooks/{id}")
    public String viewAddressBook(@PathVariable long id, Model model) {
        Optional<AddressBook> optional = addressBookRepository.findById(id);
        if (optional.isPresent()) {
            AddressBook addressBook = optional.get();
            model.addAttribute("addressBook", addressBook);
            model.addAttribute("buddies", addressBook.getPeoples());
            return "addressbook-view"; // Thymeleaf template name
        }
        return "error";
    }

    // Display home page of Address Book web application
    @PostMapping("/home")
    public String viewHomePage(Model model) {
        model.addAttribute("addressBooks", addressBookRepository.findAll());
        return "home";
    }
}
