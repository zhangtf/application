package com.ztf.application.page;

import com.github.pagehelper.IPage;

import javax.servlet.http.HttpServletRequest;

public class PageUtils {
    public IPage initIPage (HttpServletRequest request) {

        String pageNumber = request.getParameter("pageNumber");
        String pageSize = request.getParameter("pageSize");
        String orderBy = request.getParameter("orderBy");

        return null;
    }
}
