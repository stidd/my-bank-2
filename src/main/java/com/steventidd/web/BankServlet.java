package com.steventidd.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.steventidd.context.Application;
import com.steventidd.model.Transaction;
import com.steventidd.service.TransactionService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;

public class BankServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getRequestURI().equals("/")) {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().print(
                    "<html><h1>My Banking App</h1></html>"
            );
        }else if(request.getRequestURI().equals("/transactions")){

            List<Transaction> transactionList = Application.transactionService.findAllTransactions();

            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().print(Application.objectMapper.writeValueAsString(transactionList));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getRequestURI().equalsIgnoreCase("/transactions")) {

           Integer amount = Integer.valueOf(request.getParameter("amount"));
           String reference = request.getParameter("reference");


           Transaction transaction = Application.transactionService.createTransaction(amount, reference);

            response.setContentType("application/json; charset=UTF-8");
            String json = Application.objectMapper.writeValueAsString(transaction);
            response.getWriter().print(json);

        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}
