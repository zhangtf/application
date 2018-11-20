package com.ztf.application.rsa;

import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA加密和解密工具
 * @author zhangtf
 * @createTime 2018-11-11 11:11:11
 */
public class RSAUtils {

    /**
     * 数字签名，密钥算法
     */
    private static final String RSA_KEY_ALGORITHM = "RSA";
    /**
     * 数字签名，密钥算法
     */
    private static final String RSA_SIGNATURE_ALGORITHM = "SHA1WithRSA";
    /**
     * 文本字符集
     */
    private static final String ENCODING = "UTF-8";

    /**
     * 公钥
     */
    private static PublicKey publicKey;
    /**
     * 私钥
     */
    private static PrivateKey privateKey;

    /**
     * 初始化公钥和私钥
     * @param publicKeyPath     公钥路径
     * @param privateKeyPath    私钥路径
     */
    public static void init (String publicKeyPath, String privateKeyPath) {
        try {
            // 设置公钥
            String publicKeyString = getKeyString(publicKeyPath);
            publicKey = getPublicKey(publicKeyString);
            // 设置私钥
            String privateKeyString = getKeyString(privateKeyPath);
            privateKey = getPrivateKey(privateKeyString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * RSA私钥加签
     * @param context       文本
     * @return              是否加签成功
     * @throws Exception
     */
    private static String addSign(String context)throws Exception{
        //加签算法：SHA1WithRSA
        Signature signature = Signature.getInstance(RSA_SIGNATURE_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(context.getBytes(ENCODING));
        byte[] sign = signature.sign();
        return encodeBase64String(sign);
    }

    /**
     * RSA公钥验签
     * @param context       文本
     * @param signData      密文
     * @return              是否验签成功
     * @throws Exception
     */
    private static boolean verifySign(String context, String signData)throws Exception{
        Signature signature = Signature.getInstance(RSA_SIGNATURE_ALGORITHM);
        signature.initVerify(publicKey);
        byte[] bytes = decodeBase64(signData);
        signature.update(context.getBytes(ENCODING));
        boolean verify = signature.verify(bytes);
        return verify;
    }

    /**
     * 密钥转成字符串
     *
     * @param key   要转换的byte[]
     * @return      字符串
     */
    private static String encodeBase64String(byte[] key) {
        return Base64.encodeBase64String(key);
    }

    /**
     * 密钥转成byte[]
     *
     * @param key   要转换的字符串
     * @return      byte[]
     */
    private static byte[] decodeBase64(String key) {
        return Base64.decodeBase64(key);
    }

    /**
     * 获取公钥对象
     * @param key   公钥文本
     * @return      公钥对象
     * @throws Exception
     */
    private static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes = decodeBase64(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 获取私钥对象
     * @param key   私钥文本
     * @return      私钥对象
     * @throws Exception
     */
    private static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes = decodeBase64(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * 获取公钥文本
     * 获取私钥文本
     */
    private static String getKeyString (String path) throws Exception {
        File file = new File(path);

        FileInputStream fis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String readLine = null;
        StringBuffer sb = new StringBuffer();
        while ((readLine = br.readLine()) != null) {
            if (!readLine.contains("-----")) {
                sb.append(readLine);
            }
        }
        return sb.toString();
    }
}
