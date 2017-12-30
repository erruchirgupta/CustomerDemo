package com.demo.dao;

import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.beans.Customer;
import com.demo.controller.HomeController;


/**
 * @author	Ruchir Gupta
 * @since	31 dec 2017
 * @contact	erruchirgupta@gmail.com
 *
 */

@Repository
public class CustomerDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerDAO.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public String addCustomer(Customer customerBean) {
		try {
			Session s = sessionFactory.getCurrentSession();
			s.save(customerBean);
			return "Added Successfully";
		} catch (Exception e) {
			System.out.println(e);
			return "not added Successfully";
		}

	}

	@Transactional
	public boolean updateCustomer(Customer customerBean) {
		
		 logger.info("updateCustomer Method execution started");

		// Customer customerBean = getCustomer(cid);

		boolean result = false;
		try {
			sessionFactory.getCurrentSession().update(customerBean);
			result = true;
		} catch (Exception e) {
			 logger.error("Error in updateCustomer() Method", e);
		}
		 logger.info("updateCustomer Method execution ends");
		return result;
	}

	@Transactional
	public Customer getCustomer(int cid) {

		return (Customer) sessionFactory.getCurrentSession().get(
				Customer.class, cid);	
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public ArrayList<Customer> getActiveCustomers(String teller) {
		
	    logger.info("getActiveCustomers Method execution started");
		
		ArrayList<Customer> list = null;

		try {

			Session session = sessionFactory.getCurrentSession();

			list = (ArrayList<Customer>) session
					.createCriteria(Customer.class)
					.add(Restrictions.and(Restrictions.eq("teller", teller),
							Restrictions.eq("status", "ACTIVE")))
							.addOrder(Order.asc("cid"))
							.list();
		} catch (Exception e) {
			 logger.error("Error in getActiveCustomers() Method", e);
		}
		
		logger.info("getActiveCustomers Method execution ends");
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public ArrayList<Customer> getinActiveCustomers(String teller) {
		
		logger.info("getinActiveCustomers Method execution started");

		ArrayList<Customer> list = null;

		try {

			Session session = sessionFactory.getCurrentSession();

			list = (ArrayList<Customer>) session
					.createCriteria(Customer.class)
					.add(Restrictions.and(Restrictions.eq("teller", teller),
							Restrictions.eq("status", "INACTIVE")))
							.addOrder(Order.asc("cid"))
							.list();

		} catch (Exception e) {
			
			logger.error("Error in getinActiveCustomers() Method", e);
		
		}
		
		logger.info("getinActiveCustomers Method execution ends");
		
		return list;
	}

	@Transactional
	public boolean deactivateCustomer(int cid, String teller) {
		// logger.info("deleteCollege Method execution started");

		try {

			Customer customerBean = getCustomer(cid);
			if (customerBean.getStatus().equals("ACTIVE")
					&& customerBean.getTeller().equals(teller)) {
				customerBean.setStatus("INACTIVE");
				return updateCustomer(customerBean);
			} else {
				return false;
			}

		} catch (Exception e) {
			// logger.error("Error in deleteCollege() Method", e);
			return false;
		}
		// logger.info("deleteCollege Method execution ends");

	}

	@Transactional
	public boolean activateCustomer(int cid, String teller) {
		// logger.info("deleteCollege Method execution started");

		try {

			Customer customerBean = getCustomer(cid);
			if (customerBean.getStatus().equals("INACTIVE")
					&& customerBean.getTeller().equals(teller)) {
				customerBean.setStatus("ACTIVE");
				return updateCustomer(customerBean);
			} else {
				return false;
			}

		} catch (Exception e) {
			// logger.error("Error in deleteCollege() Method", e);
			return false;
		}
		// logger.info("deleteCollege Method execution ends");

	}

}
