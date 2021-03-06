package com.myx.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myx.dao.BlogRepository;
import com.myx.handler.NotFoundException;
import com.myx.po.Blog;
import com.myx.po.Type;
import com.myx.util.MarkdownUtils;
import com.myx.vo.BlogQuery;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogRepository blogRepository;

	@Override
	public Blog getBlog(Long id) {
		
		Blog one = blogRepository.findOne(id);
		
		return one;
	}

	@Override
	public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
		return blogRepository.findAll(new Specification<Blog>() {
			@Override
			public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
					predicates.add(cb.like(root.<String>get("title"), "%" + blog.getTitle() + "%"));
				}
				if (blog.getTypeId() != null) {
					predicates.add(cb.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
				}
				if (blog.isRecommend()) {
					predicates.add(cb.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
				}
				cq.where(predicates.toArray(new Predicate[predicates.size()]));
				return null;
			}
		}, pageable);
	}

	@Transactional
	@Override
	public Blog saveBlog(Blog blog) {
		// TODO Auto-generated method stub
		if (blog.getId() == null) {
			blog.setCreateTime(new Date());
			blog.setUpdateTime(new Date());
			blog.setViews(0);
		} else {
			blog.setUpdateTime(new Date());
		}
		return blogRepository.save(blog);
	}

	//修改博客
	@Transactional
	@Override
	public Blog updateBlog(Long id, Blog blog) {
		Blog b = blogRepository.findOne(id);
		if (b == null) {
			throw new NotFoundException("博客不存在");
		}
		b.setContent(blog.getContent());
		b.setDescription(blog.getDescription());
		b.setFirstPicture(blog.getFirstPicture());
		b.setFlag(blog.getFlag());
		b.setTags(blog.getTags());
		b.setType(blog.getType());
		b.setTitle(blog.getTitle());
		b.setCommentabled(blog.isCommentabled());
		b.setPublished(blog.isPublished());
		b.setAppreciation(blog.isAppreciation());
		b.setShareStatement(blog.isShareStatement());
		b.setRecommend(blog.isRecommend());
		//BeanUtils.copyProperties(blog, b);
		Blog one = blogRepository.save(b);
		return one;
	}

	@Override
	public void deleteBlog(Long id) {
		// TODO Auto-generated method stub
		blogRepository.delete(id);
	}

	@Override
	public Page<Blog> listBlog(Pageable pageable) {
		// TODO Auto-generated method stub
		return blogRepository.findAll(pageable);
	}

	
	//查处右边8条博客排序
	@Override
	public List<Blog> listRecommendBlogTop(Integer size) {
		Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
		Pageable pageable = new PageRequest(0, size, sort);
		return blogRepository.findTop(pageable);
	}

	//search的方法
	@Override
	public Page<Blog> listBlog(String query, Pageable pageable) {
		// TODO Auto-generated method stub
		return blogRepository.findByQuery(query, pageable);
	}

	@Override
	public Blog getAndConvert(Long id) {
		Blog one = blogRepository.findOne(id);
		if(one ==null){
			throw new NotFoundException("该博客不存在");
		}else{
			one.setViews(one.getViews()+1);
			blogRepository.save(one);
		}
		Blog b = new Blog();
		BeanUtils.copyProperties(one, b);
		String content = b.getContent();
	    b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
		return b;
	}

	   @Override
	    public Page<Blog> listBlog(Long tagId, Pageable pageable) {
	        return blogRepository.findAll(new Specification<Blog>() {
	            @Override
	            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
	                Join join = root.join("tags");
	                return cb.equal(join.get("id"),tagId);
	            }
	        },pageable);
	    }

	    @Override
	    public Map<String, List<Blog>> archiveBlog() {
	        List<String> years = blogRepository.findGroupYear();
	        Map<String, List<Blog>> map = new HashMap<>();
	        for (String year : years) {
	            map.put(year, blogRepository.findByYear(year));
	        }
	        return map;
	    }

	    @Override
	    public Long countBlog() {
	        return blogRepository.count();
	    }

}
