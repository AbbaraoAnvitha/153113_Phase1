package com.capgemini.core.repo;

import java.util.List;

import com.capgemini.core.beans.Customer;
import com.capgemini.core.beans.Transactions;

public interface WalletRepo {

	public boolean save(Customer customer);

	public Customer findOne(String mobileNo);

	public void remove(String mobileNo);
	
}
