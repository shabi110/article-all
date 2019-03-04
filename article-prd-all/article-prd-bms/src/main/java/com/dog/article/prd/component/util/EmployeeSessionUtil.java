package com.dog.article.prd.component.util;

import com.dog.article.common.base.model.BmsEmployeeInfo;
import com.dog.article.prd.component.session.EmployeeSession;
import com.dog.framework.base.common.utils.MyBeanUtils;


public class EmployeeSessionUtil {

    public static EmployeeSession createEmployeeSessionByEmployee(BmsEmployeeInfo employee) {
        EmployeeSession rs = (EmployeeSession) MyBeanUtils.copyBean(employee, EmployeeSession.class);
        rs.setEmployeeId(employee.getId());
        return rs;
    }
}
