package me.chan.account.test;

import java.util.concurrent.TimeUnit;

public class Account {

	private double balance;
	
	public Account(double balance) {
		this.balance = balance;
	}
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}



	public synchronized void addAmount(double amount) {
		double tmp = balance;
		try {
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		tmp += amount;
		balance = tmp;
		
	}
	
	public synchronized void subAmount(double amount) {
		double tmp = balance;
		try {
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		tmp -= amount;
		balance = tmp;
	}
	
}
