package com.hoanghiep.da1.payload;

import java.util.List;

import lombok.Data;

@Data
public class ProductSearchRequest {
	
	private List<String> titles;
    private List<String> types;
    private List<Integer> prices;
    private boolean sortByPrice;
    private String title;
    private String type;
}
