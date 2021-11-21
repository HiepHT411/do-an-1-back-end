package com.hoanghiep.da1.service.Impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoanghiep.da1.entity.Product;
import com.hoanghiep.da1.exception.ProductNotFoundException;
import com.hoanghiep.da1.repository.ProductRepository;
import com.hoanghiep.da1.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private final ProductRepository productRepository;

	@Override
	public Product findProductById(int productId) {

		return productRepository.findById(productId).get();
	}

	@Override
	public List<Product> findAllProducts() {

		return productRepository.findAllByOrderByIdAsc();
	}

	@Override
	public List<Product> findProductsByIds(List<Integer> productsId) {

		return productRepository.findByIdIn(productsId);
	}

	@Override
	public List<Product> filter(List<String> types, List<Integer> prices, boolean sortByPrice) {

		List<Product> productList = new ArrayList<>();

        if (!types.isEmpty() || !prices.isEmpty()) {
          
            if (!types.isEmpty()) {
                if (!productList.isEmpty()) {
                    List<Product> typesList = new ArrayList<>();
                    for (String type : types) {
                        typesList.addAll(productList.stream()
                                .filter(product-> product.getType().equals(type))
                                .collect(Collectors.toList()));
                    }
                    productList = typesList;
                } else {
                    productList.addAll(productRepository.findByTypeIn(types));
                }
            }
            if (!prices.isEmpty()) {
                productList = productRepository.findByPriceBetween(prices.get(0), prices.get(1));
            }
        } else {
            productList = productRepository.findAllByOrderByIdAsc();
        }
        if (sortByPrice) {
            productList.sort(Comparator.comparing(Product::getPrice));
        } else {
            productList.sort((product1, product2) -> product2.getPrice().compareTo(product1.getPrice()));
        }
        return productList;
	}

	@Override
	public List<Product> findByTitleOrderByPriceDesc(String title) {

		return productRepository.findByTitleOrderByPriceDesc(title);
	}

	@Override
	public List<Product> findByTypeOrderByPriceDesc(String productType) {

		return productRepository.findByTypeOrderByPriceDesc(productType);
	}

	
	@Override
	@Transactional
	public List<Product> deleteProducts(int productId) {
		Product product = productRepository.findById(productId)
								.orElseThrow(()-> new ProductNotFoundException("Product not found by id-"+productId));
					
		productRepository.delete(product);
		
		return productRepository.findAllByOrderByIdAsc();
	}

	@Override
	public Product saveProduct(Product product) {
		
		Date date = Calendar.getInstance().getTime();
		product.setDate(date);
		
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product updatedproduct, int productId) {
		
		Product oldProduct = productRepository.findById(productId)
				.orElseThrow(()-> new ProductNotFoundException("Product not found by id-"+productId));
		
		Date date = Calendar.getInstance().getTime();
		
		oldProduct.setTitle(updatedproduct.getTitle());
		oldProduct.setDescription(updatedproduct.getDescription());
		oldProduct.setPrice(updatedproduct.getPrice());
		oldProduct.setType(updatedproduct.getType());
		oldProduct.setDate(date);
		return productRepository.save(oldProduct);
	}
	
	

}
