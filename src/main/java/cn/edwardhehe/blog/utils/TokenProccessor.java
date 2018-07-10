package cn.edwardhehe.blog.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 利用md5生成令牌
 */
public class TokenProccessor {

    private static final TokenProccessor processor = new TokenProccessor();

    private TokenProccessor() {
    }

    /**
     * 单例模式获取令牌处理器
     */
    public static TokenProccessor getInstance() {
        return processor;
    }

    /**
     * 将内容转化为16进制数字
     *
     * @param content
     * @return
     */
    public String makeToken(String content) {

        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] = md.digest(content.getBytes());
            //base64编码--任意二进制编码明文字符
            BigInteger bigInt = new BigInteger(1, md5);
            return bigInt.toString(16);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}

