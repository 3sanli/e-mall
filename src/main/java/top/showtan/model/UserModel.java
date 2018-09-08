package top.showtan.model;

import top.showtan.entity.UserPicture;


public class UserModel {
    /**
     * 用户id
     */
    private Integer id;
    /**
     * 用户手机号
     */
    private String mobile;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 密码确认
     */
    private String passwordConfirm;
    /**
     * 用户权限
     */
    private String permission;
    /**
     * 用户资产
     */
    private Long money;
    /**
     * 用户信誉
     */
    private Integer credit;

    private UserPicture picture;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public UserPicture getPicture() {
        return picture;
    }

    public void setPicture(UserPicture picture) {
        this.picture = picture;
    }
}
