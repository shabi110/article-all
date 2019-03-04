package com.dog.article.service.base.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dog.article.common.base.model.BmsResource;
import com.dog.article.dal.base.dao.BmsResourceMapper;
import com.dog.article.service.base.service.IBmsResourceService;
import com.dog.framework.base.common.utils.EmptyUtil;
import com.dog.framework.base.database.domain.returns.BaseResult;
import com.dog.framework.base.database.domain.search.SearchCondition;
import com.dog.framework.base.database.mysql.service.impl.BaseMyBatisService;

/**
 * @author liuyuanhang
 * @version 1.0.0
 * @date 2018-01-29
 */
@Service
public class BmsResourceServiceImpl extends BaseMyBatisService<BmsResource> implements IBmsResourceService {

    @Autowired
    private BmsResourceMapper mapper;

    public BmsResourceServiceImpl() {
        super.setEntityClazz(BmsResource.class);
    }

    public BmsResource getResourceByName(String name, int type) {
        BmsResource param = new BmsResource();
        param.setName(name);
        param.setStatus(1);
        param.setType(type);
        List<BmsResource> list = this.findByCondition(new SearchCondition<BmsResource>(param));
        if (list.size() == 0)
            return null;
        else
            return list.get(0);
    }

    public BmsResource saveResource(BmsResource recource) {
        Date curTime = new Date();
        if (EmptyUtil.isEmpty(recource.getId())) {
            recource.setStatus(1);
            BaseResult br = this.save(recource);
            recource.setId(Integer.parseInt(br.getData().toString()));
        } else {
            recource.setModifyTime(curTime);
            this.modifyEntity(recource);
        }
        return recource;
    }


    @Override
    public List<BmsResource> getAllRecource() {
        BmsResource param = new BmsResource();
        param.setStatus(1);
        SearchCondition<BmsResource> con = new SearchCondition<BmsResource>(param);
        List<BmsResource> list = this.findByCondition(con);
        return list;
    }
}