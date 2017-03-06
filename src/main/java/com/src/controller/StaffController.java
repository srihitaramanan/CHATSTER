package com.src.controller;

import java.util.Date;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.src.dao.UserDAO;
import com.src.model.CollegeGroup;
import com.src.model.Department;
import com.src.model.Role;
import com.src.model.User;
import com.src.model.UserRole;


@Controller
public class StaffController {

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
	
	
	
	@RequestMapping(value="/staffs",method = RequestMethod.GET)
	public String student(Model model) 
	{
		
		String role = "ROLE_STAFF";
		
		List<User> studentList =  userDao.userList(role);
		
		model.addAttribute("staffList", studentList);
		

		
		return "Staff/list";
	}
	
	@RequestMapping(value = "/addStaff", method = RequestMethod.GET)
	public String signUp(Model model) {
		
			try {
				
				List<Role> roleList = userDao.roleList();
				model.addAttribute("roleList", roleList);
				
				List<Department> dept = userDao.departmentList();
				model.addAttribute("departmentList", dept);
				
				List<CollegeGroup> groupList = userDao.groupList();
				model.addAttribute("groupList", groupList);
				
				model.addAttribute("staff", new User());
				
			} catch (Exception e) {
				System.out.println(e);
			}
			return "Staff/create";
		}
	
	@RequestMapping(value = "/saveStaff", method = RequestMethod.POST)
	public String saveUser(Model model,
			@ModelAttribute("staff") User staff,
			@RequestParam("userRole") String roleName,
			RedirectAttributes redirectAttr) {
			try {
				if (staff != null) {
					
					String fName = staff.getFirstName().trim().replaceAll("\\s+", " ");
					String lName = staff.getLastName().trim().replaceAll("\\s+", " ");
					String emailAddress = staff.getEmailAddress().trim();
					
					String pass = staff.getPassword().trim();
					
					String password = passwordEncoder.encode(pass);
					
					
					
					String dept = staff.getDepartment().trim();
					String group = staff.getGroupName();
					
					User request = new User();
					
					/** set user details */
					request.setFirstName(fName);
					request.setLastName(lName);
					request.setEmailAddress(emailAddress);
					request.setPassword(password);
					request.setDepartment(dept);
					request.setGroupName(group);
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
							
							redirectAttr.addFlashAttribute("message", "Staff '"+ request.getEmailAddress().trim()+ "' has been added successfully");
						}
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			
			return "redirect:staffs";
	}
	
	@RequestMapping(value = "/editStaff", method = RequestMethod.GET)
	public String editUser(Model model,
			@ModelAttribute("id") Integer studentId) {
		
			try {
				User staff = userDao.getUserById(studentId);
				model.addAttribute("staff", staff);
				
				List<Department> dept = userDao.departmentList();
				model.addAttribute("departmentList", dept);
				
				List<CollegeGroup> groupList = userDao.groupList();
				model.addAttribute("groupList", groupList);
				
			} catch (Exception e) {
				System.out.println(e);
			}
			return "Staff/edit";

	}
	
	@RequestMapping(value = "/updateStaff", method = RequestMethod.POST)
	public String updateUser(Model model,
			@ModelAttribute("staff") User staff,
			RedirectAttributes redirectAttr) {
		
			try {
				if (staff != null) {
					
					User response = userDao.getUserById(staff.getUserId());
					
					/** set user details */
					response.setFirstName(staff.getFirstName());
					response.setLastName(staff.getLastName());
					response.setEmailAddress(staff.getEmailAddress());
					response.setDepartment(staff.getDepartment());
					response.setYear(staff.getYear());
					response.setGroupName(staff.getGroupName());
					response.setRowAltered(new Date());
					
					/** update user details */
					userDao.update(response);
					
					redirectAttr.addFlashAttribute("message", "Staff '"+ response.getEmailAddress().trim()+ "' has been updated successfully");
			
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			return "redirect:staffs";
	}
	@RequestMapping(value = "/deleteStaff", method = RequestMethod.GET)
	public String deleteStudent(Model model, 
			@ModelAttribute("id") Integer studentId, 
			RedirectAttributes redirectAttr) {
		
			try {
				
				User student = userDao.getUserById(studentId);
				
				userDao.deleteUserById(studentId);
				
				redirectAttr.addFlashAttribute("message", "Staff '"+ student.getEmailAddress().trim()+ "' has been deleted successfully");
				
			} catch (Exception e) {
				System.out.println(e);
			}
			
			return "redirect:staffs";

	}
	
}
