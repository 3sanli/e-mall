package top.showtan.util;

public class PageUtil {
    private Long take;
    private Long skip;

    public PageUtil(Long page, Long pageSize) {
        page -= 1;
        if (page < 0) {
            this.skip = 0L;
        } else {
            this.skip = page * pageSize;
        }
        this.take = pageSize;
    }

    public Long getTake() {
        return take;
    }

    public Long getSkip() {
        return skip;
    }
}
