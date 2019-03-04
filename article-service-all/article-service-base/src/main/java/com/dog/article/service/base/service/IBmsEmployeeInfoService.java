package com.dog.article.service.base.service;

import com.dog.article.common.base.model.BmsEmployeeInfo;
import com.dog.framework.base.database.base.service.IBaseService;
import com.dog.framework.base.database.domain.returns.DataResponse;

/**
 * @author liuyuanhang
 * @version 1.0.0
 * @date 2018-01-29
 */
public interface IBmsEmployeeInfoService extends IBaseService<BmsEmployeeInfo> {

    DataResponse loginByPhone(String phone, String pwd);

}