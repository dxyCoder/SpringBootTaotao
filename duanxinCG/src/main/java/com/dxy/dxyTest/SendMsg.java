package com.dxy.dxyTest;

import java.io.IOException;

/**
 * PACKAGE_NAME: com.dxy.dxyTest
 * PROJECT_NAME :captcha-java-demo
 * NAME: SendMsg
 * DATE :2018/4/18
 *
 *          main测试`
 */


public class SendMsg {

    public static void main(String[] args) throws IOException {

        String Phone = "18727009479";//接受验证码的手机号码
        MobileMessageSend.sendMsg(Phone);

    }


}
