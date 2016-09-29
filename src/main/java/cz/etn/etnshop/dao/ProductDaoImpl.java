package cz.etn.etnshop.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("productDao")
public class ProductDaoImpl extends AbstractDao implements ProductDao {

	@SuppressWarnings("unchecked")
	public List<Product> getProducts() {
		Criteria criteria = getSession().createCriteria(Product.class);
		return (List<Product>) criteria.list();
	}

	@Override
	public void saveOrUpdateProduct(Product product) {
		getSession().saveOrUpdate(product);
	}

	@Override
	public void removeProduct(Integer id) {
		Product p = getProduct(id);
		try {
			if(p != null){
				getSession().delete(p);
			}
		}
		catch(ObjectNotFoundException e){
			return;
		}
	}

	@Override
	public Product getProduct(Integer id) {
		Product p = (Product) getSession().get(Product.class, id);
		return p;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProducts(String key) {
		System.out.println(key);
		Criteria criteria = getSession().createCriteria(Product.class);
		
		Criterion c1 = Restrictions.like("name", key, MatchMode.ANYWHERE);
		Criterion c2 = Restrictions.like("serialNumber", key, MatchMode.ANYWHERE);
		
		criteria.add(Restrictions.or(c1, c2));
		

		Integer id = Integer.getInteger(key);
		if(id != null){
			Criterion c3 = Restrictions.like("id", key, MatchMode.ANYWHERE);
			criteria.add(Restrictions.or(c3));
		}

		List<Product>  p = criteria.list();
		return p;
	}
	
	
}
