package com.butce.takip.services;

import java.util.List;
import com.butce.takip.models.Income;
import java.util.List;

public interface IncomeService {

  List<Income> getAllIncomes(Long userId);

  Income createIncome(Income income, Long userId);
}
