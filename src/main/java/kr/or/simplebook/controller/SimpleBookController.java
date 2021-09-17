package kr.or.simplebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SimpleBookController {
	@GetMapping("/create")
	public ModelAndView create() {
		return new ModelAndView("create");
	}
	
//	@PostMapping("/create")
//	public ModelAndView createBook() {
//		
//	}
}
