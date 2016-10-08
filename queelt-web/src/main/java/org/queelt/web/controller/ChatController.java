package org.queelt.web.controller;

import org.queelt.service.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/chat")
public class ChatController {

	@Autowired
	private User user;

	@RequestMapping("/index")
	public ModelAndView execute() {
		return new ModelAndView("index");
	}

	@RequestMapping("/user/{id}")
	public ModelAndView user(@PathVariable String id) {

		return new ModelAndView("user", "_user", user.id(id));
	}
	
}
