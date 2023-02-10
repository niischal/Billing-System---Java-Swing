package com.nist.billingsystem.billing;

import java.util.List;


public interface Product {
	public void addNewProduct(ProductDTO product);
	public List<ProductDTO> getProducts();
	public List<ProductDTO> searchProduct(String searchKey);
	public void deleteProduct(int id);
	public void updateProduct(ProductDTO prod);
	public ProductDTO getProductById(int Id);
}
