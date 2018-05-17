package cn.itcast.bos.web.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.commons.io.FileUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.bos.domain.constant.Constants;
import cn.itcast.bos.domain.page.PageBean;
import cn.itcast.bos.domain.take_delivery.Promotion;
import freemarker.template.Configuration;
import freemarker.template.Template;

@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")
@SuppressWarnings("all")
public class PromotionAction extends BaseAction<Promotion> {
	@Action(value = "promotion_pageQuery", results = { @Result(name = "success", type = "json") })
	public String pageQuery() {

		// 基于WebService 获取 bos_management的 活动列表 数据信息
		PageBean<Promotion> pageBean = WebClient
				.create(Constants.BOS_MANAGEMENT_URL
						+ "/services/promotionService/pageQuery?page="
						+ page + "&rows=" + rows)
				.accept(MediaType.APPLICATION_JSON).get(PageBean.class);

		ActionContext.getContext().getValueStack().push(pageBean);

		return SUCCESS;
	}

	private String promotionId;
	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}

	@Action(value = "promotion_showDetail")
	public String showDetail() throws Exception {
		//前端传递过来 id的参数，是直接被模型驱动接收model对象 中
		/*
		 * 利用freemarker的模板来实现静态html的生成及返回
		 * 		1、根据参数id来判断，生成的详情静态html界面所在的文件夹下，是否存在 一个以id命名的html
		 * 			1）通过 String realParentPath = ServletActionContext.getServletContext().getRealPath("/freemarker")来得到文件夹的绝对路径（方法file文件的操作）
		 * 			2）File对象中   new File(realParentPath,id+".html").exists()，判断这个id命名的html是否存在。
		 * 						
		 * 		2、如果不存在 ，则通过freemarker的模板来创建一个新的静态html
		 * 			通过freemarker的模板生成一个静态html
		 * 				freemarker模板生成的步骤：
		 * 						1）创建一个freemarker的Configuration；同时绑定模板ftl的路径
		 * 						2）获取freemarker的模板工具类
		 * 						3）获取查询的详情数据
		 * 						4）将查询的详情数据与模板ftl文件合并，并生成带数据的静态html
		 * 				另一种方法：a、遍历parent文件夹中的所有文件列表，然后根据File.getName()得到每个遍历的文件夹名称，再根据id+".html"来判断是否与遍历中的某个文件名称相同，有则表示存在 ，无，则表示不存在 
		 * 		3、如果这个文件存在 ，则直接将其返回给前端
		 * 
		 * 				ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");//设置io流的 字符集，防止乱码
		 * 
		 *				FileUtils.copyFile(htmlFile, ServletActionContext.getResponse().getOutputStream());//复制查找出来的静态html的file文件
		 *				到OutputStream的流中，并返回给前端
		 */
		
		
		// 先判断 id 对应html 是否存在，如果存在 直接返回
		String htmlRealPath = ServletActionContext.getServletContext()
				.getRealPath("/freemarker");
		File htmlFile = new File(htmlRealPath + "/" + model.getId() + ".html");

		// 如果html文件不存在 ，查询数据库，结合freemarker模板生成 页面
		if (!htmlFile.exists()) {
			Configuration configuration = new Configuration(
					Configuration.VERSION_2_3_22);
			configuration.setDirectoryForTemplateLoading(new File(
					ServletActionContext.getServletContext().getRealPath(
							"/WEB-INF/freemarker_templates")));
			// 获取模板对象
			Template template = configuration
					.getTemplate("promotion_detail.ftl");

			// 动态数据
			Promotion promotion = WebClient
					.create(Constants.BOS_MANAGEMENT_URL
							+ "/services/promotionService/promotion/"
							+ model.getId()).accept(MediaType.APPLICATION_JSON)
					.get(Promotion.class);
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("promotion", promotion);

			// 合并输出
			template.process(parameterMap, new OutputStreamWriter(
					new FileOutputStream(htmlFile), "utf-8"));

		}

		// 存在 ，直接将文件返回
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		FileUtils.copyFile(htmlFile, ServletActionContext.getResponse()
				.getOutputStream());
		return NONE;
	}
}
