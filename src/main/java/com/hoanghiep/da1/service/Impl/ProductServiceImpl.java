package com.hoanghiep.da1.service.Impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoanghiep.da1.entity.EProductType;
import com.hoanghiep.da1.entity.Product;
import com.hoanghiep.da1.entity.Type;
import com.hoanghiep.da1.exception.ProductNotFoundException;
import com.hoanghiep.da1.repository.ProductRepository;
import com.hoanghiep.da1.repository.TypeRepository;
import com.hoanghiep.da1.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private final ProductRepository productRepository;

	@Autowired
	private final TypeRepository typeRepository;
	
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
	public List<Product> filter(List<String> types,List<String> titles, List<Integer> prices, boolean sortByPrice) {

		List<Product> productList = new ArrayList<>();
		//System.out.println("sending product list...");		//chay duoc den day
        if ((!types.isEmpty()) || (!titles.isEmpty()) || (!prices.isEmpty())) {
        	//System.out.println("sending product list.....");
        	if (!titles.isEmpty()) {
                if (!productList.isEmpty()) {
                    List<Product> titlesList = new ArrayList<>();
                    for (String title : titles) {
                        titlesList.addAll(productList.stream()
                                .filter(product-> product.getTitle().equals(title))
                                .collect(Collectors.toList()));
                    }
                    productList = titlesList;
                } else {
                    productList.addAll(productRepository.findByTitleIn(titles));
                }
            }
            if (!types.isEmpty()) {
                if (!productList.isEmpty()) {
                    List<Product> typesList = new ArrayList<>();
                    for (String type : types) {
                    	//System.out.println("sending product list.......");
                    	switch (type) {
                    	case "CLOTHING":               			 
                    		typesList.addAll(productList.stream()
                                    .filter(product-> product.getType().equals(EProductType.CLOTHING))
                                    .collect(Collectors.toList()));
                    		break;
                		case "DECORATION":
                			typesList.addAll(productList.stream()
                                    .filter(product-> product.getType().equals(EProductType.DECORATION))
                                    .collect(Collectors.toList()));
                			break;
                		case "FOODMENU":
                			typesList.addAll(productList.stream()
                                    .filter(product-> product.getType().equals(EProductType.FOODMENU))
                                    .collect(Collectors.toList()));
                			break;
                		case "SPECIALDEVICE":
                			typesList.addAll(productList.stream()
                                    .filter(product-> product.getType().equals(EProductType.SPECIALSERVICE))
                                    .collect(Collectors.toList()));
                			break;
                		case "TRANSPORTATION":
                			typesList.addAll(productList.stream()
                                    .filter(product-> product.getType().equals(EProductType.TRANSPORTATION))
                                    .collect(Collectors.toList()));
                			break;
                		case "VENUE":
                			typesList.addAll(productList.stream()
                                    .filter(product-> product.getType().equals(EProductType.VENUE))
                                    .collect(Collectors.toList()));
                			break;
						default:
							break;
						}
                    
                    }
                    productList = typesList;
                } 
                else {
                	for (String type : types) {
                		switch (type) {
                    	case "CLOTHING":               			 
                    		productList.addAll(productRepository.findByTypeOrderByPriceDesc(EProductType.CLOTHING));
                    		break;
                		case "DECORATION":
                			productList.addAll(productRepository.findByTypeOrderByPriceDesc(EProductType.DECORATION));
                			break;
                		case "FOODMENU":
                			productList.addAll(productRepository.findByTypeOrderByPriceDesc(EProductType.FOODMENU));
                			break;
                		case "SPECIALDEVICE":
                			productList.addAll(productRepository.findByTypeOrderByPriceDesc(EProductType.SPECIALSERVICE));
                			break;
                		case "TRANSPORTATION":
                			productList.addAll(productRepository.findByTypeOrderByPriceDesc(EProductType.TRANSPORTATION));
                			break;
                		case "VENUE":
                			productList.addAll(productRepository.findByTypeOrderByPriceDesc(EProductType.VENUE));
                			break;
						default:
							break;
						}
                	}
                }
            }
            if (!prices.isEmpty()) {
                productList = productRepository.findByPriceBetween(prices.get(0), prices.get(1));
            }
        } else {
        	//System.out.println("empty filter -> retrieving all products ...");
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
		switch (productType) {
		case "CLOTHING":
//			Type type = typeRepository.findByType(EProductType.CLOTHING)
//							.orElseThrow(() -> new RuntimeException("Error: Type is not found. 1")); 
			return productRepository.findByTypeOrderByPriceDesc(EProductType.CLOTHING);
		case "DECORATION":
			return productRepository.findByTypeOrderByPriceDesc(EProductType.DECORATION);
		case "FOODMENU":
			return productRepository.findByTypeOrderByPriceDesc(EProductType.FOODMENU);
		case "SPECIALDEVICE":
			return productRepository.findByTypeOrderByPriceDesc(EProductType.SPECIALSERVICE);
		case "TRANSPORTATION":
			return productRepository.findByTypeOrderByPriceDesc(EProductType.TRANSPORTATION);
		case "VENUE":
			return productRepository.findByTypeOrderByPriceDesc(EProductType.VENUE);
		default:
			return null;
		}
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
		
		DateFormat dateFormat = new SimpleDateFormat("hh:mm:s dd-MM-yyyy");
		Date date = Calendar.getInstance().getTime();
		String createdAt = dateFormat.format(date);
		product.setDate(createdAt);
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product updatedproduct, int productId) {
		
		Product oldProduct = productRepository.findById(productId)
				.orElseThrow(()-> new ProductNotFoundException("Product not found by id-"+productId));
		
		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd-MM-yyyy");
		Date date = Calendar.getInstance().getTime();
		String updatedAt = dateFormat.format(date);
		
		oldProduct.setTitle(updatedproduct.getTitle());
		oldProduct.setDescription(updatedproduct.getDescription());
		oldProduct.setPrice(updatedproduct.getPrice());
		oldProduct.setType(updatedproduct.getType());
		oldProduct.setDate(updatedAt);
		return productRepository.save(oldProduct);
	}
	
	

}
