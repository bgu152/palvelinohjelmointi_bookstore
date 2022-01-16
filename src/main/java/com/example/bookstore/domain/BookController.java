package com.example.bookstore.domain;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {
	@GetMapping("/index")
	public String showBooks() {
		return "index";		
	}

}
