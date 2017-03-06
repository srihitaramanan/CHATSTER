package com.src.dao;

import java.util.ArrayList;
import java.util.List;

import com.src.model.Department;
import com.src.model.CollegeGroup;
import com.src.model.Role;
import com.src.model.User;
import com.src.model.UserRole;
import com.src.model.Year;


public interface UserDAO {

	/**
	 * Save user details
	 * 
	 * @param userBean
	 */
	Integer save(User userBean);

	/**
	 * Retrieve user for given email address
	 * 
	 * @param email
	 * @param userId
	 */
	UserRole findEmail(String email, Integer userId) throws Exception;

	/**
	 * Retrieve list of users
	 * 
	 */
	List<User> userList(String role);

	/**
	 * Retrieve list of roles
	 * 
	 */
	List<Role> roleList();
	
	/**
	 * Retrieve user role list
	 * 
	 * @param name
	 * @param order
	 */
	List<UserRole> userRoleList(String name, String order);

	/**
	 * Update User details
	 * 
	 * @param userBean
	 */
	void update(User userBean);

	/**
	 * Update User details
	 * 
	 * @param userBean
	 */
	User getUserById(Integer userId);

	/**
	 * Retrieve role
	 * 
	 * @param roleName
	 */
	Role getRoleByName(String roleName);

	/**
	 * save user role
	 * 
	 * @param UserRole
	 */
	Integer saveUserRole(UserRole uRole);


	void deleteUserById(Integer studentId);

	List<Department> departmentList();

	List<Year> yearList();

	void saveGroup(CollegeGroup group);

	List<CollegeGroup> groupList();

	List<User> membersByGroupName(String groupName);

	CollegeGroup getByName(String groupName);

	boolean checkUserGroup(String groupName);

	List<CollegeGroup> getGroups(ArrayList<String> currentUserGroups);


}
