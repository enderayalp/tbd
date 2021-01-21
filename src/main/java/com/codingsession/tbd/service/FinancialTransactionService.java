package com.codingsession.tbd.service;


import com.codingsession.tbd.api.model.FinancialTransaction;
import com.codingsession.tbd.converter.FinancialTransactionConverter;
import com.codingsession.tbd.exception.DuplicateTransactionException;
import com.codingsession.tbd.exception.NotEnoughCreditException;
import com.codingsession.tbd.model.CreditLineEntity;
import com.codingsession.tbd.model.FinancialTransactionEntity;
import com.codingsession.tbd.repository.CreditLineRepository;
import com.codingsession.tbd.repository.FinancialTransactionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class FinancialTransactionService {
    static final int ZERO = 0;
    FinancialTransactionRepository financialTransactionRepository;
    CreditLineRepository creditLineRepository;
    FinancialTransactionConverter financialTransactionConverter;

    public FinancialTransaction sendMoney(FinancialTransaction financialTransaction) throws NotEnoughCreditException, DuplicateTransactionException {
        checkCredit(financialTransaction);
        FinancialTransactionEntity entity = financialTransactionConverter.convertToEntity(financialTransaction);
        try {
            return financialTransactionConverter.convertToVO(financialTransactionRepository.save(entity));
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateTransactionException();
        }
    }

    private void checkCredit(FinancialTransaction financialTransaction) throws NotEnoughCreditException {
        Optional<CreditLineEntity> creditLineEntity = Optional.ofNullable(creditLineRepository.findByAccountnumber(financialTransaction.getSender()));
        List<FinancialTransactionEntity> transactions = financialTransactionRepository.findAllBySender(financialTransaction.getSender());

        int creditline = ZERO;
        if (creditLineEntity.isPresent()) {
            creditline = creditLineEntity.get().getCreditLine();
        }

        int credit = calculateCreditByTransacations(transactions) + creditline;
        if (financialTransaction.getAmount().getValue() > credit) {
            throw new NotEnoughCreditException();
        }
    }

    private int calculateCreditByTransacations(List<FinancialTransactionEntity> transactions) {
        return transactions.stream()
                .map(FinancialTransactionEntity::getAmount)
                .mapToInt(Integer::intValue)
                .sum();
    }


}
