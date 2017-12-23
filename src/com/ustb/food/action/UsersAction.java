package com.ustb.food.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ustb.food.entity.Users;
import com.ustb.food.service.UsersService;

/**
 * 
 * @author: zhuqiuhui
 * @date: 2017年9月27日 下午8:55:45
 */

@Component("/front/UsersAction")
@Scope("prototype")
public class UsersAction extends ActionSupport implements SessionAware,
		RequestAware {
	
	private static final long serialVersionUID = 1L;
	
	private Map<String, Object> session;
	private Map<String, Object> request;
	private Users users;
	private List<Users> usersList;
	private String url;
	private String userName;
	private String password;
	private String bianhao;
	private String pswd;
	private String name;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Autowired
	UsersService usersService;

	public String login() {

		return SUCCESS;
	}

	public String top() {

		return SUCCESS;
	}

	public String top_gl() {

		return SUCCESS;
	}

	public String left() {

		return SUCCESS;
	}

	public String left_gl() {

		return SUCCESS;
	}

	public String footer() {

		return SUCCESS;
	}

	public String loginSubmit() {

		String password = (usersService
				.getList("userName", users.getUserName()).size() != 0) ? usersService
				.getList("userName", users.getUserName()).get(0)
				.getUserPassword()
				: " ";
		usersList = usersService.getList("userName", users.getUserName());
		if (!password.equals(users.getUserPassword())) {
			url = "Users/login";
			return NONE;
		}
		if (usersList.get(0).getUserName().equals("system")
				&& usersList.get(0).getUserPassword().equals("system")) {
			url = "Users/my_gl";
			session.put("user", usersList.get(0));
			return NONE;
		}
		url = "Users/login";
		session.put("user", usersList.get(0));
		// 打印到控制台
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm")
				.format(new Date()) + usersList.get(0).getUserName() + "登录");
		url = "Users/my";
		return NONE;
	}

	public String my() {
		return SUCCESS;
	}

	public String my_gl() {
		return SUCCESS;
	}

	public String reg() {
		return SUCCESS;
	}

	public String regSubmit() {
		usersService.save(users);
		url = "";
		return NONE;
	}

	public String loginOut() {
		if (session.get("user") != null) {
			session.remove("user");
		}
		url = "Users/login";
		return NONE;
	}

	public String add() {
		return SUCCESS;
	}

	public String addac() {
		Users user = new Users();
		user.setUserName(userName);
		user.setUserPassword(password);
		usersService.save(user);
		url = "Users/add";
		return NONE;
	}

	public List<Users> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<Users> usersList) {
		this.usersList = usersList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBianhao() {
		return bianhao;
	}

	public void setBianhao(String bianhao) {
		this.bianhao = bianhao;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UsersService getUsersService() {
		return usersService;
	}

	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public String show() {
		System.err.println("show");
		List<Users> list = usersService.getList();
		session.put("users", list);
		return SUCCESS;
	}

	public String delete() {
		usersService.delete(Integer.valueOf(bianhao));
		request.put("msg", "删除成功");
		return SUCCESS;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public String update() {
		Users user = new Users();
		user.setUserId(Integer.valueOf(id));
		user.setUserName(name);
		user.setUserPassword(pswd);
		usersService.update(user);
		request.put("msg", "修改成功");
		return SUCCESS;
	}

	public String password() {
		return SUCCESS;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

}
