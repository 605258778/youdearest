package com.weixin.test;

import com.weixin.po.AccessToken;
import com.weixin.util.WeixinUtil;

public class WeixinTest {
	public static void main(String[] args){
		AccessToken token = WeixinUtil.getAccessToken();
		System.out.print("Ʊ�ݣ�"+token.getToken());
		System.out.print("��Чʱ�䣺"+token.getExpireIn());
	}
}
