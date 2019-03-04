package com.dog.article.service.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dog.article.common.base.model.BmsRoleResource;
import com.dog.article.dal.base.dao.BmsRoleResourceMapper;
import com.dog.article.service.base.service.IBmsRoleResourceService;
import com.dog.framework.base.database.mysql.service.impl.BaseMyBatisService;

/**
* @author liuyuanhang
* @date 2018-01-29
*
* @version 1.0.0
*/
@Service
public class BmsRoleResourceServiceImpl extends BaseMyBatisService<BmsRoleResource> implements IBmsRoleResourceService {

    @Autowired
    private BmsRoleResourceMapper mapper;

    public BmsRoleResourceServiceImpl() {
        super.setEntityClazz(BmsRoleResource.class);
    }

}