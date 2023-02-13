package com.example.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @Author: SongJunBao@pukkasoft.cn
 * @Date: 2022/12/19 10:03
 * @Package: com.example.utils
 * @Description: -加密工具类
 */
public class Crypto {
        /**
         * 加密
         * @param data 需要加密的数据
         * @param key 秘钥
         * @return String
         */
        public static final String encrypt(String data,String key){
            byte[] encrypted = tripleDES(Cipher.ENCRYPT_MODE, data.getBytes(), key.getBytes());//3DES加密
            byte[] encoded = Base64.encodeBase64(encrypted); // Base64编码
            return new String(encoded);
        }

        /**
         * 解密
         * @param data 待解密数据
         * @param key 秘钥
         * @return String
         */
        public static final String decrypt(String data,String key){
            byte[] decoded = Base64.decodeBase64(data);// Base64编码
            byte[] decrypted = tripleDES(Cipher.DECRYPT_MODE, decoded, key.getBytes());//3DES加密
            return new String(decrypted);
        }

        private static byte[] tripleDES(int opmode, byte[] data, byte[] secretKey) {
            return cipher("DESede", "DESede/CBC/PKCS5Padding",
                    opmode, data, "01234567".getBytes(), secretKey);
        }

        /**
         * 通用的对称加密算法
         * @param algorithm, 算法名称
         * @param transformation, 算法名称/工作模式/填充模式
         * @param opmode：Cipher.ENCRYPT_MODE和Cipher.DECRYPT_MODE
         * @param data, 明文或密文数据
         * @param iv, 初始化向量
         * @param secretKey，密钥
         * @return 加密或解密的结果
         */
        private static final byte[] cipher(String algorithm, String transformation, int opmode, byte[] data, byte[] iv,
                                           byte[] secretKey) {
            try {
                // 转换密钥
                Key key = SecretKeyFactory.getInstance(algorithm)
                        .generateSecret(new DESedeKeySpec(secretKey));
                // 转换初始化向量
                IvParameterSpec spec = new IvParameterSpec(iv);

                // 加密解密器
                Cipher cipher = Cipher.getInstance(transformation);
                cipher.init(opmode, key, spec);

                // 加密解密操作
                return cipher.doFinal(data);
            } catch (InvalidKeyException | InvalidKeySpecException
                    | NoSuchAlgorithmException | NoSuchPaddingException
                    | IllegalBlockSizeException | BadPaddingException
                    | InvalidAlgorithmParameterException e) {
                throw new RuntimeException(e);
            }
        }

    /**
     * 测试加密工具类
     * @param args args
     */
    public static void main(String[] args) {
            String str = "hello world";
            String key = "12345678qwertyui123456789";
            String encrypt = encrypt(str,key);
            String decrypt = decrypt(encrypt, key);
            System.out.println("加密前："+str);
            System.out.println("加密后："+encrypt);
            System.out.println("解密后："+decrypt);
            //ifa/YLPu2pJ2GdLCgSErag==
    }
}
