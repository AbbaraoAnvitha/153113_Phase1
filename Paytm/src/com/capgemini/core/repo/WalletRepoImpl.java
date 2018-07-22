package com.capgemini.core.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.capgemini.core.beans.Customer;
import com.capgemini.core.beans.Transactions;

public class WalletRepoImpl implements WalletRepo{

	private Map<String, Customer> data; 
	
	public WalletRepoImpl() 
	{
		data = new HashMap<String, Customer>();
	}

	public boolean save(Customer customer) 
	{
		if(data.get(customer.getMobileNo()) == null) {
			data.put(customer.getMobileNo(), customer);
			return true;
		}
		return false;
	}
	
	@Override
	public void remove(String mobileNo) {
		data.remove(mobileNo);
	}
	
	public Customer findOne(String mobileNo) {
		if(data.get(mobileNo) != null) {
			return data.get(mobileNo);
		}
		return null;
	}

	
}
