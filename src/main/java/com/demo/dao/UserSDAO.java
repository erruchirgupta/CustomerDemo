/**
 * 
 */
package com.demo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.beans.Users;

/**
 * @author Ruchir Gupta
 * @since 01 JAN 2018
 * @contact erruchirgupta@gmail.com
 *
 */

@Repository
public class UserSDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserSDAO.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public Users getUsers(String name) {

		logger.info("Begining of getUsers DAO method");
		Users users = null;

		try {
			Session s = sessionFactory.getCurrentSession();

			users = (Users) s.get(Users.class, name);

			logger.debug("getUsers DAO method :: Fetching User Details =>" + ((users != null) ? users.getName() : null));

		} catch (Exception e) {
			logger.error("Error in getUsers DAO method", e);
		}

		logger.info("End of getUsers DAO method");
		return users;
	}

	@Transactional
	public boolean checkUser(Users UsersBean) {

		logger.info("Begining of checkUser DAO method");
		boolean status = false;

		if (getUsers(UsersBean.getName()) != null)
			status = true;

		logger.info("End of checkUser DAO method");
		return status;
	}

	@Transactional
	public String addUser(Users userBean) {

		logger.info("Begining of addUser DAO method");
		String status = null;

		try {
			Session s = sessionFactory.getCurrentSession();
			s.save(userBean);
			status = "Added Successfully";

			logger.debug("addUser DAO method :: Added Successfully");

		} catch (Exception e) {
			status = "Not Added Successfully";
			logger.error("Error in addUser DAO method", e);
		}

		logger.info("End of addUser DAO method");
		return status;

	}

}
