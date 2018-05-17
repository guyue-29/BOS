package cn.itcast.bos.utils;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import sun.misc.BASE64Encoder;

public class FileUtils {
	/**
	 * 下载文件时，针对不同浏览器，进行附件名的编码
	 * 
	 * @param filename
	 *            下载文件名
	 * @param agent
	 *            客户端浏览器
	 * @return 编码后的下载附件名
	 * @throws IOException
	 */
	public static String encodeDownloadFilename(String filename, String agent) throws IOException {
		if (agent.contains("Firefox")) { // 火狐浏览器
			filename = "=?UTF-8?B?" + new BASE64Encoder().encode(filename.getBytes("utf-8")) + "?=";
			filename = filename.replaceAll("\r\n", "");
		} else { // IE及其他浏览器
			filename = URLEncoder.encode(filename, "utf-8");
			filename = filename.replace("+", " ");
		}
		return filename;
	}

	private static SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmSSS");
	/**
	 * 自定义一个生成运单编号的规则
	 * @author  caiwen
	 * @date 2017年12月18日
	 * @return
	 */
	public static String getRandomWayBillNum() {
		return "WB" + sdf.format(new Date()) + new Random().nextInt(1000);

	}

	public static void main(String[] args) {
		System.out.println(getRandomWayBillNum());
	}
}
