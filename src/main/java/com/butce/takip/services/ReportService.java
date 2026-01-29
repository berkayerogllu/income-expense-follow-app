package com.butce.takip.services;

import com.butce.takip.dtos.MonthlySummaryDto;

public interface ReportService {
  MonthlySummaryDto getMonthlyReport(Long userId, String dateStr);
}