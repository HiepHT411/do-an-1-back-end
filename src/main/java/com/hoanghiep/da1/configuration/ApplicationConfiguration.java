package com.hoanghiep.da1.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		return modelMapper;
	}
}
