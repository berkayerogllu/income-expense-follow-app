package com.butce.takip.repositories;

import org.springframework.stereotype.Repository;
import com.butce.takip.models.Income;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
  List<Income> findByUserId(Long userId);

  List<Income> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);
}
