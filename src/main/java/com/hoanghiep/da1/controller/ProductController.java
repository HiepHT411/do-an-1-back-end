package com.hoanghiep.da1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoanghiep.da1.entity.Product;
import com.hoanghiep.da1.payload.ProductSearchRequest;
import com.hoanghiep.da1.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

	@Autowired
	private final ProductService productService;
	
	@GetMapping("/products/all")
	public ResponseEntity<List<Product>> getAllProducts(){
		log.info("get all products");
		return ResponseEntity.ok(productService.findAllProducts());
		
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getDetailProduct(@PathVariable int id) {
		Product product = productService.findProductById(id);
		log.info("get product: "+ product.getTitle());
		return ResponseEntity.ok(product);
	}

	@PostMapping("/products/search")
    public ResponseEntity<List<Product>> findProductsByFilterParams(@RequestBody ProductSearchRequest filter) {
        return ResponseEntity.ok(productService.filter(filter.getTypes(), filter.getTitles(), filter.getPrices(), filter.isSortByPrice()));
    }

    @PostMapping("/products/search/title")
    public ResponseEntity<List<Product>> findByProductTitle(@RequestBody ProductSearchRequest filter) {
        return ResponseEntity.ok(productService.findByTitleOrderByPriceDesc(filter.getTitle()));
    }

    @PostMapping("/products/search/type")
    public ResponseEntity<List<Product>> findByPerfumer(@RequestBody ProductSearchRequest filter) {
        return ResponseEntity.ok(productService.findByTypeOrderByPriceDesc(filter.getType()));
    }
	
	
	@PostMapping("/admin/products/save")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Product> create(@RequestBody Product product){
		log.info("Create product: "+ product.getTitle());
		return ResponseEntity.ok(productService.saveProduct(product));
	}
	
	@PutMapping("/admin/products/update/{productId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Product> update(@PathVariable("productId") int productId, @RequestBody Product product){
		log.info("Create product: "+ product.getTitle());
		return ResponseEntity.ok(productService.updateProduct(product, productId));
	}
	
	@DeleteMapping("/admin/products/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Product>> deleteProduct(@PathVariable int id){
//		Product product = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("product not found"));
//		log.info("delete product: "+ product.getTitle());
//		productRepository.delete(product);
//		
//		HashMap<String, Boolean> response = new HashMap<>();
//		response.put("deleted", Boolean.TRUE);
		
		log.info("Delete product by id-"+id);
		return ResponseEntity.ok(productService.deleteProducts(id));
	}
}
