package com.hoanghiep.da1.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1")
@Slf4j
public class GalleryController {
	
	//ga`
	@GetMapping(value = "/resource/gallery/test" , produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImage() throws IOException{
		File file = null;
		try {
			file = new ClassPathResource("static/images/khachsansaigon.jpg").getFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		byte[] array = Files.readAllBytes(file.toPath());

		return array;
	}
	
	//return base64 string
	// k dung`
	@GetMapping(value = "/resource/gallery/test2")
	public String getImage3() throws IOException{
		File file = null;
		try {
			file = new ClassPathResource("static/images/khachsansaigon.jpg").getFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		byte[] array = Files.readAllBytes(file.toPath());
		String base64Image = Base64.getEncoder().encodeToString(array);
		
		return base64Image;
	}
	
	//pro
	//for blog 
	@GetMapping(value ="/resource/gallery/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<Resource> getImage2(@PathVariable int id) throws IOException{
//		final ByteArrayResource inputStream = new ByteArrayResource(Files.readAllBytes(Paths.get("/static/images/khachsansaigon.jpg")));
		log.info("get image for blog: "+ id);
		File file = null;
		try {
			file = new ClassPathResource("static/images/blog"+ id +".jpg").getFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		final ByteArrayResource inputStream = new ByteArrayResource( Files.readAllBytes(file.toPath()));
		//System.out.println(inputStream);
		return ResponseEntity.status(HttpStatus.OK)
								.contentLength(inputStream.contentLength())
								.body(inputStream);
	}
	
	@GetMapping(value ="/resource/theme/{pageName}", produces=MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<Resource> getCollectionsTheme(@PathVariable String pageName) throws IOException{
		File file = null;
		log.info("get theme for collections");
		try {
			file = new ClassPathResource("static/images/collections-theme"+pageName+".jpg").getFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		final ByteArrayResource bytes = new ByteArrayResource(Files.readAllBytes(file.toPath()));
		
		return ResponseEntity.status(HttpStatus.OK)
								.contentLength(bytes.contentLength()).body(bytes);
	}
	
	@GetMapping(value="/resource/carousel/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<Resource> getCarouselImage(@PathVariable int id) throws IOException{
		File img = null;
		log.info("get carousel image");
		try {
			img = new ClassPathResource("static/images/slideshow_"+id+".jpg").getFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ByteArrayResource bytes = new ByteArrayResource(Files.readAllBytes(img.toPath()));
		
		return ResponseEntity.status(HttpStatus.OK).contentLength(bytes.contentLength()).body(bytes);
	}
	
	@GetMapping(value="/resource/images/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<Resource> getStaticImage(@PathVariable String name) throws IOException{
		File img = null;
		//log.info("get: "+name+".jpg");
		
		try {
			img = new ClassPathResource("static/images/"+name+".jpg").getFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ByteArrayResource bytes = new ByteArrayResource(Files.readAllBytes(img.toPath()));
		
		return ResponseEntity.status(HttpStatus.OK).contentLength(bytes.contentLength()).body(bytes);
	}
	
}
