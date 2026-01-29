package com.butce.takip.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonthlySummaryDto {
  private Double totalIncome;
  private Double totalExpense;
  private Double balance;
}