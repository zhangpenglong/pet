package com.pipichongwu.modules.comment.controller;

import com.pipichongwu.core.util.http.HttpsPostUtil;
import com.pipichongwu.core.util.jsonutil.JsonUtil;
import com.pipichongwu.core.util.jsonutil.StringUtils;
import org.apache.http.client.methods.HttpPost;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dongao on 2018/2/6.
 */
public class BaiduPet {

    static String host = "https://pet-chain.baidu.com/";
    static Double maxMoney = 100d;

    public static void main(String[] args){
        System.getProperties().setProperty("http.proxyHost", "127.0.0.1");
        System.getProperties().setProperty("http.proxyPort", "8888");
        select();
    }

    public static void select(){
        String cookies ="BAIDUID=AB924269A0C300145FA8F9CE9C87B527:FG=1;FP_UID=91279f2a4a5c81588b70fd66692fb331;BDUSS=RJRDN4R0NOfkhHbGc1Mlo1Z3h-WmpLdUdNQ1lHLTRrTENlYnNBQkwzMHJxcUJhQVFBQUFBJCQAAAAAAAAAAAEAAADbMqcyu7y1w7u828wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACsdeVorHXladm";
        String url = host + "data/market/queryPetsOnSale";
        String pageNo = "1";
        Map map = new HashMap();
        map.put("pageNo","1");
        map.put("pageSize","10");
        map.put("querySortType","AMOUNT_ASC");
        map.put("petIds","[]");
        map.put("lastAmount","null");
        map.put("lastRareDegree","null");
        map.put("requestId",System.currentTimeMillis()+"");
        map.put("appId","1");
        map.put("tpl","");

        try {
            String send = HttpsPostUtil.sendContent(url, map, "UTF-8", cookies);
            //String send1 = HttpsPostUtil.send(url, map, "UTF-8", cookies);
            if(StringUtils.isNotEmptyString(send)){
                Map<String, String> sendMap = JsonUtil.parseToMap(send);
                String errorMsg = sendMap.get("errorMsg");
                System.out.println("errorMsg======================"+errorMsg);
                String data = sendMap.get("data");
                if(StringUtils.isNotEmptyString(data)){
                    Map<String, String> dataMap = JsonUtil.parseToMap(data);
                    String hasData = dataMap.get("hasData");
                    if("true".equals(hasData)){
                        String petsOnSale = dataMap.get("petsOnSale");
                        if(StringUtils.isNotEmptyString(petsOnSale)){
                            ArrayList list = JsonUtil.fromJson(petsOnSale, ArrayList.class);
                            for(Object obj : list){
                                Map petMap = (Map) obj;
                                String amount = (String) petMap.get("amount");
                                if(maxMoney > Double.valueOf(amount)){
                                    System.out.println("==========================买");
                                }else{
                                    System.out.println("==========================结束");
                                }
                            }
                        }else {
                            System.out.println("查询petsOnSale为空=====================");
                        }
                    }else{
                        System.out.println("查询hash不为true====================="+hasData);
                    }

                }else {
                    System.out.println("查询data为空=====================");
                }

            }else {
                System.out.println("查询为空=====================");
            }
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
