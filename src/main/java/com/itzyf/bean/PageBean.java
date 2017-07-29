package com.itzyf.bean;

import java.util.List;
import lombok.Data;

/**
 * @author 依风听雨
 * @version 创建时间：2017/7/29 15:45
 */
@Data
public class PageBean {

    /**
     * 总数
     */
    private int total;
    private int currentPage;
    private int pageSize;
    private boolean hasNext;
    private List<DataList> dataList;

}
