package com.butce.takip.services.impl;

import com.butce.takip.models.Income;
import com.butce.takip.models.User;
import com.butce.takip.repositories.IncomeRepository;
import com.butce.takip.repositories.UserRepository;
import com.butce.takip.services.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

}
