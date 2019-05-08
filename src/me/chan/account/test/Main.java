package me.chan.account.test;

public class Main {

	public static void main(String[] args) {
		Account account = new Account(1000);
		Thread company = new Thread(new Company(account));
		Thread bank = new Thread(new Bank(account));
		System.out.println("账户初始余额为：" + account.getBalance()+ "元");
		company.start();
		bank.start();
		
		
		try {
			company.join();
			bank.join();
			System.out.println("账户最终余额为：" + account.getBalance()+ "元");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
