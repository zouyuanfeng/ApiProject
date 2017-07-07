package com.itzyf.service;

import com.github.pagehelper.Page;
import com.itzyf.bean.ApiBean;
import com.itzyf.bean.Result;

import java.util.List;

/**
 * @author 依风听雨
 * @version 创建时间：2017/6/30 14:33
 */
public interface ApiService {

    Result addApi(ApiBean bean);

    String queryResponseByMethod(String groupName, String method);

    Page<ApiBean> queryAllToPage(int index);

    Page<ApiBean> queryAllByGroupToPage(int index, String groupName);

    boolean deleteById(int id);

    ApiBean queryById(int id);

    boolean updateById(ApiBean apiBean);

    /**
     * 根据关键字查找
     */
    List<ApiBean> queryAllToPage(String keyword);

    List<String> queryGroup();

    void batchDeleteApis(int[] ids);
}
