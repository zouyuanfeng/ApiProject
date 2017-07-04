package com.itzyf.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itzyf.bean.ApiBean;
import com.itzyf.dao.ApiDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 依风听雨
 * @version 创建时间：2017/6/30 14:35
 */
@Service("apiService")
public class ApiServiceImpl implements ApiService {
    private final ApiDao apiDao;

    @Autowired
    public ApiServiceImpl(ApiDao apiDao) {
        this.apiDao = apiDao;
    }

    @Override
    public boolean addApi(ApiBean bean) {
        return apiDao.addApi(bean) > 0;
    }

    @Override
    public String queryResponseByMethod(String groupName, String method) {
        Map<String, String> map = new HashMap<>();
        map.put("groupname", groupName);
        map.put("method", method);
        return apiDao.queryResponseByMethod(map);
    }

    @Override
    public Page<ApiBean> queryAllToPage(int index) {
        PageHelper.startPage(index, 10);
        return (Page<ApiBean>) apiDao.query();
    }

    @Override
    public Page<ApiBean> queryAllByGroupToPage(int index, String groupName) {
        PageHelper.startPage(index, 10);
        return (Page<ApiBean>) apiDao.queryByGroup(groupName);
    }

    @Override
    public boolean deleteById(int id) {
        return id > 0 && apiDao.deleteById(id) > 0;
    }

    @Override
    public ApiBean queryById(int id) {
        return apiDao.queryById(id);
    }

    @Override
    public boolean updateById(ApiBean apiBean) {
        return apiDao.update(apiBean) > 0;
    }

    @Override
    public List<ApiBean> queryAllToPage(String keyword) {
        return apiDao.queryByKeyword(keyword);
    }

    @Override
    public List<String> queryGroup() {
        return apiDao.queryGroup();
    }
}
