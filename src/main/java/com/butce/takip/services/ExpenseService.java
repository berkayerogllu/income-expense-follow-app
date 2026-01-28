package com.butce.takip.services;

import com.butce.takip.models.Expense;
import java.util.List;

public interface ExpenseService {

  List<Expense> getAllExpenses(Long userId);

  Expense createExpense(Expense expense, Long userId);

  void deleteExpense(Long id);

  Expense updateExpense(Long id, Expense expense);

  List<Expense> getExpensesByMonth(Long userId, String dateStr);
}
