package com.gdut.interceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.gdut.domain.User;
import com.gdut.util.Authority;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.opensymphony.xwork2.util.ValueStack;

@SuppressWarnings("serial")
public class AuthorInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		String methodName=invocation.getProxy().getMethod();
		ValueStack stack = ActionContext.getContext().getValueStack();
		Map<String,Object> map = new HashMap<String,Object>();
		Method method=invocation.getAction().getClass().getMethod(methodName);
		Map<String,Object> session=invocation.getInvocationContext().getSession();
		System.out.println("pass2");
		String role=null;
		if(null!=session.get("user")&&!"".equals(session.get("user").toString())){
			role=((User)session.get("user")).getRole().toString();
		}else{
			map.put("errorMessage","连接登陆超时，请重新登陆");
			stack.push(map);
			return Action.ERROR;
		}
		if(method.isAnnotationPresent(Authority.class)){
			Authority auth=method.getAnnotation(Authority.class);
			String privilege=auth.privilege();
			String[] privileges=privilege.split(",");
			for(int i=0;i<privileges.length;i++){
				if(role==privileges[i]){
					return invocation.invoke();
				}
			}
			map.put("errorMessage","抱歉，你没有权限");
			stack.push(map);
			return Action.ERROR;
		}else{
			return invocation.invoke();
		}
	}
}
