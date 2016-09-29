package cz.etn.etnshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.etn.etnshop.dao.Product;
import cz.etn.etnshop.dao.ProductDao;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public List<Product> getProducts() {
		return productDao.getProducts();
	}

	@Override
	public void saveOrUpdateProduct(Product product) {
		productDao.saveOrUpdateProduct(product);
	}

	@Override
	public void removeProduct(Integer id) {
		productDao.removeProduct(id);
	}

	@Override
	public Product getProduct(Integer id) {
		return productDao.getProduct(id);
	}

	@Override
	public List<Product> getProducts(String key) {
		return productDao.getProducts(key);
	}

}
