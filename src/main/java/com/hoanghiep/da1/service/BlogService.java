package com.hoanghiep.da1.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

//import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoanghiep.da1.entity.Blog;
import com.hoanghiep.da1.exception.ResourceNotFoundException;
import com.hoanghiep.da1.repository.BlogRepository;

@Service
public class BlogService {
	
	@Autowired
	private BlogRepository blogRepo;
	
	public List<Blog> getAllArticles(){
		List<Blog> articles = blogRepo.findAll();
		
//		for (Blog article: articles) {
//			String escapedContentString = article.getContent();
//			String unescapedContentHtml = StringEscapeUtils.unescapeHtml4(escapedContentString);
//			
//			String escapedImgLinkString = article.getImgLink();
//			String unsescapedImgLinkString = StringEscapeUtils.unescapeHtml4(escapedImgLinkString);
//			
//			article.setContent(unescapedContentHtml);
//			article.setImgLink(unsescapedImgLinkString);
//		}
		return articles;
	}
	public Blog saveBlog(Blog blog) {
		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd-MM-yyyy");
		Date date = Calendar.getInstance().getTime();
		String blogDate = dateFormat.format(date);
		
		blog.setDate(blogDate);
		return blogRepo.save(blog);
	}
	
	public Blog updateBlog(Blog updateBlog, int id) {
		Blog oldOne = blogRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("article not exist with id: "+ id));
		
		oldOne.setAuthor(updateBlog.getAuthor());
		oldOne.setContent(updateBlog.getContent());
		oldOne.setTitle(updateBlog.getTitle());
		
		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd-MM-yyyy");
		Date date = Calendar.getInstance().getTime();
		String blogDate = dateFormat.format(date);
		oldOne.setDate(blogDate);
		
		return blogRepo.save(oldOne);
	}
	
	public Blog getArticleDetail(int id) {
		Blog article = blogRepo.findById(id)
					.orElseThrow(()-> new ResourceNotFoundException("blog not exist with id: "+id));
		
//		String escapedContentString = article.getContent();
//		String unescapedContentHtml = StringEscapeUtils.unescapeHtml4(escapedContentString);
//		
//		String escapedImgLinkString = article.getImgLink();
//		String unsescapedImgLinkString = StringEscapeUtils.unescapeHtml4(escapedImgLinkString);
//		
//		article.setContent(unescapedContentHtml);
//		article.setImgLink(unsescapedImgLinkString);
	
		return article;
	}
}
