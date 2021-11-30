package com.hoanghiep.da1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoanghiep.da1.entity.Blog;
import com.hoanghiep.da1.repository.BlogRepository;
import com.hoanghiep.da1.service.BlogService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1")
@Slf4j
@RequiredArgsConstructor
public class BlogController {
	
	@Autowired
	private final BlogService blogService;
	
	@Autowired
	private final BlogRepository blogRepo;
	
	@GetMapping("/blogs/all")
	public List<Blog> getAllArticlesInfo() {
		log.info("Get all articles");	
		return blogService.getAllArticles();
	}
	@PostMapping("/blogs")
	public ResponseEntity<Blog> createBlog(@RequestBody Blog blog){
		log.info("Created a blog, author: "+ blog.getAuthor());
		
		return ResponseEntity.ok(blogService.saveBlog(blog));
	}
	
	@GetMapping("/blogs/{id}")
	public Blog getArticle(@PathVariable int id) {
		Blog blog = blogService.getArticleDetail(id);
		log.info("get article: "+ blog.getTitle());
		return blog;
	}
	
	@PutMapping("/blogs/{id}")
	public ResponseEntity<Blog> updateBlog(@PathVariable int id, @RequestBody Blog updatedBlog){
		log.info("update blog: "+ updatedBlog.getId());
		return ResponseEntity.ok(blogService.updateBlog(updatedBlog, id));
	}
	
	@DeleteMapping("/blogs/{id}")
    public ResponseEntity < Map < String, Boolean >> deleteBlog(@PathVariable int id) {
		Blog blog = blogService.getArticleDetail(id);
        
		blogRepo.delete(blog);
		
		Map < String, Boolean > response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
