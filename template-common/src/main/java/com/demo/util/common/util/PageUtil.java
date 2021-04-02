package com.demo.util.common.util;

import com.demo.util.common.object.PageList;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author dp
 * @date 2021/4/1 9:46 下午
 */
public class PageUtil {

    private static final Integer DEFAULT_PAGE_NUM = 1;

    private static final Integer DEFAULT_PAGE_SIZE = 20;

    public static PageList empty(){
        PageList pageList = new PageList();
        pageList.setTotal(0);
        pageList.setSize(0);
        pageList.setPageNum(DEFAULT_PAGE_NUM);
        pageList.setPageSize(DEFAULT_PAGE_SIZE);
        pageList.setItems(Collections.EMPTY_LIST);
        return pageList;
    }

    public static <T> PageList<T> initPageList(int pageNum, int pageSize, Integer total, List<T> list){
        PageList<T> pageList = new PageList<>();
        pageList.setTotal(total);
        pageList.setSize(CollectionUtils.size(list));
        pageList.setPageNum(pageNum);
        pageList.setPageSize(pageSize);
        pageList.setItems(list);
        return pageList;
    }
}
