package com.dog.article.prd.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dog.article.common.base.model.BmsEmployeeInfo;
import com.dog.article.prd.component.annotation.UnSecurity;
import com.dog.article.prd.component.session.EmployeeSession;
import com.dog.article.service.base.service.IBaseCacheService;
import com.dog.article.service.base.service.IBmsEmployeeInfoService;
import com.dog.framework.base.common.utils.EmptyUtil;
import com.dog.framework.base.database.domain.page.PageSearch;
import com.dog.framework.base.database.domain.returns.DataResponse;
import com.dog.framework.base.database.redis.RedisManager;
import com.dog.framework.web.annotation.session.NeedSession;

/**
 * describe:
 *
 * @author fish
 * @date 2018/01/29
 */
@Controller
@RequestMapping(value = "user")
public class UserController {

    private static Logger baseLog = LoggerFactory.getLogger("baseLog");
    
    @Autowired
    private IBaseCacheService baseCacheService;
    
    @Autowired
    private IBmsEmployeeInfoService bmsEmployeeInfoService;
    
    @Autowired
    @Qualifier("commonRedisManager")
    private RedisManager redisManager;

  

    @NeedSession("/user/backstage")
    @UnSecurity
    @RequestMapping("backstage")
    public String backstage(Model model) {
        return "user/employeeUser";
    }
    
    @NeedSession
    @UnSecurity
    @RequestMapping("employeeUser")
    public String manageEmp(Model model) {
        return "user/employeeUser";
    }





    /**
     * 后台用户列表------------------------------------------------------------------------------------------------------
     *
     * @param pageSearch
     * @param params
     * @return
     */
    @NeedSession
    @UnSecurity
    @RequestMapping("getEmployeeUserList")
    @ResponseBody
    public DataResponse getEmployeeUserList(PageSearch pageSearch, BmsEmployeeInfo params) {
        return this.bmsEmployeeInfoService.getEmployeeUserList(pageSearch, params);
    }

    /**
     * 添加后台用户
     *
     * @param params
     * @return
     */
    @NeedSession
    @UnSecurity
    @RequestMapping(value = "saveEmployee")
    @ResponseBody
    public DataResponse saveEmployee(BmsEmployeeInfo params) {
        EmployeeSession eSession = EmployeeSession.getEmployeeSession();//操作人信息
        params.setCreateUserId(eSession.getEmployeeId());
        return this.bmsEmployeeInfoService.saveEmployee(params);
    }

    /**
     * 根据后台用户id获取后台用户详情
     *
     * @param params 后台用户对象
     * @return
     */
    @NeedSession
    @UnSecurity
    @RequestMapping(value = "getEmployeeUserById")
    @ResponseBody
    public DataResponse getEmployeeUserById(BmsEmployeeInfo params) {
        List<BmsEmployeeInfo> result = (List<BmsEmployeeInfo>) this.bmsEmployeeInfoService.getEmployeeUser(params).getData();
        if (EmptyUtil.isEmpty(result)) {
            return new DataResponse(1001, "not found");
        }
        return new DataResponse(1000, result.get(0));
    }

    /**
     * 删除后台用户
     *
     * @param params
     * @return
     */
    @NeedSession
    @UnSecurity
    @RequestMapping(value = "deleteEmployeeUser")
    @ResponseBody
    public DataResponse deleteEmployeeUser(BmsEmployeeInfo params) {
        return this.bmsEmployeeInfoService.deleteEmployeeUser(params);
    }


    /**
     * 重置密码
     * 重置为123456
     *
     * @param params
     * @return
     */
    @NeedSession
    @UnSecurity
    @RequestMapping(value = "resetPassWord")
    @ResponseBody
    public DataResponse resetPassWord(BmsEmployeeInfo params) {
        params.setPassword("123456");
        return this.bmsEmployeeInfoService.resetPassWord(params);
    }


}
