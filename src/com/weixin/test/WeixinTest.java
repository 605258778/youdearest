package com.weixin.test;

import net.sf.json.JSONObject;

import com.weixin.po.AccessToken;
import com.weixin.util.WeixinUtil;

public class WeixinTest {
	public static void main(String[] args) {
		try {
			AccessToken token = WeixinUtil.getAccessToken();
			System.out.println("票据"+token.getToken());
			System.out.println("有效时间"+token.getExpireIn());
			
//			String path = "D:/weixin.jpg";
//			String meadiaId = WeixinUtil.upload(path, token.getToken(), "thumb");
			String menu = JSONObject.fromObject(WeixinUtil.initMenu()).toString();
			int result = WeixinUtil.createMenu(token.getToken(), menu);
			if(result==0){
				System.out.println("创建菜单成功");
			}else{
				System.out.println("错误码："+result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
