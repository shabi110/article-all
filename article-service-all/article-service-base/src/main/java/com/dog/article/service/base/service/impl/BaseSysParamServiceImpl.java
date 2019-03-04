package com.dog.article.service.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dog.article.common.base.constant.RedisKeyConstant;
import com.dog.article.common.base.model.BaseSysParam;
import com.dog.article.dal.base.dao.BaseSysParamMapper;
import com.dog.article.service.base.service.IBaseSysParamService;
import com.dog.framework.base.common.utils.EmptyUtil;
import com.dog.framework.base.database.domain.search.SearchCondition;
import com.dog.framework.base.database.mysql.service.impl.BaseMyBatisService;
import com.dog.framework.base.database.redis.RedisManager;

/**
 * @author liuyuanhang
 * @version 1.0.0
 * @date 2018-01-20
 */
@Service
public class BaseSysParamServiceImpl extends BaseMyBatisService<BaseSysParam> implements IBaseSysParamService {

    @Autowired
    private BaseSysParamMapper mapper;

    @Autowired
    @Qualifier("commonRedisManager")
    private RedisManager redisManager;

    //redis 缓存时长
    private static final int CACHE_TIME = 2 * 60 * 60;

    public BaseSysParamServiceImpl() {
        super.setEntityClazz(BaseSysParam.class);
    }

    @Override
    public String getValueByKey(String key) { 
        String value = this.redisManager.getStringValueByKey(RedisKeyConstant.INDEX_KEY + key);
        if (EmptyUtil.isEmpty(value)) {
            BaseSysParam params = new BaseSysParam();
            params.setParamKey(key);
            List<BaseSysParam> list = this.findByCondition(new SearchCondition<BaseSysParam>(params));
            if (EmptyUtil.isNotEmpty(list)) {
                value = list.get(0).getParamValue();
                if (EmptyUtil.isNotEmpty(value))
                    this.redisManager.saveStringBySeconds(RedisKeyConstant.INDEX_KEY + key, value, CACHE_TIME);
            }
        }
        return value;
    }

    @Override
    public String updateValueByKey(String key) {
        String value = this.redisManager.getStringValueByKey(RedisKeyConstant.INDEX_KEY + key);
        if (EmptyUtil.isNotEmpty(value)) {
            redisManager.delete(RedisKeyConstant.INDEX_KEY + key);
        }
        BaseSysParam params = new BaseSysParam();
        params.setParamKey(key);
        List<BaseSysParam> list = this.findByCondition(new SearchCondition<BaseSysParam>(params));
        if (EmptyUtil.isNotEmpty(list)) {
            value = list.get(0).getParamValue();
            this.redisManager.saveStringBySeconds(RedisKeyConstant.INDEX_KEY + key, value, CACHE_TIME);
        }
        return value;
    }

}