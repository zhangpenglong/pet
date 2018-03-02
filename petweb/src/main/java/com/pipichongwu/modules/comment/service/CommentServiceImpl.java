package com.pipichongwu.modules.comment.service;

import com.pipichongwu.modules.comment.dao.CommentMapper;
import com.pipichongwu.modules.comment.model.Comment;
import com.pipichongwu.modules.stick.model.Stick;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dongao on 2018/1/30.
 */
@Service
public class CommentServiceImpl implements CommentService {

   @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> selectByStickPage(Comment comment) {
        return commentMapper.selectByStickPage(comment);
    }

    @Override
    public List<Comment> selectReply(Integer stickId) {
        return commentMapper.selectReply(stickId);
    }

    @Override
    public void addCom(Comment comment){
        commentMapper.addCom(comment);
    }
}
