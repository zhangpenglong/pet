package com.pipichongwu.core.util.http;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dongao on 2018/2/6.
 */
public class HttpsPostUtil {
    public static String sendContent(String url, Map<String,String> map, String encoding, String cookies) throws KeyManagementException, NoSuchAlgorithmException, IOException {
        String body = "";

        //采用绕过验证的方式处理https请求
        SSLContext sslcontext = SslUtils.createIgnoreVerifySSL();

        // 设置协议http和https对应的处理socket链接工厂的对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext))
                .build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        HttpClients.custom().setConnectionManager(connManager);

        //创建自定义的httpclient对象
        CloseableHttpClient client = HttpClients.custom().setConnectionManager(connManager).build();
//       CloseableHttpClient client = HttpClients.createDefault();
//        client.
        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
//        httpPost.setConfig(new RequestConfig());
        HttpHost proxy = new HttpHost("127.0.0.1", 8888,"https");
        RequestConfig config = null;
        config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(1 * 1000 * 60).build();
        httpPost.setConfig(config);
        //装填参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if(map!=null){
            for (Map.Entry<String, String> entry : map.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        //nvps.add(new BasicNameValuePair("", new String[]{});
        //设置参数到请求对象中
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));


        System.out.println("请求地址："+url);
        System.out.println("请求参数："+nvps.toString());

        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        httpPost.setHeader("Accept","application/json");
        httpPost.setHeader("Accept-Encoding","gzip,deflate,br");
        httpPost.setHeader("Accept-Language","zh-CN,zh;q=0.9");
        httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        httpPost.setHeader("Cookie",cookies);
        httpPost.setHeader("Content-Type","application/json");
        httpPost.setHeader("Referer","https://pet-chain.baidu.com/chain/dogMarket?t=1517912174007");
        httpPost.setHeader("Origin","https://pet-chain.baidu.com");
        httpPost.setHeader("Connection","keep-alive");
        // httpPost.setHeader("Content-Length","153");
        httpPost.setHeader("Host","pet-chain.baidu.com");
        //  httpPost.setHeader("X-Requested-With","XMLHttpRequest");

        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpPost);
        /*Header[] allHeaders = response.getAllHeaders();
        if(null != allHeaders){
            for (int i = 0; i < allHeaders.length;i++){
                if(allHeaders[i].getName().equals("Set-Cookie")){
                    String value = allHeaders[i].getValue();
                    System.out.println(value);
                }
            }
        }
*/
//        获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, encoding);
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        return body;
    }
    public static String send(String url, Map<String,String> map, String encoding) throws KeyManagementException, NoSuchAlgorithmException, IOException {
        String body = "";

        //采用绕过验证的方式处理https请求
        SSLContext sslcontext = SslUtils.createIgnoreVerifySSL();

        // 设置协议http和https对应的处理socket链接工厂的对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext))
                .build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        HttpClients.custom().setConnectionManager(connManager);

        //创建自定义的httpclient对象
        CloseableHttpClient client = HttpClients.custom().setConnectionManager(connManager).build();
//       CloseableHttpClient client = HttpClients.createDefault();
//        client.
        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
//        httpPost.setConfig(new RequestConfig());
        //装填参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if(map!=null){
            for (Map.Entry<String, String> entry : map.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        //nvps.add(new BasicNameValuePair("", new String[]{});
        //设置参数到请求对象中
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));


        System.out.println("请求地址："+url);
        System.out.println("请求参数："+nvps.toString());

        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpPost);
//        获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, encoding);
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        return body;
    }
}
