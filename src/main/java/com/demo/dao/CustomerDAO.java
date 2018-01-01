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

/**
 * @author Ruchir Gupta
 * @since 01 JAN 2018
 * @contact erruchirgupta@gmail.com
 *
 */

@Repository
public class CustomerDAO {

	private static final Logger logger = LoggerFactory.getLogger(CustomerDAO.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public String addCustomer(Customer customerBean) {

		logger.info("Begining of addCustomer DAO method");
		String status = null;

		try {
			Session s = sessionFactory.getCurrentSession();
			s.save(customerBean);
			status = "Added Successfully";

			logger.debug("addCustomer DAO method :: Added Successfully");

		} catch (Exception e) {
			status = "Not Added Successfully";
			logger.error("Error in addCustomer DAO method", e);
		}

		logger.info("End of addCustomer DAO method");
		return status;

	}

	@Transactional
	public boolean updateCustomer(Customer customerBean) {

		logger.info("Begining of updateCustomer DAO method");

		boolean result = false;
		try {
			sessionFactory.getCurrentSession().update(customerBean);
			logger.debug("updateCustomer DAO method :: Update Customer Successful");
			result = true;

		} catch (Exception e) {
			logger.error("Error in updateCustomer DAO method", e);
		}

		logger.info("End of updateCustomer DAO method");
		return result;
	}

	@Transactional
	public Customer getCustomer(int cid) {

		logger.info("Begining of getCustomer DAO method");
		Customer customer = null;

		try {

			customer = (Customer) sessionFactory.getCurrentSession().get(Customer.class, cid);

			logger.debug("getCustomer DAO method :: Fetching Customer Details =>"
					+ ((customer != null) ? customer.getEmail() : null));

		} catch (Exception e) {
			logger.error("Error in getCustomer DAO method", e);
		}

		logger.info("End of getCustomer DAO method");
		return customer;

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public ArrayList<Customer> getActiveCustomers(String teller) {

		logger.info("Begining of getActiveCustomers DAO method");
		ArrayList<Customer> list = null;

		try {

			Session session = sessionFactory.getCurrentSession();

			list = (ArrayList<Customer>) session.createCriteria(Customer.class)
					.add(Restrictions.and(Restrictions.eq("teller", teller), Restrictions.eq("status", "ACTIVE")))
					.addOrder(Order.asc("cid")).list();

			logger.debug("getActiveCustomers DAO method :: Fetching ActiveCustomersList Size =>"
					+ ((list != null) ? list.size() : 0));

		} catch (Exception e) {
			logger.error("Error in getActiveCustomers DAO method", e);
			return null;
		}

		logger.info("End of getActiveCustomers DAO method");
		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public ArrayList<Customer> getInActiveCustomers(String teller) {

		logger.info("Begining of getInActiveCustomers DAO method");

		ArrayList<Customer> list = null;

		try {

			Session session = sessionFactory.getCurrentSession();

			list = (ArrayList<Customer>) session.createCriteria(Customer.class)
					.add(Restrictions.and(Restrictions.eq("teller", teller), Restrictions.eq("status", "INACTIVE")))
					.addOrder(Order.asc("cid")).list();

			logger.debug("getActiveCustomers DAO method :: Fetching InActiveCustomersList Size =>"
					+ ((list != null) ? list.size() : 0));

		} catch (Exception e) {
			logger.error("Error in getInActiveCustomers DAO method", e);
			return null;
		}

		logger.info("End of getInActiveCustomers DAO method");
		return list;
	}

	@Transactional
	public boolean deactivateCustomer(int cid, String teller) {

		logger.info("Begining of deactivateCustomer DAO method");
		boolean updateStatus = false;
		try {

			Customer customerBean = getCustomer(cid);
			if (customerBean.getStatus().equals("ACTIVE") && customerBean.getTeller().equals(teller)) {
				customerBean.setStatus("INACTIVE");
				updateStatus = updateCustomer(customerBean);

				logger.debug("deactivateCustomer DAO method ::ACTIVE Customer(email_Id) deactivated =>"
						+ customerBean.getEmail());

			}
		} catch (Exception e) {
			logger.error("Error in deactivateCustomer DAO method", e);
		}

		logger.info("End of deactivateCustomer DAO method");
		return updateStatus;

	}

	@Transactional
	public boolean activateCustomer(int cid, String teller) {

		logger.info("Begining of activateCustomer DAO method");
		boolean updateStatus = false;

		try {

			Customer customerBean = getCustomer(cid);
			if (customerBean.getStatus().equals("INACTIVE") && customerBean.getTeller().equals(teller)) {
				customerBean.setStatus("ACTIVE");
				updateStatus = updateCustomer(customerBean);

				logger.debug("activateCustomer DAO method :: DEACTIVE Customer(email_Id) activated =>"
						+ customerBean.getEmail());
			}

		} catch (Exception e) {
			logger.error("Error in activateCustomer DAO method", e);
			return false;
		}

		logger.info("End of activateCustomer DAO method");
		return updateStatus;

	}

}
