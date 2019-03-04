package com.dog.article.prd.component.util;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import com.dog.article.common.base.model.BmsResource;
import com.dog.article.prd.component.session.EmployeeSession;
import com.dog.framework.base.common.utils.EmptyUtil;
import com.google.common.collect.Lists;


public class UserAuthCheckUtil {

    public static boolean isPass(String url, String[] parentUrls) {
        return checkAuth(url, parentUrls);
    }

    private static boolean checkAuth(String url, String[] parentUrls) {
        EmployeeSession eSession = EmployeeSession.getEmployeeSession();
        if (eSession != null) {
            if ("admin".equals(eSession.getLoginName())) {
                return true;
            }
            List<BmsResource> resources = eSession.getResources();
            List employeeUrls = Lists.newArrayList();
            for (BmsResource resource : resources) {
                if (EmptyUtil.isNotEmpty(resource.getUrl())) {
                    employeeUrls.add(resource.getUrl());
                }
            }
            if (employeeUrls.contains(url)) {
                return true;
            }

            if (ArrayUtils.isNotEmpty(parentUrls)) {
                for (String parentUrl : parentUrls) {
                    if (employeeUrls.contains(parentUrl)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
