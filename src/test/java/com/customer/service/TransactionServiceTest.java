package com.customer.service;

import com.customer.entity.Transaction;
import com.customer.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TransactionServiceTest {
	@Mock
	private TransactionRepository transactionRepository;

	@InjectMocks
	private TransactionService transactionService;

	@BeforeEach
	public void initMocks(){
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void getLast3MonthRewardInformationTest(){

		when(transactionRepository.findLast3MonthCustomerTransactions("praveen")).thenReturn(getMockedTransactionsList());
		Map<String, Double> rewardsMonthwise = transactionService.getLast3MonthRewardInformation("praveen");
		assertEquals(rewardsMonthwise.get("APRIL"), 142);
		assertEquals(rewardsMonthwise.get("MARCH"), 132);
		assertEquals(rewardsMonthwise.get("TOTAL"), 274);
	}

	private List<Transaction> getMockedTransactionsList() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		List<Transaction> transactions = new ArrayList<>();
		try {
			Transaction t1 = new Transaction();
			t1.setCustomerid("praveen");
			t1.setAmount(100);
			t1.setTransactiondate(new Timestamp(dateFormat.parse("2024/04/18").getTime()));

			Transaction t2 = new Transaction();
			t2.setCustomerid("praveen");
			t2.setAmount(121);
			t2.setTransactiondate(new Timestamp(dateFormat.parse("2024/04/16").getTime()));

			Transaction t3 = new Transaction();
			t3.setCustomerid("praveen");
			t3.setAmount(141);
			t3.setTransactiondate(new Timestamp(dateFormat.parse("2024/03/16").getTime()));

			transactions.add(t1);
			transactions.add(t2);
			transactions.add(t3);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

		return transactions;
	}



}
