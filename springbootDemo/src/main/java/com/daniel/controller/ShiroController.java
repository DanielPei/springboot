package com.daniel.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daniel.constant.Constant;
import com.daniel.entity.User;

@Controller
public class ShiroController {
	
	private static Logger logger = LoggerFactory.getLogger(ShiroController.class);
	
	// 跳转到登录表单页面
	@RequestMapping(value = "/login")
	public String login() {
		return "/login";
	}
	
	// 登陆验证，这里方便url测试，正式上线需要使用POST方式提交
	@RequestMapping(value = "/ajaxLogin", method = RequestMethod.GET)
	public String index(User user) {
		if (user.getUsername() != null && user.getPassword()!= null) {
			UsernamePasswordToken token = new UsernamePasswordToken(
					user.getUsername(), user.getPassword(), "login");
			Subject currentUser = SecurityUtils.getSubject();
			
			logger.info("对用户[" + user.getUsername() + "]进行登录验证..验证开始");
			try {
				currentUser.login(token);
				// 验证是否登录成功
				if (currentUser.isAuthenticated()) {
					logger.info("用户[" + user.getUsername()
							+ "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
					System.out.println("用户[" + user.getUsername()
							+ "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
					return "/";
				} else {
					token.clear();
					System.out.println("用户[" + user.getUsername()
							+ "]登录认证失败,重新登陆");
					return "/login";
				}
			} catch (UnknownAccountException uae) {
				logger.info("对用户[" + user.getUsername()
						+ "]进行登录验证..验证失败-username wasn't in the system");
			} catch (IncorrectCredentialsException ice) {
				logger.info("对用户[" + user.getUsername()
						+ "]进行登录验证..验证失败-password didn't match");
			} catch (LockedAccountException lae) {
				logger.info("对用户[" + user.getUsername()
						+ "]进行登录验证..验证失败-account is locked in the system");
			} catch (AuthenticationException ae) {
				logger.error(ae.getMessage());
			}
		}
		return "/login";
	}

	/**
	 * ajax登录请求接口方式登陆
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)
	@ResponseBody
	public String submitLogin(String username, String password,
			Model model) {
		try {

			UsernamePasswordToken token = new UsernamePasswordToken(username,
					password);
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.login(token);
			// 验证是否登录成功
			if (currentUser.isAuthenticated()) {
				logger.info("用户[" + username
						+ "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
				System.out.println("用户[" + username
						+ "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
				return "redirect:/";
			} else {
				token.clear();
				System.out.println("用户[" + username
						+ "]登录认证失败,重新登陆");
				return "redirect:/login";
			}
		} catch (UnknownAccountException uae) {
			logger.info("对用户[" + username
					+ "]进行登录验证..验证失败-username wasn't in the system");
		} catch (IncorrectCredentialsException ice) {
			logger.info("对用户[" + username
					+ "]进行登录验证..验证失败-password didn't match");
		} catch (LockedAccountException lae) {
			logger.info("对用户[" + username
					+ "]进行登录验证..验证失败-account is locked in the system");
		} catch (AuthenticationException ae) {
			logger.error(ae.getMessage());
		}
		return "/login";
	}
	

	@RequestMapping("/404")
	public String Error_404(){
		return Constant.ERR_PAGE_BASE + "404";
	}

	@RequestMapping("/403")
	public String Error_403(){
		return Constant.ERR_PAGE_BASE + "/403";
	}
	
	@RequestMapping("/500")
	public String Error_500(){
		return Constant.ERR_PAGE_BASE + "500";
	}
}
