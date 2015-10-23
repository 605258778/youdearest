package com.weixin.test;

import com.weixin.po.AccessToken;
import com.weixin.util.WeixinUtil;

public class WeixinTest {
	public static void main(String[] args){
		AccessToken token = WeixinUtil.getAccessToken();
		System.out.print("票据："+token.getToken());
		System.out.print("有效时间："+token.getExpireIn());
	}
}
