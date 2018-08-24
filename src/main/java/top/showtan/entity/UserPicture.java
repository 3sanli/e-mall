package top.showtan.entity;

public class UserPicture {
    /**
     * 用户图片id
     */
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 图片
     */
    private String picture;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
