package top.showtan.model;

import top.showtan.entity.ProductPicture;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductModel {
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
     * 商品价格
     */
    private Long price;
    /**
     * 商品简介
     */
    private String introduction;
    /**
     * 商品创建人id
     */
    private Integer creatorId;
    /**
     * 商品创建人名称
     */
    private String creatorName;
    /**
     * 商品创建时间
     */
    private Date createdTime;
    /**
     * 商品状态
     */
    private String status;

    private List<ProductPicture> pictures;

    public ProductModel() {
        pictures = new ArrayList<>();
    }

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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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

    public List<ProductPicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<ProductPicture> pictures) {
        this.pictures = pictures;
    }

 /*   public String getProductCategory(){

    }*/
}
