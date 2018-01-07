package com.myx.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.myx.po.Type;

public interface TypeService {

	Type saveType(Type type);
	Type getType(Long id);
	Page<Type> listType(Pageable pageable);
	Type update(Long id,Type type);
	void deleteType(Long id);
	Type getTypeByName(String name);
	List<Type> listType();
	List<Type> listTypeTop(Integer size);
}
