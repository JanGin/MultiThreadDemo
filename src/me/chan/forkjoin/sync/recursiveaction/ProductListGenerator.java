package me.chan.forkjoin.sync.recursiveaction;

import java.util.LinkedList;
import java.util.List;

public class ProductListGenerator {

	public List<Product> generate(int size) {
		List<Product> res = new LinkedList<>();
		for (int i=0; i<size; i++) {
			Product product = new Product("product"+i);
			product.setPrice(10.00);
			res.add(product);
		}
		return res;
	}
	
}
