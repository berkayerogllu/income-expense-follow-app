package com.butce.takip.services.impl;

import com.butce.takip.dtos.MonthlySummaryDto;
import com.butce.takip.dtos.TransactionDto;
import com.butce.takip.models.Income;
import com.butce.takip.models.Expense;
import com.butce.takip.repositories.ExpenseRepository;
import com.butce.takip.repositories.IncomeRepository;
import com.butce.takip.services.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

  private final IncomeRepository incomeRepository;
  private final ExpenseRepository expenseRepository;

  @Override
  public MonthlySummaryDto getMonthlyReport(Long userId, String dateStr) {
    // 1. Tarihleri Hesapla (Örn: 01-2025 -> 1 Ocak ve 31 Ocak)
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
    YearMonth yearMonth = YearMonth.parse(dateStr, formatter);
    LocalDate startDate = yearMonth.atDay(1);
    LocalDate endDate = yearMonth.atEndOfMonth();

    List<Income> incomes = incomeRepository.findByUserIdAndDateBetween(userId, startDate, endDate);

    Double totalIncome = incomes.stream()
        .mapToDouble(Income::getAmount)
        .sum();

    List<Expense> expenses = expenseRepository.findByUserIdAndDateBetween(userId, startDate, endDate);
    Double totalExpense = expenses.stream()
        .mapToDouble(Expense::getAmount)
        .sum();

    Double balance = totalIncome - totalExpense;

    return new MonthlySummaryDto(totalIncome, totalExpense, balance);
  }

  @Override
  public MonthlySummaryDto getMonthlyBalance(Long userId, String dateStr) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
    YearMonth yearMonth = YearMonth.parse(dateStr, formatter);

    LocalDate startDate = yearMonth.atDay(1);
    LocalDate endDate = yearMonth.atEndOfMonth();

    List<Income> incomes = incomeRepository.findByUserIdAndDateBetween(userId, startDate, endDate);
    Double totalIncome = incomes.stream()
        .mapToDouble(Income::getAmount)
        .sum();

    List<Expense> expenses = expenseRepository.findByUserIdAndDateBetween(userId, startDate, endDate);
    Double totalExpense = expenses.stream()
        .mapToDouble(Expense::getAmount)
        .sum();

    Double balance = totalIncome - totalExpense;

    return new MonthlySummaryDto(totalIncome, totalExpense, balance);
  }

  @Override
  public List<TransactionDto> getHistory(Long userId) {

    List<Income> incomes = incomeRepository.findByUserId(userId);
    List<TransactionDto> transactions = new ArrayList<>();

    for (Income income : incomes) {
      transactions.add(new TransactionDto(
          "GELİR",
          income.getDescription(),
          income.getAmount(),
          income.getDate()));
    }

    List<Expense> expenses = expenseRepository.findByUserId(userId);
    for (Expense expense : expenses) {
      transactions.add(new TransactionDto(
          "GİDER",
          expense.getDescription(),
          expense.getAmount(),
          expense.getDate()));
    }

    return transactions.stream()
        .sorted(Comparator.comparing(TransactionDto::getDate).reversed())
        .collect(Collectors.toList());
  }

}