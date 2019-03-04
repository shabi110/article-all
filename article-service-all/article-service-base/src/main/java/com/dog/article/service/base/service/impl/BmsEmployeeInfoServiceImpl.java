package com.dog.article.service.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dog.article.common.base.model.BmsEmployeeInfo;
import com.dog.article.dal.base.dao.BmsEmployeeInfoMapper;
import com.dog.article.service.base.service.IBmsEmployeeInfoService;
import com.dog.framework.base.common.utils.EmptyUtil;
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
}