package top.showtan.model;


import java.util.Date;

public class BuyModel {
    /**
     * 用户购买记录id
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
     * 购买状态
     */
    private String status;

    /**
     * 购买记录涉及到的商品
     */
    private ProductModel product;
    /**
     * 该次交易是否被评论
     */
    private Boolean comment;

    public BuyModel() {
        this.comment = false;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public Boolean getComment() {
        return comment;
    }

    public void setComment(Boolean comment) {
        this.comment = comment;
    }
}
