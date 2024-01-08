package com.testing.models.carts;

public class Product {
	private double discountPercentage;
	private int total;
	private String thumbnail;
	private int quantity;
	private int discountedPrice;
	private int price;
	private int id;
	private String title;

	public void setDiscountPercentage(double discountPercentage){
		this.discountPercentage = discountPercentage;
	}

	public double getDiscountPercentage(){
		return discountPercentage;
	}

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setThumbnail(String thumbnail){
		this.thumbnail = thumbnail;
	}

	public String getThumbnail(){
		return thumbnail;
	}

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public int getQuantity(){
		return quantity;
	}

	public void setDiscountedPrice(int discountedPrice){
		this.discountedPrice = discountedPrice;
	}

	public int getDiscountedPrice(){
		return discountedPrice;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	@Override
 	public String toString(){
		return 
			"Product{" +
			"discountPercentage = '" + discountPercentage + '\'' + 
			",total = '" + total + '\'' + 
			",thumbnail = '" + thumbnail + '\'' + 
			",quantity = '" + quantity + '\'' + 
			",discountedPrice = '" + discountedPrice + '\'' + 
			",price = '" + price + '\'' + 
			",id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}
