package com.steventidd.web;

import com.steventidd.model.Transaction;
import com.steventidd.service.TransactionService;
import com.steventidd.dto.TransactionDTO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }


    @GetMapping("/transactions")
    public List<Transaction> transactions(){
        return transactionService.findAllTransactions();
    }

    @PostMapping("/transactions")
    public Transaction createTransaction(@RequestBody @Valid TransactionDTO transactionDTO){
        return transactionService.createTransaction(transactionDTO.getAmount(), transactionDTO.getReference(), transactionDTO.getReceivingUser());
    }



}
