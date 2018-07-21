package com.capg.service;
import java.util.List;
import com.capg.bean.BankDetails;
import com.capg.dao.PaymentAppDao;

public class PaymentAppService implements IPaymentAppService
{
	PaymentAppDao dao= new PaymentAppDao();
	

	public float showBalance() 
	{
		return  dao.showBalance();
	}
	

	public boolean depositAmount(float amount)
	{
		return dao.depositAmount(amount);
	}

	
	public boolean withdrawAmount(float amount)
	{
		return dao.withdrawAmount(amount);
	}

	
	public boolean addCustomerDetail(BankDetails account) 
	{
		return dao.addCustomerDetail(account);
	}


	public boolean fundTransfer(long recieverAccountNum, float amount) {
		
		return dao.fundTransfer(recieverAccountNum, amount);
	}


	public boolean loginAccount(String name, String pass) {
		
		return dao.loginAccount(name, pass);
	}


	public void printTransaction() {
		 dao.printTransaction();
		
	}



	
	

}
