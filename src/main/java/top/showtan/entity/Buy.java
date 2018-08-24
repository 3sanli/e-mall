package top.showtan.entity;

import java.util.Date;

public class Buy {
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
}
