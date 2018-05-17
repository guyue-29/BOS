package cn.itcast.cw.web.action;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.cw.web.common.BaseAction;

@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class TestUploadAction extends BaseAction<T> {

	private static final Logger LOGGER = Logger.getLogger(TestUploadAction.class);

	// 接收上传文件
	private File file3;
	private String file3FileName;
	private String file3ContentType;

	public void setFile3(File file3) {
		this.file3 = file3;
	}

	public void setFile3FileName(String file3FileName) {
		this.file3FileName = file3FileName;
	}

	public void setFile3ContentType(String file3ContentType) {
		this.file3ContentType = file3ContentType;
	}

	@Action(value = "testUpload", results = {
			@Result(name = "success", type = "redirect", location = "/testUpload.html") })
	public void testUpload() {
		System.out.println("文件：" + file3);
		System.out.println("文件名：" + file3FileName);
		System.out.println("文件类型：" + file3ContentType);

		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			// ServletActionContext.getResponse().getWriter()
			// .println("上传文件成功!");

			String savePath = ServletActionContext.getServletContext().getRealPath("/upload/");
			String saveUrl = ServletActionContext.getRequest().getContextPath() + "/upload/";

			System.out.println("upload savePath=" + savePath);
			System.out.println("upload saveUrl=" + saveUrl);

			// 生成随机图片名
			UUID uuid = UUID.randomUUID();
			String ext = file3FileName.substring(file3FileName.lastIndexOf("."));
			String randomFileName = uuid + ext;

			// 保存图片 (绝对路径)
			File destFile = new File(savePath + "/" + randomFileName);
			FileUtils.copyFile(file3, destFile);

			System.out.println("保存后的文件名=" + destFile.getName());
			System.out.println("保存后的文件路径=" + destFile.getPath());
			System.out.println("保存后的文件绝对路径=" + destFile.getAbsolutePath());

			ServletActionContext.getResponse().getWriter()
					.println("<img src='http://localhost:9010/bos_review/upload/" + destFile.getName() + "' />");

			// ServletActionContext.getResponse().getWriter()
			// .println("<img
			// src='http://localhost:9001/bos_management/upload/testUpload.jpg'
			// />");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String param1;

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	private String param2;

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	private Double rand;

	public void setRand(Double rand) {
		this.rand = rand;
	}

	// 批量区域数据导入
	@Action(value = "test_ocupload")
	public String batchImport() throws IOException {
		System.out.println("param1=" + param1);
		System.out.println("param2=" + param2);
		System.out.println("rand=" + rand);
		System.out.println("文件：" + file3);
		System.out.println("文件名：" + file3FileName);
		System.out.println("文件类型：" + file3ContentType);

		// 上传的文件默认保存在缓存路径下，如果需要将其保存到指定目录，通过如下操作
		FileUtils.copyFile(file3, new File(ServletActionContext.getRequest().getRealPath("/upload"), file3FileName));
		return NONE;
	}

}
