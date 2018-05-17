package cn.itcast.cw.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.cw.domain.Menu;

@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class ZTreeTestAction extends ActionSupport {

	@Action(value = "/ztree_query", results = { @Result(name = "success", type = "json") })
	public String queryMenu(){
		List<Menu> menuList = getMenuJson();
		//数据保存到植栈，返回给前端
		ActionContext.getContext().getValueStack().push(menuList);
		
		return SUCCESS;
	}
	private List<Menu> getMenuJson() {
		List<Menu> menuList = new ArrayList<>();
		menuList.add(new Menu(1, 0, "基本功能演示", null, true));
		menuList.add(new Menu(101, 1, "最简单的树 --  标准 JSON 数据", "菜单的链接URL"));
		menuList.add(new Menu(102, 1, "最简单的树 --  简单 JSON 数据", "菜单的链接URL"));
		menuList.add(new Menu(103, 1, "不显示 连接线", "菜单的链接URL"));

		menuList.add(new Menu(2, 0, "复/单选框功能 演示", null, false));
		menuList.add(new Menu(201, 2, "Checkbox 勾选操作", "菜单的链接URL"));
		menuList.add(new Menu(202, 2, "Checkbox nocheck 演示", "菜单的链接URL"));
		menuList.add(new Menu(203, 2, "Checkbox 勾选统计", "菜单的链接URL"));

		menuList.add(new Menu(3, 0, "编辑功能 演示", null, false));
		menuList.add(new Menu(301, 3, "拖拽 节点 基本控制", "菜单的链接URL"));
		menuList.add(new Menu(302, 3, "拖拽 节点 高级控制", "菜单的链接URL"));

		menuList.add(new Menu(4, 0, "基本功能演示", null, true));
		menuList.add(new Menu(401, 4, "一次性加载大数据量", "菜单的链接URL"));
		menuList.add(new Menu(402, 4, "保持展开单一路径", "菜单的链接URL"));

		return menuList;
	}
}
