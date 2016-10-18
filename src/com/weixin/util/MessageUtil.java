package com.weixin.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.weixin.po.Image;
import com.weixin.po.ImageMessage;
import com.weixin.po.Music;
import com.weixin.po.MusicMessage;
import com.weixin.po.NewMessage;
import com.weixin.po.News;
import com.weixin.po.TextMessage;

public class MessageUtil {
	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_NEWS = "news";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_MUSIC = "music";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW = "VIEW";

	/**
	 * xml转map集合
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest request)
			throws IOException, DocumentException {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		InputStream ins = request.getInputStream();
		Document doc = reader.read(ins);
		Element root = doc.getRootElement();
		java.util.List<Element> list = root.elements();
		for (Element e : list) {
			map.put(e.getName(), e.getText());
		}
		ins.close();
		return map;

	}

	/**
	 * 将文本类型转xml
	 * 
	 * @param textMessage
	 * @return
	 */
	public static String textMessageToxml(TextMessage textMessage) {
		XStream xstream = new XStream();
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}

	/**
	 * 图文消息转xml
	 * 
	 * @param newMessage
	 * @return
	 */
	public static String newsMessageToxml(NewMessage newMessage) {
		XStream xstream = new XStream();
		xstream.alias("xml", newMessage.getClass());
		xstream.alias("item", new News().getClass());
		return xstream.toXML(newMessage);
	}

	public static String initText(String toUserName, String formUserName,
			String content) {
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
	 * 关注后主菜单
	 * 
	 * @return
	 */
	public static String menutext() {
		StringBuffer sb = new StringBuffer();
		sb.append("欢迎你的关注，请按照菜单提示操作：\n\n");
		sb.append("1、你是谁\n\n");
		sb.append("2、我是谁\n\n");
		sb.append("回复?调出此菜单");
		return sb.toString();

	}

	public static String firstMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append("谁知道你是谁");
		return sb.toString();
	}

	public static String secondMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append("你怎么知道我是谁");
		return sb.toString();
	}

	/**
	 * 图文消息组装
	 * 
	 * @param toUserName
	 * @param formUserName
	 * @return
	 */
	public static String initNewsMessage(String toUserName, String formUserName) {
		String message = null;
		List<News> newsList = new ArrayList<News>();
		NewMessage newMessage = new NewMessage();
		News news = new News();
		news.setTitle("第一个标题");
		news.setDescription("松岛枫松岛枫松岛枫松岛枫身份");
		news.setPicUrl("http://22065828.nat123.net/weixin/image/title.png");
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
	
	/**
	 * 组装图片消息
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String initImageMessage(String toUserName,String fromUserName){
		String message = null;
		Image image = new Image();
		image.setMediaId("hDMKky0-oI74FOPozSGmE5W048gUOGufkPzRWkLfIw0dEoi0W7k4-7XiWrqsv4BI");
		ImageMessage imageMessage = new ImageMessage();
		imageMessage.setFromUserName(toUserName);
		imageMessage.setToUserName(fromUserName);
		imageMessage.setMsgType(MESSAGE_IMAGE);
		imageMessage.setCreateTime(new Date().toString());
		imageMessage.setImage(image);
		message = imageMessageToXml(imageMessage);
		return message;
	}
	
	/**
	 * 图片消息转为xml
	 * @param imageMessage
	 * @return
	 */
	public static String imageMessageToXml(ImageMessage imageMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", imageMessage.getClass());
		return xstream.toXML(imageMessage);
	}
	
	/**
	 * 组装音乐消息
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String initMusicMessage(String toUserName,String fromUserName){
		String message = null;
		Music music = new Music();
		music.setThumbMediaId("-FsqAojlWYKG7wBl9jcldhwHrxzYSYr7ANxyibY019eANM93qrSkWE3HzLhVwoQy");
		music.setTitle("see you again");
		music.setDescription("速7片尾曲");
		music.setMusicUrl("http://22065828.nat123.net/Weixin/resource/See You Again.mp3");
		music.setHQMusicUrl("http://22065828.nat123.net/Weixin/resource/See You Again.mp3");
		
		MusicMessage musicMessage = new MusicMessage();
		musicMessage.setFromUserName(toUserName);
		musicMessage.setToUserName(fromUserName);
		musicMessage.setMsgType(MESSAGE_MUSIC);
		musicMessage.setCreateTime(new Date().toString());
		musicMessage.setMusic(music);
		message = musicMessageToXml(musicMessage);
		return message;
	}
	
	/**
	 * 音乐消息转为xml
	 * @param musicMessage
	 * @return
	 */
	public static String musicMessageToXml(MusicMessage musicMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}
}
