package com.example.Personal_Expense_Tracker.Services;

import com.example.Personal_Expense_Tracker.Model.Expense;
import com.example.Personal_Expense_Tracker.Repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {


    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }


    public  List<Expense>getAllExpense(){return expenseRepository.findAll();};
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id).orElse(null);
    }

    public  void addExpense(Expense expense) {
        expenseRepository.save(expense);
    }

    public static void saveExpense(Expense expense) {
    }


    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
}
//    public List<Expense> getExpensesByUser(User user) {
//        return expenseRepository.findByUser(user);
//    }
