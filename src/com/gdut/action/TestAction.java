package com.gdut.action;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.gdut.base.BaseAction;
//import com.gdut.base.OrderStatus;
//import com.gdut.base.Role;
//import com.gdut.base.UserStatus;
//import com.gdut.domain.Order;
//import com.gdut.domain.PageBean;
import com.gdut.domain.User;
//import com.gdut.dto.SalesSumary;
import com.gdut.util.EmailUtil;
import com.opensymphony.xwork2.ActionContext;
import com.swetake.util.Qrcode;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class TestAction extends BaseAction<User>
{
	@Resource
	private EmailUtil emailUtil;
	public String add()
	{
		BufferedImage image=QrCodeCommon("怎么感觉我说得像杀人犯，微信大哥不要屏蔽我啊啊啊啊啊！（已死），看完哦哦一下",null,10);
		try {
			ImageIO.write(image, "jpg",response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		productService.findbyVendor("xx");
		return null;
	}
	
	public String addUI()
	{
		return "addUI";
	}
	public String list()
	{
		List<User> list=userService.findAll();
		ActionContext.getContext().put("list", list);
		return "list";
	}
	private BufferedImage QrCodeCommon(String content,String imageType,int size){
		BufferedImage bufImage=null;
		try{
			Qrcode qrcodeHandler=new Qrcode();
			//设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小  
			qrcodeHandler.setQrcodeErrorCorrect('Q');
			qrcodeHandler.setQrcodeEncodeMode('B');
			// 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
			qrcodeHandler.setQrcodeVersion(size);
			//获得内容的字节数组,设置编码格式
			byte[] contentBytes=content.getBytes("UTF-8");
			//图片尺寸
			int imgSize=67+12*(size-1);
			bufImage=new BufferedImage(imgSize,imgSize,BufferedImage.TYPE_INT_RGB);
			Graphics2D gs=bufImage.createGraphics();
			//设置背景色
			gs.setBackground(Color.white);
			gs.clearRect(0, 0, imgSize, imgSize);
			//设定图像颜色>Black
			gs.setColor(Color.BLACK);
			//设置偏移量,不设置可能导致解析错误
			int pixoff=2;
			//输出内容 >二维码
			if(contentBytes.length>0&&contentBytes.length<800){
				boolean[][] codeOut=qrcodeHandler.calQrcode(contentBytes);
				for(int i=0; i<codeOut.length;i++){
					for(int j=0;j<codeOut.length;j++){
						if(codeOut[j][i]){
							gs.fillRect(j*3+pixoff,i*3+pixoff,3,3);
						}
					}
				}
			}else{
				System.out.println("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");
			}
			gs.dispose();
			bufImage.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
		return bufImage;
	}
}
