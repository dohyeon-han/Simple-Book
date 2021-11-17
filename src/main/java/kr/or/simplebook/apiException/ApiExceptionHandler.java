package kr.or.simplebook.apiException;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kr.or.simplebook.entity.Message;
import kr.or.simplebook.entity.StatusEnum;

@RestControllerAdvice
public class ApiExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Message> exceptionHandler(HttpServletRequest request, Exception e){
		Message msg = new Message(StatusEnum.INTERNAL_SERVER_ERROR,"Api exception",new HashMap<String, Object>() {{
			put("request",request.getMethod());
			put("response",request.getRequestURI());
		}});
		logger.debug("Exception이 발생했습니다. {}",e.getMessage());
		return new ResponseEntity<Message>(msg,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
