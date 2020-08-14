package com.codingsession.tbd.repository;


import com.codingsession.tbd.model.FinancialTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinancialTransactionRepository extends JpaRepository<FinancialTransactionEntity, Integer> {
    List<FinancialTransactionEntity> findAllBySender(int accountnumber);
}
