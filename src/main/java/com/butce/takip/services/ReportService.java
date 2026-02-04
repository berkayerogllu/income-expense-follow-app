package com.butce.takip.services;

import java.util.List;

import com.butce.takip.dtos.MonthlySummaryDto;
import com.butce.takip.dtos.TransactionDto;

public interface ReportService {
  MonthlySummaryDto getMonthlyReport(Long userId, String dateStr);

  MonthlySummaryDto getMonthlyBalance(Long userId, String dateStr);

  List<TransactionDto> getHistory(Long userId);
}