package com.daniel.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.daniel.constant.Constant;

@Controller
public class ShiroController {
	
	private static Logger logger = LoggerFactory.getLogger(ShiroController.class);
	
	// 跳转到登录表单页面
	@RequestMapping(value = "/")
	public String index() {
		return "/index";
	}

	// 跳转到登录表单页面
	@RequestMapping(value = "/login",method=RequestMethod.GET)
	public String login() {
		return "/login";
	}
	
	/**
	 * ajax登录请求接口方式登陆
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String submitLogin(String username, String password,
			Model model,RedirectAttributes redirectAttributes) {
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
				//	为重定向添加一些属性
				redirectAttributes.addFlashAttribute("name", username);  
				
				//	使用依赖spring mvc的 ViewResolver直接跳转，会去映射controller里的mapper "/",跳转到"/index.html",浏览器地址栏为"/"
				return "redirect:/";
				
				//	直接映射页面路径 /index.html,浏览器地址栏为"/index.html";
				//	return "/index";
			} else {
				token.clear();
				logger.info("用户[" + username + "]登录认证失败,重新登陆");
				return "redirect:/login";
			}
		}catch(UnknownAccountException uae){  
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");  
            redirectAttributes.addFlashAttribute("message", "未知账户");  
        }catch(IncorrectCredentialsException ice){  
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");  
            redirectAttributes.addFlashAttribute("message", "密码不正确");  
        }catch(LockedAccountException lae){  
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");  
            redirectAttributes.addFlashAttribute("message", "账户已锁定");  
        }catch(ExcessiveAttemptsException eae){  
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");  
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");  
        }catch(AuthenticationException ae){  
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");  
            ae.printStackTrace();  
            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");  
        }
		return "redirect:/login";
	}
	
	// 退出登录	
    @RequestMapping(value="/logout",method=RequestMethod.GET)  
    public String logout(RedirectAttributes redirectAttributes ){ 
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();  
        redirectAttributes.addFlashAttribute("param.logout", true);  
        return "redirect:/login";
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
