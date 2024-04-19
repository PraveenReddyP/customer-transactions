package com.customer.service;

import com.customer.entity.Transaction;
import com.customer.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Month;
import java.util.*;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction addTransaction(Transaction transaction) throws Exception {
        Optional<Transaction> transOptional  = transactionRepository.findById(transaction.getTransactionid());
        if(!transOptional.isPresent()) {
            return transactionRepository.save(transaction);
        }
        else{
            throw new Exception("TransactionId already exists");
        }
    }
    public Transaction updateTransaction(String transactionid, Transaction transaction) throws Exception {

        Optional<Transaction> transOptional  = transactionRepository.findById(transactionid);
        if(transOptional.isPresent()){
            Transaction trans = transOptional.get();
            trans.setAmount(transaction.getAmount());
            trans.setCustomerid(transaction.getCustomerid());
            trans.setTransactiondate(transaction.getTransactiondate());
            return transactionRepository.save(trans);
        }
        else {
            throw new Exception("transaction not found by transactionid");
        }
    }

    public Map<String, Double> getLast3MonthRewardInformation(String customerId) {

        List<Transaction>  transactionList = transactionRepository.findLast3MonthCustomerTransactions(customerId);
        Map<Integer, List<Transaction>> transactionsMonthWise = partitionByMonth(transactionList);
        Map<String, Double> rewardsByMonth = new HashMap<>();
        double total = 0;
        for(int month:transactionsMonthWise.keySet()){
            double rewards = 0;
            List<Transaction> transactions = transactionsMonthWise.get(month);
            for(Transaction t:transactions){
                double amount = t.getAmount();
                if(amount>100){
                    rewards += (amount-100)*2+50;
                }
                else if(amount>50){
                    rewards += amount-50;
                }
            }
            rewardsByMonth.put(Month.of(month).toString(), rewards);
            total +=rewards;
        }
        rewardsByMonth.put("TOTAL", total);
        return rewardsByMonth;
    }

    private Map<Integer, List<Transaction>> partitionByMonth(List<Transaction> tranasactions) {
        Map<Integer, List<Transaction>> partitionedMap = new HashMap<>();

        for (Transaction trans : tranasactions) {
            Timestamp timestamp = trans.getTransactiondate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(timestamp);
            int month = calendar.get(Calendar.MONTH) + 1;
            partitionedMap.computeIfAbsent(month, m -> new ArrayList<>()).add(trans);
        }
        return partitionedMap;
    }


}
