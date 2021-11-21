package com.hoanghiep.da1.service;

import java.util.List;

import com.hoanghiep.da1.entity.Product;

public interface ProductService {

//	DataFetcher<Product> getProductByQuery();
//
//    DataFetcher<List<Product>> getAllProductsByQuery();
//
//    DataFetcher<List<Product>> getAllProductsByIdsQuery();

    Product findProductById(int productId);

    List<Product> findAllProducts();

    List<Product> findProductsByIds(List<Integer> productsId);

    List<Product> filter(List<String> types, List<Integer> prices, boolean sortByPrice);

    List<Product> findByTitleOrderByPriceDesc(String title);

    List<Product> findByTypeOrderByPriceDesc(String productType);
    
    Product saveProduct(Product product);
    
    Product updateProduct(Product product, int id);

    List<Product> deleteProducts(int productId);
}
