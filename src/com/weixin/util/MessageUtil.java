package com.weixin.util;

import java.awt.im.InputContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.weixin.po.NewMessage;
import com.weixin.po.News;
import com.weixin.po.TextMessage;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.util.List;
import com.thoughtworks.xstream.XStream;

public class MessageUtil {
	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_NEWS = "news";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW= "VIEW";
	/**
	 * xmlתmap����
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static Map<String,String>xmlToMap(HttpServletRequest request) throws IOException, DocumentException{
		Map<String,String> map = new HashMap<String,String>();
		SAXReader reader = new SAXReader();
		InputStream ins = request.getInputStream();
		Document doc = reader.read(ins);
		Element root =  doc.getRootElement();
		java.util.List<Element> list = root.elements();
		for(Element e:list){
			map.put(e.getName(), e.getText());
		}
		ins.close();
		return map;
		
		
	}
	/**
	 * ���ı�����תxml
	 * @param textMessage
	 * @return
	 */
public static String textMessageToxml(TextMessage textMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}
/**
 * ͼ����Ϣתxml
 * @param newMessage
 * @return
 */
public static String newsMessageToxml(NewMessage newMessage){
	XStream xstream = new XStream();
	xstream.alias("xml", newMessage.getClass());
	xstream.alias("item", new News().getClass());
	return xstream.toXML(newMessage);
}
public static String initText(String toUserName,String formUserName,String content){
	TextMessage text = new TextMessage();
	text.setFromUserName(toUserName);
	text.setToUserName(formUserName);
	text.setMsgType(MessageUtil.MESSAGE_TEXT);
	text.setCreateTime(new Date().toString());
	text.setContent(content);
	text.setCreateTime(new Date().toString());
	return textMessageToxml(text);
}
/**
 * ��ע�����˵�
 * @return
 */
public static String menutext(){
	StringBuffer sb = new StringBuffer();
	sb.append("��ӭ��Ĺ�ע���밴�ղ˵���ʾ������\n\n");
	sb.append("1������˭\n\n");
	sb.append("2������˭\n\n");
	sb.append("�ظ�?�����˲˵�");
	return sb.toString();
	
}
public static String firstMenu(){
	StringBuffer sb = new StringBuffer();
	sb.append("˭֪������˭");
	return sb.toString();
}
public static String secondMenu(){
	StringBuffer sb = new StringBuffer();
	sb.append("����ô֪������˭");
	return sb.toString();
}
/**
 * ͼ����Ϣ��װ
 * @param toUserName
 * @param formUserName
 * @return
 */
public static String initNewsMessage(String toUserName,String formUserName){
	String message = null;
	List<News> newsList = new ArrayList<News>();
	NewMessage newMessage = new NewMessage();
	News news = new News();
	news.setTitle("��һ������");
	news.setDescription("�ɵ����ɵ����ɵ����ɵ������");
	news.setPicUrl("http://zengwei.tunnel.mobi/weixin/image/title.png");
	news.setUrl("www.baidu.com");
	newsList.add(news);
	newMessage.setFromUserName(toUserName);
	newMessage.setToUserName(formUserName);
	newMessage.setCreateTime(new Date().toString());
	newMessage.setMsgType(MESSAGE_NEWS);
	newMessage.setArticles(newsList);
	newMessage.setArticleCount(newsList.size());
	message = newsMessageToxml(newMessage);
	return message;
	
}
}

