package top.showtan.model.criteria;

public class ProductCriteria {
    /**
     * 商品id
     */
    private Integer id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品类别id
     */
    private Integer productCategoryId;
    /**
     * 商品创建人id
     */
    private Integer creatorId;
    /**
     * 商品价格
     * 0:从低到高
     * 1:从高到低
     */
    private Integer priceCriteria;

    /**
     * 卖家积分
     * 0:从低到高
     * 1:从高到低
     */
    private Integer creditCriteria;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getPriceCriteria() {
        return priceCriteria;
    }

    public void setPriceCriteria(Integer priceCriteria) {
        this.priceCriteria = priceCriteria;
    }

    public Integer getCreditCriteria() {
        return creditCriteria;
    }

    public void setCreditCriteria(Integer creditCriteria) {
        this.creditCriteria = creditCriteria;
    }
}
