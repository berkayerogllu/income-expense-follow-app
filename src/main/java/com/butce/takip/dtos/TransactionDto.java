package com.butce.takip.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TransactionDto {
  private String type;
  private String description;
  private Double amount;
  private LocalDate date;
}