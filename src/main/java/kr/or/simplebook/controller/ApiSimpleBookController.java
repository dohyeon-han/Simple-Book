package kr.or.simplebook.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.simplebook.entity.Book;
import kr.or.simplebook.entity.Category;
import kr.or.simplebook.entity.CustomUserDetails;
import kr.or.simplebook.entity.Message;
import kr.or.simplebook.entity.StatusEnum;
import kr.or.simplebook.service.CustomUserDetailsService;
import kr.or.simplebook.service.SimpleBookService;

@RestController
@RequestMapping("/api")
public class ApiSimpleBookController {
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	SimpleBookService simpleBookService;
	
	@PostMapping("/join")
	public ResponseEntity<Message> insertUser(@RequestBody Map<String, Object> param) {
		CustomUserDetails user = customUserDetailsService.joinUser((String) param.get("id"), (String) param.get("pw"));
		Message msg = new Message();
		msg.setStatus(StatusEnum.OK);
		if (user == null) {
			msg.setMessage("");
		} else {
			msg.setMessage("success");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", user.getUsername());
			map.put("pw", user.getPassword());
			msg.setData(map);
		}
		return new ResponseEntity<Message>(msg, HttpStatus.OK);
	}
	
	@GetMapping("/category/get")
	public ResponseEntity<Message> selectCategory() {
		List<Category> categories = simpleBookService.selectCategories();
		Message msg = new Message();
		msg.setStatus(StatusEnum.OK);
		msg.setMessage("success");
		msg.setData(categories);
		return new ResponseEntity<Message>(msg, HttpStatus.OK);
	}
	
	@PostMapping("/book/post")
	public ResponseEntity<Message> createBook(@RequestBody Book param) {
		int insert = simpleBookService.insertBook(param);
		Message msg = new Message();
		if (insert == 1) {
			msg.setStatus(StatusEnum.OK);
			msg.setMessage("success");
			msg.setData(param);
			return new ResponseEntity<Message>(msg, HttpStatus.OK);
		} else {
			msg.setStatus(StatusEnum.OK);
			msg.setMessage("Can't insert");
			return new ResponseEntity<Message>(msg, HttpStatus.OK);
		}
	}
	
	@GetMapping("/list/{page}")
	public ResponseEntity<Message> list(@PathVariable("page") int page){
		final int ROW = 10;  
		int start = (page-1)*ROW;
		int end = page*ROW-1;
		List<Book> book = simpleBookService.selectBookPaging(start, end);
		int count = simpleBookService.countBooks();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", count);
		map.put("book", book);
		Message msg = new Message();
		msg.setStatus(StatusEnum.OK);
		msg.setMessage("success");
		msg.setData(map);
		return new ResponseEntity<Message>(msg,HttpStatus.OK);
	}
	
	@GetMapping("/book/{id}")
	public ResponseEntity<Message> selectBook(@PathVariable("id") int id){
		Book book = simpleBookService.selectBookById(id);
		Message msg = new Message();
		msg.setStatus(StatusEnum.OK);
		msg.setMessage("success");
		msg.setData(book);
		return new ResponseEntity<Message>(msg,HttpStatus.OK);
	}
	
	@PutMapping("/put/{id}")
	public ResponseEntity<Message> updateBook(@PathVariable("id") int id){
		Book book = simpleBookService.selectBookById(id);
		Message msg = new Message();
		msg.setStatus(StatusEnum.OK);
		msg.setMessage("success");
		msg.setData(book);
		return new ResponseEntity<Message>(msg,HttpStatus.OK);
	}	
}
