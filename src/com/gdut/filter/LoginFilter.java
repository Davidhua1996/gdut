//package com.gdut.filter;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.protocol.HttpClientContext;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
///**
// * 学生登陆过滤
// * @author David
// *
// */
//public class LoginFilter implements Filter {
//
//	@Override
//	public void destroy() {
//		
//	}
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response,
//			FilterChain chain) throws IOException, ServletException {
//		HttpClientContext context = new HttpClientContext();
//		CloseableHttpClient httpClient = HttpClients.createDefault();
//		HttpPost httpPost = null;
//		HttpResponse httpResponse = null;
//		try{
//			httpPost = new HttpPost("http://jwgl.gdut.edu.cn/");
//			httpPost.setHeader("Accept","application/html");
//			httpPost.setHeader("Content-Type","application/html;charset=utf-8");
//			response = httpClient.execute(httpPost, context);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//		
//	}
//
//}
