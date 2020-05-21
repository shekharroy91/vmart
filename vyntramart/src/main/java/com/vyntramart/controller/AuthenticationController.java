package com.vyntramart.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.vyntramart.models.User;
import com.vyntramart.service.UserService;

@RestController
public class AuthenticationController {

	@Autowired
	UserService userService;
	
	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index"); // resources/template/index.html
		return modelAndView;
	}
	
	@RequestMapping(value = "/vlogin", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("vlogin"); // resources/template/login.html
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user); 
		modelAndView.setViewName("register"); // resources/template/register.html
		return modelAndView;
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home"); // resources/template/home.html
		return modelAndView;
	}
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminHome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin"); // resources/template/admin.html
		return modelAndView;
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ModelAndView registerUser(@Valid User user,BindingResult bindingresult,ModelMap modelmap) {
		ModelAndView modelAndView = new ModelAndView();
		if(userService.isUserAlreadyPresent(user)){
			modelAndView.addObject("successMessage","User Already Exist");
		}
		else if(bindingresult.hasErrors()) {
			modelAndView.addObject("successMessage","please correct the error in the form!");
			modelmap.addAttribute("bindingResult",bindingresult);
		}
		else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage","User Register Succesfully");
		}
		modelAndView.addObject("user",new User());
		modelAndView.setViewName("register");
		return modelAndView;
	}
}
