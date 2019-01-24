package sam.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductRestController {
	
	@GetMapping("/product/getAll")
	public List<String> getAllProducts() {
		
		List <String>productList=new ArrayList <String>();
		productList.add("p1");
		productList.add("p2");
		productList.add("p4");
		return productList;
		
	}
	
	

}
