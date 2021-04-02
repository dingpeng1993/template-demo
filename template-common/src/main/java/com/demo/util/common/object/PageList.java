package com.demo.util.common.object;

import lombok.Data;

import java.util.List;

/**
 * @author dp
 * @date 2020/11/2 8:58 下午
 */
@Data
public class PageList<T> {

    private List<T> items;
    private Integer total;
    private Integer size;
    /**
     * 当前查询页码 默认从1开始
     */
    private Integer pageNum = 1;
    /**
     * 每次查询条数 默认10个
     */
    private Integer pageSize;

    public void setPageNum(Integer pageNum) {
        if (pageNum > 0) {
            this.pageNum = pageNum;
        }
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    public Integer getPageTotal() {
        Integer total = getTotal();
        Integer pageSize = getPageSize();

        Integer pageTotal = total / pageSize;

        if (total % pageSize > 0) {
            pageTotal += 1;
        }
        return pageTotal;
    }
}
