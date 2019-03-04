package com.dog.article.prd.component.intereptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dog.article.prd.component.annotation.ParentSecurity;
import com.dog.article.prd.component.annotation.UnSecurity;
import com.dog.article.prd.component.session.EmployeeSession;
import com.dog.article.prd.component.util.UserAuthCheckUtil;
import com.dog.framework.web.annotation.session.UnSession;
import com.dog.framework.web.mvc.utils.RequestUtil;


public class BmsSecurityInterceptor extends HandlerInterceptorAdapter {

    @Value("${noSessionUrl}")
    private String noSessionUrl;

    @Value("${noSecurityUrl}")
    private String noSecurityUrl;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String url = RequestUtil.getRequestUrl(request);
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        UnSession unSession = (UnSession) handlerMethod.getMethodAnnotation(UnSession.class);
        if (unSession != null) {
            return true;
        }
        EmployeeSession eSession = EmployeeSession.getEmployeeSession();
        if ((eSession == null) || ((eSession.getEmployeeId() == null))) {
            response.sendRedirect("/");
            return false;
        }
        UnSecurity security = (UnSecurity) handlerMethod.getMethodAnnotation(UnSecurity.class);
        if (security != null) {
            return true;
        }
        ParentSecurity parent = (ParentSecurity) handlerMethod.getMethodAnnotation(ParentSecurity.class);
        if (parent != null) {
            String[] parentUrls = parent.value();
            for (String parentUrl : parentUrls) {
                if (UserAuthCheckUtil.isPass(url, new String[]{parentUrl})) {
                    return true;
                }
            }
        } else if (UserAuthCheckUtil.isPass(url, new String[0])) {
            return true;
        }

        request.setAttribute("msg", "您没有[" + url + "]资源的权限！");
        request.getRequestDispatcher(noSecurityUrl).forward(request, response);
        return false;
    }
}
