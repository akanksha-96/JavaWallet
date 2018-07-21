package com.capg.service;

import java.util.List;
import com.capg.bean.BankDetails;

public interface IPaymentAppService {

	public float showBalance();
	public boolean addCustomerDetail(BankDetails accountNum);
	public boolean depositAmount(float amount);
	public boolean withdrawAmount(float amount);
	public boolean fundTransfer(long recieverAccountNum, float amount);
	public void printTransaction();
}
