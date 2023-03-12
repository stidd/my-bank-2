package com.steventidd.service;

import com.steventidd.model.Transaction;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class TransactionService {

    private List<Transaction> transactions = new CopyOnWriteArrayList<>();

    public TransactionService() {

    }

    public List<Transaction> findAllTransactions() {
        return transactions;
    }

    public Transaction createTransaction(Integer amount, String reference){

        ZonedDateTime timestamp = ZonedDateTime.now();

        Transaction transaction = new Transaction(amount, timestamp, reference);

        transactions.add(transaction);

        return transaction;
    }



}
