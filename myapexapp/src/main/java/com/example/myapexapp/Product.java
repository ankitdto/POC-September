package com.example.myapexapp;

public class Product {
	
	public int itemid;
	public String itemName;
	public int price;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(int itemid, String itemName, int price) {
		super();
		this.itemid = itemid;
		this.itemName = itemName;
		this.price = price;
	}

	public int getItemid() {
		return itemid;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	

	
	
	
}
