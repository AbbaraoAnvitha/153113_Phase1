package com.capgemini.paytm.junittest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.paytm.beans.Customer;
import com.capgemini.paytm.beans.Wallet;
import com.capgemini.paytm.exception.InvalidInputException;
import com.capgemini.paytm.service.WalletService;
import com.capgemini.paytm.service.WalletServiceImpl;

import static org.junit.Assert.*;
public class TestCases {
	
	private static WalletService walletservice; 
	Customer cust1, cust2,cust3;	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		walletservice = new WalletServiceImpl();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		walletservice = null;
		
	}
	
	@Before
	public void initData(){
		Map<String,Customer> data= new HashMap<String, Customer>();
		Customer cust1=new Customer("Amit", "9900112212",new Wallet(new BigDecimal(9000)));
		Customer cust2=new Customer("Ajay", "9963242422",new Wallet(new BigDecimal(6000)));
		Customer cust3=new Customer("Yogini", "9922950519",new Wallet(new BigDecimal(7000)));
		data.put("9900112212", cust1);
		data.put("9963242422", cust2);	
		data.put("9922950519", cust3);	
		walletservice= new WalletServiceImpl(data);
				
	}
	@Test(expected=NullPointerException.class)
	public void testCreateAccount0() {
		walletservice.createAccount(null, null, null);
	}
	    
	@Test
	public void testCreateAccount() {
		String name = "Anvitha";
		String mobileno = "9177640926";
		BigDecimal amount = new BigDecimal(12000);
		walletservice.createAccount(name, mobileno, amount);
	}
		
	@Test
	public void testCreateAccount1() {
		Customer customer1=new Customer();
		Customer customer2=new Customer();
		customer2=walletservice.createAccount("Anvitha","9177640926",new BigDecimal(10000));
		customer1.setName("Anvitha");
		customer1.setMobileNo("9177640926");
		customer1.setWallet(new Wallet(new BigDecimal(10000)));
		Customer actual =customer1;
		Customer expected=customer2;
		assertEquals(expected, actual);
	}
	@Test	
	public void testCreateAccount2() {
		Customer cust=new Customer();
		cust=walletservice.createAccount("Anvitha","9177640926",new BigDecimal(7000));
		assertEquals("Anvitha", cust.getName());
	}
	@Test
	public void testCreateAccount3() {
		Customer customer=new Customer();
		customer=walletservice.createAccount("Anvitha","9177640926",new BigDecimal(7000));
		assertEquals("9177640926", customer.getMobileNo());
	}
	@Test
	public void testCreateAccount4() {
		Customer cust=new Customer();
		cust=walletservice.createAccount("Anvitha","9177640926",new BigDecimal(7000));
		BigDecimal expected = new BigDecimal(7000);
		BigDecimal actual = cust.getWallet().getBalance();
		assertEquals(expected, actual);
	}
	@Test(expected=InvalidInputException.class)
	public void testShowBalance1() {
		Customer cust=new Customer();
		cust=walletservice.showBalance("9579405744");
	}
    @Test
	public void testShowBalance2() {
		Customer cust=new Customer();
		cust=walletservice.showBalance("9922950519");
		BigDecimal amount = new BigDecimal(7000);
		assertEquals(cust.getWallet().getBalance(), amount);
	}
    @Test
    public void testShowBalance3() {
    	Customer cust=new Customer();
    	cust=walletservice.showBalance("9900112212");
    	BigDecimal actual=cust.getWallet().getBalance();
    	BigDecimal expected=new BigDecimal(9000);
    	assertEquals(expected, actual);

    }

	@Test(expected=InvalidInputException.class)
	public void testFundTransfer1() {
		walletservice.fundTransfer(null, null,new BigDecimal(7000));
	}
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer2() {
		walletservice.fundTransfer(null, "9177640926",new BigDecimal(7000));
	}
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer3() {
		walletservice.fundTransfer("9177640926", null ,new BigDecimal(7000));
	}

	@Test(expected=InvalidInputException.class)
	public void testFundTransfer4() {
		walletservice.fundTransfer(null, null ,null);
	}
	@Test
	public void testFundTransfer5() {
		cust1=walletservice.fundTransfer("9900112212","9963242422",new BigDecimal(2000));
		BigDecimal actual=cust1.getWallet().getBalance();
		BigDecimal expected=new BigDecimal(8000);
		assertEquals(expected, actual);
	}
	@Test(expected=InvalidInputException.class)
	public void testDeposit1()
	{
		walletservice.depositAmount("900000000", new BigDecimal(2000));
	}
		
	@Test
	public void testDeposit2()
	{
		cust1=walletservice.depositAmount("9963242422", new BigDecimal(2000));
		BigDecimal actual=cust1.getWallet().getBalance();
		BigDecimal expected=new BigDecimal(8000);
		assertEquals(expected, actual);
	}
	
	@Test(expected=InvalidInputException.class)
	public void testDeposit3()
	{
		walletservice.depositAmount(null, new BigDecimal(2000));
	}
	@Test(expected=InvalidInputException.class)
	public void testDeposit4()
	{
		walletservice.depositAmount(null, null);
	}
	
	@Test(expected=InvalidInputException.class)
	public void testWithdraw1()
	{
		walletservice.withdrawAmount("900000000", new BigDecimal(2000));
	}
		
	@Test
	public void testWithdraw2()
	{
		cust1=walletservice.withdrawAmount("9963242422", new BigDecimal(2000));
		BigDecimal actual=cust1.getWallet().getBalance();
		BigDecimal expected=new BigDecimal(4000);
		assertEquals(expected, actual);
	}	
	@Test(expected=InvalidInputException.class)
	public void testWithdraw3()
	{
		walletservice.withdrawAmount(null, new BigDecimal(2000));
	}
	@Test(expected=InvalidInputException.class)
	public void testWithdraw4()
	{
		walletservice.withdrawAmount(null, null);
	}
	
    @After
	public void testAfter()
	{
		walletservice=null;
	}

}
