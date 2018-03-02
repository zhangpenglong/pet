package com.pipichongwu.modules.stick.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.pipichongwu.core.util.http.HttpsPostUtil;
import com.pipichongwu.core.util.jsonutil.JsonUtil;
import com.pipichongwu.core.util.jsonutil.StringUtils;
import com.pipichongwu.core.util.myutil.IpUtils;
import com.pipichongwu.modules.comment.model.Comment;
import com.pipichongwu.modules.comment.service.CommentService;
import com.pipichongwu.modules.stick.model.Stick;
import com.pipichongwu.modules.stick.service.StickService;
import com.pipichongwu.modules.user.model.User;
import com.pipichongwu.modules.user.service.UserService;
import org.codehaus.jackson.node.DoubleNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dongao on 2018/1/27.
 */
@Controller
@RequestMapping("/stickController")
public class StickController {

    @Autowired
    private StickService stickService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @RequestMapping("/stickPage")
    @ResponseBody
    public String stickPage(HttpServletRequest request) throws IOException{
        Map resMap = new HashMap();
        resMap.put("code","0");
        resMap.put("msg","无数据");
        try {
            Integer id = null;
            String userId = request.getParameter("userId");
            String isMy = request.getParameter("isMy");
            String page = request.getParameter("page");
            Stick stick = new Stick();
            Comment comment = new Comment();
            comment.setPageSize(2);
            comment.setCurrentPage(1);
            stick.setPageSize(10);
            stick.setCurrentPage(Integer.valueOf(page));
            stick.setUserId(Integer.valueOf(userId));
            if(StringUtils.isNotEmptyString(isMy) && "true".equals(isMy)){
                stick.setMyFlag(true);
            }
            int limitOffset = stick.getLimitOffset();
            List<Stick> sticks = stickService.selectByPage(stick);
            if(!CollectionUtils.isEmpty(sticks)){
                for(Stick stick1 : sticks){
                    if(null != stick1.getComNum() && stick1.getComNum() >0){
                        comment.setStickId(stick1.getId());
                        List<Comment> comments = commentService.selectByStickPage(comment);
                        stick1.setCommentList(comments);
                    }
                    Date creatDate = stick1.getCreatDate();
                    if(null != stick1.getPicNum() && stick1.getPicNum() > 0){
                        String pic = stick1.getPic();
                        String[] split = pic.split(";");
                        List list = CollectionUtils.arrayToList(split);
                        stick1.setPicList(list);
                    }
                }
                int totalPage = stick.getTotalPage();
                resMap.put("code","1");
                resMap.put("msg",sticks);
                resMap.put("totalPage",totalPage);
                String s = JsonUtil.toJson(resMap);
                return s;
            }
        } catch (Exception e) {
            resMap.put("msg","请求无数据");
        }

        return JsonUtil.toJson(resMap);
    }


    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request) throws Exception{
        Map map = new HashMap();
        System.out.println("getIpAddr:"+ IpUtils.getIpAddr(request));
        System.out.println("getRemoteAddr:"+IpUtils.getRemoteAddr(request));
        System.out.println("getClientIpAddr:"+IpUtils.getClientIpAddr(request));
        System.out.println("getClientIpAddress:"+IpUtils.getClientIpAddress(request));
        User user = null;
        try {
            String code = request.getParameter("code");
            String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wx21b9057159fbbe2c&secret=4374cd1b695123d66a98e1281ca1223d&js_code="+code+"&grant_type=authorization_code";
            String send = HttpsPostUtil.send(url, new HashMap<>(), "UTF-8");
            Map<String, String> map2 = JsonUtil.parseToMap(send);
            String openid = map2.get("openid");
            /*String openid = request.getParameter("openid");*/
            String userbase = request.getParameter("userInfo");
            if( StringUtils.isEmptyString(userbase)){  //说明没有同意获取用户信息
                user = new User();
                user.setUserName("游客");
                userService.insert(user);
                map.put("userId",user.getId());
                map.put("userName",user.getUserName());
                return JsonUtil.toJson(map);
            }
            user = userService.selectByOpenId(openid);
            if(null == user){
                user = new User();
                user.setLoginIp(IpUtils.getClientIpAddr(request));
                Map<String, String> map1 = JsonUtil.parseToMap(userbase);
                String nickName = map1.get("nickName");
                String province = map1.get("province");
                String gender = map1.get("gender");
                String country = map1.get("country");
                String city = map1.get("city");
                String avatarUrl = map1.get("avatarUrl");
                if(StringUtils.isNotEmptyString(nickName)){
                    user.setWxUserName(nickName);
                    user.setUserName(nickName);
                }
                if(StringUtils.isNotEmptyString(province)){
                    user.setProvince(province);
                }
                if(StringUtils.isNotEmptyString(gender)){
                    user.setGender(Integer.valueOf(gender));
                }
                if(StringUtils.isNotEmptyString(country)){
                    user.setCountry(country);
                }
                if(StringUtils.isNotEmptyString(city)){
                    user.setCity(city);
                }
                if(StringUtils.isNotEmptyString(avatarUrl)){
                    user.setHeadUrl(avatarUrl);
                }
                String latitude = request.getParameter("latitude");
                if(StringUtils.isNotEmptyString(latitude)){
                    String speed = request.getParameter("speed");
                    String longitude = request.getParameter("longitude");
                    String accuracy = request.getParameter("accuracy");
                    user.setSpeed(Double.valueOf(speed));
                    user.setLatitude(Double.valueOf(latitude));
                    user.setLongitude(Double.valueOf(longitude));
                    user.setAccuracy(Double.valueOf(accuracy));
                }

                user.setIsValid(1);
                user.setLive(1);
                user.setOpenid(openid);
                user.setLoginTime(new Date());
                userService.insert(user);
            }else{
                String latitude = request.getParameter("latitude");
                if(StringUtils.isNotEmptyString(latitude)){
                    String speed = request.getParameter("speed");
                    String longitude = request.getParameter("longitude");
                    String accuracy = request.getParameter("accuracy");
                    user.setSpeed(Double.valueOf(speed));
                    user.setLatitude(Double.valueOf(latitude));
                    user.setLongitude(Double.valueOf(longitude));
                    user.setAccuracy(Double.valueOf(accuracy));
                }
                user.setLoginIp(IpUtils.getClientIpAddr(request));
                user.setLoginTime(new Date());
                userService.updateUser(user);
            }
        } catch (NumberFormatException e) {
            return JsonUtil.toJson(map);
        }
        map.put("userId",user.getId());
        map.put("userName",user.getUserName());
        return JsonUtil.toJson(map);
    }

    @RequestMapping("/toView")
    @ResponseBody
    public String toView(HttpServletRequest request) throws IOException{
        Map resMap = new HashMap();
        resMap.put("code","0");
        resMap.put("msg","失败");
        try {
            Map dataMap = new HashMap();
            String stickId = request.getParameter("stickId");
            String userId = request.getParameter("userId");
            Stick myStick = new Stick();
            myStick.setUserId(Integer.valueOf(userId));
            myStick.setId(Integer.valueOf(stickId));
            Stick stick = stickService.selectById(myStick);
            if(null != stick.getComNum() && stick.getComNum() >0){

                Comment comment = new Comment();
                comment.setPageSize(100);
                comment.setCurrentPage(1);
                comment.setStickId(Integer.valueOf(stickId));
                comment.setUserId(Integer.valueOf(userId));
                List<Comment> comments = commentService.selectByStickPage(comment);
                for(Comment com : comments){
                    Integer stickId1 = com.getId();
                    List<Comment> comments1 = commentService.selectReply(stickId1);
                    if(!CollectionUtils.isEmpty(comments1)){
                        for(Comment c : comments1){
                            if(null != c.getReplyComId()){

                            }
                        }
                        com.setReplyList(comments1);
                    }
                }
                stick.setCommentList(comments);
            }
            resMap.put("msg", stick);
            resMap.put("code","1");
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return JsonUtil.toJson(resMap);
    }


    @RequestMapping("/addStick")
    @ResponseBody
    public String addStick(HttpServletRequest request) throws IOException{
        Map resMap = new HashMap();
        resMap.put("code",0);
        resMap.put("msg","提交失败");
        try {
            String userId = request.getParameter("userId");
            String addressName = request.getParameter("addressName");
            String address = request.getParameter("address");
            String latitude = request.getParameter("latitude");
            String longitude = request.getParameter("longitude");
            String stickMsg = request.getParameter("stickMsg");
            String videoSrc = request.getParameter("videoSrc");
            String pic = request.getParameter("pic");
            if(StringUtils.isEmptyString(userId)){
                return JsonUtil.toJson(resMap);
            }

            Stick stick = new Stick();
            stick.setUserId(Integer.valueOf(userId));
            if(StringUtils.isNotEmptyString(addressName)){
                stick.setAddressName(addressName);
            }
            if(StringUtils.isNotEmptyString(videoSrc)){
                stick.setVideo(videoSrc);
            }
            if(StringUtils.isNotEmptyString(addressName)){
                stick.setAddressName(addressName);
            }
            if(StringUtils.isNotEmptyString(address)){
                stick.setAddress(address);
            }
            if(StringUtils.isNotEmptyString(latitude)){
                stick.setLatitude(Double.valueOf(latitude));
            }
            if(StringUtils.isNotEmptyString(longitude)){
                stick.setLongitude(Double.valueOf(longitude));
            }
            if(StringUtils.isNotEmptyString(stickMsg)){
                stick.setStickMsg(stickMsg);
            }
            if(StringUtils.isNotEmptyString(pic)){
                stick.setPic(pic);
                stick.setPicNum(pic.split(",").length);
            }else{
                stick.setPicNum(0);
            }
            stick.setCreatDate(new Date());
            stick.setIsValid(1);
            stickService.insertStick(stick);


        } catch (IOException e) {
            return JsonUtil.toJson(resMap);
        }

        resMap.put("code",1);
        return JsonUtil.toJson(resMap);
    }


    public static void main(String[] a){
        Date da = new Date();
        Date date = new Date();
        date.getDay();

    }

}
