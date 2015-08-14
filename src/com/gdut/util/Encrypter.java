package com.gdut.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 * 加密和解密工具
 * @author David
 *
 */
public class Encrypter {
	//定义密匙
	private static final byte[] DES_KEY={34,56,-33,85,-32,-88,-123,65};
	/**
	 * BASE64编码
	 */
	public static String base64Encodeer(String src){
		BASE64Encoder encoder = new BASE64Encoder();
		byte[] b = null;
		try {
			b=src.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encoder.encode(b);
	}
	/**
	 * 用MD5加密数字签名
	 * @param sign
	 * @return
	 */
	public static String encodeSignature(String sig){
		byte[] b = null;
		StringBuilder sb = new StringBuilder();  
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			b = digest.digest(sig.getBytes("UTF-8"));
	        for (int i = 0; i < b.length; i++) {  
	            String s = Integer.toHexString(b[i] & 0xFF);  
	            if (s.length() == 1) {  
	                sb.append("0");  
	            }  
	            sb.append(s.toUpperCase());  
	        } 
		} catch(Exception e){
			e.printStackTrace();
		}
		return sb.toString(); 
	}
	/**
	 * 密码MD5加密
	 * @param username
	 * @param password
	 */
	public static String encodePassword(String username,String password){
		int nameCode=username.hashCode();
		String code ="";
		try {
			MessageDigest digest=MessageDigest.getInstance("MD5");
			byte[] bs=password.getBytes();
			digest.update(bs);
			for(byte b:digest.digest(bs)){
				int a=(b+nameCode)&0xff;
				if(a<16){
					a+=16;
				}
				code=code+Integer.toHexString(a);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return code;
	}
	/**
	 * DES加密
	 * @param text
	 * @return
	 */
	public static String encodeDES(String text){
		String code=null;
		try{
			//随机数据源
			SecureRandom sr=new SecureRandom();
			DESKeySpec deskey=new DESKeySpec(DES_KEY);
			//创建密匙工厂
			SecretKeyFactory factory=SecretKeyFactory.getInstance("DES");
			SecretKey key=factory.generateSecret(deskey);
			//加密对象
			Cipher cipher=Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key,sr);
			code=new BASE64Encoder().encode(cipher.doFinal(text.getBytes()));
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("加密失败");
		}
		return code;
	}
	/**
	 * DES解密
	 * @param code
	 * @return
	 */
	public static String decodeDES(String code){
		String text=null;
		try{
			//随机数据源
			SecureRandom sr=new SecureRandom();
			DESKeySpec deskey=new DESKeySpec(DES_KEY);
			//创建密匙工厂
			SecretKeyFactory factory=SecretKeyFactory.getInstance("DES");
			SecretKey key=factory.generateSecret(deskey);
			//解密对象
			Cipher cipher=Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key,sr);
			text=new String(cipher.doFinal(new BASE64Decoder().decodeBuffer(code)));
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("解密失败");
		}
		return text;
	}
}
