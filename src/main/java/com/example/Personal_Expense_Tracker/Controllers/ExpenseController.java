package com.example.Personal_Expense_Tracker.Controllers;

import com.example.Personal_Expense_Tracker.Model.Expense;
import com.example.Personal_Expense_Tracker.Repository.ExpenseRepository;
import com.example.Personal_Expense_Tracker.Model.User;
import com.example.Personal_Expense_Tracker.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getExpenses(Model model) {
        model.addAttribute("expenses", expenseRepository.findAll());
        model.addAttribute("expense", new Expense()); // Bind form data to Expense object
        return "expenses";
    }

    @PostMapping
    public String addExpense(@ModelAttribute Expense expense) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            expense.setUser(user); // Make sure the user is set before saving
            expenseRepository.save(expense);
        } else {
            // Handle error: user not found
            return "redirect:/error"; // Replace with your error handling page
        }
        return "redirect:/expenses";
    }

    @GetMapping("/edit/{id}")
    public String editExpense(@PathVariable("id") Long id, Model model) {
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid expense Id:" + id));
        model.addAttribute("expense", expense);
        return "edit-expense";
    }

    @PostMapping("/update/{id}")
    public String updateExpense(@PathVariable("id") Long id, @ModelAttribute Expense expense) {
        Expense existingExpense = expenseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid expense Id:" + id));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            existingExpense.setUser(user); // Make sure the user is set before saving
            existingExpense.setAmount(expense.getAmount());
            existingExpense.setDescription(expense.getDescription());
            existingExpense.setCategory(expense.getCategory());
            expenseRepository.save(existingExpense);
        } else {
            // Handle error: user not found
            return "redirect:/error"; // Replace with your error handling page
        }
        return "redirect:/expenses";
    }

    @GetMapping("/delete/{id}")
    public String deleteExpense(@PathVariable("id") Long id) {
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid expense Id:" + id));
        expenseRepository.delete(expense);
        return "redirect:/expenses";
    }

    @GetMapping("/search")
    public String searchExpenses(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("expenses", expenseRepository.findByDescriptionContainingOrCategoryContaining(keyword, keyword));
        return "expenses";
    }
}