package top.showtan.util;

public class Pager {
    /**
     * 总条数
     */
    private Long totalCount;
    /**
     * 当前页
     */
    private Long currentPage;
    /**
     * 当前页结果集数目
     */
    private Long pageSize;


    public Pager(Long totalCount, Long currentPage, Long pageSize) {
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public Long getCurrentPage() {
        return currentPage;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public Long getTotalPage() {
        return (totalCount + pageSize - 1) / pageSize;
    }
}
