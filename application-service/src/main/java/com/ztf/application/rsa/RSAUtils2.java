package com.ztf.application.rsa;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA加密和解密工具
 *
 * @Author: syj
 * @CreateDate: 2018/7/20 16:52
 */
public class RSAUtils2 {


    /**
     * 数字签名，密钥算法
     */
    private static final String RSA_KEY_ALGORITHM = "RSA";
    /**
     * 数字签名签名/验证算法
     */
    private static final String SIGNATURE_ALGORITHM = "MD5withRSA";
    /**
     * RSA密钥长度，RSA算法的默认密钥长度是1024密钥长度必须是64的倍数，在512到65536位之间
     */
    private static final int KEY_SIZE = 1024;

    /**
     * 生成密钥对
     */
    private static Map<String, String> initKey() throws Exception {
        KeyPairGenerator keygen = KeyPairGenerator.getInstance(RSA_KEY_ALGORITHM);
        SecureRandom secrand = new SecureRandom();
        /**
         * 初始化随机产生器
         */
        secrand.setSeed("zhangtf".getBytes());
        /**
         * 初始化密钥生成器
         */
        keygen.initialize(KEY_SIZE, secrand);
        KeyPair keys = keygen.genKeyPair();

        byte[] pub_key = keys.getPublic().getEncoded();
        String publicKeyString = Base64.encodeBase64String(pub_key);

        byte[] pri_key = keys.getPrivate().getEncoded();
        String privateKeyString = Base64.encodeBase64String(pri_key);

        Map<String, String> keyPairMap = new HashMap<>();
        keyPairMap.put("publicKeyString", publicKeyString);
        keyPairMap.put("privateKeyString", privateKeyString);

        return keyPairMap;
    }

    /**
     * 密钥转成字符串
     *
     * @param key
     * @return
     */
    public static String encodeBase64String(byte[] key) {
        return Base64.encodeBase64String(key);
    }

    /**
     * 密钥转成byte[]
     *
     * @param key
     * @return
     */
    public static byte[] decodeBase64(String key) {
        return Base64.decodeBase64(key);
    }

    /**
     * 公钥加密
     *
     * @param data      加密前的字符串
     * @param publicKey 公钥
     * @return 加密后的字符串
     * @throws Exception
     */
    public static String encryptByPubKey(String data, String publicKey) throws Exception {
        byte[] pubKey = RSAUtils2.decodeBase64(publicKey);
        byte[] enSign = encryptByPubKey(data.getBytes(), pubKey);
        return Base64.encodeBase64String(enSign);
    }

    /**
     * 公钥加密
     *
     * @param data   待加密数据
     * @param pubKey 公钥
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPubKey(byte[] data, byte[] pubKey) throws Exception {
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pubKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥加密
     *
     * @param data       加密前的字符串
     * @param privateKey 私钥
     * @return 加密后的字符串
     * @throws Exception
     */
    public static String encryptByPriKey(String data, String privateKey) throws Exception {
        byte[] priKey = RSAUtils2.decodeBase64(privateKey);
        byte[] enSign = encryptByPriKey(data.getBytes(), priKey);
        return Base64.encodeBase64String(enSign);
    }

    /**
     * 私钥加密
     *
     * @param data   待加密的数据
     * @param priKey 私钥
     * @return 加密后的数据
     * @throws Exception
     */
    public static byte[] encryptByPriKey(byte[] data, byte[] priKey) throws Exception {
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(priKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥解密
     *
     * @param data   待解密的数据
     * @param pubKey 公钥
     * @return 解密后的数据
     * @throws Exception
     */
    public static byte[] decryptByPubKey(byte[] data, byte[] pubKey) throws Exception {
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pubKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥解密
     *
     * @param data      解密前的字符串
     * @param publicKey 公钥
     * @return 解密后的字符串
     * @throws Exception
     */
    public static String decryptByPubKey(String data, String publicKey) throws Exception {
        byte[] pubKey = RSAUtils2.decodeBase64(publicKey);
        byte[] design = decryptByPubKey(Base64.decodeBase64(data), pubKey);
        return new String(design);
    }

    /**
     * 私钥解密
     *
     * @param data   待解密的数据
     * @param priKey 私钥
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPriKey(byte[] data, byte[] priKey) throws Exception {
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(priKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥解密
     *
     * @param data       解密前的字符串
     * @param privateKey 私钥
     * @return 解密后的字符串
     * @throws Exception
     */
    public static String decryptByPriKey(String data, String privateKey) throws Exception {
        byte[] priKey = RSAUtils2.decodeBase64(privateKey);
        byte[] design = decryptByPriKey(Base64.decodeBase64(data), priKey);
        return new String(design);
    }

    /**
     * RSA签名
     *
     * @param data   待签名数据
     * @param priKey 私钥
     * @return 签名
     * @throws Exception
     */
    public static String sign(byte[] data, byte[] priKey) throws Exception {
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(priKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        // 生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 实例化Signature
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        // 初始化Signature
        signature.initSign(privateKey);
        // 更新
        signature.update(data);
        return Base64.encodeBase64String(signature.sign());
    }

    /**
     * RSA校验数字签名
     *
     * @param data   待校验数据
     * @param sign   数字签名
     * @param pubKey 公钥
     * @return boolean 校验成功返回true，失败返回false
     */
    public boolean verify(byte[] data, byte[] sign, byte[] pubKey) throws Exception {
        // 实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        // 初始化公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pubKey);
        // 产生公钥
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        // 实例化Signature
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        // 初始化Signature
        signature.initVerify(publicKey);
        // 更新
        signature.update(data);
        // 验证
        return signature.verify(sign);
    }

    public static void main(String[] args) {
        try {
            Map<String, String> keyMap = initKey();
//            String publicKeyString = keyMap.get("publicKeyString");
//            String privateKeyString = keyMap.get("privateKeyString");

            String publicKeyString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDhHt8IcYQWUWZkhbZBDmdh9VtKVDEPkwiB99jvQbpvgdXz+lWX7gf0J6ooAMLjjrlVuZKgGUHVH3n9RlwHiPlSXsu124kt5sF3tjLsByaEULE0Z0/cnJxdUZw5dF/jkGGGJuoF+Dvf4tKETbMKoKEcD6b60pIrGcnWS9OcaPikNQIDAQAB";
            String privateKeyString = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAOEe3whxhBZRZmSFtkEOZ2H1W0pUMQ+TCIH32O9Bum+B1fP6VZfuB/QnqigAwuOOuVW5kqAZQdUfef1GXAeI+VJey7XbiS3mwXe2MuwHJoRQsTRnT9ycnF1RnDl0X+OQYYYm6gX4O9/i0oRNswqgoRwPpvrSkisZydZL05xo+KQ1AgMBAAECgYA1V6RPkt5Sj30sqoZSCgCzNHkqNqLdZuCoMztDDhcIHwK8af/oHS3jS5bFHdEQLJx71JoKt5Fi16+HNSnPW0SALy38n716kmTsuUtPvs0+CsvKVsnYSdtEssyyAAWW/bFDjqFVydiaskgdR7UPNjIgF+p1k362aDqHEG5qU7eBjQJBAPyOPVkF+8IdbSfr1IhxsMMgvkYR8ZV0R+hsssfkxT65P8+VKXQ5tVrSHJhFHuufqRPYyLW+biUZp9EQ60BEPA8CQQDkMNij2BLOg3KoIESt4y+B3M11mBModtMfV3C0GpjMzFcoaoha9CDSw/X2ktlfB4LPDiaKaqEpn6AhOJUQ3qd7AkBS+vVDM8kVbY+45y6MqjY21qJN6YVj4rHhtx1CunLoeftyXAMj0fY5YgHNMiDHsEzgXYEM3gXYoMZX1eRF2GWXAkBRLt+kSvdF6ZYdIn3Mr4rzVK70K0qFluC9wcojCWNbWUeY5W3ljf0vfvA+7TfQi0eKiqpDjHQR7ux1ZUADZHOJAkABL3JkckgPbFVSsQT1NYEwpw2V+C1TaosQefmZ7rFO5HvbkNa+7Q6yMVe6hPAiQisDoqyqEjK4O2Ss4z/zj3nh";
            System.out.println("公钥:" + publicKeyString);
            System.out.println("私钥:" + privateKeyString);

            // 待加密数据
            String data = "admin123";
            // 公钥加密
            String encrypt = RSAUtils2.encryptByPubKey(data, publicKeyString);
            // 私钥解密
            String decrypt = RSAUtils2.decryptByPriKey(encrypt, privateKeyString);

            System.out.println("加密前:" + data);
            System.out.println("加密后:" + encrypt);
            System.out.println("请对比:B5EB19W+9BifxRRF90LfSiKYJehV7CUucxvBw1DHCDGUSQG7Cll2lFAOPQRajTa9ZWHp9Z1JaFlUjy71fRQWBM/yXY1EM1JQpZARDiuwBNNtoL2El86hzAS3r9KptruHKJ0i7elgs+L23E8JpbSVRn0R3fOxyZ7ljQWW7SN5EA0=");
            System.out.println("解密后:" + decrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
