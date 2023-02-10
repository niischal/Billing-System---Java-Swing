package com.nist.billingsystem.billing;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductImpl implements Product {
	PreparedStatement ps = null;
	@Override
	public void addNewProduct(ProductDTO product) {
		String sql = "INSERT INTO products(brand,name,cost,quantity) VALUES (?,?,?,?)";
		try {
			ps = DatabaseConnectivity.getConnection().prepareStatement(sql);
			ps.setString(1, product.getBrand());
			ps.setString(2, product.getName());
			ps.setFloat(3, product.getCost());
			ps.setInt(4, product.getQuantity());
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	@Override
	public List<ProductDTO> getProducts() {
		List<ProductDTO> productList = new ArrayList<ProductDTO>();
		String sql = "SELECT * FROM products";
		try {
			ps = DatabaseConnectivity.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ProductDTO product = new ProductDTO();
				product.setId(rs.getInt("id"));
				product.setBrand(rs.getString("brand"));
				product.setName(rs.getString("name"));
				product.setCost(rs.getInt("cost"));
				product.setQuantity(rs.getInt("quantity"));
				productList.add(product);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productList;
	}
	@Override
	public List<ProductDTO> searchProduct(String searchKey) {
		ArrayList<ProductDTO> productList  = new ArrayList<ProductDTO>();
		String sql = "SELECT * FROM products WHERE brand LIKE ? OR name LIKE ?";
		try {
			ps = DatabaseConnectivity.getConnection().prepareStatement(sql);
			ps.setString(1, "%" + searchKey +"%");
			ps.setString(2, "%"+searchKey+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ProductDTO product = new ProductDTO();
				product.setId(rs.getInt("id"));
				product.setBrand(rs.getString("brand"));
				product.setName(rs.getString("name"));
				product.setCost(rs.getInt("cost"));
				product.setQuantity(rs.getInt("quantity"));
				productList.add(product);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productList;
	}
	@Override
	public void deleteProduct(int id) {
		String sql = "DELETE FROM products WHERE ID=?";
		try {
			ps = DatabaseConnectivity.getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void updateProduct(ProductDTO product) {
		String sql = "UPDATE products SET brand = ?, name=?, cost=?, quantity=? WHERE id = ?";
		try {
			ps= DatabaseConnectivity.getConnection().prepareStatement(sql);
			ps.setString(1, product.getBrand());
			ps.setString(2, product.getName());
			ps.setFloat(3, product.getCost());
			ps.setInt(4, product.getQuantity());
			ps.setInt(5, product.getId());
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Override
	public ProductDTO getProductById(int Id) {
		ProductDTO p = new ProductDTO()	;
		String sql = "SELECT * from products WHERE id = ?";
		System.out.println(Id);
		try {
			ps = DatabaseConnectivity.getConnection().prepareStatement(sql);
			ps.setInt(1, Id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p.setId(rs.getInt("id"));
				p.setBrand(rs.getString("brand"));
				p.setName(rs.getString("name"));
				p.setCost(rs.getFloat("cost"));
				p.setQuantity(rs.getInt("quantity"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return p;
	}
	
}