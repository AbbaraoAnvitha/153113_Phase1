package com.capgemini.paytm.service;

import java.math.BigDecimal;

import com.capgemini.paytm.beans.Customer;
import com.capgemini.paytm.exception.InsufficientBalanceException;
import com.capgemini.paytm.exception.InvalidInputException;


public interface WalletService {
	
	public Customer createAccount(String name ,String mobileno, BigDecimal amount)  throws InvalidInputException, InsufficientBalanceException;
	public Customer showBalance (String mobileno)  throws InvalidInputException, InsufficientBalanceException;
	public Customer fundTransfer (String sourceMobileNo,String targetMobileNo, BigDecimal amount)  throws InvalidInputException, InsufficientBalanceException;
	public Customer depositAmount (String mobileNo,BigDecimal amount )  throws InvalidInputException, InsufficientBalanceException;
	public Customer withdrawAmount(String mobileNo, BigDecimal amount)  throws InvalidInputException, InsufficientBalanceException;
}
