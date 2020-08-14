package com.codingsession.tbd.repository;


import com.codingsession.tbd.model.CreditLineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditLineRepository extends JpaRepository<CreditLineEntity, Integer> {
    CreditLineEntity findByAccountnumber(int accountnumber);
}
