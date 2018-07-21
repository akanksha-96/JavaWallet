package com.capg.service;



public class CustomerDataValidation {

	public boolean validateAadharNo(String aadhar)
	{
		if(aadhar.length()==12)
		{
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
	public boolean validateMobileNo(String phoneNum)
	{
		if(phoneNum.length()==10)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean validateCustInitBal(float custInitBal)
	{
		if(custInitBal >=500)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean validateGender(String gender)
	{
		if(gender.equals("female")||gender.equals("male")||gender.equals("f")||gender.equals("m")||gender.equals("other"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
