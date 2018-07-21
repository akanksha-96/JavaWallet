package com.capg.bean;


import java.util.List;

public class BankDetails 
{
	private String name;
	private long accNumber;
	private float custBal;
	private String pass;
	private List<String> transaction;
	
	PaymentAppDetails acc;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(long accNumber) {
		this.accNumber = accNumber;
	}

	public float getCustBal() {
		return custBal;
	}

	public void setCustBal(float custBal) {
		this.custBal = custBal;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public List<String> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<String> transaction) {
		this.transaction = transaction;
	}

	public PaymentAppDetails getAcc() {
		return acc;
	}

	public void setAcc(PaymentAppDetails acc) {
		this.acc = acc;
	}
	

	
	
}