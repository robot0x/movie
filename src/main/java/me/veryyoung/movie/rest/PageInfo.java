package me.veryyoung.movie.rest;

import lombok.Data;

import java.util.List;

/**
 * Created by veryyoung on 2015/5/5.
 * <p/>
 * 分页工具
 */
@Data
public class PageInfo<T> {

    // 每页多少行记录
    private int pageSize = 20;

    // 当前第几页
    private int pageNo = 1;

    // 记录总数
    private int totalRows = -1;

    // 开始记录数
    private int startRow;

    // 结束记录数
    private int endRow;

    // 总页数
    private int totalPages;

    // 记录集合
    private List<T> resultList;

    public PageInfo(int pageNo, int pageSize, List<T> resultList, int totalRows) {
        super();
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.resultList = resultList;
        this.totalRows = totalRows;
    }

    public PageInfo() {
    }

    public PageInfo(int pageNo) {
        int endRowNum = pageNo * pageSize;
        this.startRow = endRowNum - pageSize;
    }

    public PageInfo(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        int endRowNum = pageNo * pageSize;
        this.startRow = endRowNum - pageSize;
        if (pageSize != 0)
            this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return (int) Math.ceil((float) totalRows / pageSize);
    }


}
