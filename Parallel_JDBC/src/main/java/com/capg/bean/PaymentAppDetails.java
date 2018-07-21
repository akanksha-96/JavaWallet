package com.capg.bean;


public class PaymentAppDetails {
	
	//private String valid;
	private String gender;
	private int age;
	private String address;
	private String phoneNum;
	private String aadhar;
	
	public String getAadhar() {
		return aadhar;
	}
	
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public String getPhoneNum() {
		return phoneNum;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	



	@Override
	public String toString() {
		return "PaymentAppDetails [ gender=" + gender + ", age=" + age + ", address=" + address
				+ ", phoneNum=" + phoneNum + ", aadhar=" + aadhar +  "]";
	}

	


	


	
	

}
