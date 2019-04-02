package org.deepsl.hrm.controller;

import org.apache.ibatis.annotations.Insert;
import org.deepsl.hrm.domain.User;
import org.deepsl.hrm.service.HrmService;
import org.deepsl.hrm.util.common.HrmConstants;
import org.deepsl.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 处理用户请求控制器
 * */
@Controller
public class UserController {
	
	/**
	 * 自动注入UserService
	 * */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
		
	/**
	 * 处理登录请求
	 * @param  loginname  登录名
	 * @param  password 密码
	 * @return 跳转的视图
	 * */
	@RequestMapping(value="/login")
	 public ModelAndView login(@RequestParam("loginname") String loginname,
                               @RequestParam("password") String password,
                               HttpSession session,
                               ModelAndView mv){
		// 调用业务逻辑组件判断用户是否可以登录
		System.out.println(loginname);
		System.out.println(password);
		User user = hrmService.login(loginname, password);
		System.out.println(user);
		if(user != null){
			// 将用户保存到HttpSession当中
			session.setAttribute(HrmConstants.USER_SESSION, user);
			// 客户端跳转到main页面
			mv.setViewName("redirect:/main");
		}else{
			// 设置登录失败提示信息
			mv.addObject("message", "登录名或密码错误!请重新输入");
			// 服务器内部跳转到登录页面
			mv.setViewName("forward:/loginForm");
		}
		return mv;
		
	}

	//对选中的用户信息进入修改页面
	@RequestMapping("/user/updateUser")
	public String UserMessage(User user){
		System.out.println("点击查看用户信息");
		System.out.println("user:"+user);
		return "/user/showUpdateUser";
	}
	@RequestMapping("/user/updateUser2")
	public String updateUser(Model model, User user, HttpServletRequest request, HttpServletResponse response){
		System.out.println("点击进行用户信息修改");
		System.out.println("user:"+user);
		hrmService.modifyUser(user);
		return "redirect:selectUser";
	}
	
	/**
	 * 处理查询请求
	 * @param
	 * @param
	 * @param
	 * */
	@RequestMapping("/user/selectUser")
	public String searchUser(String username,String status,Model model,HttpServletRequest request){
		System.out.println("进行用户搜索操作");
        int i2 = 0;
        if (status!=null && !status.equals("")) {
            i2 = Integer.parseInt(status);
        }
		System.out.println("username:"+username);
		System.out.println("status:"+status);
		request.setAttribute("username",username);
		request.setAttribute("status",status);
		/*model.addAttribute("username",username);
		model.addAttribute("status",status);*/
        //查询用户总数
        int RecordCount =  hrmService.findUserCountByuserNameAndStatus(username, i2);

        PageModel pageModel = new PageModel();
        System.out.println("经过条件查询的获取的用户总数为:"+RecordCount);
        //设置总数
        pageModel.setRecordCount(RecordCount);

        String pageIndex = request.getParameter("pageIndex");
        if (pageIndex == null) {
            pageIndex = "1";
        }
        int i = Integer.parseInt(pageIndex);
        pageModel.setPageSize(4);
        pageModel.setPageIndex(i);

        request.setAttribute("pageModel",pageModel);

		List<User> users = hrmService.findUserByuserNameAndStatusOfPage((i-1)*4,4,username, i2);
		model.addAttribute("users",users);
		return "/user/user";
	}
	
	/**
	 * 处理删除用户请求
	 * @param
	 * @param
	 * */
	@RequestMapping("/user/removeUser")
	public String DeleteMessage(HttpServletRequest request){
		System.out.println("点击删除用户信息");
		/*System.out.println("user:"+user);*/
		String ids = request.getParameter("ids");
		String[] str = ids.split(",");
		for (int i = 0; i < str.length; i++) {
			System.out.println(str[i]);
			int i1 = Integer.parseInt(str[i]);
			hrmService.removeUserById(i1);
		}
		return "redirect:selectUser";
	}

	//添加用户
	@RequestMapping("/user/addUser")
	public String addUser(){
		return "/user/showAddUser";
	}

	@RequestMapping("/user/addUser2")
	public String addUser2(User user){
		System.out.println("user:"+user);
        boolean b = hrmService.addUser(user);
        return "redirect:selectUser";
	}
	
}
