package com.butce.takip.controllers;

import com.butce.takip.models.Income;
import com.butce.takip.services.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/incomes")
@RequiredArgsConstructor
public class IncomeController {

  private final IncomeService incomeService;

  @GetMapping("/{userId}")
  public ResponseEntity<List<Income>> getAllIncomes(@PathVariable Long userId) {
    return ResponseEntity.ok(incomeService.getAllIncomes(userId));
  }

  @PostMapping("/{userId}")
  public ResponseEntity<Income> createIncome(@PathVariable Long userId, @RequestBody Income income) {
    return ResponseEntity.ok(incomeService.createIncome(income, userId));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Income> updateIncome(@PathVariable Long id, @RequestBody Income income) {
    return ResponseEntity.ok(incomeService.updateIncome(id, income));
  }

  @DeleteMapping
  public ResponseEntity<String> deleteIncome(@PathVariable Long id) {
    incomeService.deleteIncome(id);
    return ResponseEntity.ok("Income başarıyla silindi. ID: " + id);
  }

}
