package com.codingsession.tbd.controller;


import com.codingsession.tbd.api.model.FinancialTransaction;
import com.codingsession.tbd.api.server.SendMoneyApi;
import com.codingsession.tbd.exception.NotEnoughCreditException;
import com.codingsession.tbd.service.FinancialTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class FinancialTransactionController implements SendMoneyApi {
    @Override
    public ResponseEntity<Void> transferMoney(FinancialTransaction financialTransaction) {
        try {
            financialTransactionService.sendMoney(financialTransaction);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (NotEnoughCreditException e) {
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private final FinancialTransactionService financialTransactionService;


}
