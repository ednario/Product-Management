package persistence;

import models.Product;

public interface ProductRepository {
	
	public void insert(Product product);
	
	public void remove(int id);
	
	public Product[] findBy(String fieldName, String value);
	
	public Product[] findAll();
	
	public void update(Product product);
}
