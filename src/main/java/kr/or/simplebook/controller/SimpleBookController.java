package kr.or.simplebook.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.or.simplebook.entity.CustomUserDetails;
import kr.or.simplebook.service.CustomUserDetailsService;

@RestController
public class SimpleBookController {
	
	@Autowired
	CustomUserDetailsService customUserDatailService;
		
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
	
	@GetMapping("/join")
	public ModelAndView join(HttpSession session) {
		if(session.getAttribute("id")!=null) {
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("join");
	}
	
	@PostMapping("/join")
	public ModelAndView insertUser(@RequestParam("id") String id, @RequestParam("pw") String pw) {
		CustomUserDetails user = customUserDatailService.joinUser(id,pw);
		ModelAndView mav = new ModelAndView();
		if(user == null) {
			mav.setViewName("redirect:join");
		}
		else {
			mav.setViewName("login");
		}
		return mav;
	}
//	@PostMapping("/create")
//	public ModelAndView createBook() {
//		
//	}
}
