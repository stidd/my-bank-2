package com.steventidd.web;

import com.steventidd.web.forms.AccountForm;
import com.steventidd.service.TransactionService;
import com.steventidd.web.forms.LoginForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/account")
    public String login(Model model){

        model.addAttribute("loginForm", new LoginForm());

        return "login.html";
    }

    @PostMapping("/account")
    public String login(@ModelAttribute AccountForm accountForm, Model model) {

        return "redirect:/account/" + accountForm.getReceivingUser();
    }


    @GetMapping("/account/{userId}")
    public String transactions(Model model, @PathVariable String userId){

        buildAccountPageModel(model, userId);
        model.addAttribute("accountForm", new AccountForm());
        return "account.html";

    }

    @PostMapping("/account/{userId}")
    public String addTransaction(@ModelAttribute("accountForm") AccountForm accountForm, BindingResult bindingResult, Model model, @PathVariable("userId") String userId){

        buildAccountPageModel(model, userId);


        if (bindingResult.hasErrors()){
            return "account.html";
        }

        createTransaction(accountForm);


        return "redirect:/account/" + userId;

    }



    private void buildAccountPageModel(Model model, String userId) {
        model.addAttribute("userId", userId);
        model.addAttribute("transactions", transactionService.findAllAccountTransactions(userId));
    }

    private void createTransaction(AccountForm accountForm) {
        transactionService.createTransaction(accountForm.getAmount(), accountForm.getReference(), accountForm.getReceivingUser());
    }
}
