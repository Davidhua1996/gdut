package com.gdut.test;

//import java.security.SecureRandom;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Properties;

//import javax.crypto.Cipher;
//import javax.crypto.SecretKey;
//import javax.crypto.SecretKeyFactory;
//import javax.crypto.spec.DESKeySpec;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.objectweb.asm.xwork.tree.MultiANewArrayInsnNode;

import com.gdut.util.Encrypter;
//
//import sun.misc.BASE64Decoder;
//
//import com.cloopen.rest.sdk.utils.encoder.BASE64Encoder;
/**
 * 测试类
 * @author David
 *
 */
public class SETest extends Thread{
	
	@Test
	public void go(){
		String name = "admin";
		String password = "666666";
		String newPassword = Encrypter.encodePassword(name, password);
		System.out.println(newPassword);
	}
	@Test
	public void register() throws UnsupportedEncodingException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpClientContext context = new HttpClientContext();
		HttpResponse response;
		HttpEntity entity = null;
		HttpPost httpPost =new HttpPost("http://localhost:8080/gdut/user_validateVendorRegister.action");
		StringBody loginName = new StringBody("华D",Charset.forName("utf-8"));
		StringBody password = new StringBody("123456",ContentType.TEXT_HTML);
		StringBody address = new StringBody("广工龙洞",ContentType.TEXT_HTML);
		StringBody phoneNum = new StringBody("13456667788",ContentType.TEXT_HTML);
		//密保问题和答案
		
		//商家信息
		StringBody companyName = new StringBody("腾讯公司",ContentType.TEXT_HTML);
		StringBody areaName = new StringBody("外区",ContentType.TEXT_HTML);
		StringBody buildingName = new StringBody("C栋",ContentType.TEXT_HTML);
		FileBody img = new FileBody(new File("d:/testPic/test.jpeg"));
		HttpEntity httpEntity = MultipartEntityBuilder.create()
				.addPart("loginName",loginName)
				.addPart("password",password)
				.addPart("address", address)
				.addPart("phoneNum",phoneNum)
				.addPart("vendor.companyName",companyName)
				.addPart("vendor.areaName",areaName)
				.addPart("vendor.buildingName", buildingName)
				.addPart("picture",img).build();
		httpPost.setEntity(httpEntity);
		try {
			response = httpClient.execute(httpPost,context);
			entity = response.getEntity();
			if(null!=entity){
				System.out.println(entity);
			}
			EntityUtils.consume(entity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				httpClient.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}
