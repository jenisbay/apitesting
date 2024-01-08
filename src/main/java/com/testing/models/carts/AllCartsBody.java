package com.testing.models.carts;

import java.util.List;

public class AllCartsBody{
	private int total;
	private List<Cart> carts;
	private int limit;
	private int skip;

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setCarts(List<Cart> carts){
		this.carts = carts;
	}

	public List<Cart> getCarts(){
		return carts;
	}

	public void setLimit(int limit){
		this.limit = limit;
	}

	public int getLimit(){
		return limit;
	}

	public void setSkip(int skip){
		this.skip = skip;
	}

	public int getSkip(){
		return skip;
	}

	@Override
 	public String toString(){
		return 
			"AllCartsBody{" + 
			"total = '" + total + '\'' + 
			",carts = '" + carts + '\'' + 
			",limit = '" + limit + '\'' + 
			",skip = '" + skip + '\'' + 
			"}";
		}
}