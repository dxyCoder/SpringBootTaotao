package com.dxy.dxyTest;

import java.security.MessageDigest;


/**
 * PACKAGE_NAME: com.dxy.dxyTest
 * PROJECT_NAME :captcha-java-demo
 * NAME: CheckSumBuilder
 * DATE :2018/4/18
 *
 *      计算CheckSum
 */


public class CheckSumBuilder {


    //获取CheckSum
    public static String getCheckSum(String appSecret,String nonce ,String curTime){
        return encode("sha1",appSecret + nonce + curTime);//注意这里sha1是数字“1，不是英文”l

    }
    // 计算并获取CheckSum
//    public static String getCheckSum(String appSecret, String nonce, String curTime) {
//        return encode("sha1", appSecret + nonce + curTime);
//    }


    //获取MD5值
    public static String getMD5(String requestBody){
        return encode("md5", requestBody);
    }





    private static String encode(String algorithm,String value){
        if(value==null){
            return null;
        }
        try {
            MessageDigest messageDigest =
                    MessageDigest.getInstance(algorithm);

            messageDigest.update(value.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);

        }

    }

    private static String getFormattedText(byte[] bytes){
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (int j=0;j<len;j++){
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();

    }

    //可随机的值
    public static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};


}
