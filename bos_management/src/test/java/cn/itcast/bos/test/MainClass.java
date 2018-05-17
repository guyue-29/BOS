package cn.itcast.bos.test;

import java.io.File;

public class MainClass {

	public static void main(String[] args) {
		//如何遍历本地磁盘上的文件夹
		File file = new File("E:\\Bos备课笔记\\workspace\\completeProject\\bos_management\\src\\main\\webapp\\upload");
		//遍历文件夹
		File[] fs = file.listFiles();
		String realPath = "http://localhost:9001/bos_management/upload/";
		for(File f : fs){
			System.out.println(realPath+f.getName());
		}
		
		
		
		
	}
}
