package com.pipichongwu.modules.stick.model;

import com.pipichongwu.core.pageutil.PageParameter;
import com.pipichongwu.modules.comment.model.Comment;

import java.util.Date;
import java.util.List;

/**
 * Created by dongao on 2018/1/27.
 */
public class Stick extends PageParameter {
    private Integer id;
    private Integer userId;
    private Date creatDate;
    private String pic;
    private Integer isValid;
    private String headUrl;
    private String stickMsg;
    private Integer picNum;
    private Integer comNum;
    private List<String> picList;
    private Double longitude;

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String video;
    private Double latitude;
    private String addressName;
    private String address;
    private Integer isFabulous;
    private boolean myFlag;

    public boolean isMyFlag() {
        return myFlag;
    }

    public void setMyFlag(boolean myFlag) {
        this.myFlag = myFlag;
    }

    public Integer getIsFabulous() {
        return isFabulous;
    }

    public void setIsFabulous(Integer isFabulous) {
        this.isFabulous = isFabulous;
    }

    public List<String> getPicList() {
        return picList;
    }

    public void setPicList(List<String> picList) {
        this.picList = picList;
    }

    public Integer getComNum() {
        return comNum;
    }

    public void setComNum(Integer comNum) {
        this.comNum = comNum;
    }

    public Integer getPicNum() {
        return picNum;
    }

    public void setPicNum(Integer picNum) {
        this.picNum = picNum;
    }

    public String getStickMsg() {
        return stickMsg;
    }

    public void setStickMsg(String stickMsg) {
        this.stickMsg = stickMsg;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

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

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    private String userName;
    private String wxUserName;
    private String commentMsg;
    private Integer fabulousCount;
    private Integer comId;
    private Integer replyId;
    private List<Comment> commentList;

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWxUserName() {
        return wxUserName;
    }

    public void setWxUserName(String wxUserName) {
        this.wxUserName = wxUserName;
    }

    public String getCommentMsg() {
        return commentMsg;
    }

    public void setCommentMsg(String commentMsg) {
        this.commentMsg = commentMsg;
    }

    public Integer getFabulousCount() {
        return fabulousCount;
    }

    public void setFabulousCount(Integer fabulousCount) {
        this.fabulousCount = fabulousCount;
    }
}
