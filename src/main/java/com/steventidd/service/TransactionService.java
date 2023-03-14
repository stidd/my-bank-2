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

    public List<Transaction> findAllAccountTransactions(String userId) {
        List<Transaction> accountTransactions = new CopyOnWriteArrayList<>();

        for(Transaction transaction : transactions){
            if (transaction.getReceivingUser().equals(userId)) {
                accountTransactions.add(transaction);
            }
        }

        return accountTransactions;

    }


    public Transaction createTransaction(Integer amount, String reference, String receivingUser){

        ZonedDateTime timestamp = ZonedDateTime.now();

        Transaction transaction = new Transaction(amount, timestamp, reference, receivingUser);

        transactions.add(transaction);

        return transaction;
    }



}
