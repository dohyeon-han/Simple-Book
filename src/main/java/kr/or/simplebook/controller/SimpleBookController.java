package kr.or.simplebook.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SimpleBookController {
	
	@GetMapping("/login")
	public ModelAndView login(HttpSession session) {
		if(session.getAttribute("id")!=null) {
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("login");
	}
	
	@GetMapping("/create")
	public ModelAndView create() {
		return new ModelAndView("create");
	}
	
//	@PostMapping("/create")
//	public ModelAndView createBook() {
//		
//	}
}
