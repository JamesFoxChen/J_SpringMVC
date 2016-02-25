package com.james.web.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.james.domain.User;
import com.james.service.UserService;
import com.james.utils.ExportUtil;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/loginIndex")
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value = "/loginCheck")
	public ModelAndView loginCheck(HttpServletRequest request, LoginCommand loginCommand) {
		boolean isValidUser = userService.hasMatchUser(loginCommand.getUserName(), loginCommand.getPassword());
		if (!isValidUser) {
			return new ModelAndView("login", "error", "用户名或密码错误。");
		} else {
			User user = userService.findUserByUserName(loginCommand.getUserName());
			user.setLastIp(request.getLocalAddr());
			user.setLastVisit(new Date());
			userService.loginSuccess(user);
			request.getSession().setAttribute("user", user);
			return new ModelAndView("main");
		}
	}

	@RequestMapping(value = "/excelDownload")
	public String exportExcel(HttpServletResponse response) {
		try {
			//String fileName = new String(("导出excel标题").getBytes(), "UTF-8") + ".xlsx";
			String fileName=new String(("导出excel标题").getBytes("gb2312"), "iso8859-1")+ ".xlsx";
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setCharacterEncoding("utf-8");

			// response.setHeader("Content-disposition", "attachment; filename="
			// + "exdddcel" + ".xlsx");// 组装附件名称和格式

			String[] titles = { "最后IP", "最后访问时间", "密码", "用户名", "用户编号" };

			/*SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = df.format(new Date());
			Date dateNow = null;
			try {
				dateNow = df.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}*/
			Date dateNow = new Date();
			
			ArrayList<User> users = new ArrayList<User>();
			User user = new User();
			user.setLastIp("127.0.0.1");
			user.setLastVisit(dateNow);
			user.setPassword("123");
			user.setUserId(1);
			user.setUserName("名称：James");
			users.add(user);

			user = new User();
			user.setLastIp("192.0.0.1");
			user.setLastVisit(dateNow);
			user.setPassword("456");
			user.setUserId(2);
			user.setUserName("名称：Mary");
			users.add(user);

			ServletOutputStream outputStream = response.getOutputStream();
			ExportUtil.ExportExcel(titles, users, outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
