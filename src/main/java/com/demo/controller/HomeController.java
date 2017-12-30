package com.demo.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.CookieValue;
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
 * Handles requests for the application home page.
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
	 * Simply selects the home view to render by returning its name.
	 */
	 @ExceptionHandler(HttpSessionRequiredException.class)
	    public String handleException(final HttpSessionRequiredException e) {
	        return "redirect:/";//"forward:/";
	    }

//	    @RequestMapping(value = "/n/error", method = RequestMethod.GET)
//	    public String error(final RedirectAttributes redirectAttributes) {
//	        return "redirect:/";
//	    }
	//	
		
		@RequestMapping(value = "/", method = RequestMethod.GET)
		public String collegeHome(Locale locale, Model model,
				@CookieValue(value = "name", defaultValue = "null") String name,
				@CookieValue(value = "pass", defaultValue = "null") String pass,
				HttpServletResponse response) {
			name = "abc";
			pass = "abc";
			Cookie c = (new Cookie("name", name));
			c.setMaxAge(1800);
			c.setSecure(true);
			Cookie c1 = (new Cookie("pass", pass));
			c1.setMaxAge(1800);
			c1.setSecure(true); 
			
		    c.setPath("/");
		    c1.setPath("/");
		    
			response.addCookie(c);
			response.addCookie(c1);
			
			logger.info("Welcome home! the client locale is " + locale.toString());
			model.addAttribute("users", new Users());
			return "HomePage.page";
		}

		
		@RequestMapping(value = "/registerUserPage", method = RequestMethod.GET)
		public String registerUserPage(Locale locale, Model model) {
//			logger.info("Welcome home! the client locale is " + locale.toString());
			model.addAttribute("users", new Users());
			return "registerUser.page";
		}
		
		
		
		@RequestMapping(value = "/login", method = RequestMethod.POST)
		public String addCollege(Model model, Users userSBean,
				BindingResult bindResult) {
			if(userSDAO.checkUser(userSBean)){
				model.addAttribute("user", userSBean.getName());
				model.addAttribute("message", "Welcome User: "+userSBean.getName());
				return "redirect:/home";//"WelcomePage.page";
				}
			else
				{
//				model.addAttribute("users", new UserS());
				return "redirect:/";
				}
		}
		
		@RequestMapping(value = "/home", method = RequestMethod.GET)
		public String home(@ModelAttribute("user") String user, Model model, Customer customerBean,
				BindingResult bindResult) {
			logger.info("Welcome home! the home locale is " + customerBean.getFname());
			model.addAttribute("message", "Welcome User: "+user);
			return "WelcomePage.page";
		}
		
		
		@RequestMapping(value = "/activeCustomersPage", method = RequestMethod.GET)
		public String activeCustomersPage(@ModelAttribute("user") String user, Model model) {
			model.addAttribute("status", "Active");
			//model.addAttribute("user",user);
			model.addAttribute("customers", customerDAO.getActiveCustomers(user));
			return "customersList.page";
		}
		
		@RequestMapping(value = "/inActiveCustomersPage", method = RequestMethod.GET)
		public String inActiveCustomersPage(@ModelAttribute("user") String user, Model model) {
			model.addAttribute("status", "InActive");
			//model.addAttribute("user",user);
			model.addAttribute("customers", customerDAO.getinActiveCustomers(user));
			return "customersList.page";
		}
		
		
		@RequestMapping(value = "/addCustomersPage", method = RequestMethod.GET)
		public ModelAndView addCustomersPage(@ModelAttribute("user") String user, Model model) {
			return new ModelAndView("addCustomer.page", "customer" , new Customer());
		}
		
		@RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
		public String addCollege(@ModelAttribute("user") String user, Model model, Customer customerBean,
				BindingResult bindResult) {
			customerBean.setTeller(user);

			System.out.println(customerBean.getAddress());
			System.out.println(customerBean.getCity());
			System.out.println(customerBean.getCtype());
			System.out.println(customerBean.getEmail());
			System.out.println(customerBean.getFname());
			System.out.println(customerBean.getLname());
			System.out.println(customerBean.getPan());
			System.out.println(customerBean.getPhoneno());
			System.out.println(customerBean.getState());
			System.out.println(customerBean.getStatus());
			System.out.println(customerBean.getTeller());
			System.out.println(customerBean.getZip());
			System.out.println(customerBean.getDob());
			
			if(!customerBean.getCtype().equals("General"))
				customerBean.setPan("Not Required (for GEN only)");
			customerBean.setStatus("ACTIVE");
			model.addAttribute("message", "Customer "+customerDAO.addCustomer(customerBean));
			return "WelcomePage.page";
		}
		
		@RequestMapping(value = "/addUser", method = RequestMethod.POST)
		public String addUser(Model model, Users userBean, BindingResult bindResult) {	
			model.addAttribute("message", "User "+userSDAO.addUser(userBean));
			return "userReg.page";
		}
		
		@RequestMapping(value = "/deactivateCustomer/{cid}", method = RequestMethod.GET)
		public String deactivateCustomer(@ModelAttribute("user") String user, @PathVariable("cid") int cid, Model model) {
			customerDAO.deactivateCustomer(cid, user);
			model.addAttribute("status", "Active");
			model.addAttribute("customers", customerDAO.getActiveCustomers(user));
			return activeCustomersPage(user, model);
			//return "customersList.page";
		}

		@RequestMapping(value = "/activateCustomer/{cid}", method = RequestMethod.GET)
		public String activateCustomer(@ModelAttribute("user") String user, @PathVariable("cid") int cid, Model model) {
			customerDAO.activateCustomer(cid, user);
			model.addAttribute("status", "Inactive");
			model.addAttribute("customers", customerDAO.getinActiveCustomers(user));
			return inActiveCustomersPage(user, model);
			//return "customersList.page";
		}
		
		@RequestMapping(value = "/searchCustomersPage", method = RequestMethod.GET)
		public String searchCustomerPage(@ModelAttribute("user") String user, Model model) {
			model.addAttribute("errorMsg", "");
			model.addAttribute("customer" , new Customer());
			return "searchCustomer.page";
			
		}
		
		@RequestMapping(value = "/searchCustomer", method = RequestMethod.POST)
		public String searchCustomer(@ModelAttribute("user") String user, Model model, Customer customerBean, BindingResult bindResult) {

			
			if(customerBean.getCid()!=null){
				Customer customer = customerDAO.getCustomer(customerBean.getCid());
				
				if(customer!=null){
					model.addAttribute("errorMsg", "");
					model.addAttribute("customer", customer);
					return "showCustomer.page";
					}
					else{
						model.addAttribute("errorMsg", "is not a Valid Customer ID, try again");
					return "searchCustomer.page";
					}
			
			}else{
				System.out.println("..");
				model.addAttribute("errorMsg", "Enter a Valid Customer ID!");
			return "searchCustomer.page";
			}
			

			}

		@RequestMapping(value = "/searchCustomer/{cid}", method = RequestMethod.GET)
		public String searchCustomerClick(@ModelAttribute("user") String user, Model model, @PathVariable("cid") int cid) {

			Customer customer = customerDAO.getCustomer(cid);
			model.addAttribute("customer", customer);
			return "showCustomer.page";

			}
		
		@RequestMapping(value = "/updateCustomerPage/{cid}", method = RequestMethod.GET)
		public String updateCustomerPage(@ModelAttribute("user") String user, Model model, @PathVariable("cid") int cid) {

			Customer customer = customerDAO.getCustomer(cid);
			model.addAttribute("customer", customer);
			return "updateCustomer.page";

			}
		
		@RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
		public String updateCustomerPage(@ModelAttribute("user") String user, Model model, Customer customerBean, BindingResult bindResult) {

			if (customerDAO.updateCustomer(customerBean)){
				model.addAttribute("message", "Customer Updated Successfully");			
			}
			else{
				model.addAttribute("message", "Customer not Updated");
			}
			return "WelcomePage.page";

			}
	
}
