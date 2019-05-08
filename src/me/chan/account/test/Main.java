package me.chan.account.test;

public class Main {

	public static void main(String[] args) {
		Account account = new Account(1000);
		Thread company = new Thread(new Company(account));
		Thread bank = new Thread(new Bank(account));
		System.out.println("�˻���ʼ���Ϊ��" + account.getBalance()+ "Ԫ");
		company.start();
		bank.start();
		
		
		try {
			company.join();
			bank.join();
			System.out.println("�˻��������Ϊ��" + account.getBalance()+ "Ԫ");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
