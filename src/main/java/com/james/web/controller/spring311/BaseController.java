package com.james.web.controller.spring311;

import javax.servlet.http.HttpServletRequest;

import com.james.common.CommonConstant;
import com.james.domain.spring311.User;

/**
 * 所有Controller的基类
 * </pre>
 * @see
 */
public class BaseController {
    protected static final String ERROR_MSG_KEY = "errorMsg";

    /**
     * 获取保存在Session中的用户对象
     *
     * @param request
     * @return
     */
    protected User getSessionUser(HttpServletRequest request) {
    	
    	Object userContext=request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
    	if(userContext!=null)
    	{
    		return (User)userContext;
    	}
    	return null;
    }

    /**
     * 保存用户对象到Session中
     * @param request
     * @param user
     */
    protected void setSessionUser(HttpServletRequest request,User user) {
        request.getSession().setAttribute(CommonConstant.USER_CONTEXT,
                user);
    }


    /**
     * 获取基于应用程序的url绝对路径
     *
     * @param request
     * @param url
     *            以"/"打头的URL地址
     * @return 基于应用程序的url绝对路径
     */
    public final String getAppbaseUrl(HttpServletRequest request, String url) {
        //Assert.hasLength(url, "url不能为空");
        //Assert.isTrue(url.startsWith("/"), "必须以/打头");
        return request.getContextPath() + url;
    }

}

