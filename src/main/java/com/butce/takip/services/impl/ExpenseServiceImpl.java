package com.butce.takip.services.impl;

import com.butce.takip.models.Expense;
import com.butce.takip.models.User;
import com.butce.takip.repositories.ExpenseRepository;
import com.butce.takip.repositories.UserRepository;
import com.butce.takip.services.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
}