package com.weixin.test;

import net.sf.json.JSONObject;

import com.weixin.po.AccessToken;
import com.weixin.util.WeixinUtil;

public class WeixinTest {
	public static void main(String[] args) {
		try {
			AccessToken token = WeixinUtil.getAccessToken();
			System.out.println("Ʊ��"+token.getToken());
			System.out.println("��Чʱ��"+token.getExpireIn());
			
//			String path = "D:/weixin.jpg";
//			String meadiaId = WeixinUtil.upload(path, token.getToken(), "thumb");
			String menu = JSONObject.fromObject(WeixinUtil.initMenu()).toString();
			int result = WeixinUtil.createMenu(token.getToken(), menu);
			if(result==0){
				System.out.println("�����˵��ɹ�");
			}else{
				System.out.println("�����룺"+result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
