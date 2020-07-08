package com.myx.service;

import com.myx.dao.HaoUserRepository;
import com.myx.dao.TagRepository;
import com.myx.po.HaoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HaoUserServiceImpl implements HaoUserService{

    @Autowired
    private HaoUserRepository haoUserRepository;


    @Override
    public HaoUser getHaoUser(Long id) {
        return  haoUserRepository.findById(id);
    }
}
