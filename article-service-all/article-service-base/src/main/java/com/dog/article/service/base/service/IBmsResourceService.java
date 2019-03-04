package com.dog.article.service.base.service;

import java.util.List;

import com.dog.article.common.base.model.BmsResource;
import com.dog.framework.base.database.base.service.IBaseService;

/**
 * @author liuyuanhang
 * @version 1.0.0
 * @date 2018-01-29
 */
public interface IBmsResourceService extends IBaseService<BmsResource> {

    BmsResource getResourceByName(String name, int type);

    BmsResource saveResource(BmsResource recource);

    List<BmsResource> getAllRecource();

}