package top.showtan.util;

import java.util.List;

public class PageModel<T> {
    /**
     * 查询总数目
     */
    private Long totalCount;
    /**
     * 符合条件结果集
     */
    private List<T> data;

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
