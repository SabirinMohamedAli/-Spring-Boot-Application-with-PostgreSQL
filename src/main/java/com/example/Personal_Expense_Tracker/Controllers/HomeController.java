package com.example.Personal_Expense_Tracker.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String index() {
        return "index"; // This is your home page view
    }

    @GetMapping("/about")
    public String about() {
        return "about"; // This is your about page view
    }

    // Haddii mapping-ku yahay isku mid, beddel URL-ka
    @GetMapping("/daily-expense-home")
    public String dailyExpense() {
        return "daily-expense"; // This is your daily expense page view
    }

    @GetMapping("/services")
    public String services() {
        return "services"; // This is your services page view
    }

}
