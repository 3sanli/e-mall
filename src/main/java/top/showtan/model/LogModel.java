package top.showtan.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogModel {
    /**
     * 日志记录id
     */
    private Integer id;
    /**
     * 商品id
     */
    private Integer productId;
    /**
     * 记录类型
     * 0：购买记录 1：卖出记录
     */
    private Integer type;
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
     * 日志涉及到的商品详情
     */
    private ProductModel product;
    /**
     * 日志涉及到的交易记录评论
     */
    private CommentModel comment;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public CommentModel getComment() {
        return comment;
    }

    public void setComment(CommentModel comment) {
        this.comment = comment;
    }

    public String getLogTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月/dd日");
        return simpleDateFormat.format(this.getCreatedTime());
    }
}