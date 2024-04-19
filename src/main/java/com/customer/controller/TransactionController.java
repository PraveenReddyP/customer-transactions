package com.customer.controller;

import com.customer.entity.Transaction;
import com.customer.service.TransactionService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/addTransaction")
    public ResponseEntity<?> addTransaction(@RequestBody Transaction transaction){
        Transaction trans = null;
        try {
            trans = transactionService.addTransaction(transaction);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
        }
        return ResponseEntity.ok(trans);
    }
    @PostMapping("/updateTransaction/{transactionId}")
    public ResponseEntity<?> updateTransaction(@PathVariable String transactionId, @RequestBody Transaction transaction){
        Transaction trans;
        try {
            trans = transactionService.updateTransaction(transactionId, transaction);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
        }
        return ResponseEntity.ok(trans);
    }

    @GetMapping ("/getrewards/{customerid}")
    public ResponseEntity<?> updateTransaction(@PathVariable String customerid){
        Map<String, Double> rewardsByMonth =  transactionService.getLast3MonthRewardInformation(customerid);
        return  ResponseEntity.ok(rewardsByMonth);

    }


}
