package top.showtan.model;


import java.util.Date;

public class SoldModel {
    /**
     * 商品卖出记录id
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
    /**
     * 创建人名称
     */
    private String creatorName;
    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 用户卖出记录所涉及到的商品
     */
    private ProductModel product;

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

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }
}
