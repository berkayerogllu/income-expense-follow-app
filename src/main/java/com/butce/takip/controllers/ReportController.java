package com.butce.takip.controllers;

import com.butce.takip.dtos.MonthlySummaryDto;
import com.butce.takip.services.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

  private final ReportService reportService;

  // Örnek İstek: GET /api/reports/summary/1?date=01-2026
  @GetMapping("/summary/{userId}")
  public ResponseEntity<MonthlySummaryDto> getMonthlySummary(
      @PathVariable Long userId,
      @RequestParam String date) {

    return ResponseEntity.ok(reportService.getMonthlyReport(userId, date));
  }
}