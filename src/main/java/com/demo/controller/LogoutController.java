package com.demo.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Ruchir Gupta
 * @since 01 JAN 2017
 * @contact erruchirgupta@gmail.com
 *
 * @about Handles requests for the application logout request.
 */

@Controller
@RequestMapping("/logout")
public class LogoutController {

	private static final Logger logger = LoggerFactory.getLogger(LogoutController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String logout(HttpSession session) {

		logger.info("Begining of logout method");

		session.invalidate();
		logger.debug("logout method :: redirecting to => /");

		logger.info("End of logout method");
		return "redirect:/";
	}
}