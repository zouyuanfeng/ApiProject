package com.itzyf.dao;

import com.itzyf.bean.ApiBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 依风听雨
 * @version 创建时间：2017/6/30 14:19
 */
@Repository
public interface ApiDao {

    int addApi(ApiBean bean);

    String queryResponseByMethod(Map<String, String> map);

    List<ApiBean> query();

    List<ApiBean> queryByGroup(String groupname);

    int deleteById(int id);

    ApiBean queryById(int id);

    int update(ApiBean bean);

    List<ApiBean> queryByKeyword(String keyword);

    List<String> queryGroup();

    int checkMethod(ApiBean bean);

    void batchDeleteApis(int[] ids);
}
