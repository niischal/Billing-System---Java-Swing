package com.nist.billingsystem.billing;

public class OrderDTO {
	private int id,quantity;
	private String productName, customerName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public float getSubTotal() {
		return SubTotal;
	}
	public void setSubTotal(float subTotal) {
		SubTotal = subTotal;
	}
	private float cost, SubTotal;
	
}
