package cn.itcast.cw.domain;

public class Menu {

	private int id;// 菜单 的ID
	private int pid;// 菜单 的父级ID
	private String name;// 菜单 名称
	private String file;// 菜单打开的文件路径

	private boolean open;//是否默认展开

	public Menu(int id, int pid, String name, String file, boolean open) {
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.file = file;
		this.open = open;
	}
	public Menu(int id, int pid, String name, String file) {
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.file = file;
		this.open = false;
	}

	public Menu() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

}
