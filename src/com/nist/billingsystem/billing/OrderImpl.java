package com.nist.billingsystem.billing;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderImpl implements Order {
	PreparedStatement ps =null;
	@Override
	public void saveOrder(String customerName, List<OrderDTO> order) {
		String sql = "INSERT INTO orders (customerName,orderDaetails) VALUES (?,?)";

		Object[] o = order.toArray();
		try {
			ps = DatabaseConnectivity.getConnection().prepareStatement(sql);
			ps.setString(1, customerName);
			ps.setString(2, o.toString());
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

}
