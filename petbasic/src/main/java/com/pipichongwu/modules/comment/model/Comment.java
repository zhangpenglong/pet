package com.pipichongwu.modules.comment.model;

import com.pipichongwu.core.pageutil.PageParameter;

import java.util.Date;
import java.util.List;

/**
 * Created by dongao on 2018/1/29.
 */
public class Comment extends PageParameter {
    private Integer id;
    private Integer userId;
    private Integer stickId;
    private String commentMsg;
    private String commentPic;
    private Date creatDate;
    private Integer isValid;
    private Integer replyId;
    private String comUserName;
    private String comWxUserName;
    private Integer fabulousCount;
    private Integer replyComId;
    private String repUserName;
    private String repWxUserName;

    public Integer getIsFabulous() {
        return isFabulous;
    }

    public void setIsFabulous(Integer isFabulous) {
        this.isFabulous = isFabulous;
    }

    private Integer isFabulous;


    public String getRepUserName() {
        return repUserName;
    }

    public void setRepUserName(String repUserName) {
        this.repUserName = repUserName;
    }

    public String getRepWxUserName() {
        return repWxUserName;
    }

    public void setRepWxUserName(String repWxUserName) {
        this.repWxUserName = repWxUserName;
    }

    public Integer getReplyComId() {
        return replyComId;
    }

    public void setReplyComId(Integer replyComId) {
        this.replyComId = replyComId;
    }

    public Integer getFabulousCount() {
        return fabulousCount;
    }

    public void setFabulousCount(Integer fabulousCount) {
        this.fabulousCount = fabulousCount;
    }

    private List<Comment> replyList;
    private String headUrl;

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public List<Comment> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Comment> replyList) {
        this.replyList = replyList;
    }

    public String getComUserName() {
        return comUserName;
    }

    public void setComUserName(String comUserName) {
        this.comUserName = comUserName;
    }

    public String getComWxUserName() {
        return comWxUserName;
    }

    public void setComWxUserName(String comWxUserName) {
        this.comWxUserName = comWxUserName;
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

    public Integer getStickId() {
        return stickId;
    }

    public void setStickId(Integer stickId) {
        this.stickId = stickId;
    }

    public String getCommentMsg() {
        return commentMsg;
    }

    public void setCommentMsg(String commentMsg) {
        this.commentMsg = commentMsg;
    }

    public String getCommentPic() {
        return commentPic;
    }

    public void setCommentPic(String commentPic) {
        this.commentPic = commentPic;
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }
}
