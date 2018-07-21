package com.capg.dao;

import java.util.List;
import com.capg.bean.BankDetails;

public interface IPaymentAppDao {
public boolean addCustomerDetail(BankDetails accountNum) ;
public float showBalance();
public boolean withdrawAmount(float amount);
public boolean fundTransfer(long recieverAccountNum, float amount);
public boolean loginAccount(String name, String pass);
public void  printTransaction();
}