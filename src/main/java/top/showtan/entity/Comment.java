package top.showtan.entity;

import java.util.Date;

public class Comment {
    /**
     * 评论id
     */
    private Integer id;
    /**
     * 商品id
     */
    private Integer productId;
    /**
     * 评论内容
     */
    private String comment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
}
