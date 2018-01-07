package com.myx.service;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.NoType;
import javax.lang.model.type.NullType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.WildcardType;
import javax.lang.model.util.Types;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myx.dao.TypeRepository;
import com.myx.handler.NotFoundException;
import com.myx.po.Type;

@Service
public class TypeServiceImpl implements TypeService {
	@Autowired
	private TypeRepository typeRepository;

	@Transactional
	@Override
	public Type saveType(Type type) {
		// TODO Auto-generated method stub
		return typeRepository.save(type);
	}

	@Transactional
	@Override
	public Type getType(Long id) {
		// TODO Auto-generated method stub
		return typeRepository.findOne(id);
	}

	@Transactional
	@Override
	public Page<Type> listType(Pageable pageable) {
		// TODO Auto-generated method stub
		return typeRepository.findAll(pageable);
	}

	// 更新
	@Transactional
	@Override
	public Type update(Long id, Type type) {
		// TODO Auto-generated method stub
		Type t = typeRepository.findOne(id);
		if (t == null) {
			throw new NotFoundException("不存在该类型");
		}
		BeanUtils.copyProperties(type, t);
		return typeRepository.save(t);
	}

	// 删除
	@Transactional
	@Override
	public void deleteType(Long id) {
		// TODO Auto-generated method stub
		typeRepository.delete(id);
	}

	@Override
	public Type getTypeByName(String name) {
		// TODO Auto-generated method stub
		return typeRepository.findByName(name);
	}

	@Override
	public List<Type> listType() {
		// TODO Auto-generated method stub
		return typeRepository.findAll();
	}
	
	@Transactional
	@Override
	public List<Type> listTypeTop(Integer size) {
		Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");
		Pageable pageable = new PageRequest(0,size,sort);
		return typeRepository.findTop(pageable);
	}

}
