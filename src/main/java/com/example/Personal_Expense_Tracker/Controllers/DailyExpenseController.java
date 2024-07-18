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

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/daily-expense")
public class DailyExpenseController {



    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getExpenses(Model model) {
        model.addAttribute("expenses", expenseRepository.findAll());
        model.addAttribute("expense", new Expense());
        return "expenses";
    }

    @PostMapping
    public String addExpense(@ModelAttribute Expense expense) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            expense.setUser(user);
            expenseRepository.save(expense);
        } else {
            throw new IllegalArgumentException("User not found.");
        }
        return "redirect:/daily-expense";
    }

    @GetMapping("/edit/{id}")
    public String editExpense(@PathVariable("id") Long id, Model model) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid expense Id: " + id));
        model.addAttribute("expense", expense);
        return "edit-expense";
    }

    @PostMapping("/update/{id}")
    public String updateExpense(@PathVariable("id") Long id, @ModelAttribute Expense expense) {
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid expense Id: " + id));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            existingExpense.setUser(user);
            existingExpense.setAmount(expense.getAmount());
            existingExpense.setDescription(expense.getDescription());
            existingExpense.setCategory(expense.getCategory());
            expenseRepository.save(existingExpense);
        } else {
            throw new IllegalArgumentException("User not found.");
        }
        return "redirect:/daily-expense";
    }

    @GetMapping("/delete/{id}")
    public String deleteExpense(@PathVariable("id") Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid expense Id: " + id));
        expenseRepository.delete(expense);
        return "redirect:/daily-expense";
    }



    @GetMapping("/search")
    public String searchExpenses(@RequestParam("keyword") String keyword, Model model) {
        List<Expense> expenses = expenseRepository.findByDescriptionContainingOrCategoryContaining(keyword, keyword);
        model.addAttribute("expenses", expenses);
        model.addAttribute("keyword", keyword);
        model.addAttribute("expense", new Expense()); // Ensure the "expense" attribute is always available
        return "expenses";
    }

}