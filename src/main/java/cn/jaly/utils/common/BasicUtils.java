package cn.jaly.utils.common;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class BasicUtils {

    /**
     * 将字符串转化为id数组
     * @param str
     * @return
     */
    public static List<Integer> deIdList(String str){
        int length = str.length();
        int index = str.lastIndexOf(",");
        if(length == 0 || (index != length - 1)){
            return null;
        }
        String[] tokens = str.split(",");
        List<Integer> idList = new ArrayList<>();
        for(String token : tokens){
            idList.add(Integer.parseInt(token));
        }
        return idList;
    }

    /**
     * 将id数组转化为字符串
     * @param idList
     * @return
     */
    public static String enIdList(List<Integer> idList){
        if(idList == null){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for(Integer id : idList){
            sb.append(id).append(",");
        }
        return sb.toString();
    }

    public static String md5Encrypt(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * string转unicode
     * @param string
     * @return
     */
    public static String string2Unicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            unicode.append("\\u" + Integer.toHexString(c));
        }
        return unicode.toString();
    }

    /**
     * unicode转string
     * @param unicode
     * @return
     */
    public static String unicode2String(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            int data = Integer.parseInt(hex[i], 16);
            string.append((char) data);
        }
        return string.toString();
    }

    /**
     * 判断某字符串是否为整数
     *
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 获取6位随机加密码
     *
     * @param count
     * @return
     */
    public static String getRadomEncrypt(int count) {
        byte[] src = new byte[62];
        for (byte i = 48; i < 58; i++) {
            src[i - 48] = i;
        }
        for (byte i = 65; i < 91; i++) {
            src[i - 55] = i;
        }
        for (byte i = 97; i < 123; i++) {
            src[i - 61] = i;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            int pos = (int) (Math.floor(Math.random() * 62));
            sb.append((char) src[pos]);
        }
        return sb.toString();
    }

}
