package com.example.Personal_Expense_Tracker.Repository;

import com.example.Personal_Expense_Tracker.Model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByDescriptionContainingOrCategoryContaining(String description, String category);}
