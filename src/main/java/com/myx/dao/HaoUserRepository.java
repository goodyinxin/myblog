package com.myx.dao;

import com.myx.po.HaoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HaoUserRepository extends JpaRepository<HaoUser, Long>, JpaSpecificationExecutor<HaoUser> {

    HaoUser findById(Long id);

}
