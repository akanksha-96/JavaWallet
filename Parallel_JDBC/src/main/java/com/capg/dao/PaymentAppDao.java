package com.capg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import com.capg.bean.BankDetails;
import com.capg.util.UtilityClass;

public class PaymentAppDao implements IPaymentAppDao {

	public static HashMap<String, BankDetails> map = new HashMap<String, BankDetails>();
	// public static BankDetails bank1;
	static String aadhar;

	long transactionId = (long) ((Math.random() * 123) + 999);

	public boolean addCustomerDetail(BankDetails bank) {

		Connection conn = UtilityClass.getConnection();
		try {

			String insertQuery1 = "insert into BankDetails values(?,?,?,?,?)";
			PreparedStatement preStat1 = conn.prepareStatement(insertQuery1);
			preStat1.setString(1, bank.getName());
			preStat1.setLong(2, bank.getAccNumber());
			preStat1.setFloat(3, bank.getCustBal());
			preStat1.setString(4, bank.getPass());
			preStat1.setString(5, bank.getAcc().getAadhar());

			String insertQuery2 = "insert into PaymentAppDetails values(?,?,?,?,?)";
			PreparedStatement preStat2 = conn.prepareStatement(insertQuery2);
			preStat2.setString(1, bank.getAcc().getGender());
			preStat2.setInt(2, bank.getAcc().getAge());
			preStat2.setString(3, bank.getAcc().getAddress());
			preStat2.setString(4, bank.getAcc().getPhoneNum());
			preStat2.setString(5, bank.getAcc().getAadhar());
			preStat2.executeUpdate();
			preStat1.executeUpdate();
			// bank1 = bank;
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

		/*
		 * boolean b= true; if (b) { map.put(account.getName(), account); b=true; } else
		 * { System.out.println("Account not created!"); b=false; }return b;
		 */
	}

	public float showBalance() {
		Connection conn = UtilityClass.getConnection();
		float f = 1.1f;
		try {
			String disQuery = "select * from BankDetails WHERE aadhar=?";
			PreparedStatement preStat = conn.prepareStatement(disQuery);
			preStat.setString(1, aadhar);
			ResultSet rs = preStat.executeQuery();
			while (rs.next()) {
				// System.out.println(bank1.getCustBal());
				f = rs.getFloat("custBal");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return f;
	}

	public boolean depositAmount(float amount) {
		Connection conn = UtilityClass.getConnection();

		try {
			String depQuery = "update bankdetails set custBal= custBal+'"+amount+"' where aadhar= ?";
			PreparedStatement preStat = conn.prepareStatement(depQuery);
			preStat.setString(1, aadhar);
			preStat.executeUpdate();
			
			String transaction="Deposited :"+amount;
			String trans="insert into transactiondetails value("+aadhar+",'"+transaction+"')";
			PreparedStatement preStat1 = conn.prepareStatement(trans);
			preStat1.executeUpdate();
		} 
		catch (SQLException e) {

			e.printStackTrace();
		}

		return true;

		/*
		 * BankDetails bank1 = new BankDetails(); bank1.setCustBal(bank1.getCustBal() +
		 * amount); String dep = transactionId + "    Amount deposited is:   " + amount;
		 * bank1.getTransaction().add(dep);
		 */

	}

	public boolean withdrawAmount(float amount) {
		Connection conn = UtilityClass.getConnection();
		try {
			String withQuery = "update bankdetails set custBal= custBal-'"+amount+"' where aadhar= ?";
			PreparedStatement preStat = conn.prepareStatement(withQuery);
			preStat.setString(1, aadhar);
			preStat.executeUpdate();
			String transaction="Withdrawn :"+amount;
			String trans="insert into transactiondetails value("+aadhar+",'"+transaction+"')";
			PreparedStatement preStat1 = conn.prepareStatement(trans);
			preStat1.executeUpdate();
		} 
		catch (SQLException e) {

			e.printStackTrace();
		}
		return true;
		/*BankDetails bank1 = new BankDetails();
		if (bank1.getCustBal() >= amount) {
			bank1.setCustBal(bank1.getCustBal() - amount);
			String with = transactionId + "    Amount withdrawn is:   " + amount;
			bank1.getTransaction().add(with);
			return true;
		} else {
			System.out.println("Insufficient funds");
		}*/
		

	}

	public boolean fundTransfer(long recieverAccountNum, float amount) {
		Connection conn = UtilityClass.getConnection();
		
		try {
			String transferQuery = "update bankdetails set custBal= custBal-? where aadhar=? ";
			String transferQuery1="update bankdetails set custBal = custBal+'"+amount+"' where accNumber=? ";
			PreparedStatement preStat = conn.prepareStatement(transferQuery);
			PreparedStatement preStat1 = conn.prepareStatement(transferQuery1);
			preStat.setFloat(1, amount);
			preStat.setString(2, aadhar);
			preStat1.setLong(1, recieverAccountNum);
			preStat.executeUpdate();
			preStat1.executeUpdate();
		
			
			String transaction="Transferred :"+amount+" to "+recieverAccountNum;
			String trans="insert into transactiondetails value("+aadhar+",'"+transaction+"')";
			PreparedStatement preStat2 = conn.prepareStatement(trans);
			preStat2.executeUpdate();
			

			String transaction1="Recieved :"+amount;
			String trans1="insert into transactiondetails value("+aadhar+",'"+transaction1+"')";
			PreparedStatement preStat3 = conn.prepareStatement(trans1);
			preStat3.executeUpdate();
			return true;
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		/*BankDetails bank1 = new BankDetails();
		for (String key : map.keySet()) {
			BankDetails recieverAccount = map.get(key);
			if (recieverAccount.getAccNumber() == recieverAccountNum) {
				recieverAccount.setCustBal(recieverAccount.getCustBal() + amount);
				bank1.setCustBal(bank1.getCustBal() - amount);
				String transfer = transactionId + "  Amount  is transferred:   " + amount;
				bank1.getTransaction().add(transfer);
				return true;
			}

		}*/
		return false;
	}

	public boolean loginAccount(String name, String pass) {

		Connection conn = UtilityClass.getConnection();
		try {
			Statement stat = conn.createStatement();
			String select = "Select bankdetails.name, bankdetails.pass, paymentappdetails.aadhar from bankdetails, paymentappdetails where paymentappdetails.aadhar=bankdetails.aadhar ";
			ResultSet rs = stat.executeQuery(select);
			while (rs.next()) {
				String uname = rs.getString(1);
				String upass = rs.getString(2);
				//String uaadhar = rs.getString(3);
				if (uname.equals(name) && upass.equals(pass)) {
					aadhar = rs.getString("aadhar");
					return true;
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;
		/*
		 * for (String key : map.keySet()) { bank = map.get(key); if
		 * (bank.getName().equals(name) && bank.getPass().equals(pass)) { return true; }
		 * else { System.out.println("Enter valid name or password!!"); } } return
		 * false;
		 */
	}

	public void printTransaction() {
		Connection conn = UtilityClass.getConnection();
		
		try {
			String transactions = "Select * from transactiondetails where aadhar=?";
			PreparedStatement preStat = conn.prepareStatement(transactions);
			preStat.setString(1, aadhar);
			
			ResultSet rs= preStat.executeQuery();
			while(rs.next()) {
			System.out.println(rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
