package com.kiven.xq;

import android.util.Base64;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author gxz
 * @version 2013-8-9 上午10:00:42
 * @类说明 加密
 */
public class EncryptUtils {

    //这个长度必须为16位
    private static String iv = "2345tqIv_mobsads";
    //secretKey
    private static String SecretKey = "2345android_key_";

    public static String encrypt(String text) throws Exception {
        IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());//偏移量
        SecretKeySpec keyspec = new SecretKeySpec(SecretKey.getBytes(), "AES");//生成密钥
        try {
            Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(text.getBytes());
            return Base64.encodeToString(encrypted, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String decrypt(String data) {
        IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());//偏移量
        SecretKeySpec keyspec = new SecretKeySpec(SecretKey.getBytes(), "AES");
        try {
            byte[] encrypted = Base64.decode(data.getBytes(), Base64.DEFAULT);
            Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] original = cipher.doFinal(encrypted);
            return new String(original, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


}
