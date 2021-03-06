package cz.etn.etnshop.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cz.etn.etnshop.dao.Product;

public interface ProductService {

	@Transactional(readOnly = true)
	List<Product> getProducts();
	
	@Transactional(readOnly = true)
	List<Product> getProducts(String key);
	
	@Transactional
	void saveOrUpdateProduct(Product product);

	@Transactional
	void removeProduct(Integer id);

	@Transactional(readOnly = true)
	Product getProduct(Integer id);

}
