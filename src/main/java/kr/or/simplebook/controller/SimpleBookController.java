package kr.or.simplebook.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.or.simplebook.service.CustomUserDetailsService;
import kr.or.simplebook.service.SimpleBookService;

@RestController
public class SimpleBookController {

	@Autowired
	CustomUserDetailsService customUserDatailService;

	@Autowired
	SimpleBookService simpleBookService;

	@GetMapping("/login")
	public ModelAndView login(HttpSession session) {
		if (session.getAttribute("id") != null) {
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("login");
	}

	@GetMapping("/join")
	public ModelAndView join(HttpSession session) {
		if (session.getAttribute("id") != null) {
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("join");
	}
	
	@GetMapping("/create")
	public ModelAndView create() {
		return new ModelAndView("create");
	}

	@GetMapping(value = {"/list/{page}", "/list"})
	public ModelAndView list(@PathVariable(value = "page",required = false) Optional<Integer> page) {
		return new ModelAndView("/list");
	}
	
	@GetMapping(value = {"/update/{page}"})
	public ModelAndView update(@PathVariable(value = "page") Integer page) {
		return new ModelAndView("/update");
	}
}
