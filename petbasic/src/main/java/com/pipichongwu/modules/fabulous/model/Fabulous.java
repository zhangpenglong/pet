package com.pipichongwu.modules.fabulous.model;

import java.util.Date;

/**
 * Created by dongao on 2018/1/31.
 */
public class Fabulous {
    private Integer id;
    private Integer stickId;
    private Integer userId;
    private Date creatDate;
    private Integer isValid;
    private Integer comId;

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStickId() {
        return stickId;
    }

    public void setStickId(Integer stickId) {
        this.stickId = stickId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

}
