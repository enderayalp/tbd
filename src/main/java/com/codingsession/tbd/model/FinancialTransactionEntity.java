package com.codingsession.tbd.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Table(name = "transaction")
public class FinancialTransactionEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sender")
    private int sender;

    @Column(name = "receiver")
    private int receiver;

    @Column(name = "amount")
    private int amount;

    @Column(name = "nonce", unique = true)
    private UUID nonce;

}
