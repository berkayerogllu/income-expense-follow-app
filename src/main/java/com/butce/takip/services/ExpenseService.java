package com.butce.takip.services;

import com.butce.takip.models.Expense;
import java.util.List;

public interface ExpenseService {

  List<Expense> getAllExpenses(Long userId);

  Expense createExpense(Expense expense, Long userId);
}
