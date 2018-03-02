package com.pipichongwu.modules.comment.controller;

import com.pipichongwu.core.util.jsonutil.JsonUtil;
import com.pipichongwu.core.util.jsonutil.StringUtils;
import com.pipichongwu.modules.comment.model.Comment;
import com.pipichongwu.modules.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dongao on 2018/2/3.
 */
@Controller
@RequestMapping("/commentController")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @RequestMapping("/addCom")
    @ResponseBody
    public String addCom(HttpServletRequest request) throws IOException{

        Map resMap = new HashMap();
        resMap.put("code","0");
        resMap.put("msg","");

        try {
            String userId = request.getParameter("userId");
            String replyId = request.getParameter("replyId");
            String stickId = request.getParameter("stickId");
            String msg = request.getParameter("content");
            String replyComId = request.getParameter("replyComId");

            Comment comment = new Comment();
            if(!replyId.equals(stickId)){
                comment.setReplyId(Integer.valueOf(replyId));
            }
            if(StringUtils.isNotEmptyString(replyComId)){
              comment.setReplyComId(Integer.valueOf(replyComId));
            }
            comment.setStickId(Integer.valueOf(stickId));
            comment.setCommentMsg(msg);
            comment.setUserId(Integer.valueOf(userId));
            comment.setCreatDate(new Date());
            comment.setIsValid(1);
            commentService.addCom(comment);
            resMap.put("code","1");
            resMap.put("msg","");
        } catch (Exception e) {
            e.printStackTrace();
        }


        return JsonUtil.toJson(resMap);
    }


}
