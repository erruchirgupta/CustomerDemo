package com.demo.controller;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.demo.dao.UserSDAO;
import com.demo.beans.Customer;
import com.demo.beans.Users;
import com.demo.dao.CustomerDAO;

/**
 * @author Ruchir Gupta
 * @since 01 JAN 2018
 * @contact erruchirgupta@gmail.com
 *
 * @about Handles requests for the application home page.
 */

@Controller
@SessionAttributes({ "customers", "user", "message" })
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private CustomerDAO customerDAO;
	@Autowired
	private UserSDAO userSDAO;

	/**
	 * Simply redirects to default view when session elements expires.
	 */
	@ExceptionHandler(HttpSessionRequiredException.class)
	public String handleException(final HttpSessionRequiredException e) {

		logger.info("Begining of handleException method");
		logger.debug("defaultHome method :: redirecting to => /");
		logger.info("End of defaultHome method");
		return "redirect:/";
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultHome(Locale locale, Model model, HttpServletResponse response) {

		logger.info("Begining of defaultHome Controller method");
		String landingTilePage = "HomePage.page";

		try {
			response.addHeader("Set-Cookie", "user = " + null + " ; HttpOnly; path=/");
			model.addAttribute("users", new Users());
		} catch (Exception e) {
			logger.error("Error in defaultHome Controller method", e);
		}

		logger.info("End of defaultHome Controller method");
		return landingTilePage;

	}

	@RequestMapping(value = "/registerUserPage", method = RequestMethod.GET)
	public String registerUserPage(Locale locale, Model model) {

		logger.info("Begining of registerUserPage Controller method");
		String landingTilePage = "registerUser.page";

		try {
			model.addAttribute("users", new Users());
		} catch (Exception e) {
			logger.error("Error in registerUserPage Controller method", e);
		}

		logger.info("End of registerUserPage Controller method");

		return landingTilePage;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginHomePage(Model model, Users userSBean, BindingResult bindResult) {

		logger.info("Begining of loginHomePage Controller method");
		String landingTilePage = "redirect:/";

		try {
			if (userSDAO.checkUser(userSBean)) {
				model.addAttribute("user", userSBean.getName());
				model.addAttribute("message", "Welcome User: " + userSBean.getName());
				logger.debug("loginHomePage Controller method :: User's Name => " + userSBean.getName());
				landingTilePage = "redirect:/home";
			}
		} catch (Exception e) {
			logger.error("Error in loginHomePage Controller method", e);
		}

		logger.info("End of loginHomePage Controller method");
		return landingTilePage;
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homePageMethod(@ModelAttribute("user") String user, Model model, Customer customerBean,
			BindingResult bindResult) {

		logger.info("Begining of homePageMethod Controller method");
		String landingTilePage = "WelcomePage.page";

		try {
			model.addAttribute("message", "Welcome User: " + user);
			logger.debug("homePageMethod Controller method :: User's Name => " + user);
			logger.debug("homePageMethod Controller method :: Customer's email => " + customerBean.getEmail());
		} catch (Exception e) {
			logger.error("Error in homePageMethod Controller method", e);
		}

		logger.info("End of homePageMethod Controller method");
		return landingTilePage;
	}

	@RequestMapping(value = "/activeCustomersPage", method = RequestMethod.GET)
	public String activeCustomersPage(@ModelAttribute("user") String user, Model model) {

		logger.info("Begining of activeCustomersPage Controller method");
		String landingTilePage = "customersList.page";
		ArrayList<Customer> list = null;

		try {

			list = customerDAO.getActiveCustomers(user);
			model.addAttribute("customers", list);
			model.addAttribute("status", "Active");
			logger.debug("activeCustomersPage Controller method :: User's Name => " + user);
			logger.debug("activeCustomersPage Controller method :: Fetching ActiveCustomersList Size =>"
					+ ((list != null) ? list.size() : 0));
		} catch (Exception e) {
			logger.error("Error in activeCustomersPage Controller method", e);
		}

		logger.info("End of activeCustomersPage Controller method");
		return landingTilePage;
	}

	@RequestMapping(value = "/inActiveCustomersPage", method = RequestMethod.GET)
	public String inActiveCustomersPage(@ModelAttribute("user") String user, Model model) {

		logger.info("Begining of inActiveCustomersPage Controller method");
		String landingTilePage = "customersList.page";
		ArrayList<Customer> list = null;

		try {

			list = customerDAO.getInActiveCustomers(user);
			model.addAttribute("customers", list);
			model.addAttribute("status", "InActive");
			logger.debug("inActiveCustomersPage Controller method :: User's Name => " + user);
			logger.debug("inActiveCustomersPage Controller method :: Fetching InActiveCustomersList Size =>"
					+ ((list != null) ? list.size() : 0));
		} catch (Exception e) {
			logger.error("Error in inActiveCustomersPage Controller method", e);
		}

		logger.info("End of inActiveCustomersPage Controller method");
		return landingTilePage;
	}

	@RequestMapping(value = "/addCustomersPage", method = RequestMethod.GET)
	public ModelAndView addCustomersPage(@ModelAttribute("user") String user, Model model) {

		logger.info("Begining of addCustomersPage Controller method");
		String landingTilePage = "addCustomer.page";
		logger.info("End of inActiveCustomersPage Controller method");

		return new ModelAndView(landingTilePage, "customer", new Customer());
	}

	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
	public String addCustomer(@ModelAttribute("user") String user, Model model, Customer customerBean,
			BindingResult bindResult) {

		logger.info("Begining of addCustomer Controller method");
		String landingTilePage = "WelcomePage.page";
		String message = null;
		try {
			customerBean.setTeller(user);
			if (!customerBean.getCtype().equals("General"))
				customerBean.setPan("Not Required (for GEN only)");
			customerBean.setStatus("ACTIVE");
			message = "Customer " + customerDAO.addCustomer(customerBean);
			model.addAttribute("message", message);

			logger.debug("addCustomer Controller method :: Customer's email => " + customerBean.getEmail());
		} catch (Exception e) {
			logger.error("Error in addCustomer Controller method", e);
		}

		logger.info("End of addCustomer Controller method");

		return landingTilePage;
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(Model model, Users userBean, BindingResult bindResult) {

		logger.info("Begining of addUser Controller method");
		String landingTilePage = "userReg.page";
		String message = null;

		try {
			message = "User " + userSDAO.addUser(userBean);
			model.addAttribute("message", message);
			logger.debug("addUser Controller method :: User Add response => " + message);
		} catch (Exception e) {
			logger.error("Error in addUser Controller method", e);
		}

		logger.info("End of addUser Controller method");

		return landingTilePage;
	}

	@RequestMapping(value = "/deactivateCustomer/{cid}", method = RequestMethod.GET)
	public String deactivateCustomer(@ModelAttribute("user") String user, @PathVariable("cid") int cid, Model model) {

		logger.info("Begining of deactivateCustomer Controller method");
		String landingTilePage = null;
		boolean deactivateCustomerStatus = false;
		ArrayList<Customer> list = null;

		try {
			deactivateCustomerStatus = customerDAO.deactivateCustomer(cid, user);
			list = customerDAO.getActiveCustomers(user);

			model.addAttribute("status", "Active");
			model.addAttribute("customers", list);
			logger.debug("deactivateCustomer Controller method :: Deactivated Customer Status =>"
					+ deactivateCustomerStatus);
			logger.debug("deactivateCustomer Controller method :: Fetching ActiveCustomersList Size =>"
					+ ((list != null) ? list.size() : 0));

			landingTilePage = activeCustomersPage(user, model);

		} catch (Exception e) {
			logger.error("Error in deactivateCustomer Controller method", e);
		}

		logger.info("End of deactivateCustomer Controller method");

		return landingTilePage;
	}

	@RequestMapping(value = "/activateCustomer/{cid}", method = RequestMethod.GET)
	public String activateCustomer(@ModelAttribute("user") String user, @PathVariable("cid") int cid, Model model) {

		logger.info("Begining of activateCustomer Controller method");
		String landingTilePage = null;
		boolean activateCustomerStatus = false;
		ArrayList<Customer> list = null;

		try {
			activateCustomerStatus = customerDAO.activateCustomer(cid, user);
			list = customerDAO.getInActiveCustomers(user);

			model.addAttribute("status", "Inactive");
			model.addAttribute("customers", list);
			logger.debug("activateCustomer Controller method :: Activated Customer Status =>" + activateCustomerStatus);
			logger.debug("activateCustomer Controller method :: Fetching InactiveCustomersList Size =>"
					+ ((list != null) ? list.size() : 0));

			landingTilePage = inActiveCustomersPage(user, model);

		} catch (Exception e) {
			logger.error("Error in activateCustomer Controller method", e);
		}

		logger.info("End of activateCustomer Controller method");

		return landingTilePage;
	}

	@RequestMapping(value = "/searchCustomersPage", method = RequestMethod.GET)
	public String searchCustomerPage(@ModelAttribute("user") String user, Model model) {

		logger.info("Begining of searchCustomerPage Controller method");
		String landingTilePage = "searchCustomer.page";

		try {
			model.addAttribute("errorMsg", "");
			model.addAttribute("customer", new Customer());
		} catch (Exception e) {
			logger.error("Error in searchCustomerPage Controller method", e);
		}

		logger.info("End of searchCustomerPage Controller method");

		return landingTilePage;

	}

	@RequestMapping(value = "/searchCustomer", method = RequestMethod.POST)
	public String searchCustomer(@ModelAttribute("user") String user, Model model, Customer customerBean,
			BindingResult bindResult) {

		logger.info("Begining of searchCustomer Controller method");
		String landingTilePage = null;
		String message = "";

		try {
			if (customerBean.getCid() != null) {
				Customer customer = customerDAO.getCustomer(customerBean.getCid());

				if (customer != null) {
					model.addAttribute("customer", customer);

					logger.debug("searchCustomer Controller method :: Customer's email => " + customerBean.getEmail());

					landingTilePage = "showCustomer.page";
				} else {
					message = "is not a Valid Customer ID, try again";
					landingTilePage = "searchCustomer.page";
				}

			} else {
				message = "Enter a Valid Customer ID!";
				landingTilePage = "searchCustomer.page";
			}
		} catch (Exception e) {
			logger.error("Error in searchCustomer Controller method", e);
		}

		model.addAttribute("errorMsg", message);
		logger.info("End of searchCustomer Controller method");

		return landingTilePage;

	}

	@RequestMapping(value = "/searchCustomer/{cid}", method = RequestMethod.GET)
	public String searchCustomerClick(@ModelAttribute("user") String user, Model model, @PathVariable("cid") int cid) {

		logger.info("Begining of searchCustomerClick Controller method");
		String landingTilePage = "showCustomer.page";

		try {
			Customer customer = customerDAO.getCustomer(cid);
			model.addAttribute("customer", customer);
			logger.debug("searchCustomerClick Controller method :: Customer's email => " + customer.getEmail());

		} catch (Exception e) {
			logger.error("Error in searchCustomerClick Controller method", e);
		}

		logger.info("End of searchCustomerClick Controller method");

		return landingTilePage;

	}

	@RequestMapping(value = "/updateCustomerPage/{cid}", method = RequestMethod.GET)
	public String updateCustomerPage(@ModelAttribute("user") String user, Model model, @PathVariable("cid") int cid) {

		logger.info("Begining of updateCustomerPage Controller method");
		String landingTilePage = "updateCustomer.page";

		try {
			Customer customer = customerDAO.getCustomer(cid);
			model.addAttribute("customer", customer);
			logger.debug("updateCustomerPage Controller method :: Customer's email => " + customer.getEmail());

		} catch (Exception e) {
			logger.error("Error in updateCustomerPage Controller method", e);
		}

		logger.info("End of updateCustomerPage Controller method");

		return landingTilePage;

	}

	@RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
	public String updateCustomer(@ModelAttribute("user") String user, Model model, Customer customerBean,
			BindingResult bindResult) {

		logger.info("Begining of updateCustomer Controller method");
		String landingTilePage = "WelcomePage.page";
		String message = "Customer not Updated";

		try {
			if (customerDAO.updateCustomer(customerBean))
				message = "Customer Updated Successfully";

			logger.debug("addUser updateCustomer Controller method :: Customer Update response => " + message);
		} catch (Exception e) {
			logger.error("Error in updateCustomer Controller method", e);
		}

		model.addAttribute("message", message);
		logger.info("End of updateCustomer Controller method");

		return landingTilePage;

	}

}
