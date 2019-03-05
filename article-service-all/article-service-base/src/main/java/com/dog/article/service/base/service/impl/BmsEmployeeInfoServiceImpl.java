package com.dog.article.service.base.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dog.article.common.base.model.BmsEmployeeInfo;
import com.dog.article.dal.base.dao.BmsEmployeeInfoMapper;
import com.dog.article.service.base.service.IBmsEmployeeInfoService;
import com.dog.framework.base.common.utils.EmptyUtil;
import com.dog.framework.base.common.utils.MD5Util;
import com.dog.framework.base.database.domain.page.PageResult;
import com.dog.framework.base.database.domain.page.PageSearch;
import com.dog.framework.base.database.domain.returns.DataResponse;
import com.dog.framework.base.database.domain.search.SearchCondition;
import com.dog.framework.base.database.mysql.service.impl.BaseMyBatisService;

/**
 * @author liuyuanhang
 * @version 1.0.0
 * @date 2018-01-29
 */
@Service
public class BmsEmployeeInfoServiceImpl extends BaseMyBatisService<BmsEmployeeInfo> implements IBmsEmployeeInfoService {

    @Autowired
    private BmsEmployeeInfoMapper mapper;

    public BmsEmployeeInfoServiceImpl() {
        super.setEntityClazz(BmsEmployeeInfo.class);
    }

    @Override
    public DataResponse loginByPhone(String phone, String pwd) {
        BmsEmployeeInfo params = new BmsEmployeeInfo();
        params.setMobile(phone);
        params.setPassword(pwd);
        params.setStatus(1);
        SearchCondition<BmsEmployeeInfo> condition = new SearchCondition<BmsEmployeeInfo>(params);
        params = this.findOneByCondition(condition);

        if (EmptyUtil.isEmpty(params)) {
            return new DataResponse(1001, "手机号或密码错误.");
        } else {
            return new DataResponse(1000, "登录成功.", params);
        }
    }
    
    @Override
    public DataResponse getEmployeeUserList(PageSearch pageSearch, BmsEmployeeInfo params) {
        params.setStatus(1);//未删除用户标记
        SearchCondition<BmsEmployeeInfo> condition = new SearchCondition<BmsEmployeeInfo>(params, pageSearch);
        PageResult<BmsEmployeeInfo> result = this.findByPage(condition);
        return new DataResponse(1000, result);
    }

    @Override
    public DataResponse saveEmployee(BmsEmployeeInfo params) {
        if (EmptyUtil.isEmpty(params.getId()) ) {
        	if(EmptyUtil.isEmpty(params.getPassword())) {
        		return new DataResponse(1001,"密码不能为空");
        	}else {
        		params.setPassword(MD5Util.MD5Encode(params.getPassword(), "utf-8"));
        	}
        }
        params.setCreateTime(new Date());
        if (EmptyUtil.isEmpty(params.getId())) {
            this.save(params);
        } else {
            this.modifyEntity(params);
        }
        return new DataResponse(1000, "success");
    }

    @Override
    public DataResponse getEmployeeUser(BmsEmployeeInfo params) {
        SearchCondition<BmsEmployeeInfo> condition = new SearchCondition<BmsEmployeeInfo>(params);
        List<BmsEmployeeInfo> result = this.findByCondition(condition);
        return new DataResponse(1000, result);
    }

    @Override
    public DataResponse deleteEmployeeUser(BmsEmployeeInfo params) {
        params.setStatus(2);//删除标记
        this.modifyEntity(params);
        return new DataResponse(1000, "success");
    }

    @Override
    public DataResponse resetPassWord(BmsEmployeeInfo params) {
        if (EmptyUtil.isNotEmpty(params.getPassword())) {
            params.setPassword(MD5Util.MD5Encode(params.getPassword(), "utf-8"));
        }
        this.modifyEntity(params);
        return new DataResponse(1000, "success");
    }


}