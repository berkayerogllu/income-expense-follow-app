package com.butce.takip.controllers;

import com.butce.takip.models.Expense;
import com.butce.takip.services.ExpenseService;

import jakarta.persistence.PostUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

  private final ExpenseService expenseService;

  @GetMapping("/{userId}")
  public ResponseEntity<List<Expense>> getAllExpenses(@PathVariable Long userId) {
    return ResponseEntity.ok(expenseService.getAllExpenses(userId));
  }

  @PostMapping("/{userId}")
  public ResponseEntity<Expense> createExpense(@PathVariable Long userId, @RequestBody Expense expense) {
    return ResponseEntity.ok(expenseService.createExpense(expense, userId));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
    return ResponseEntity.ok(expenseService.updateExpense(id, expense));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
    return ResponseEntity.ok("Expense Silindi: " + id);
  }

  @GetMapping("/{userId}/monthly")
  public ResponseEntity<List<Expense>> getExpensesByMonth(
      @PathVariable Long userId,
      @RequestParam String date) {
    return ResponseEntity.ok(expenseService.getExpensesByMonth(userId, date));
  }

  @GetMapping("/{userId}/search")
  public ResponseEntity<List<Expense>> searchExpenses(
      @PathVariable Long userId,
      @RequestParam String keyword) {
    return ResponseEntity.ok(expenseService.searchExpenses(userId, keyword));
  }

}
