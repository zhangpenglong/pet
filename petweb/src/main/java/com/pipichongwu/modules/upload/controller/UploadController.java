package com.pipichongwu.modules.upload.controller;

import com.alibaba.fastjson.JSON;
import com.pipichongwu.core.Constants;
import com.pipichongwu.core.util.jsonutil.JsonUtil;
import com.pipichongwu.core.util.myutil.FileEntity;
import com.pipichongwu.core.util.myutil.FileUploadTool;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dongao on 2018/2/27.
 */
@Controller
@RequestMapping("/upload")
public class UploadController {


    @RequestMapping("/uploadImages")
    @ResponseBody
    public String uploadImages(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "file", required = false) MultipartFile multipartFile) throws Exception{
        Map resMap = new HashMap();
        resMap.put("code","0");
        resMap.put("msg","上传失败");
        //获取文件需要上传到的路径
        String path = Constants.FILEIMAGES;
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
//        multipartFile.
        String userId = request.getParameter("userId");
        // logger.debug("path=" + path);

        request.setCharacterEncoding("utf-8");  //设置编码
        //获得磁盘文件条目工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();

        //如果没以下两行设置的话,上传大的文件会占用很多内存，
        //设置暂时存放的存储室,这个存储室可以和最终存储文件的目录不同
        /**
         * 原理: 它是先存到暂时存储室，然后再真正写到对应目录的硬盘上，
         * 按理来说当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的
         * 然后再将其真正写到对应目录的硬盘上
         */
        factory.setRepository(dir);
        //设置缓存的大小，当上传文件的容量超过该缓存时，直接放到暂时存储室
        factory.setSizeThreshold(1024 * 1024);
        //高水平的API文件上传处理
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {

            List<FileItem> list = upload.parseRequest(request);
            FileItem picture = null;
            for (FileItem item : list) {
                //获取表单的属性名字
                String name = item.getFieldName();
                //如果获取的表单信息是普通的 文本 信息
                if (item.isFormField()) {
                    //获取用户具体输入的字符串
                    String value = item.getString();
                    request.setAttribute(name, value);
                   // logger.debug("name=" + name + ",value=" + value);
                } else {
                    picture = item;
                }
            }

            //自定义上传图片的名字为userId.jpg
            Date date = new Date();
            String fileName = (int)((Math.random()*9+1)*1000) + ".jpg";
            String destPath = path+String.valueOf(request.getParameter("userId"))+"\\";
          //  logger.debug("destPath=" + destPath);
            File destPathdir = new File(destPath);
            if (!destPathdir.exists()) {
                destPathdir.mkdir();
            }
            destPath += fileName;
            //真正写到磁盘上
            File file = new File(destPath);
            OutputStream out = new FileOutputStream(file);
           // InputStream in = picture.getInputStream();
            InputStream in = multipartFile.getInputStream();
            int length = 0;
            byte[] buf = new byte[1024];
            // in.read(buf) 每次读到的数据存放在buf 数组中
            while ((length = in.read(buf)) != -1) {
                //在buf数组中取出数据写到（输出流）磁盘上
                out.write(buf, 0, length);
            }
            in.close();
            out.close();
            resMap.put("code",1);
            resMap.put("url","http://static.pipichongwu.cn/images/"+String.valueOf(request.getParameter("userId"))+"/"+fileName);
        } catch (FileUploadException e1) {
            return JsonUtil.toJson(resMap);
        } catch (Exception e) {
            return JsonUtil.toJson(resMap);
        }
        return JsonUtil.toJson(resMap);
    }

    @RequestMapping("/uploadVideo")
    @ResponseBody
    public String uploadVideo(@RequestParam(value = "file", required = false) MultipartFile multipartFile,HttpServletRequest request) throws IOException{
        Map resMap = new HashMap();
        resMap.put("code","0");
        resMap.put("msg","上传失败");
        //获取文件需要上传到的路径
        String path =  "D:\\pipi\\static\\";
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
//        multipartFile = request.
        FileEntity entity = new FileEntity();
        FileUploadTool fileUploadTool = new FileUploadTool();
        entity = fileUploadTool.createFile(multipartFile, request);
        if (entity != null) {
           System.out.println("成功");
           resMap.put("code","1");
           resMap.put("url","https://static.pipichongwu.cn/"+entity.getPath());
        } else {
            System.out.println("失败");
        }
        return JsonUtil.toJson(resMap);
    }



    public static void main(String[] a){
        System.out.println(new Date().getTime());
        System.out.println((Math.random()*9+1));
    }
}
