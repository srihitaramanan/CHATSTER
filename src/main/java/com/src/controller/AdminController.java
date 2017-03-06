package com.src.controller;




import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.src.dao.UserDAO;
import com.src.model.CollegeGroup;
import com.src.model.User;
import com.src.model.UserRole;



@Controller
public class AdminController
{
	
	/** Inject UserDAO **/
	@Autowired
	UserDAO userDao;
	
	/** Inject BCryptPasswordEncoder **/
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	/** Inject HttpSession **/
	@Autowired
	HttpSession session;
	
	
	@RequestMapping(value="/index",method = RequestMethod.GET)
	public String index(Model model) 
	{
		return "redirect:profile";
	}
	
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String setupForm(Model model) 
	{
		return "login";
	}
	
	@RequestMapping(value="/logout",method = RequestMethod.GET)
	public String logout() 
	{
		session.setAttribute("currentUser", null);
		return "redirect:/";
	}

	/**
	 * This method is used to login with email address and password
	 * 
	 * @param emailAddress
	 * @param password
	 * @return Trackers
	 */
	@RequestMapping(value = "/signIn", method = RequestMethod.POST)
	public String signIn(Model model,
			@ModelAttribute("emailAddress") String emailAddress,
			@ModelAttribute("password") String password,
			RedirectAttributes redirectAttr) {
		String returnPage = "";
		Boolean userExist = false;
		String authority = null;
		
		
		try {
			UserRole userRole = userDao.findEmail(emailAddress, null);
			
			if (userRole != null) {
				if (userRole.getUserFk().getIsActive() == 1 && userRole.getUserFk().getPassword() != null && userRole.getUserFk().getPassword() != "") {
					userExist = passwordEncoder.matches(password, userRole.getUserFk().getPassword());
				}
				if (userExist) {
					
					authority = userRole.getRoleFk().getAuthority();
					
					session.setAttribute("authority", authority);
					
					User currentUser = userRole.getUserFk();
					
					System.out.println(":::::::::::: " + currentUser.getGroupName());
					
					
					session.setAttribute("currentUser", currentUser);
					
					returnPage = "redirect:index";
						
				}else{
					redirectAttr.addFlashAttribute("message", "Invalid Email Address/ Password");
					returnPage = "redirect:/";
				}
			} else {
				redirectAttr.addFlashAttribute("message", "Invalid Email Address/ Password");
				returnPage = "redirect:/";
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return returnPage;
	}
	@RequestMapping(value="/profile",method = RequestMethod.GET)
	public String profile(Model model){
		
		User currentUser = (User) session.getAttribute("currentUser");
		model.addAttribute("details", currentUser);
		
		return "profile";
	}
	
	@RequestMapping(value="/saveGroup",method = RequestMethod.POST)
	public @ResponseBody String saveGroup(@RequestParam("groupName") String groupName){
		
		CollegeGroup g = new CollegeGroup();
		g.setGroupName(groupName);
		g.setIsActive((short)1);
		g.setRowCreated(new Date());
		g.setRowAltered(new Date());
		
		userDao.saveGroup(g);
		return "success";
	}
	

}