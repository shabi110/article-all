package com.dog.article.service.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dog.article.common.base.model.BmsRole;
import com.dog.article.dal.base.dao.BmsRoleMapper;
import com.dog.article.service.base.service.IBmsRoleService;
import com.dog.framework.base.database.mysql.service.impl.BaseMyBatisService;

/**
* @author liuyuanhang
* @date 2018-01-29
*
* @version 1.0.0
*/
@Service
public class BmsRoleServiceImpl extends BaseMyBatisService<BmsRole> implements IBmsRoleService {

    @Autowired
    private BmsRoleMapper mapper;

    public BmsRoleServiceImpl() {
        super.setEntityClazz(BmsRole.class);
    }

}