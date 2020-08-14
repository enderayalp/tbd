package com.codingsession.tbd.converter;

import com.codingsession.tbd.api.model.Amount;
import com.codingsession.tbd.api.model.FinancialTransaction;
import com.codingsession.tbd.model.FinancialTransactionEntity;
import org.springframework.stereotype.Component;

@Component
public class FinancialTransactionConverter {

    public FinancialTransaction convertToVO(FinancialTransactionEntity entity) {
        FinancialTransaction vo = new FinancialTransaction();
        vo.setSender(entity.getSender());
        vo.setReceiver(entity.getReceiver());
        Amount amount = new Amount();
        amount.setValue(entity.getAmount());
        //TODO aus Datenbank holen
        amount.setCurrency(Amount.CurrencyEnum.EUR);
        vo.setAmount(amount);
        vo.setNonce(entity.getNonce());

        return vo;
    }

    public FinancialTransactionEntity convertToEntity(FinancialTransaction vo) {
        FinancialTransactionEntity entity = new FinancialTransactionEntity();
        entity.setSender(vo.getSender());
        entity.setReceiver(vo.getReceiver());
        entity.setAmount(vo.getAmount().getValue());
        entity.setNonce(vo.getNonce());

        return entity;
    }
}
