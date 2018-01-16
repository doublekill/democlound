package com.jincin.book.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongwujing on 2017/6/9.
 */
public class JpaPageUtil {

    /**
     * 构建page Request
     * @param pageNum
     * @param pageSize
     * @param sortField
     * @param direction
     * @return
     */
    public static PageRequest buildPageRequest(int pageNum, int pageSize, String sortField, String direction) {
//        //多条件
//        List<Sort.Order> orders = new ArrayList<Sort.Order>();
//        orders.add(new Sort.Order(Sort.Direction.ASC,"author"));
//        orders.add(new Sort.Order(Sort.Direction.DESC,"price"));
//        Sort sort2 = new Sort(orders);

        Sort sort = null;
        if (StringUtils.isBlank(sortField)) {
            //如果没有排序字段
            return new PageRequest(pageNum - 1, pageSize);

        } else if (StringUtils.isNotBlank(direction)) {
            //如果有排序字段,则根据升序或者降序进行设置
            if (Sort.Direction.ASC.name().equals(direction)) {
                //升序排序
                sort = new Sort(Sort.Direction.ASC, sortField);
            } else {
                //降序排序
                sort = new Sort(Sort.Direction.DESC, sortField);
            }
            //封装分页请求对象
            return new PageRequest(pageNum - 1, pageSize, sort);

        } else {
            //如果有排序的字段 没有排序方式则默认降序排序
            sort = new Sort(Sort.Direction.DESC, sortField);
            return new PageRequest(pageNum - 1, pageSize, sort);
        }
    }

    /**
     * 构建pageRequest
     * @param pageNum
     * @param pageSize
     * @param sortField
     * @return
     */
    public static PageRequest buildPageRequest(int pageNum, int pageSize, String sortField) {
        return buildPageRequest(pageNum, pageSize, sortField, null);
    }


}
