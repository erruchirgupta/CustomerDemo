/**
 * 
 */
package com.demo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.beans.Users;

/**
 * @author	Ruchir Gupta
 * @since	31 dec 2017
 * @contact	erruchirgupta@gmail.com
 *
 */

@Repository
public class UserSDAO {

	@Autowired
	private SessionFactory sessionFactory;
	

	@Transactional
	public Users getUsers(String name) {
		try{
		Session s =	sessionFactory.getCurrentSession();
		System.out.println(s);
		System.out.println("_"+s.get(Users.class, name));
		return (Users)s.get(Users.class, name);
		}
		catch (Exception e) {
			return null;
		}
		
	}
	
	@Transactional
	public boolean checkUser(Users UsersBean) {
		if(getUsers(UsersBean.getName())!=null)
			return true;
		else
			return false;
	}
	
	@Transactional
	public String addUser(Users userBean) {
		try {
			Session s = sessionFactory.getCurrentSession();
			s.save(userBean);
			return "Added Successfully";
		} catch (Exception e) {
			return "not added Successfully";
		}

	}
	
	
}
