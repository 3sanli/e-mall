package top.showtan.entity;

public class ProductPicture {
    /**
     * 商品图片id
     */
    private Integer id;
    /**
     * 商品id
     */
     private Integer productId;
    /**
     * 图片
     */
    private String picture;
    /**
     * 是否为主图
     */
    private Boolean main;

    public ProductPicture() {
        this.main = false;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }
}
