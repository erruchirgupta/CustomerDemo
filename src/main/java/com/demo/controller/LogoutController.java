package com.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author	Ruchir Gupta
 * @since	31 dec 2017
 * @contact	erruchirgupta@gmail.com
 *
 * @about Handles requests for the application logout request.
 */

@Controller
@RequestMapping("/logout")
public class LogoutController {

  @RequestMapping(method=RequestMethod.GET)
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/";
  }
}