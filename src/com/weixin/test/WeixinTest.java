package com.weixin.test;

import com.weixin.po.AccessToken;
import com.weixin.util.WeixinUtil;

public class WeixinTest {
	public static void main(String[] args) {
		try {
			AccessToken token = WeixinUtil.getAccessToken();
			System.out.println("票据"+token.getToken());
			System.out.println("有效时间"+token.getExpireIn());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
