package kr.or.simplebook.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.or.simplebook.entity.Book;
import kr.or.simplebook.entity.Category;
import kr.or.simplebook.entity.CustomUserDetails;
import kr.or.simplebook.entity.Message;
import kr.or.simplebook.entity.StatusEnum;
import kr.or.simplebook.service.CustomUserDetailsService;
import kr.or.simplebook.service.SimpleBookService;

@RestController
public class SimpleBookController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CustomUserDetailsService customUserDatailService;
		
	@Autowired
	SimpleBookService simpleBookService;	
	
	@GetMapping("/login")
	public ModelAndView login(HttpSession session) {
		if(session.getAttribute("id")!=null) {
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("login");
	}
	
	@GetMapping("/join")
	public ModelAndView join(HttpSession session) {
		if(session.getAttribute("id")!=null) {
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("join");
	}
	
	@PostMapping("/join")
	public ResponseEntity<Message> insertUser(@RequestBody Map<String, Object> param) {
		CustomUserDetails user = customUserDatailService.joinUser((String)param.get("id"),(String)param.get("pw"));
		Message msg = new Message(); 
		msg.setStatus(StatusEnum.OK);
		if(user == null) {
			msg.setMessage("same id exist");
		}
		else {
			msg.setMessage("success");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", user.getUsername());
			map.put("pw", user.getPassword());
			msg.setData(map);
		}
		return new ResponseEntity<Message>(msg,HttpStatus.OK);
	}
	
	@PostMapping("/category/select")
	public ResponseEntity<Message> selectCategory(){
		List<Category> categories = simpleBookService.selectCategories();
		Message msg = new Message();
		msg.setStatus(StatusEnum.OK);
		msg.setMessage("OK");
		msg.setData(categories);
		return new ResponseEntity<Message>(msg,HttpStatus.OK);
	}
	
	@GetMapping("/create")
	public ModelAndView create() {
		return new ModelAndView("create");
	}
	
	@PostMapping("/create")
	public ResponseEntity<Message> createBook(@RequestBody Book param) {
		int insert = simpleBookService.insertBook(param);
		Message msg = new Message();
		msg.setStatus(StatusEnum.OK);
		msg.setMessage("OK");
		msg.setData(param);
		return new ResponseEntity<Message>(msg,HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public ModelAndView list() {
		return new ModelAndView("/list");
	}
	
}
