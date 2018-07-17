package com.capgemini.paytm.repo;

import com.capgemini.paytm.beans.Customer;
import com.capgemini.paytm.exception.InsufficientBalanceException;
import com.capgemini.paytm.exception.InvalidInputException;


public interface WalletRepo {

public boolean save(Customer customer) throws InvalidInputException, InsufficientBalanceException;
	
	public Customer findOne(String mobileNo) throws InvalidInputException, InsufficientBalanceException;
	
}
