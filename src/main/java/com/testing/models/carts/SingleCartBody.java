package com.testing.models.carts;

import java.util.List;

public class SingleCartBody{
	private int discountedTotal;
	private int total;
	private int totalQuantity;
	private int totalProducts;
	private int id;
	private int userId;
	private List<Product> products;

	public void setDiscountedTotal(int discountedTotal){
		this.discountedTotal = discountedTotal;
	}

	public int getDiscountedTotal(){
		return discountedTotal;
	}

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setTotalQuantity(int totalQuantity){
		this.totalQuantity = totalQuantity;
	}

	public int getTotalQuantity(){
		return totalQuantity;
	}

	public void setTotalProducts(int totalProducts){
		this.totalProducts = totalProducts;
	}

	public int getTotalProducts(){
		return totalProducts;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setProducts(List<Product> products){
		this.products = products;
	}

	public List<Product> getProducts(){
		return products;
	}

	@Override
 	public String toString(){
		return 
			"SingleCartBody{" + 
			"discountedTotal = '" + discountedTotal + '\'' + 
			",total = '" + total + '\'' + 
			",totalQuantity = '" + totalQuantity + '\'' + 
			",totalProducts = '" + totalProducts + '\'' + 
			",id = '" + id + '\'' + 
			",userId = '" + userId + '\'' + 
			",products = '" + products + '\'' + 
			"}";
		}
}