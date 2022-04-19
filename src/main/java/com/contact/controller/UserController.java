package com.contact.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.contact.dao.UserDAO;
import com.contact.model.User;

@Controller
public class UserController {

	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping("/welcome")
	public ModelAndView helloWorld() {
		List<User> users = userDAO.getUsers();
		ModelAndView mv = new ModelAndView();
		mv.addObject("users", users);
		mv.setViewName("welcome");
		return mv;
	} 
	
	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
	public ModelAndView newUser(ModelAndView mv)
	{
		User user = new User();
		mv.addObject("user", user);
		mv.setViewName("addUser");
		return mv;
	
	}
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public ModelAndView saveContact(@ModelAttribute User user) {		
			userDAO.save(user);		
	    return new ModelAndView("redirect:/welcome");
	}
	
	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
	    int userId = Integer.parseInt(request.getParameter("id"));
	    User user = userDAO.getUserById(userId);
	    ModelAndView model = new ModelAndView("editUser");
	    model.addObject("user", user);
	 
	    return model;
	}
	
	@RequestMapping(value = "/editContact", method = RequestMethod.POST)
	public ModelAndView editContact(@ModelAttribute User user) {		
			userDAO.update(user);
	    return new ModelAndView("redirect:/welcome");
	}
		
	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public ModelAndView deleteContact(@RequestParam int id) {	   
	    userDAO.delete(id);
	    return new ModelAndView("redirect:/welcome");
	}
	
	@RequestMapping(value = "/aboutus", method = RequestMethod.GET)
	public ModelAndView deleteContact(ModelAndView mv) {	   
	    mv.setViewName("aboutUsPage");
		return mv;
	}
	
	@RequestMapping(value = "/newFeature", method = RequestMethod.GET)
	public ModelAndView newFeature(ModelAndView mv) {	   
	    mv.setViewName("newFeaturePage");
		return mv;
	}
	
}
