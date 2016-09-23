package cz.etn.etnshop.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

@Repository("productDao")
public class ProductDaoImpl extends AbstractDao implements ProductDao {

	@SuppressWarnings("unchecked")
	public List<Product> getProducts() {
		Criteria criteria = getSession().createCriteria(Product.class);
		return (List<Product>) criteria.list();
	}

}
