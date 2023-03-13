package com.steventidd.web;

import com.steventidd.model.Transaction;
import com.steventidd.service.TransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class WebsiteController {
    private final TransactionService transactionService;
    private final String bankSlogan;

    public WebsiteController(TransactionService transactionService, @Value("${bank.slogan}") String bankSlogan) {
        this.transactionService = transactionService;
        this.bankSlogan = bankSlogan;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("slogan", bankSlogan);
        return "index.html";
    }


    @GetMapping("/account/{userId}")
    public String transactions(Model model, @PathVariable String userId){
        model.addAttribute("userId", userId);
        model.addAttribute("transactions", transactionService.findAllAccountTransactions(userId));

        return "account.html";

    }

}
