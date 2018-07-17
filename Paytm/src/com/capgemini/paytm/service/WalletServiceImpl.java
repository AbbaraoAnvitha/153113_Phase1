package com.capgemini.paytm.service;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import com.capgemini.paytm.beans.Customer;
import com.capgemini.paytm.beans.Wallet;
import com.capgemini.paytm.exception.InsufficientBalanceException;
import com.capgemini.paytm.exception.InvalidInputException;
import com.capgemini.paytm.repo.WalletRepo;
import com.capgemini.paytm.repo.WalletRepoImpl;
public class WalletServiceImpl implements WalletService {
public WalletRepo repo;
	
	public WalletServiceImpl(){
		repo= new WalletRepoImpl();
	}
	public WalletServiceImpl(Map<String, Customer> data){
		repo= new WalletRepoImpl(data);
	}
	public WalletServiceImpl(WalletRepo repo) {
		super();
		this.repo = repo;
	}
	
	WalletRepoImpl obj=new WalletRepoImpl();
	
	public Customer createAccount(String name, String mobileNo, BigDecimal amount) {
		
		if(mobileNo.length()<10) {
			throw new InputMismatchException("InvalidPhone Number");
		}
		Customer cust=new Customer(name,mobileNo,new Wallet(amount));		
		
		boolean result=repo.save(cust);
		if(result==true)
			return cust;
		else
			return null;
		}

	public Customer showBalance(String mobileNo) {
		
		Customer customer=repo.findOne(mobileNo);		
		if(customer!=null)
			return customer;
		else
			throw new InvalidInputException("Invalid mobile no ");
	}

	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) {	
		
		Customer sourceCust=new Customer();
		Customer targetCust=new Customer();
		Wallet sourceWallet=new Wallet();
		Wallet targetWallet=new Wallet();
		sourceCust=repo.findOne(sourceMobileNo);
		targetCust=repo.findOne(targetMobileNo);
		if(sourceCust!=null && targetCust!=null){		
			BigDecimal bal=sourceCust.getWallet().getBalance();
		if(bal.compareTo(amount)>0){
			BigDecimal diff=bal.subtract(amount);
			sourceWallet.setBalance(diff);
			sourceCust.setWallet(sourceWallet);
			BigDecimal baladd=targetCust.getWallet().getBalance();
			BigDecimal sum=baladd.add(amount);			
			targetWallet.setBalance(sum);
			targetCust.setWallet(targetWallet);
			obj.getData().put(targetMobileNo, targetCust);
			obj.getData().put(sourceMobileNo, sourceCust);
		}else{
			System.out.println("Insufficient Balance");
		}
				
		}else{
			throw new InvalidInputException("Account does not exist");
		}	
		return targetCust;
	}

	public Customer depositAmount(String mobileNo, BigDecimal amount) {
		Customer cust=new Customer();
		Wallet wallet=new Wallet();
		cust=repo.findOne(mobileNo);
		if(cust!=null){
			BigDecimal amtAdd=cust.getWallet().getBalance().add(amount);
			wallet.setBalance(amtAdd);
			cust.setWallet(wallet);
			obj.getData().put(mobileNo, cust);
		}
		return cust;
	}

	public Customer withdrawAmount(String mobileNo, BigDecimal amount) {
		Customer cust=new Customer();
		Wallet wallet=new Wallet();
		cust=repo.findOne(mobileNo);
		if(cust!=null){
			BigDecimal bal=cust.getWallet().getBalance();
			BigDecimal amtSub;
			if(bal.compareTo(amount)>0){
				amtSub=bal.subtract(amount);
				wallet.setBalance(amtSub);
				cust.setWallet(wallet);
				obj.getData().put(mobileNo, cust);
			}else{
				System.out.println("Insufficient Balance");
			}
		}
		return cust;
	}
}
