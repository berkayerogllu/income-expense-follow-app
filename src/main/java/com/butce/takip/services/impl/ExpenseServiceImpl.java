package com.butce.takip.services.impl;

import com.butce.takip.models.Expense;
import com.butce.takip.models.User;
import com.butce.takip.repositories.ExpenseRepository;
import com.butce.takip.repositories.UserRepository;
import com.butce.takip.services.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

  private final ExpenseRepository expenseRepository;
  private final UserRepository userRepository;

  @Override
  public List<Expense> getAllExpenses(Long userId) {
    return expenseRepository.findByUserId(userId);
  }

  @Override
  public Expense createExpense(Expense expense, Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));

    expense.setUser(user);

    return expenseRepository.save(expense);
  }

  @Override
  public void deleteExpense(Long id) {
    expenseRepository.deleteById(id);
  }

  @Override
  public Expense updateExpense(Long id, Expense expenseDetails) {
    Expense existingExpense = expenseRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Expense bulunamadı id:" + id));

    existingExpense.setDescription(expenseDetails.getDescription());
    existingExpense.setAmount(expenseDetails.getAmount());
    existingExpense.setDate(expenseDetails.getDate());

    return expenseRepository.save(existingExpense);

  }

  @Override
  public List<Expense> getExpensesByMonth(Long userId, String dateStr) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
    YearMonth yearMonth = YearMonth.parse(dateStr, formatter);

    LocalDate startDate = yearMonth.atDay(1);
    LocalDate endDate = yearMonth.atEndOfMonth();

    return expenseRepository.findByUserIdAndDateBetween(userId, startDate, endDate);
  }

  @Override
  public List<Expense> searchExpenses(Long userId, String keyword) {
    return expenseRepository.findByUserIdAndDescriptionContainingIgnoreCase(userId, keyword);
  }
}