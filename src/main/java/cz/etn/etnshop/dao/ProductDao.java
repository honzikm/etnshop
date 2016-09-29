package cz.etn.etnshop.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface ProductDao {

	@Transactional(readOnly = true)
	List<Product> getProducts();

	@Transactional
	void saveOrUpdateProduct(Product product);

	@Transactional
	void removeProduct(Integer id);

	@Transactional(readOnly = true)
	Product getProduct(Integer id);

	@Transactional(readOnly = true)
	List<Product> getProducts(String key);
}
