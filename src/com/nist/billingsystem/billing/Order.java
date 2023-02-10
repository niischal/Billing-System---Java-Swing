package com.nist.billingsystem.billing;

import java.util.List;

public interface Order {
	public void saveOrder(String customerName, List<OrderDTO> order);

}
