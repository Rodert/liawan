package com.liawan.web.interceptor;

import cn.hutool.core.util.StrUtil;
import com.liawan.model.dto.LiawanConst;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 博客注册拦截器
 */
@Component
public class InstallInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
//        if (StrUtil.equals("true", LiawanConst.OPTIONS.get("is_install"))) {
//            return true;
//        }
        if (StringUtils.equals("true", LiawanConst.OPTIONS.get("is_install"))) {
            return true;
        }
        response.sendRedirect("/install");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
