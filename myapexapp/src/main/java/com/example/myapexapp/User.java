package com.example.myapexapp;

public class User {

	public int uid;
	public String uname;
	public int amount;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int uid, String uname, int amount) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.amount = amount;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", amount=" + amount + "]";
	}
	
	
	
	
	
	

	
	
	
}
