package com.ztf.application.page;

import com.github.pagehelper.IPage;

/**
 * 分页参数
 * @author zhangtf
 * @createTime 2018-11-11 11:11:11
 */
public class PageParam implements IPage {

    private Integer pageNum;
    private Integer pageSize;
    private String orderBy;

    public PageParam (Integer pageNum) {
        this.pageNum = pageNum;
    }
    public PageParam (Integer pageNum, String orderBy) {
        this.pageNum = pageNum;
    }
    public PageParam (Integer pageNum, Integer pageSize) {
        this.pageNum =  pageNum;
        this.pageSize = pageSize;
    }
    public PageParam (Integer pageNum, Integer pageSize, String orderBy) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
    }
    @Override
    public Integer getPageNum() {
        return pageNum;
    }

    @Override
    public Integer getPageSize() {
        return pageSize;
    }

    @Override
    public String getOrderBy() {
        return orderBy;
    }
}
