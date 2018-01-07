package com.myx.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myx.dao.TagRepository;
import com.myx.handler.NotFoundException;
import com.myx.po.Tag;
@Service
public class TagServiceImpl implements TagService {

	
	@Autowired
	private TagRepository tagRepository;
	@Transactional
	@Override
	public Tag saveTag(Tag tag) {
		// TODO Auto-generated method stub
		return tagRepository.save(tag);
	}
	@Transactional
	@Override
	public Tag getTag(Long id) {
		// TODO Auto-generated method stub
		return tagRepository.findOne(id);
	}
	@Transactional
	@Override
	public Tag getTagByName(String name) {
		// TODO Auto-generated method stub
		return tagRepository.findByName(name);
	}
	@Transactional
	@Override
	public Page<Tag> listTag(Pageable pageable) {
		// TODO Auto-generated method stub
		return tagRepository.findAll(pageable);
	}
	@Transactional
	@Override
	public List<Tag> listTag() {
		// TODO Auto-generated method stub
		return tagRepository.findAll();
	}
	@Transactional
	@Override
	public List<Tag> listTag(String ids) {
		// TODO Auto-generated method stub
		return tagRepository.findAll(convertToList(ids));
	}
	
	 private List<Long> convertToList(String ids) {
	        List<Long> list = new ArrayList<>();
	        if (!"".equals(ids) && ids != null) {
	            String[] idarray = ids.split(",");
	            for (int i=0; i < idarray.length;i++) {
	                list.add(new Long(idarray[i]));
	            }
	        }
	        return list;
	    }
	
	@Transactional
	@Override
	public Tag updateTag(Long id, Tag tag) {
		 Tag t = tagRepository.findOne(id);
	        if (t == null) {
	            throw new NotFoundException("不存在该标签");
	        }
	        BeanUtils.copyProperties(tag,t);
	        return tagRepository.save(t);
	}
	@Transactional
	@Override
	public void deleteTag(Long id) {
		  tagRepository.delete(id);

	}
	@Override
	public List<Tag> listTagTop(Integer size) {
		Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");
		Pageable pageable = new PageRequest(0,size,sort);
		return tagRepository.findTop(pageable);
	}

}
