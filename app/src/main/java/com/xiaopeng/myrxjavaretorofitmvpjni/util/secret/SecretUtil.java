package com.xiaopeng.myrxjavaretorofitmvpjni.util.secret;

import android.text.TextUtils;

import com.orhanobut.logger.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;


/**
 * @author Mzc.wang
 * @version 2014年7月23日下午1:51:18
 */
public class SecretUtil {

    public static final int NEW_DECRYPTION = 1;
    public static final int OLD_DECRYPTION = 2;
    public static final int LB_DECRYPTION = 3;

    private static final String TAG = "SecretUtil";

    private static final String ALGORITHM = "DESede"; // 定义 加密算法,可用 DES,DESede,Blowfish

    private static SecretUtil sSecretUtil;

    public static String SECRET_KEY;
    public static String SECRET_KEY_OLD;
    public static String SECRET_KEY_LB;

    // 向量
    public static String IV;
    public static String IV_OLD;
    public static String IV_LB;
    // 加解密统一使用的编码方式
    private final static String encoding = "utf-8";

    public static SecretUtil getInstance() {

        if (null == sSecretUtil) {
            sSecretUtil = new SecretUtil();
        }
        return sSecretUtil;
    }

    private static String getSecretKey(String text) {
        return MD5.getMD5(text).substring(0, 10) + text.substring(10);
    }

    @SuppressWarnings("unused")
    private static String decodeByBase64(String src) {

        return new String(Base64Util.decode(src));
    }

    private static String encodeByBase64(byte[] src) {
        return Base64Util.encode(src);
    }

    //----------------------------------   加密   -------------------------------------------------------

    public static String encryption(String param) {
        return encryptionSwitch(param);
    }

    private static String encryptionSwitch(String param) {
        if (TextUtils.isEmpty(param)) {
//            throw new EcommerceException(new Result(Result.PARAM_ERROR));
            return "";
        }
        try {
            Logger.t(TAG).d("encryption param:" + param);
            String UrlencoderParam = URLEncoder.encode(param, "utf-8");
            Logger.t(TAG).d(" encryption urlencode :" + UrlencoderParam);
            byte[] encrypitonKey = encryptionBy3DESSwitch(UrlencoderParam);
            Logger.t(TAG).d("encryption 3des :" + encrypitonKey);
            return encodeByBase64(encrypitonKey);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    // key byte为加密密钥，长度为24字节
    // src为被加密的数据缓冲区（源）
    private static byte[] encryptionBy3DESSwitch(String plainText) {
        try {
            // 生成密钥
            Key deskey = null;
            DESedeKeySpec spec = null;

            // 加密
            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
            IvParameterSpec ips = null;
            spec = new DESedeKeySpec(getSecretKey(SECRET_KEY).getBytes());
            ips = new IvParameterSpec(IV.getBytes());

            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
            deskey = keyfactory.generateSecret(spec);

            cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);

            return cipher.doFinal(plainText.getBytes(encoding));
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //----------------------------------  解密   -------------------------------------------------------

    public static String decryption(String param) {
        return decryptionSwitch(param);
    }

    private static String decryptionSwitch(String param) {
        if (TextUtils.isEmpty(param)) {
            return "";
        }
        try {
            Logger.t(TAG).d("decryption param:" + param);
            byte[] decodeKey = Base64Util.decode(param);
            Logger.t(TAG).d("decryption base64:" + decodeKey);
            String decryptParam = new String(decryptionBy3DESSwitch(decodeKey));
            Logger.t(TAG).d("decryption 3des:" + decryptParam);
            Logger.t(TAG).d("decryption decode:" + URLDecoder.decode(decryptParam, "utf-8"));
            return URLDecoder.decode(decryptParam, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    // keybyte为加密密钥，长度为24字节
    // src为加密后的缓冲区
    private static byte[] decryptionBy3DESSwitch(byte[] encryptText) {
        try {
            // 生成密钥
            Key deskey = null;
            DESedeKeySpec spec = null;

            // 解密
            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
            IvParameterSpec ips = null;

            spec = new DESedeKeySpec(getSecretKey(SECRET_KEY).getBytes());
            ips = new IvParameterSpec(IV.getBytes());

            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
            deskey = keyfactory.generateSecret(spec);

            cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
            return cipher.doFinal(encryptText);

        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
