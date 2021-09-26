package kr.or.simplebook.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@PostMapping("/loginprocess")
	public String LoginProcess(HttpSession session,@RequestParam(value="id") String id) {
		session.setAttribute("login", true);
		session.setAttribute("id", id);
		System.out.println(id);	
		System.out.println("ABCDEF\n\n\nA");
		return "redirect:/";
	}
}
