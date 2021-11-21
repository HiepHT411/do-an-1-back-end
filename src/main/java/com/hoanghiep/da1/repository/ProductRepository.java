package com.hoanghiep.da1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoanghiep.da1.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findAllByOrderByIdAsc();

    List<Product> findByTitleIn(List<String> titles);

    List<Product> findByPriceBetween(Integer startingPrice, Integer endingPrice);

    List<Product> findByTitleOrderByPriceDesc(String title);

    List<Product> findByTypeIn(List<String> types);
    
    List<Product> findByTypeOrderByPriceDesc(String prpductType);

    List<Product> findByIdIn(List<Integer> productsIds);
    
    
}
