package com.dog.article.prd.component.filter;

import javax.servlet.http.HttpSession;

import com.dog.article.prd.component.session.EmployeeSession;
import com.dog.framework.web.mvc.filter.BaseAbstractSessionFilter;
import com.dog.framework.web.mvc.session.HttpSessionTool;


public class BmsSessionFilter extends BaseAbstractSessionFilter {

    public void saveMembersSessionOrEmployeeSessionToThreadLocal(HttpSession session) {
        EmployeeSession employeeSession = (EmployeeSession) session.getAttribute(HttpSessionTool.EMPLOYEE_SESSION_KEY);
        EmployeeSession.setUserSession(employeeSession);
    }
}
