package com.butce.takip.repositories;

import com.butce.takip.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
  List<Expense> findByUserId(Long userId);
}