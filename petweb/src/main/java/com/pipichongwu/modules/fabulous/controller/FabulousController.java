package com.pipichongwu.modules.fabulous.controller;

import com.pipichongwu.core.util.jsonutil.JsonUtil;
import com.pipichongwu.core.util.jsonutil.StringUtils;
import com.pipichongwu.modules.fabulous.model.Fabulous;
import com.pipichongwu.modules.fabulous.service.FabulousService;
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
 * Created by dongao on 2018/1/31.
 */
@Controller
@RequestMapping("/fabulousController")
public class FabulousController {

    @Autowired
    private FabulousService fabulousService;

    @RequestMapping("/addFabu")
    @ResponseBody
    public String addFabu(HttpServletRequest request) throws IOException{
        Map  resMap = new HashMap();
        resMap.put("code",0);
        resMap.put("msg","失败");
        try {
            String userId = request.getParameter("userId");
            String stickId = request.getParameter("stickId");
            String comId = request.getParameter("comId");
            Fabulous fabulous = null;
            Fabulous fabulous1 = new Fabulous();
            fabulous1.setUserId(Integer.valueOf(userId));
            if(StringUtils.isNotEmptyString(comId)){
                fabulous1.setComId(Integer.valueOf(comId));
                fabulous = fabulousService.selectByComId(fabulous1);
            }else{
                fabulous1.setStickId(Integer.valueOf(stickId));
                fabulous = fabulousService.selectByUserId(fabulous1);
            }
            if(null == fabulous){
                fabulous = new Fabulous();
                fabulous.setUserId(Integer.valueOf(userId));
                if(StringUtils.isNotEmptyString(comId)){
                    fabulous.setComId(Integer.valueOf(comId));
                }else{
                    fabulous.setStickId(Integer.valueOf(stickId));
                }
                fabulous.setCreatDate(new Date());
                fabulous.setIsValid(1);
                fabulousService.addFabu(fabulous);
                Integer integer = null;
                if(StringUtils.isNotEmptyString(comId)){
                    integer =fabulousService.countFabuByComId(Integer.valueOf(comId));
                }else{
                    integer = fabulousService.countFabu(Integer.valueOf(stickId));
                }
                resMap.put("msg",integer);
                resMap.put("code",1);
                return JsonUtil.toJson(resMap);
            }else{
                resMap.put("msg","您已经赞过了");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonUtil.toJson(resMap);
    }
}
