package com.pipichongwu.modules.comment.dao;

import com.pipichongwu.modules.comment.model.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dongao on 2018/1/30.
 */
@Repository
public interface CommentMapper {
    List<Comment> selectByStickPage(Comment comment);
    List<Comment> selectReply(Integer stickId);
    void addCom(Comment comment);
}
