package com.gdut.sms;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.gdut.util.Encrypter;
import com.google.gson.Gson;
@Service("SMSService")
public class SMSService {
	private Logger logger=Logger.getLogger(SMSService.class);
	@Resource(name="smsConfiguration")
	private Configuration config;
	/**
	 * 发送短信
	 */
	@SuppressWarnings({ "unchecked"})
	public String sendMsg(String to,String param){
		DefaultHttpClient httpClient=new DefaultHttpClient();
		StringBuffer sb = new StringBuffer("https://api.ucpaas.com");
		Map<String, Object> result= new HashMap<String,Object>();
		try{
			SimpleDateFormat simple = new  SimpleDateFormat("yyyyMMddHHmmss");
			String timeStamp=simple.format(new Date());
			String sig = config.getAccountSid()+config.getAuthToken()+timeStamp;
			String signature = Encrypter.encodeSignature(sig);
			String url =sb.append("/").append(config.getVersion())
						.append("/Accounts/").append(config.getAccountSid())
						.append("/Messages/templateSMS")
						.append("?sig=").append(signature).toString();
			TemplateSMS templateSMS = new TemplateSMS();
			templateSMS.setAppId(config.getAppId());
			templateSMS.setTemplateId(config.getTemplatedId());
			templateSMS.setTo(to);
			templateSMS.setParam(param);
			Gson gson = new Gson();
			String body = gson.toJson(templateSMS);
			body="{\"templateSMS\":"+body+"}";
			System.out.println(body);
			logger.info(body);
			//发送短信
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept","application/json");
			httpPost.setHeader("Content-Type","application/json;charset=utf-8");
			String auth = Encrypter.base64Encodeer(config.getAccountSid()+":"+timeStamp);
			httpPost.setHeader("Authorization",auth);
			BasicHttpEntity requestBody = new BasicHttpEntity();
			requestBody.setContent(new ByteArrayInputStream(body.getBytes("UTF-8")));
			requestBody.setContentLength(body.getBytes("UTF-8").length);
			httpPost.setEntity(requestBody);
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if(entity!=null){
				String res=EntityUtils.toString(entity);
				result=(Map<String,Object>)gson.fromJson(res,Object.class);
			}
			EntityUtils.consume(entity);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			httpClient.getConnectionManager().shutdown();
		}
		String code=(((Map<String,Object>)(result.get("resp"))).get("respCode")).toString();
		if(code.equals("000000")){
			return "发送信息成功，新的密码已经发到你的手机，请查收";
		}else{
			return "网络异常，发送信息失败";
		}
	}
}
