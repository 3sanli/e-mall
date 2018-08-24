package top.showtan.model.criteria;

public class FavoritesCriteria {
    /**
     * 用户收藏id
     */
    private Integer id;
    /**
     * 商品id
     */
    private Integer productId;
    /**
     * 创建人id
     */
    private Integer creatorId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }
}
