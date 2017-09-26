package com.example.myapexapp;

import java.util.ArrayList;
import java.util.List;

public class RecommendationInput {
	
	public int uid;
	public String uname;
	public List<String> recommendedCategories = new ArrayList<String>();
	public int itemid;
	public String category;
	public int price;
	
	public List<Product> recommendedProducts = new ArrayList<Product>();

	public RecommendationInput() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecommendationInput(int uid, String uname, List<String> recommendedCategories, int itemid, String category,
			int price, List<Product> recommendedProducts) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.recommendedCategories = recommendedCategories;
		this.itemid = itemid;
		this.category = category;
		this.price = price;
		this.recommendedProducts = recommendedProducts;
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

	public List<String> getRecommendedCategories() {
		return recommendedCategories;
	}

	public void setRecommendedCategories(List<String> recommendedCategories) {
		this.recommendedCategories = recommendedCategories;
	}

	public int getItemid() {
		return itemid;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<Product> getRecommendedProducts() {
		return recommendedProducts;
	}

	public void setRecommendedProducts(List<Product> recommendedProducts) {
		this.recommendedProducts = recommendedProducts;
	}

	

	
	
	

}
