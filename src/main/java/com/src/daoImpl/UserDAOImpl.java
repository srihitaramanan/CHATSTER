package com.src.daoImpl;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.src.dao.UserDAO;
import com.src.model.Department;
import com.src.model.CollegeGroup;
import com.src.model.Role;
import com.src.model.User;
import com.src.model.UserRole;
import com.src.model.Year;



@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager manager;
	
	
	public Integer save(User userBean) {
		try{
			manager.persist(userBean);
		}catch(Exception e){
			System.out.println(e);
		}
		return userBean.getUserId();
	}


	public UserRole findEmail(String email, Integer userId) throws Exception {
		UserRole user =null;
		TypedQuery<UserRole> query = null;
		try{
			if(userId!=null && userId!=0){
				query = manager.createQuery("from UserRole u where LOWER(u.userFk.emailAddress)=:email and u.userFk.userId!=:id",UserRole.class);
				user = query.setParameter("email",email.toLowerCase()).setParameter("id",userId).setMaxResults(1).getSingleResult();
			}
			else{
				query = manager.createQuery("from UserRole u where LOWER(u.userFk.emailAddress)=:email",UserRole.class);
				user = query.setParameter("email",email.toLowerCase()).setMaxResults(1).getSingleResult();
			}
		
		}catch(Exception e){
			user = null;
			System.out.println(e);
		}
		return user;
	}


	public List<User> userList(String role) {
		List<User> userList = new ArrayList<User>();
		List<UserRole> uRoleList = null;
		try{
			uRoleList = manager.createQuery("from UserRole u where u.roleFk.authority='"+role+"'", UserRole.class).getResultList();
			
			for(int i=0;i<uRoleList.size();i++){
				userList.add(uRoleList.get(i).getUserFk());
			}
		}catch(Exception e){System.out.println(e);}
		
		return userList;
	}


	public List<Role> roleList() {
		List<Role> roleList = null;
		try{
			roleList = manager.createQuery("from Role", Role.class).getResultList();
		}catch(Exception e){System.out.println(e);}
		return roleList;
	}


	public List<UserRole> userRoleList(String name, String order) {
		
		List<UserRole> userRole = null;
		try{
			userRole = manager.createQuery("from UserRole ORDER BY userFk."+name+" "+order, UserRole.class).getResultList();
		}catch(Exception e){System.out.println(e);}
		
		return userRole;
	}


	public void update(User userBean) {
		manager.merge(userBean);
		
	}


	public User getUserById(Integer userId) {
		User user = manager.find(User.class,userId);
		return user;
	}


	public Role getRoleByName(String roleName) {
		Role role=null;
		try{
			TypedQuery<Role> query = manager.createQuery("from Role u where u.authority=:name",Role.class);
			role = query.setParameter("name",roleName).getSingleResult();
			}catch(Exception e){
				role = null;
				System.out.println(e);
			}
		return role;
	}


	public Integer saveUserRole(UserRole uRole) {
		manager.persist(uRole);
		return uRole.getUserRoleId();
	}
	public Integer updateUserRole(UserRole uRole) {
		manager.merge(uRole);
		return uRole.getUserRoleId();
	}

	public Role getRoleByUserId(Integer userId) {
		Role role=null;
		try{
			TypedQuery<UserRole> query = manager.createQuery("from UserRole u where u.userFk.userId=:id",UserRole.class);
			UserRole urole = query.setParameter("id",userId).getSingleResult();
			role = urole.getRoleFk();
			}catch(Exception e){
				role = null;
				System.out.println(e);
			}
		return role;
	}

	public UserRole userRoleByUserId(Integer userId) {
		UserRole userRole =null;
		try{
		TypedQuery<UserRole> query = manager.createQuery("from UserRole u where u.userFk.userId=:id",UserRole.class);
		userRole = query.setParameter("id",userId).getSingleResult();
		}catch(Exception e){
			userRole = null;
			System.out.println(e);
		}
		return userRole;
	}



	public User getUserId(String email) {
		User user=null;
		try{
			TypedQuery<User> query = manager.createQuery("from User u where LOWER(u.emailAddress)=:name",User.class);
			user = query.setParameter("name",email.toLowerCase()).getSingleResult();
			}catch(Exception e){
				user = null;
				System.out.println(e);
			}
		return user;
	}


	public void deleteUserById(Integer studentId) {
		
		UserRole ur = userRoleByUserId(studentId);
		
		UserRole uRole = manager.find(UserRole.class, ur.getUserRoleId());
		manager.remove(uRole);
		
		User user = manager.find(User.class, studentId);
		manager.remove(user);
		
	}

	public List<Department> departmentList() {
		
		List<Department> departmentList = null;
		try{
			departmentList = manager.createQuery("from Department", Department.class).getResultList();
			
		}catch(Exception e){System.out.println(e);}
		
		return departmentList;
	}
	
	public List<Year> yearList() {
		
		List<Year> yearList = null;
		try{
			yearList = manager.createQuery("from Year", Year.class).getResultList();
			
		}catch(Exception e){System.out.println(e);}
		
		return yearList;
	}


	public void saveGroup(CollegeGroup group) {
		try{
			System.out.println("before");
			manager.persist(group);
			System.out.println("after");
		}catch(Exception e){
			System.out.println(e);
		}
	}


	public List<CollegeGroup> groupList() {
		
		List<CollegeGroup> groupList = null;
		try{
			groupList = manager.createQuery("from CollegeGroup", CollegeGroup.class).getResultList();
			
		}catch(Exception e){System.out.println(e);}
		
		return groupList;
	}


	public List<User> membersByGroupName(String groupName) {
		
		List<User> userList = new ArrayList<User>();
		try{
			
			userList = manager.createQuery("from User u where u.groupName like '%"+groupName+"%'", User.class).getResultList();
			
		}catch(Exception e){System.out.println(e);}
		
		return userList;
	}

	public CollegeGroup getByName(String groupName) {
		CollegeGroup grp = null;
		try{
			TypedQuery<CollegeGroup> query = manager.createQuery("from CollegeGroup u where LOWER(u.groupName)=:groupName",CollegeGroup.class);
			grp = query.setParameter("groupName",groupName.toLowerCase()).getSingleResult();
			}catch(Exception e){
				grp = null;
				System.out.println(e);
			}
		return grp;
		
	}

	public boolean checkUserGroup(String groupName) {
		boolean isExists = false;
		try {
			 int count = ((Number)manager.createQuery("SELECT COUNT(b.groupName) FROM User b WHERE LOWER(b.groupName)=:groupName").setParameter("groupName",groupName.toLowerCase()).getSingleResult()).intValue();
			if(count > 0){
				isExists = true;
			}
			else{
				isExists = false;
			}
			}
	        catch(Exception e){
				System.out.println(e);
			}		
		
		return isExists;
	}


	public List<CollegeGroup> getGroups(ArrayList<String> myGroups) {
		
		List<CollegeGroup> groupList = null;
		try{
			TypedQuery<CollegeGroup> query  = manager.createQuery("from CollegeGroup cg where cg.groupName in(:groupNames)", CollegeGroup.class);
			groupList = query.setParameter("groupNames",myGroups).getResultList();
		}catch(Exception e){System.out.println(e);}
		
		
		System.out.println("daoimpl:::::: " +groupList);
		
		return groupList;
	}

}
