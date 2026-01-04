package com.butce.takip.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "expenses")
public class Expense {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;

  private Double amount;

  private LocalDate date;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

}
