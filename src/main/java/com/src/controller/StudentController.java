package com.src.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.src.dao.UserDAO;
import com.src.model.CollegeGroup;
import com.src.model.Department;
import com.src.model.Role;
import com.src.model.User;
import com.src.model.UserRole;
import com.src.model.Year;


@Controller
public class StudentController {

	/** Inject UserDAO **/
	@Autowired
	UserDAO userDao;
	
	/** Inject BCryptPasswordEncoder **/
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	/** Inject HttpSession **/
	@Autowired
	HttpSession session;
	
	AdminController controller = new AdminController();
	
	@RequestMapping(value="/students",method = RequestMethod.GET)
	public String student(Model model) 
	{
		
		String role = "ROLE_STUDENT";
		
		List<User> studentList =  userDao.userList(role);
		
		model.addAttribute("studentList", studentList);
		
		
		return "Student/list";
	}
	
	@RequestMapping(value = "/addStudent", method = RequestMethod.GET)
	public String signUp(Model model) {
		
			try {
				List<Role> roleList = userDao.roleList();
				model.addAttribute("roleList", roleList);
				
				List<Department> dept = userDao.departmentList();
				model.addAttribute("departmentList", dept);
				
				List<Year> year = userDao.yearList();
				model.addAttribute("yearList", year);
				
				List<CollegeGroup> groupList = userDao.groupList();
				model.addAttribute("groupList", groupList);
				
				model.addAttribute("student", new User());
				
			} catch (Exception e) {
				System.out.println(e);
			}
			return "Student/create";
		}
	
	@RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
	public String saveUser(Model model,
			@ModelAttribute("student") User user,
			@RequestParam("userRole") String roleName,
			RedirectAttributes redirectAttr) {
			try {
				if (user != null) {
					
					String fName = user.getFirstName().trim().replaceAll("\\s+", " ");
					String lName = user.getLastName().trim().replaceAll("\\s+", " ");
					String emailAddress = user.getEmailAddress().trim();
					
					String pass = user.getPassword().trim();
					String password = passwordEncoder.encode(pass);
					
					String dept = user.getDepartment().trim();
					String year = user.getYear().trim();
					String group = user.getGroupName();
					
					User request = new User();
					
					/** set user details */
					request.setFirstName(fName);
					request.setLastName(lName);
					request.setEmailAddress(emailAddress);
					request.setPassword(password);
					request.setDepartment(dept);
					request.setYear(year);
					request.setGroupName(group);
					request.setIsActive((short)1);
					request.setRowCreated(new Date());
					
					/** save user */
					Integer userId = userDao.save(request);
					
					if (userId != null && userId != 0) {
						request.setUserId(userId);
						Role role = userDao.getRoleByName(roleName);
						
						UserRole insert = new UserRole();
						
						//set user role details
						insert.setRoleFk(role);
						insert.setUserFk(request);
						insert.setIsActive((short) 1);
						insert.setRowCreated(new Date());
						
						/** save user role */
						Integer uRoleId = userDao.saveUserRole(insert);
						
						if (uRoleId != null && uRoleId != 0) {
							
							redirectAttr.addFlashAttribute("message", "Student '"+ request.getEmailAddress().trim()+ "' has been added successfully");
						}
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			
			return "redirect:students";
	}
	
	@RequestMapping(value = "/editStudent", method = RequestMethod.GET)
	public String editUser(Model model,
			@ModelAttribute("id") Integer studentId) {
		
			try {
				User user = userDao.getUserById(studentId);
				model.addAttribute("student", user);
				
				List<Department> dept = userDao.departmentList();
				model.addAttribute("departmentList", dept);
				
				List<Year> year = userDao.yearList();
				model.addAttribute("yearList", year);
				
				List<CollegeGroup> groupList = userDao.groupList();
				model.addAttribute("groupList", groupList);
				
			} catch (Exception e) {
				System.out.println(e);
			}
			return "Student/edit";

	}
	
	@RequestMapping(value = "/updateStudent", method = RequestMethod.POST)
	public String updateUser(Model model,
			@ModelAttribute("student") User user,
			RedirectAttributes redirectAttr) {
		
			try {
				if (user != null) {
					
					User response = userDao.getUserById(user.getUserId());
					
					/** set user details */
					response.setFirstName(user.getFirstName());
					response.setLastName(user.getLastName());
					response.setEmailAddress(user.getEmailAddress());
					response.setDepartment(user.getDepartment());
					response.setGroupName(user.getGroupName());
					response.setYear(user.getYear());
					response.setRowAltered(new Date());
					
					/** update user details */
					userDao.update(response);
					
					redirectAttr.addFlashAttribute("message", "Student '"+ response.getEmailAddress().trim()+ "' has been updated successfully");
			
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			return "redirect:students";
	}
	@RequestMapping(value = "/deleteStudent", method = RequestMethod.GET)
	public String deleteStudent(Model model, 
			@ModelAttribute("id") Integer studentId, 
			RedirectAttributes redirectAttr) {
		
			try {
				
				User student = userDao.getUserById(studentId);
				userDao.deleteUserById(studentId);
				
				redirectAttr.addFlashAttribute("message", "Student '"+ student.getEmailAddress().trim()+ "' has been deleted successfully");
				
			} catch (Exception e) {
				System.out.println(e);
			}
			
			return "redirect:students";

	}
	
}
