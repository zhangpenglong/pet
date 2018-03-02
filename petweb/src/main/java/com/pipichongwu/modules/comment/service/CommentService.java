package com.pipichongwu.modules.comment.service;

import com.pipichongwu.modules.comment.model.Comment;
import com.pipichongwu.modules.stick.model.Stick;

import java.util.List;

/**
 * Created by dongao on 2018/1/30.
 */
public interface CommentService {
    List<Comment> selectByStickPage(Comment comment);
    List<Comment> selectReply(Integer stickId);
    void addCom(Comment comment);
}
