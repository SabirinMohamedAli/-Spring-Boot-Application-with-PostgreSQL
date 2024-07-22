package com.example.Personal_Expense_Tracker.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {

    @GetMapping("/contact")
    public String showContactForm() {
        return "contact";
    }

    @PostMapping("/contact")
    public String submitContactForm(@RequestParam("firstName") String firstName,
                                    @RequestParam("lastName") String lastName,
                                    @RequestParam("email") String email,
                                    @RequestParam("phone") String phone,
                                    @RequestParam("message") String message,
                                    Model model) {
        // Print a simple confirmation message to the user
        model.addAttribute("successMessage", "Your message has been sent successfully! Thank you for contacting us.");
        return "contact";
    }
}
