package com.capg.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.capg.bean.BankDetails;
import com.capg.bean.PaymentAppDetails;
import com.capg.service.CustomerDataValidation;
import com.capg.service.PaymentAppService;


public class ClientMain {

	public static void main(String[] args) {

		int choice1;
		

		while (true) {
			System.out.println("___________");
			System.out.println("|a.Login   |");
			System.out.println("|b.Signup  |");
			System.out.println("|c.Exit    |");
			System.out.println("|__________|");
			Scanner sc = new Scanner(System.in);
			char choice = sc.next().charAt(0);
			switch (choice) {

			case 'a':
				boolean b = login();
				
				while (b) {
						System.out.println("_____________________________________");
						System.out.println("|Select your choice-                 |");
						System.out.println("|WELCOME TO WALLET APPLICATION!      |");
						System.out.println("|1.Show Balance                      |");
						System.out.println("|2.Deposit balance                   |");
						System.out.println("|3.Withdraw amount                   |");
						System.out.println("|4.Transfer funds to another account |");
						System.out.println("|5.Display account details           |");
						System.out.println("|6.Exit                              |");
						System.out.println("|____________________________________|");
						choice1 = sc.nextInt();
						switch (choice1) {
						case 1:

							showBalance();
							break;
						case 2:
							depositBalance();
							break;
						case 3:
							withdrawAmount();
							break;

						case 4:
							fundTransfer();
							break;

						case 5:
							printTransaction();
							break;

						case 6:
							b=false;
							
						
							
						}
						
					}
				break;
			case 'b':
				createAccount();
				break;
			case 'c':
				System.exit(0);

			}
		}

	}

	public static void printTransaction() {
		PaymentAppService service = new PaymentAppService();
		service.printTransaction();
	/*for (String string : list) {
		System.out.println(string);
	}
	if(list.isEmpty()) {
		System.out.println("No transactions done yet!");
	}*/
	}

	public static void fundTransfer() {
		Scanner s = new Scanner(System.in);
		System.out.println(" Enter Account Number to transfer amount");
		long recieverAccountNum = s.nextLong();
		System.out.println("Enter Amount to Transfer");
		float amount = s.nextFloat();
		PaymentAppService service = new PaymentAppService();
		boolean b = service.fundTransfer(recieverAccountNum, amount);
		if (b) {
			System.out.println("Fund Successfully Transfer");
		} else {
			System.out.println("Enter Correct Input");
		}

	}

	private static void depositBalance() {

		Scanner sc = new Scanner(System.in);
		PaymentAppService service = new PaymentAppService();
		System.out.println("Enter amount to be deposited-");
		float amount = sc.nextFloat();
		boolean isDeposit = service.depositAmount(amount);
		if (isDeposit) {
			System.out.println("Amount Deposited in your account");
		}

	}

	public static void withdrawAmount() {
		Scanner scan = new Scanner(System.in);
		PaymentAppService service = new PaymentAppService();

		System.out.println("Enter amount to withdraw");
		float amount = scan.nextFloat();
		boolean isDeposit = service.withdrawAmount(amount);

		if (isDeposit) {
			System.out.println("Amount Withdraw from your account");
		}

	}

	static boolean b = false;

	public static boolean login() {
		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
		PaymentAppService service = new PaymentAppService();
		try {
			System.out.println("Enter your name: ");
			String name = br1.readLine();
			System.out.println("Enter password: ");
			String pass = br1.readLine();
			b = service.loginAccount(name, pass);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (b) {
			return true;
		}
		return false;

	}

	public static void showBalance() {
		//Scanner scan = new Scanner(System.in);

		PaymentAppService service = new PaymentAppService();

		float balance = service.showBalance();
		System.out.println("Account Balance is: " + balance);
		if(balance<=500 ) {
			System.out.println("Low Balance!");
		}
		
	}

	public static void createAccount() {
		CustomerDataValidation validate = new CustomerDataValidation();
		BankDetails bank = new BankDetails();
		PaymentAppDetails acc = new PaymentAppDetails();
		PaymentAppService service = new PaymentAppService();
		List<String> transaction=new ArrayList<String>();

		long accountNum = (long) (Math.random() * 100000 + 450000);
		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));

		try {

			System.out.println("        Create account!           ");
			System.out.println("Enter your name");
			String name = br1.readLine();
			System.out.println("Enter your age");
			int age = Integer.parseInt(br1.readLine());
			System.out.println("Enter gender(male(m)/female(f)/other)");
			String gender = br1.readLine();
			System.out.println("Enter your phone number");
			String phoneNum = br1.readLine();
			System.out.println("Enter your 12-digit AADHAR number");
			String aadhar = br1.readLine();
			System.out.println("Enter your permanent address");
			String address = br1.readLine();
			System.out.println("Choose your password");
			String pass = br1.readLine();
			System.out.println("Enter opening balance");
			float custInitBal = Float.parseFloat(br1.readLine());

			boolean isValidAadharNo = validate.validateAadharNo(aadhar);
			boolean isValidMobileNo = validate.validateMobileNo(phoneNum);
			boolean isValidCustInitBal = validate.validateCustInitBal(custInitBal);
			boolean isValidGender = validate.validateGender(gender);
			
			if (isValidAadharNo && isValidCustInitBal && isValidMobileNo && isValidGender) {
				bank.setName(name);
				bank.setPass(pass);
				bank.setCustBal(custInitBal);
				bank.setAccNumber(accountNum);
				bank.setTransaction(transaction);
				acc.setAadhar(aadhar);
				acc.setAge(age);
				acc.setGender(gender);
				acc.setAddress(address);
				acc.setPhoneNum(phoneNum);
				
				bank.setAcc(acc);
			
				boolean b = service.addCustomerDetail(bank);
				if (b) {
					System.out.println("Account Created.. Account Number is: " + accountNum);
					System.out.println("Your UserName is: " + name);
					System.out.println("Your Password is :" + pass);
				} else {
					System.out.println("Account Not Created- enter valid details!");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
