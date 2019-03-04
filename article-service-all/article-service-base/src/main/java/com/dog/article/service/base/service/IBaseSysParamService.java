package com.dog.article.service.base.service;

import com.dog.article.common.base.model.BaseSysParam;
import com.dog.framework.base.database.base.service.IBaseService;

/**
* @author liuyuanhang
* @date 2018-01-20
*
* @version 1.0.0
*/
public interface IBaseSysParamService extends IBaseService<BaseSysParam> {

    public String getValueByKey(String key);

    public String updateValueByKey(String key);

}