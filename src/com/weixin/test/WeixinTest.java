package com.weixin.test;

import com.weixin.po.AccessToken;
import com.weixin.util.WeixinUtil;

public class WeixinTest {
	public static void main(String[] args) {
		try {
			AccessToken token = WeixinUtil.getAccessToken();
			System.out.println("Ʊ��"+token.getToken());
			System.out.println("��Чʱ��"+token.getExpireIn());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
