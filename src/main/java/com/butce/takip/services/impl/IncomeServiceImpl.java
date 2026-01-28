package com.butce.takip.services.impl;

import com.butce.takip.models.Income;
import com.butce.takip.models.User;
import com.butce.takip.repositories.ExpenseRepository;
import com.butce.takip.repositories.IncomeRepository;
import com.butce.takip.repositories.UserRepository;
import com.butce.takip.services.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {
  private final IncomeRepository incomeRepository;
  private final UserRepository userRepository;

  @Override
  public List<Income> getAllIncomes(Long userId) {
    return incomeRepository.findByUserId(userId);
  }

  @Override
  public Income createIncome(Income income, Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı..."));
    income.setUser(user);

    return incomeRepository.save(income);
  }

  @Override
  public void deleteIncome(Long id) {
    incomeRepository.deleteById(id);
  }

  @Override
  public Income updateIncome(Long id, Income incomeDetails) {

    Income existingIncome = incomeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Gelir bulunamadı:" + id));

    existingIncome.setDescription(incomeDetails.getDescription());
    existingIncome.setAmount(incomeDetails.getAmount());
    existingIncome.setDate(incomeDetails.getDate());

    return incomeRepository.save(existingIncome);
  }

  @Override
  public List<Income> getIncomesByMonth(Long userId, String dateStr) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
    YearMonth yearMonth = YearMonth.parse(dateStr, formatter);

    LocalDate startDate = yearMonth.atDay(1);
    LocalDate endDate = yearMonth.atEndOfMonth();

    return incomeRepository.findByUserIdAndDateBetween(userId, startDate, endDate);
  }

}
