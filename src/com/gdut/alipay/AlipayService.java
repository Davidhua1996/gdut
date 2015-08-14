package com.gdut.alipay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdut.domain.Order;
import com.gdut.domain.OrderItems;

@Service("alipayService")
public class AlipayService {
	@Resource(name="payConfiguration")
	private Configuration configuration;
	/**
	 * 支付请求
	 */
	public void pay(Order order){
		Map<String,String> params =new HashMap<String,String>();
		params.put("service",configuration.getService());
		params.put("partner", configuration.getPartner());
		params.put("seller_email", configuration.getSeller_email());
		params.put("_input_charset",configuration.getInput_charset());
		//收款类型
		params.put("payment_type","1");
		//服务器异步通知路径
		params.put("notify_url", "");
		//成功后页面跳转
		params.put("return_url", "");
		//商户网站唯一订单号
		params.put("out_trade_no", order.getId().toString());
		//商品(订单)名称
		List<OrderItems> list = order.getOrderLists();
		StringBuilder name = new StringBuilder();
		for(int i=0;i<list.size();i++){
			if(i==list.size()-1){
				name.append(list.get(i).getBrand());
			}else{
				name.append(list.get(i).getBrand()+",");
			}
		}
		params.put("subject",name.toString());
		//付款金额
		params.put("price",order.getTotalAmount().toString());
		
	}
}
