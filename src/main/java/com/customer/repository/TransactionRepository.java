package com.customer.repository;

import com.customer.entity.Transaction;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface  TransactionRepository extends  JpaRepository<Transaction, String> {

    @Query(value = "select * from transaction  where customerid = ?1 and transactiondate >= CURRENT_DATE - INTERVAL '3 months'", nativeQuery = true)
    List<Transaction> findLast3MonthCustomerTransactions(String customerid);
}
