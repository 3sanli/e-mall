package top.showtan.model;

import java.util.Date;

public class ScoreModel {
    /**
     * 评分id
     */
    private Integer id;
    /**
     * 评分人id
     */
    private Integer fromId;
    /**
     * 评分人名称
     */
    private String fromName;
    /**
     * 评分对象id
     */
    private Integer toId;
    /**
     * 评分对象名称
     */
    private String toName;
    /**
     * 评分数值
     */
    private Integer credit;
    /**
     * 创建时间
     */
    private Date createdTime;

    public ScoreModel() {
    }

    public ScoreModel(Integer fromId, String fromName, Integer toId, String toName, Integer credit) {
        this.fromId = fromId;
        this.fromName = fromName;
        this.toId = toId;
        this.toName = toName;
        this.credit = credit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
