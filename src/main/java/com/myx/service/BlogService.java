package com.myx.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.myx.po.Blog;
import com.myx.vo.BlogQuery;

public interface BlogService {

	Blog getBlog(Long id);
	Page<Blog> listBlog(Pageable pageable,BlogQuery blog);
	Page<Blog> listBlog(Pageable pageable);
	Page<Blog> listBlog(Long tagId ,Pageable pageable);
    List<Blog> listRecommendBlogTop(Integer size);
	Blog saveBlog(Blog blog);
	Blog updateBlog(Long id,Blog blog);
	void deleteBlog(Long id);
	Page<Blog> listBlog(String query, Pageable pageable);
	Blog getAndConvert(Long id);
	Map<String,List<Blog>> archiveBlog();
	Long countBlog();
}
