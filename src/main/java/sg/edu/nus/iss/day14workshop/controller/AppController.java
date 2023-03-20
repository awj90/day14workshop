package sg.edu.nus.iss.day14workshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import sg.edu.nus.iss.day14workshop.model.User;
import sg.edu.nus.iss.day14workshop.service.AppService;

@Controller
public class AppController {
    
    @Autowired
    AppService appService;

    @GetMapping(path="/")
    public String renderForm(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping(path="/contact")
    public String formSubmissionHandler(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        User newUser = appService.createContact(user);
        model.addAttribute("user", newUser);
        return "created";
    }

    @GetMapping(path="/contact/{id}")
    public String getContactById(@PathVariable String id, Model model) {
        User user = appService.getContactById(id);
        model.addAttribute("user", user);
        return "contact";
    }

    @GetMapping(path="/contacts")
    public String getAllContacts(Model model){
        List<String> contactIDs = appService.getAllContacts();
        model.addAttribute("contactIDs", contactIDs);
        return "contacts";
    }
}
