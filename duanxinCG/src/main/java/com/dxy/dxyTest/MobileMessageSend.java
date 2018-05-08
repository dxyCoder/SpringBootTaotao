package com.dxy.dxyTest;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * PACKAGE_NAME: com.dxy.dxyTest
 * PROJECT_NAME :captcha-java-demo
 * NAME: MobileMessageSend
 * DATE :2018/4/18
 *
 *          短信发送工具类
 */


public class MobileMessageSend {

    //发送验证码的请求路径URL
    private static final String SERVER_URL="https://api.netease.im/sms/sendcode.action";
    //网易云分配的账号，替换在管理后台申请的appkey
    private static final String APP_KEY="7ac42ba8fe3da6ab805a40a478209bc7";
    //网易云分配的密钥，替换appsecret
    private static final String APP_SECRET="c0fbeeb96f57";
    //随机数
    private static final String NONCE="123456";
    //短信模板id
    private static final String TEMPLATEID="3942739";
    //手机号
//    private static final String MOBILE_PHONE="18727009479";
    //验证码的长度，默认4位
    private static final String CODELEN="6";



    public static  void sendMsg(String phone) throws IOException {


        //创建执行请求的httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //POST请求
        HttpPost post = new HttpPost(SERVER_URL);


        //CheckSum的计算
        String curTime = String.valueOf((new Date().getTime() / 1000L));//get时间
        String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, NONCE, curTime);//组合appkey账号，随机数，时间

        //设置请求的header
        post.addHeader("AppKey", APP_KEY);
        post.addHeader("Nonce", NONCE);
        post.addHeader("CurTime", curTime);
        post.addHeader("CheckSum", checkSum);
        post.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

        //设置请求参数
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("mobile", phone));
        nameValuePairs.add(new BasicNameValuePair("templateid", TEMPLATEID));
        nameValuePairs.add(new BasicNameValuePair("codeLen", CODELEN));

        post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));//这里有异常需抛出

        //执行请求
        HttpResponse response = httpClient.execute(post);
        String responseEntity = EntityUtils.toString(response.getEntity(), "UTF-8");

        //获取发送状态码
        String code = JSON.parseObject(responseEntity).getString("code");


        if (code.equals("200")) {
            //这是获取发送的验证码
//            String sms= JSON.parseObject(responseEntity).getString("obj");
            System.out.println("发送成功");
            return;
        }
        System.out.println("发送失败!");



    }





}
