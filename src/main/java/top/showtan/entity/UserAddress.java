package top.showtan.entity;

public class UserAddress {
    /**
     * 用户地址id
     */
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 地址id
     */
    private Integer addressDictId;

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

    public Integer getAddressDictId() {
        return addressDictId;
    }

    public void setAddressDictId(Integer addressDictId) {
        this.addressDictId = addressDictId;
    }
}
